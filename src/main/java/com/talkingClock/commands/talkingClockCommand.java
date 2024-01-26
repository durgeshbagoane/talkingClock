package com.talkingClock.commands;

import com.talkingClock.services.talkingClockService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Talking Clock Commands Implementation.
 *
 * @author Durgesh Bagoane
 *
 */
@ShellComponent
public class talkingClockCommand {
    private final talkingClockService talkingClockService;

    public talkingClockCommand(talkingClockService talkingClockService) {
        this.talkingClockService = talkingClockService;
    }

    /**
     * Talking Clock Commands 'talking-clock' Implementation.
     * @param time
     *          String, representing time in HH:mm format
     * @return String
     *          Words representation of time.
     * @author Durgesh Bagoane
     *
     */
    @ShellMethod(key = "talking-clock", value = "Return time in Human friendly text.")
    public String talkingClock(@ShellOption("")String time){
        return talkingClockService.translate(time);
    }
}
