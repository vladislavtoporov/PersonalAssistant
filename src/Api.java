import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class Api {
    final String KAZAN = "http://api.openweathermap.org/data/2.5/forecast?q=Kazan&mode=xml&appid=c897b438605eff38e5df5f6928351367&units=metric";
    final String ALMET = "http://api.openweathermap.org/data/2.5/forecast?q=Almetyevsk&mode=xml&appid=c897b438605eff38e5df5f6928351367&units=metric";
    final String CHELNY = "http://api.openweathermap.org/data/2.5/forecast?q=NaberezhnyeChelny&mode=xml&appid=c897b438605eff38e5df5f6928351367&units=metric";
    private final int CAPACITY = 140;

    public String[] weather(String city) {
        SAXBuilder parser = new SAXBuilder();
        String weather[] = new String[CAPACITY];
        int i = 0;

        try {
            URL url = new URL(city);
            Document xmlDoc = parser.build(url);
            List elements = xmlDoc.getRootElement().getContent(new ElementFilter("forecast"));
            Iterator iterator = elements.iterator();

            while (iterator.hasNext()) {
                Element forecast = (Element) iterator.next();
                List mixedCo = forecast.getChildren("time");
                Iterator itr = mixedCo.iterator();

                while (itr.hasNext()) {
                    Element day = (Element) itr.next();
                    String StrDate = day.getAttributeValue("from");
                    String[] date = StrDate.split("T");
                    String StrDate2 = day.getAttributeValue("to");
                    String[] date2 = StrDate2.split("T");
                    int time1 = (date[1].charAt(0) - '0') * 10 + date[1].charAt(1) - '0' + 3;
                    int time2 = (date2[1].charAt(0) - '0') * 10 + date2[1].charAt(1) - '0' + 3;
                    String dt = "";

                    if (time1 == 6 && time2 == 9) {
                        dt = "morning";
                    } else {
                        if (time1 >= 9 && time2 <= 18) {
                            dt = "day";
                        } else {
                            if (time1 == 18 && time2 == 21) {
                                dt = "evening";
                            } else {
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
                            } else {
                                if (num == 801) {
                                    symbol = "few clouds";
                                } else {
                                    symbol = "cloudy";
                                }
                            }
                            break;
                    }

                    Element temperature = day.getChild("temperature");
                    double value = Double.parseDouble(temperature.getAttributeValue("value"));
                    Element wind = day.getChild("windSpeed");
                    double speed = Double.parseDouble(wind.getAttributeValue("mps"));
                    String direction = day.getChild("windDirection").getAttributeValue("name");

                    if ((time2 > 9 && time2 < 18) || !(time2 <= 21 && time2 >= 6)) {
                        speed += Double.parseDouble(wind.getAttributeValue("mps"));
                        value += Double.parseDouble(temperature.getAttributeValue("value"));
                    } else {
                        if (time2 == 18 || time2 == 6) {
                            speed = speed / 3;
                            value = value / 3;
                        }
                        weather[i] = "Date: " + date[0] + " " + dt;
                        i++;
                        short val = (short) Math.round(value);
                        short spd = (short) Math.round(speed);
                        weather[i] = "Temperature: ";
                        if (value > 0) {
                            weather[i] += "+" + val + " °C";
                        } else
                            weather[i] += val + " °C";
                        i++;
                        weather[i] = "Wind direction " + direction + ", speed is " + spd + " mps";
                        i++;
                        weather[i] = "Weather: " + symbol + "\n";
                        i++;
                    }

                }
            }
        } catch (JDOMException | NullPointerException | java.io.IOException e) {
            e.printStackTrace();
        }
        return weather;
    }

}
