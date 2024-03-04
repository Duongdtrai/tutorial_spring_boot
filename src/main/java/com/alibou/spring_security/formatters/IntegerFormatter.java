package com.alibou.spring_security.formatters;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class IntegerFormatter implements Formatter<Integer> {
    @Override
    public Integer parse(String text, Locale locale) throws ParseException {
        System.out.println("Parsing text to Integer: " + text);
        return Integer.parseInt(text);
    }

    @Override
    public String print(Integer object, Locale locale) {
        System.out.println("Printing Integer: " + object);
        return object.toString();
    }
}
