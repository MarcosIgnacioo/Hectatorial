package SQL;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuncionesSQL {


    public static String parseDate(String dateString) {
        try {
            DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = inputFormat.parse(dateString);

            DateFormat outputFormat = new SimpleDateFormat("dd");
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
