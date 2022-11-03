package com.adotapets.backend.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateWithTimeZone {

    private static String saoPauloId = "America/Sao_Paulo";

    private static LocalDateTime getLocalDateTime(String zoneId) {
        zoneId = zoneId == null ? saoPauloId : zoneId;
        ZoneId zone = ZoneId.of(zoneId);
        return ZonedDateTime.ofInstant(Instant.now(), zone).toLocalDateTime();
    }

    public static LocalDateTime getDateWithTimeZone() {
        return getLocalDateTime(null);
    }

    public static LocalDateTime getDateWithTimeZone(String zoneId) {
        return getLocalDateTime(zoneId);
    }

    private static LocalDateTime formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String s = formatter.format(dateTime);

        return LocalDateTime.parse(s, formatter);
    }

}
