package com.techmave.agfarm.utility;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utility {

    public static String getDayStringFromTimestamp(Long millis) {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.US);

        c.setTimeInMillis(millis * 1000L);

        return sdf.format(c.getTime());
    }

    public static String roundToTwoDecimalPoints(Double value) {

        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }
}
