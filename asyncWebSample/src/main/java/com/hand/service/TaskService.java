package com.hand.service;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
    public String execute(){
        try{
            Thread.sleep(3000);
            System.out.println("Slow task executed");
            return "task done";
        }catch (Exception e){
            return e.toString();
//            throw new RuntimeException();
        }
    }
}
