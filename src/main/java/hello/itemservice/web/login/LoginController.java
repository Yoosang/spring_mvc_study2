package hello.itemservice.web.login;

import hello.itemservice.domain.member.Member;
import hello.itemservice.web.login.form.LoginForm;
import hello.itemservice.web.login.session.SessionConst;
import hello.itemservice.web.login.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
        return "login/loginForm";
    }

    //@PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        log.info("login? {}", loginMember);

        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 혹은 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);

        return "redirect:/home";
    }

    //@PostMapping("/login")
    public String loginV2(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        log.info("login? {}", loginMember);

        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 혹은 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공처리
        sessionManager.createSession(loginMember, response);

        return "redirect:/home";
    }

    //@PostMapping("/login")
    public String loginV3(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult, HttpServletRequest request){

        log.info("loginV3");
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        log.info("login? {}", loginMember);

        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 혹은 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공처리
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/home";
    }

    @PostMapping("/login")
    public String loginV4(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult,
                          @RequestParam(defaultValue = "/home") String redirectURL,
                          HttpServletRequest request){

        log.info("loginV4");
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        log.info("login? {}", loginMember);

        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 혹은 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공처리
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    //@PostMapping("/logout")
    public String logout(HttpServletResponse response){
        expireCookie(response, "memberId");
        return "redirect:/home";
    }

    //@PostMapping("/logout")
    public String logoutV2(HttpServletRequest request){
        sessionManager.expire(request);
        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request){

        log.info("logoutV3");
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/home";
    }

    private void expireCookie(HttpServletResponse response, String cookieName){
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
