package com.talkingClock.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class talkingClockServiceTest {

    @Autowired
    private talkingClockService talkingClockService;

    @Test
    public void translateValid() {
        assertEquals("passed","Midday Twelve",talkingClockService.translate("12:00"));
        assertEquals("passed","Midnight Twelve",talkingClockService.translate("00:00"));
        assertEquals("passed","One minute past Twelve",talkingClockService.translate("00:01"));
        assertEquals("passed","One minute to One",talkingClockService.translate("00:59"));
        assertEquals("passed","Quarter past Twelve",talkingClockService.translate("00:15"));
        assertEquals("passed","Half past Twelve",talkingClockService.translate("00:30"));
        assertEquals("passed","Quarter to One",talkingClockService.translate("00:45"));
        assertEquals("passed","Seventeen minutes past Twelve",talkingClockService.translate("00:17"));
        assertEquals("passed","Nineteen minutes to One",talkingClockService.translate("00:41"));
    }

    @Test
    public void translateInvalid() {
        assertEquals("Failed","Invalid Time format, should be HH:mm",talkingClockService.translate(""));
        assertEquals("Failed","Invalid Time format, should be HH:mm",talkingClockService.translate("40:00"));
        assertEquals("Failed","Invalid Time format, should be HH:mm",talkingClockService.translate("00:99"));
        assertEquals("Failed","Invalid Time format, should be HH:mm",talkingClockService.translate("0h0:59"));
        assertEquals("Failed","Invalid Time format, should be HH:mm",talkingClockService.translate("00:15o"));
        assertEquals("Failed","Invalid Time format, should be HH:mm",talkingClockService.translate("0:30"));
        assertEquals("Failed","Invalid Time format, should be HH:mm",talkingClockService.translate("00:4"));
        assertEquals("Failed","Invalid Time format, should be HH:mm",talkingClockService.translate("000:17"));
        assertEquals("Failed","Invalid Time format, should be HH:mm",talkingClockService.translate("00:410"));
    }

}