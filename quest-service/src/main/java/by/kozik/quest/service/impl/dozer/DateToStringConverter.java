package by.kozik.quest.service.impl.dozer;

import org.dozer.DozerConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Serge_Kozik on 3/3/2017.
 */
public class DateToStringConverter extends DozerConverter<Date,String> {

    public DateToStringConverter() {
        super(Date.class, String.class);
    }

    @Override
    public String convertTo(Date date, String s) {
        if (date==null) {
            return "--";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(date);
    }

    @Override
    public Date convertFrom(String s, Date date) {
        Date result = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
        dateFormat.setTimeZone(TimeZone.getDefault());
        try {
            result = dateFormat.parse(s);
        } catch (ParseException e) {
            return null;
        }
        return result;
    }
}
