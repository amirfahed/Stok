package com.example.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    private SimpMessagingTemplate template;

    @Autowired
    public GreetingController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/hello/{room}")
    //@SendTo("/topic/greetings")
    public void greeting(@DestinationVariable String room, Message message) throws Exception{
        //Thread.sleep(1000);
        Greeting greet = new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName() + "!"));
        this.template.convertAndSend("/topic/greetings/" + room, greet);
        //return greet;
    }
}
