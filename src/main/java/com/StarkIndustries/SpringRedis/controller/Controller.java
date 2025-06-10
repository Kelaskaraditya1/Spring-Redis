package com.StarkIndustries.SpringRedis.controller;

import com.StarkIndustries.SpringRedis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    public RedisService redisService;

    @GetMapping("/greetings")
    public ResponseEntity<?> greetings(){
        return ResponseEntity.status(HttpStatus.OK).body("Greetings,I am Optimus Prime!!");
    }

    @PostMapping("/add-to-redis/{key}/{value}")
    public ResponseEntity<?> addToRedis(@PathVariable("key") String key,@PathVariable("value") String value){
        if(this.redisService.addValue(key,value))
            return ResponseEntity.status(HttpStatus.OK).body("Pair added Successfully!!");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Pair already exists!!");
    }
}
