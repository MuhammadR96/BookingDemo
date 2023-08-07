package org.example.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {


    //Changes Date format to YYYY-MM-DD
    public String changeConfirmationPageDatesFormat(String Date) {
        String inputDate = Date;
        String outputFormat = "yyyy-MM-dd";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("E, MMM d, yyyy");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);

        String outputDate = null;
        try {
            Date date = inputDateFormat.parse(inputDate);
            outputDate = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

    public String changeExcelSheetDatesFormat(String Date) {
        String inputDate = Date;
        String outputFormat = "yyyy-MM-dd";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("d MMMM yyyy");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);

        String outputDate = null;
        try {
            Date date = inputDateFormat.parse(inputDate);
            outputDate = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }

}
