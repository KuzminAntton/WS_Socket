package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataUtils {

    public static String getCurrentDataByFormat(String dataFormat) {
        DateFormat dateFormat = new SimpleDateFormat(dataFormat);
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }
}