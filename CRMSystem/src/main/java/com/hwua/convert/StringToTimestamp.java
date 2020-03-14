package com.hwua.convert;



import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class StringToTimestamp implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        Date date=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
             date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
