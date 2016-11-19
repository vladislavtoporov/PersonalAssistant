import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SAXBuilder parser = new SAXBuilder();
        Document xmlDoc;

        /* берем и коннектимся к апи
        парсим xml с данными
        api.openweathermap.org/data/2.5/weather?id=551487&mode=xml
        xmlDoc.getRootElement().getContent(new ElementFilter("city"));
        */
        //Получаем XML с погодой.
        try {
            xmlDoc = parser.build(new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=Kazan&mode=xml&appid=c897b438605eff38e5df5f6928351367&units=metric"));
            List elements = xmlDoc.getRootElement().getContent(new ElementFilter("forecast"));
            Iterator iterator = elements.iterator();
            while(iterator.hasNext()) {
                Element forecast = (Element)iterator.next();
                List mixedCo = forecast.getChildren("time");
                Iterator itr = mixedCo.iterator();
                while (itr.hasNext()) {
                    Element day = (Element)itr.next();
                    //Узнаем дату
                    String StrDate = day.getAttributeValue("day");
                    System.out.println("Прогноз на: " + StrDate);
                    //Температура
                    Element temperature = day.getChild("temperature");
                    String max_temp = temperature.getAttributeValue("max");
                    String min_temp = temperature.getAttributeValue("min");
                    System.out.println("Температура \nМаксимум: " + max_temp + "\nМинимум: " + min_temp);
                }
            }
        }
        catch(JDOMException | NullPointerException | java.io.IOException e) {
            e.printStackTrace();
        }
    }

}
