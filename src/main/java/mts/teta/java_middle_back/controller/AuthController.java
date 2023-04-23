package mts.teta.java_middle_back.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class AuthController {

    @PostMapping
    public String doPost(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        model.addAttribute("invalidPass", true);
        return "signIn";
    }
}
