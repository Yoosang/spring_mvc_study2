package hello.itemservice.web.login;

import hello.itemservice.domain.member.Member;
import hello.itemservice.domain.member.MemberRepository;
import hello.itemservice.web.argumentresolver.Login;
import hello.itemservice.web.login.session.SessionConst;
import hello.itemservice.web.login.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    //@GetMapping("/home")
    public String home() {
        return "login/home";
    }

    //@GetMapping("/home")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model){

        if(memberId == null){
            return "login/home";
        }

        Member loginMember = memberRepository.findById(memberId);

        if(loginMember == null){
            return "login/home";
        }

        model.addAttribute("member", loginMember);
        return "login/loginHome";
    }

    //@GetMapping("/home")
    public String homeLoginV2(HttpServletRequest request, Model model){

        Member member = (Member) sessionManager.getSession(request);
        if(member == null){
            return "login/home";
        }

        model.addAttribute("member", member);
        return "login/loginHome";
    }

    //@GetMapping("/home")
    public String homeLoginV3(HttpServletRequest request, Model model){

        log.info("homeLoginV3");
        HttpSession session = request.getSession(false);
        if(session == null){
            return "login/home";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(loginMember == null){
            return "login/home";
        }

        model.addAttribute("member", loginMember);
        return "login/loginHome";
    }

    //@GetMapping("/home")
    public String homeLoginV3Spring(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember
            , Model model){

        log.info("homeLoginV3Spring");

        if(loginMember == null){
            return "login/home";
        }

        model.addAttribute("member", loginMember);
        return "login/loginHome";
    }

    @GetMapping("/home")
    public String homeLoginV3ArgumentResolver(@Login Member loginMember, Model model){

        log.info("homeLoginV3ArgumentResolver");

        if(loginMember == null){
            return "login/home";
        }

        model.addAttribute("member", loginMember);
        return "login/loginHome";
    }
}