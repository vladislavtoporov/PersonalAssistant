package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ch/makery/weather/view/WeatherOverview.fxml"));
        primaryStage.show();
    }


    public static void main(String[] args) {

        private final Image STORM = new Image("PNGs/storm.png");
        private final Image SUNNY = new Image("PNGs/sunny.png");
        private final Image CLOUDY = new Image("PNGs/cloudy.png");
        private final Image CLEAR_MOON = new Image("PNGs/clearNightSky.png");
        private final Image FOG = new Image("PNGs/fog.png");
        private final Image PARTLY_CLOUDY_DAY = new Image("PNGs/partlyCloudyDay.png");
        private final Image PARTLY_CLOUDLY_DAY = new Image("PNGs/partlyCloudyNight.png");
        private final Image RAINY = new Image("PNGs/Rainy.png");
        private final Image SNOWY = new Image("PNGs/snowy.png");
        private final String TEMP_VERY_HOT = "Avoid heatstroke, drink more water and don't forget to put up your headwear.";
        private final String TEMP_HOT = "It's great weather to go to the beach! Don't forget your shades!";
        private final String TEMP_VERY_WARM = "A light clothes made of natural materials recommended.";
        private final String TEMP_WARM = "You should wear light and warm clothes.";
        private final String TEMP_WARMER = "Wearing overcoat or jacket is recommended.";
        private final String TEMP_ZERO = "Caution!!! Sleet is possible! Please, be careful on the road! And wear warm jacket too.";
        private final String TEMP_CHILLY = "It's good idea to go to the ice rink or go skiing.";
        private final String TEMP_COLDER = "It's better to wear warm clothes.";
        private final String TEMP_COLD = "You really should wear winter clothes now.";
        private final String TEMP_VERY_COLD = "Wow, it's really cold out there. Better wear warm sweater and travel by subway.";
        private final String TEMP_FREEZING = "Hopefully, you will manage to start your car (if you have a car). If not, you'd better to put up the warmest clothes you have.";
        private final String TEMP_VERY_FREEZING = "Maybe it's better to stay in your warm comfortable home with a cup of hot tea and cookies?";
        String temp = Api.getTemp(i);
        String weather = Api.getWeather(i);
        String advice = "";
        if ((short) temp >= 30.0) {
            advice += TEMP_VERY_HOT;
        }
        if ((short) temp < 30 && (short) temp >= 20) {
            advice += TEMP_HOT;
        }
        if ((short) temp >= 15 && (short) temp < 20) {
            advice += TEMP_VERY_WARM;
        }
        if ((short) temp >= 5 && (short) temp < 15) {
            advice += TEMP_WARM;
        }
        if ((short) temp >= 1 && (short) temp < 5) {
            advice += TEMP_WARMER;
        }
        if ((short) temp >= -1 && (short) temp < 1) {
            advice += TEMP_ZERO;
        }
        if ((short) temp >= - 5 && (short) temp < -1) {
            advice+= TEMP_CHILLY;
        }
        if ((short) temp >= -10 && (short) temp < -5) {
            advice += TEMP_COLDER;
        }
        if ((short) temp >= -15 && (short) temp < -10) {
            advice += TEMP_COLD;
        }
        if ((short) temp >= -20 && (short) temp < - 15) {
            advice += TEMP_VERY_COLD;
        }
        if ((short) temp >= -30 && (short) temp < -20) {
            advice += TEMP_FREEZING;
        }
        if ((short) temp < -30) {
            advice += TEMP_VERY_FREEZING;
        }
        launch(args);
    }
}
