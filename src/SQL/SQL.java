package SQL;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQL {
    /*
    * A la hora de descargar las fechas al dtm pasarles un parseData, puedo simplemente hacer un contains fecha
    * al nombre de la columna column.getname o algo asi era y si tiene fecha ponerle el parse
    *
    * */

    public static String parseDate(String dateString) {
        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(dateString);

            DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
