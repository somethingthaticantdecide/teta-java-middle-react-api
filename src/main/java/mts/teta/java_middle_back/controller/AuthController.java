package mts.teta.java_middle_back.controller;

import org.json.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class AuthController {
    private final PasswordEncoder passwordEncoder;

    public AuthController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/api/auth")
    public String doGet(HttpServletRequest request) {
        JSONObject token = new JSONObject();
        token.put("token", "token");
        JSONObject data = new JSONObject();
        data.put("data", token);
        return data.toString();
    }

    @PostMapping
    public String doPost(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        model.addAttribute("invalidPass", true);
        return "signIn";
    }
}
