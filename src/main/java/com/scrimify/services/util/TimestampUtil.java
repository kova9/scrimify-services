package com.scrimify.services.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimestampUtil {

    public static ZonedDateTime now() {
        return ZonedDateTime.now(ZoneId.of("Europe/Berlin"));
    }
}
