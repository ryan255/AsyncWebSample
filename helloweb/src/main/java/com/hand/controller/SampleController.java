package com.hand.controller;

import com.hand.service.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SampleController {
    private TaskService taskService = new TaskService();
    @RequestMapping("/hello")
    public String index() {
        System.out.println("Request received at " + System.currentTimeMillis());
        String result = taskService.execute();
        System.out.println("Servlet thread released at "+ System.currentTimeMillis());
        return result;
    }
}
