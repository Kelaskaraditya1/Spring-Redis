package com.StarkIndustries.SpringRedis.controller;

import com.StarkIndustries.SpringRedis.model.Person;
import com.StarkIndustries.SpringRedis.model.SimpleKeyValueModel;
import com.StarkIndustries.SpringRedis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
public class Controller {

    @Autowired
    public RedisService redisService;

    @GetMapping("/greetings")
    public ResponseEntity<?> greetings(){
        return ResponseEntity.status(HttpStatus.OK).body("Greetings,I am Optimus Prime!!");
    }

    @PostMapping("/add-to-redis")
    public ResponseEntity<?> addToRedis(@RequestBody SimpleKeyValueModel simpleKeyValueModel){
        if(this.redisService.addValue(simpleKeyValueModel))
            return ResponseEntity.status(HttpStatus.OK).body("Pair added Successfully!!");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Pair already exists!!");
    }

    @GetMapping("/get-pair/{key}")
    public ResponseEntity<?> getPairFromRedis(@PathVariable("key") String key){

        Object value = this.redisService.getValue(key, Person.class);
        if(value!=null && !value.toString().isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(value);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pair doesn't exist!!");
    }

    @DeleteMapping("/delete-pair/{key}")
    public ResponseEntity<?> deletePair(@PathVariable("key") String key){
        if(this.redisService.deleteValue(key))
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully!!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to found the Pair!!");
    }


}
