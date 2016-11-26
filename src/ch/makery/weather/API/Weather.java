package ch.makery.weather.API;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class Weather {

    public static void main(String[] args) {
        SAXBuilder parser = new SAXBuilder();

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=Kazan&mode=xml&appid=c897b438605eff38e5df5f6928351367&units=metric");
            Document xmlDoc = parser.build(url);
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
                    int time1 = (date[1].charAt(0) - '0') * 10 + date[1].charAt(1) - '0';
                    int time2 = (date2[1].charAt(0) - '0') * 10 + date2[1].charAt(1) - '0';
                    String dt = "";

                    if (time1 == 6 && time2 == 9) {
                        dt = "morning";
                    }
                    else {
                        if (time1 >=9 && time2 <= 18) {
                            dt = "day";
                        }
                        else {
                            if (time1 == 18 && time2 == 21) {
                                dt = "evening";
                            }
                            else {
                                dt = "night";
                            }
                        }
                    }


                    Element sky = day.getChild("symbol");
                    String code = sky.getAttributeValue("number");
                    int num = Integer.parseInt(code);
                    String symbol = "";

                    switch (num / 100) {
                        case 2:
                            symbol = "thunderstorm";
                            break;
                        case 3:
                            symbol = "rain";
                            break;
                        case 5:
                            symbol = "rain";
                            break;
                        case 6:
                            symbol = "snow";
                            break;
                        case 7:
                            symbol = "fog";
                            break;
                        case 8:
                            if (num == 800) {
                                symbol = "clear sky";
                            }
                            else {
                                if (num == 801){
                                    symbol = "few clouds";
                                }
                                else {
                                    symbol = "cloudy";
                                }
                            }
                            break;
                    }

                    //Температура
                    Element temperature = day.getChild("temperature");
                    double value = Double.parseDouble(temperature.getAttributeValue("value"));
                    Element wind = day.getChild("windSpeed");
                    double speed = Double.parseDouble(wind.getAttributeValue("mps"));
                    String direction = day.getChild("windDirection").getAttributeValue("name");

                    if ((time2 > 9 && time2 < 18) || !(time2 <= 21 && time2 >= 6)) {
                        speed += Double.parseDouble(wind.getAttributeValue("mps"));
                        value += Double.parseDouble(temperature.getAttributeValue("value"));
                    }
                    else {
                        if (time2 == 18 || time2 == 6) {
                            speed = speed / 3;
                            value = value / 3;
                        }
                        System.out.println("Date: " + date[0] + "\n" + dt);
                        long val = Math.round(value);
                        long spd = Math.round(speed);
                        if (value > 0){
                            System.out.println("Temperature: " + "+" + val + " °C");
                        }
                        else
                            System.out.println("Temperature: " + val + " °C");
                        System.out.println("Weather: " + symbol);
                        System.out.println("Wind direction " + direction + ", speed is " + spd + " mps");
                        System.out.println();
                    }

                }
            }
        }
        catch(JDOMException | NullPointerException | java.io.IOException e) {
            e.printStackTrace();
        }
    }

}