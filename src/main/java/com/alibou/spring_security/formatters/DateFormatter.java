package com.alibou.spring_security.formatters;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class DateFormatter implements Formatter<Date> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        System.out.println("text: " + text);
        System.out.println("date: " +dateFormat.parse(text));
        return dateFormat.parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        System.out.println("Object: " + object);
        return dateFormat.format(object);
    }
}
