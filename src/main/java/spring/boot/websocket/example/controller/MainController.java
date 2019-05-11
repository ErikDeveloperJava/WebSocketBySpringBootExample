package spring.boot.websocket.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.boot.websocket.example.config.security.CurrentUser;
import spring.boot.websocket.example.repository.UserRepository;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String main(Model model,
                       @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("userList",userRepository.findAllByIdNotIn(currentUser.getUser().getId()));
        model.addAttribute("currentUser",currentUser.getUser());
        return "index";
    }
}