package spring.boot.websocket.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.boot.websocket.example.config.security.CurrentUser;
import spring.boot.websocket.example.repository.MessageRepository;
import spring.boot.websocket.example.repository.UserRepository;

@Controller
public class MessageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/user/{userId}/message/page")
    public String messagePage(@PathVariable("userId")int userId,
                              @AuthenticationPrincipal CurrentUser currentUser, Model model){
        model.addAttribute("user",userRepository.findById(userId).get());
        model.addAttribute("currentUser",currentUser.getUser());
        model.addAttribute("messageList",messageRepository.findByToAndFrom(userId,currentUser.getUser().getId()));
        return "message_page";
    }
}