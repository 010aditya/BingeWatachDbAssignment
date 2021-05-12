package com.dbAissgnment.bingeWatachDbAssignment.commons;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    public static Date parseDate(String date) /*throws InvalidDateFormatException*/{
        Date inputDate = null;
        try {
            inputDate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
        } catch (ParseException e) {
         //   throw new  InvalidDateFormatException(e.getMessage());
        }
        return inputDate;
    }
    public static String parseDate(Long date1) /*throws InvalidDateFormatException*/{
        Date date = new Date(date1);
        Format format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }
}

