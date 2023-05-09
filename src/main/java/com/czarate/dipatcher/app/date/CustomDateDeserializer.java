package com.czarate.dipatcher.app.date;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class CustomDateDeserializer extends JsonDeserializer<Date> {
    private static final Pattern DATE_PATTERN = Pattern.compile("ISODate\\('(.*?)'\\)");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String dateString = jsonParser.getText();
        Matcher matcher = DATE_PATTERN.matcher(dateString);
        if (matcher.matches()) {
            String dateStr = matcher.group(1);
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                return dateFormat.parse(dateStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        throw new JsonParseException("Invalid date format: " + dateString, jsonParser.getCurrentLocation());
    }
}
