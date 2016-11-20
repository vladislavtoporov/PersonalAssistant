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
            xmlDoc = parser.build(new URL("http://api.openweathermap.org/data/2.5/forecast?q=Kazan&mode=xml&appid=c897b438605eff38e5df5f6928351367&units=metric"));
            List elements = xmlDoc.getRootElement().getContent(new ElementFilter("forecast"));
            Iterator iterator = elements.iterator();
            while(iterator.hasNext()) {
                Element forecast = (Element)iterator.next();
                List mixedCo = forecast.getChildren("time");
                Iterator itr = mixedCo.iterator();
                while (itr.hasNext()) {
                    Element day = (Element)itr.next();
                    //Узнаем дату
                    String StrDate = day.getAttributeValue("from");
                    String[] date = StrDate.split("T");
                    String StrDate2 = day.getAttributeValue("to");
                    String[] date2 = StrDate2.split("T");
                    System.out.println("Прогноз на: " + date[0] + "\n" + date[1] + " - " + date2[1]);
                    //Температура
                    Element temperature = day.getChild("temperature");
                    String value = temperature.getAttributeValue("value");
                    System.out.println("Температура: " + value);
                    Element sky = day.getChild("clouds");
                    String clouds = sky.getAttributeValue("value");
                    System.out.println("Состояние погоды: " + clouds);
                    Element wind = day.getChild("windSpeed");
                    String speed = wind.getAttributeValue("mps");
                    System.out.println("Скорость ветра: " + speed + " м/с");
                    System.out.println();
                }
            }
        }
        catch(JDOMException | NullPointerException | java.io.IOException e) {
            e.printStackTrace();
        }
    }

}
