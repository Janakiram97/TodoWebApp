package com.example.todowebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

    @RequestMapping (value="/",method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("name",getLoggedInUser());
        return "welcome";
    }
   private String getLoggedInUser()
   {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      return authentication.getName();
   }
}
