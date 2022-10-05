package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/basic")
    public String basicIndex(){
        return "basic/basicIndex";
    }

    @GetMapping("/form")
    public String formIndex(){
        return "form/itemServiceIndex";
    }

    @GetMapping("/validation")
    public String validationIndex(){
        return "validation/validationIndex";
    }

    @GetMapping("/login")
    public String loginIndex(){
        return "login/loginIndex";
    }
}
