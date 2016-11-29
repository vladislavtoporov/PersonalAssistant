package weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.File;

public class Main extends Application {
    //final static String KAZAN = "http://api.openweathermap.org/data/2.5/forecast?q=Kazan&mode=xml&appid=c897b438605eff38e5df5f6928351367&units=metric";
    //final static String ALMET = "http://api.openweathermap.org/data/2.5/forecast?q=Almetyevsk&mode=xml&appid=c897b438605eff38e5df5f6928351367&units=metric";
    //final static String CHELNY = "http://api.openweathermap.org/data/2.5/forecast?q=NaberezhnyeChelny&mode=xml&appid=c897b438605eff38e5df5f6928351367&units=metric";
    final static String STORM = "weather/resource/PNGs/storm.png";
    final static String SUNNY = "weather/resource/PNGs/sunny.png";
    final static String CLOUDY = "weather/resource/PNGs/cloudy.png";
    final static String CLEAR_MOON = "weather/resource/PNGs/clearNightSky.png";
    final static String FOG = "weather/resource/PNGs/fog.png";
    final static String PARTLY_CLOUDY_DAY = "weather/resource/PNGs/partlyCloudyDay.png";
    final static String PARTLY_CLOUDY_NIGHT = "weather/resource/PNGs/partlyCloudyNight.png";
    final static String RAINY = "weather/resource/PNGs/Rainy.png";
    final static String SNOWY = "weather/resource/PNGs/snowy.png";
    private final static String TEMP_VERY_HOT = "Avoid heatstroke, drink more water\nand don't forget to put up your headwear.";
    private final static String TEMP_HOT = "It's great weather to go to the beach!\nDon't forget your shades!";
    private final static String TEMP_VERY_WARM = "A light clothes made of natural materials\nrecommended.";
    private final static String TEMP_WARM = "You should wear light and warm clothes.";
    private final static String TEMP_WARMER = "Wearing overcoat or jacket is recommended.";
    private final static String TEMP_ZERO = "Caution!!! Sleet is possible! Please, be careful\non the road! And wear warm jacket too.";
    private final static String TEMP_CHILLY = "It's good idea to go to the ice rink\nor go skiing.";
    private final static String TEMP_COLDER = "It's better to wear warm clothes.";
    private final static String TEMP_COLD = "You really should wear winter clothes now.";
    private final static String TEMP_VERY_COLD = "Wow, it's really cold out there.\nBetter wear warm sweater and travel by subway.";
    private final static String TEMP_FREEZING = "Hopefully, you will manage to start your car\n(if you have a car). If not, you'd better\nto put up the warmest clothes you have.";
    private final static String TEMP_VERY_FREEZING = "Maybe it's better to stay in your warm\ncomfortable home with a cup of hot tea and cookies?";
    private static final int CAPACITY = 13;
    private static String advice[] = new String[CAPACITY];
    private static String[] weather = new String[CAPACITY];

    public static String getAdvice(int i) {
        return advice[i];
    }


    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }

    public static void createAdvice() {
        for (int i = 0; i < CAPACITY; i++) {
            short temp = Api.getTemp(i);
            if (temp >= 30.0) {
                advice[i] = TEMP_VERY_HOT;
            }
            if (temp < 30 && temp >= 20) {
                advice[i] = TEMP_HOT;
            }
            if (temp >= 15 && temp < 20) {
                advice[i] = TEMP_VERY_WARM;
            }
            if (temp >= 5 && temp < 15) {
                advice[i] = TEMP_WARM;
            }
            if (temp >= 1 && temp < 5) {
                advice[i] = TEMP_WARMER;
            }
            if (temp >= -1 && temp < 1) {
                advice[i] = TEMP_ZERO;
            }
            if (temp >= -5 && temp < -1) {
                advice[i] = TEMP_CHILLY;
            }
            if (temp >= -10 && temp < -5) {
                advice[i] = TEMP_COLDER;
            }
            if (temp >= -15 && temp < -10) {
                advice[i] = TEMP_COLD;
            }
            if (temp >= -20 && temp < -15) {
                advice[i] = TEMP_VERY_COLD;
            }
            if (temp >= -30 && temp < -20) {
                advice[i] = TEMP_FREEZING;
            }
            if (temp < -30) {
                advice[i] = TEMP_VERY_FREEZING;
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ch/makery/weather/view/WeatherOverview.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
