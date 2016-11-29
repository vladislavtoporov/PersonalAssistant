package weather;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import static java.awt.SystemColor.text;

public class Api {
    final private static int CAPACITY = 14;
    private static String[] weatherState = new String[CAPACITY];
    private static String[] windDirection = new String[CAPACITY];
    private static short[] temp = new short[CAPACITY];
    private static String[] date = new String[CAPACITY];

    public static short getTemp(int i) {
        return temp[i];
    }

    public static String getState(int i) {
        return weatherState[i];
    }

    public static void weather(String city) throws FileNotFoundException {
        SAXBuilder parser = new SAXBuilder();

        int i = 0, k = 0;
        File file = new File("src/weather/resource/weather.txt");
        try {
            if (file.exists()) {
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            try {
                pw.println((new SimpleDateFormat("EE dd.MM.yy", Locale.ENGLISH)).format(new Date()));
                try {
                    URL url = new URL(city);
                    Document xmlDoc = parser.build(url);
                    List elements = xmlDoc.getRootElement().getContent(new ElementFilter("forecast"));
                    Iterator iterator = elements.iterator();

                    while (iterator.hasNext()) {
                        Element forecast = (Element) iterator.next();
                        List mixedCo = forecast.getChildren("time");
                        Iterator itr = mixedCo.iterator();

                        while (k < 26 && itr.hasNext()) {
                            Element day = (Element) itr.next();
                            String StrDate = day.getAttributeValue("from");
                            String[] date1 = StrDate.split("T");
                            String StrDate2 = day.getAttributeValue("to");
                            String[] date2 = StrDate2.split("T");
                            int time1 = (date1[1].charAt(0) - '0') * 10 + date1[1].charAt(1) - '0' + 3;
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
                                date[i] = date1[0] + " " + dt;
                                pw.println(date[i]);
                                short val = (short) Math.round(value);
                                short spd = (short) Math.round(speed);
                                temp[i] = val;
                                if (temp[i] > 0) {
                                    pw.println("Temperature: +" + temp[i] + " °C");
                                } else {
                                    pw.println("Temperature: " + temp[i] + " °C");
                                }
                                windDirection[i] = "Wind direction " + direction + ", speed is " + spd + " mps";
                                pw.println(windDirection[i]);
                                weatherState[i] = symbol;
                                pw.println("Weather: " + weatherState[i]);
                                i++;
                            }
                            k++;
                        }
                    }
                } catch (JDOMException | NullPointerException | java.io.IOException e) {
                    e.printStackTrace();
                }
                ;
            } finally {
                pw.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}