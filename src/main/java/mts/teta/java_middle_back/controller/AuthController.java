package mts.teta.java_middle_back.controller;

import mts.teta.java_middle_back.config.JwtTokenUtil;
import mts.teta.java_middle_back.model.JwtRequest;
import mts.teta.java_middle_back.model.JwtResponse;
import mts.teta.java_middle_back.model.User;
import mts.teta.java_middle_back.model.roles.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class AuthController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/api/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
//        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//        User user = usersService.loadUserByUsername(authenticationRequest.getUsername());
        User user = new User("user", "pass", Role.ROLE_ADMIN);
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping
    public String doPost(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        model.addAttribute("invalidPass", true);
        return "signIn";
    }
}
