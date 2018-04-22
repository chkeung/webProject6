package ouhk.comps380f.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "redirect:/item/list";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    /*@RequestMapping("login")
    public String login(HttpServletRequest request) {
        if(request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_USER") ){
            return "redirect:/ticket/list";
        }else{
            return "login";
        }
        
    }
    
    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
       return "login";       
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String logedin() {
       return "redirect:/ticket/list";       
    }*/
}
