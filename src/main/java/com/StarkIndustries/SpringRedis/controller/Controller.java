package com.StarkIndustries.SpringRedis.controller;

import com.StarkIndustries.SpringRedis.model.Person;
import com.StarkIndustries.SpringRedis.model.SimpleKeyValueModel;
import com.StarkIndustries.SpringRedis.service.RedisService;
import com.StarkIndustries.SpringRedis.service.RedisServiceWithHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    public RedisService redisService;

    @Autowired
    public RedisServiceWithHash redisServiceWithHash;

    @GetMapping("/greetings")
    public ResponseEntity<?> greetings(){
        return ResponseEntity.status(HttpStatus.OK).body("Greetings,I am Optimus Prime!!");
    }

    @PostMapping("/add-to-redis")
    public ResponseEntity<?> addToRedis(@RequestBody Person person){
        Person person1 = this.redisServiceWithHash.addValue(person);
        if(person1!=null)
            return ResponseEntity.status(HttpStatus.OK).body(person1);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Pair already exists!!");
    }

    @GetMapping("/get-pair/{key}")
    public ResponseEntity<?> getPairFromRedis(@PathVariable("key") String key){
        Object value = this.redisServiceWithHash.getValue(key);
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

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllValues(){
        Map<Object,Object> personMaps = this.redisServiceWithHash.getValues();
        List<Person> personList = personMaps.values().stream().map(
                value-> (Person) value).toList();
        if(!personList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(personList);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("First enter valid entries!!");
    }


}
