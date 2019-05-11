package spring.boot.websocket.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import spring.boot.websocket.example.config.security.CurrentUser;
import spring.boot.websocket.example.dto.MessageDto;
import spring.boot.websocket.example.model.User;
import spring.boot.websocket.example.repository.MessageRepository;
import spring.boot.websocket.example.repository.UserRepository;

@Controller
public class WebSocketController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/message/sock")
    public void send(Message<MessageDto> sentMessage,
                            @AuthenticationPrincipal CurrentUser currentUser){
        MessageDto messageDto = sentMessage.getPayload();
        spring.boot.websocket.example.model.Message newMessage = spring.boot.websocket.example.model.Message
                .builder()
                .message(messageDto.getMessage())
                .to(userRepository.findById(messageDto.getUserId()).get())
                .from(currentUser.getUser())
                .build();
        messageRepository.save(newMessage);
        messagingTemplate.convertAndSend("/send/message/to/" + newMessage.getTo().getId(),newMessage);
    }
}
