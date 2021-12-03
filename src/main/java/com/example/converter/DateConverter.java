package com.example.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String dateStr) {
        // 将日期字符串转换成真正的日期对象
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
