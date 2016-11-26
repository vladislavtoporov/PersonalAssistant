package ch.makery.weather.API;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.io.FileNotFoundException;
public class Advices {
    public static void main(String args[]) throws FileNotFoundException {
        File input = new File("weather.txt");
        Scanner sc = new Scanner(input);
        float temperature = sc.nextFloat();
        String state = sc.nextLine();
    }
    public String advice(float temperature, String state) {
        File output = new File("advice.txt");
        String note = "";
        Pattern p = Pattern.compile("\\D*(rain|Rain)\\D*");
        Matcher m = p.matcher(state);
        if (temperature >= 30.0) {
            note += "Avoid heatstroke, drink more water and don't forget to put up your headwear.";
        }
        if (temperature < 30 && temperature >= 20) {
            note += "It's great weather to go to the beach! Don't forget your shades!";
        }
        if (temperature >= 15 && temperature < 20) {
            note += "A light clothes made from natural materials recommended.";
        }
        if (temperature >= 5 && temperature < 15) {
            note += "You should wear light and warm clothes.";
        }
        if (temperature >= 1 && temperature < 5) {
            note += "Wearing overcoat or jacket is recommended.";
        }
        if (temperature >= -1 && temperature < 1) {
            note += "Caution!!! Sleet is possible! Please, be careful on the road! And wear warm jacket too.";
        }
        if (temperature >= - 5 && temperature < -1) {
            note+= "It's good idea to go to the ice rink or go skiing.";
        }
        if (temperature >= -10 && temperature < -5) {
            note += "It's better to wear warm clothes.";
        }
        if (temperature >= -15 && temperature < -10) {
            note += "You really should wear winter clothes now.";
        }
        if (temperature >= -20 && temperature < - 15) {
            note += "Wow, it's really cold out there. Better wear warm sweater and travel by subway.";
        }
        if (temperature >= -30 && temperature < -20) {
            note += "Hopefully, you will manage to start your car (if you have a car). If not, you'd better to put up the warmest clothes you have.";
        }
        if (temperature < -30) {
            note += "Maybe it's better to stay in your warm comfortable home with a cup of hot tea and cookies?";
        }
        if (m.matches()) {
            note += "Don't forget your umbrella, if you don't want to get wet.";
        }
        System.out.println(note);
    }
}
