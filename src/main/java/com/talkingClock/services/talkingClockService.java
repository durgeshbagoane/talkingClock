package com.talkingClock.services;

import com.talkingClock.services.interfaces.talkingClockInterface;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Talking Clock Logic & Validation Implementation.
 *
 * @author Durgesh Bagoane
 *
 */
@Service
public class talkingClockService implements talkingClockInterface {
    private static final String[] UNITS_ARRAY = {
            "Twelve", "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen",
            "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen", "Twenty", "Twenty one",
            "Twenty two", "Twenty three", "Twenty four",
            "Twenty five", "Twenty six", "Twenty seven",
            "Twenty eight", "Twenty nine",
    };

    /**
     * Convert time String to int in HH && mm.
     *
     * @param time
     * 			String, representing time in HH:mm format
     * @return String
     * 			Words representation of time.
     */
    @Override
    public String translate(final String time) {

        // validate time format
        if(!this.validate(time))
            return "Invalid Time format, should be HH:mm";

        String[] timeSplit = time.split(":");

        // convert hours into words
        int HH = Integer.parseInt(timeSplit[0]);

        // convert minutes into words
        int mm = Integer.parseInt(timeSplit[1]);

        return logic(HH,mm);
    }

    /**
     * Convert time, in format HH:mm, into words.
     *
     * @param HH
     *          Int, representing HH in hours.
     * @param mm
     * 			Int, representing mm in minutes
     * @return String
     * 			Words representation of time
     */
    public String logic(int HH, int mm) {

        if (mm == 0){
            if(HH == 0)
                return "Midnight " + UNITS_ARRAY[HH];
            else if (HH == 12)
                return "Midday " + UNITS_ARRAY[HH];

            return  UNITS_ARRAY[HH] + " o' clock ";
        }
        else if (mm == 1)
            return "One minute past " + UNITS_ARRAY[HH];

        else if (mm == 59)
            return "One minute to " + UNITS_ARRAY[(HH % 12) + 1];

        else if (mm == 15)
            return "Quarter past " + UNITS_ARRAY[HH];

        else if (mm == 30)
            return "Half past " + UNITS_ARRAY[HH];

        else if (mm == 45)
            return "Quarter to " + UNITS_ARRAY[(HH % 12) + 1];

        else if (mm <= 30)
            return UNITS_ARRAY[mm] + " minutes past " + UNITS_ARRAY[HH];

        else if (mm > 30)
            return UNITS_ARRAY[60 - mm] + " minutes to " + UNITS_ARRAY[(HH % 12) + 1];

        return "";
    }


    /**
     * Validate the given time
     *
     * @param time
     * 			String, representing time
     * @return boolean
     *          False if its invalid.
     */
    public boolean validate(final String time)  {

        // validate that the time is not blank / null
        if(time == null || time.isBlank()) {
            return false;
        }

        // validate that the time is of the right format
        if(time.length() != 5 || !time.contains(":")) {
            return false;
        }

        String[] timeSplit = time.split(":");

        // validate that the hours are an integer
        int HH;
        try {
            HH = Integer.parseInt(timeSplit[0]);
        } catch(NumberFormatException e) {
            return false;
        }

        // validate that the hours are in the range
        if(HH < 0 || HH > 23) {
            return false;
        }

        // validate that the minutes are an integer
        int mm;
        try {
            mm = Integer.parseInt(timeSplit[1]);
        } catch(NumberFormatException e) {
            return false;
        }

        // validate that the minutes are in the range
        if(mm < 0 || mm > 59) {
            return false;
        }
        return true;
    }
}
