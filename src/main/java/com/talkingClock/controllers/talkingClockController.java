package com.talkingClock.controllers;

import com.talkingClock.services.talkingClockService;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("talkingClock/v1/")
public class talkingClockController {

    private final talkingClockService talkingClockService;

    public talkingClockController(talkingClockService talkingClockService) {
        this.talkingClockService = talkingClockService;
    }


    @GetMapping("{time}")
    public ResponseEntity<String> talkingClock( @PathVariable String time){
        JSONObject data = new JSONObject();
        data.put("value",talkingClockService.translate(time));
        return new ResponseEntity<String>(data.toString(), HttpStatus.OK);
    }

}
