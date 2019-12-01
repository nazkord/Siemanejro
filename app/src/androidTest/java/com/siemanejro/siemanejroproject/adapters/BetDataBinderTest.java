package com.siemanejro.siemanejroproject.adapters;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class BetDataBinderTest {

    private String date = "2017-08-11T19:00:00Z";

    @Test
    public void displayData() {


        ZoneId timeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = LocalDateTime.parse(date,
                DateTimeFormatter.ISO_DATE_TIME).atZone(timeZone);

        assertEquals("3", Instant.parse(date));
    }
}