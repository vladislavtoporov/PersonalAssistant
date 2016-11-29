package weather;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static java.awt.SystemColor.text;

public class Controller {
    private final String KAZAN = "http://api.openweathermap.org/data/2.5/forecast?q=Kazan&mode=xml&appid=c897b438605eff38e5df5f6928351367&units=metric";
    private final String ALMET = "http://api.openweathermap.org/data/2.5/forecast?q=Almetyevsk&mode=xml&appid=c897b438605eff38e5df5f6928351367&units=metric";
    private final String CHELNY = "http://api.openweathermap.org/data/2.5/forecast?q=NaberezhnyeChelny&mode=xml&appid=c897b438605eff38e5df5f6928351367&units=metric";
    private final String STORM = "weather/resource/PNGs/storm.png";
    private final String SUNNY = "weather/resource/PNGs/sunny.png";
    private final String CLOUDY = "weather/resource/PNGs/cloudy.png";
    private final String CLEAR_MOON = "weather/resource/PNGs/clearNightSky.png";
    private final String FOG = "weather/resource/PNGs/fog.png";
    private final String PARTLY_CLOUDY_DAY = "weather/resource/PNGs/partlyCloudyDay.png";
    private final String PARTLY_CLOUDY_NIGHT = "weather/resource/PNGs/partlyCloudyNight.png";
    private final String RAINY = "weather/resource/PNGs/Rainy.png";
    private final String SNOWY = "weather/resource/PNGs/snowy.png";
    private String[] today = {"", "", "", ""};
    private String[] tomorrow = {"", "", "", ""};
    private String[] after = {"", "", "", ""};
    private Image[] todayImg = new Image[4];
    private Image[] tomorrowImg = new Image[4];
    private Image[] afterImg = new Image[4];
    @FXML
    private Label labelDate;
    @FXML
    private ImageView imgTodayMorn, imgToday, imgTodayEve, imgTodayNight, imgTomorrowMorn, imgTomorrow, imgTomorrowEve, imgTomorrowNight, imgAfterMorn, imgAfter, imgAfterEve, imgAfterNight;
    @FXML
    private TextArea txtTodayMorn, txtToday, txtTodayEve, txtTodayNight, txtTomorrowMorn, txtTomorrow, txtTomorrowEve, txtTomorrowNight, txtAfterMorn, txtAfter, txtAfterEve, txtAfterNight;
    private File f = new File("src/weather/resource/city.txt");
    private Scanner sr = new Scanner(f);
    private String city;

    public Controller() throws FileNotFoundException {
    }

    private void writeIn(String s) {
        try {
            if (f.exists()) {
                f.delete();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
            PrintWriter pw = new PrintWriter(f);

            try {
                pw.print(s);
            } finally {
                pw.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private Image setImg(String s) {
        Image image = new Image(STORM);
            switch (s) {
                case "thunderstorm":
                    image = new Image(STORM);
                    break;
                case "clear sky":
                    image = new Image(SUNNY);
                    break;
                case "rain":
                    image = new Image(RAINY);
                    break;
                case "snow":
                    image = new Image(SNOWY);
                    break;
                case "fog":
                    image = new Image(FOG);
                    break;
                case "cloudy":
                    image = new Image(CLOUDY);
                    break;
                case "few clouds":
                    image = new Image(PARTLY_CLOUDY_DAY);
                    break;
            }
            return image;
    }

    @FXML
    private void cityKazan(ActionEvent actionEvent) throws FileNotFoundException {
        writeIn(KAZAN);
        sr = new Scanner(f);
        refresh(actionEvent);
    }

    @FXML
    private void cityChelny(ActionEvent actionEvent) throws FileNotFoundException {
        writeIn(CHELNY);
        sr = new Scanner(f);
        refresh(actionEvent);
    }

    @FXML
    private void cityAlmet(ActionEvent actionEvent) throws FileNotFoundException {
        writeIn(ALMET);
        sr = new Scanner(f);
        refresh(actionEvent);
    }

    public void fill() throws FileNotFoundException {
        txtTodayMorn.setText(today[0]);
        txtToday.setText(today[1]);
        txtTodayEve.setText(today[2]);
        txtTodayNight.setText(today[3]);
        txtTomorrowMorn.setText(tomorrow[0]);
        txtTomorrow.setText(tomorrow[1]);
        txtTomorrowEve.setText(tomorrow[2]);
        txtTomorrowNight.setText(tomorrow[3]);
        txtAfterMorn.setText(after[0]);
        txtAfter.setText(after[1]);
        txtAfterEve.setText(after[2]);
        txtAfterNight.setText(after[3]);
        imgTodayMorn.setImage(todayImg[0]);
        imgToday.setImage(todayImg[1]);
        imgTodayEve.setImage(todayImg[2]);
        imgTodayNight.setImage(todayImg[3]);
        imgTomorrowMorn.setImage(tomorrowImg[0]);
        imgTomorrow.setImage(tomorrowImg[1]);
        imgTomorrowEve.setImage(tomorrowImg[2]);
        imgTomorrowNight.setImage(tomorrowImg[3]);
        imgAfterMorn.setImage(afterImg[0]);
        imgAfter.setImage(afterImg[1]);
        imgAfterEve.setImage(afterImg[2]);
        imgAfterNight.setImage(afterImg[3]);
    }

    @FXML
    private void refresh(ActionEvent actionEvent) throws FileNotFoundException {
        sr = new Scanner(f);
        city = sr.nextLine();
        Api.weather(city);
        Main.createAdvice();
        getInfo();
        fill();
        clear();
    }

    public void clear() {
        for (int i = 0; i < 4; i++) {
            today[i] = "";
            after[i] = "";
            tomorrow[i] = "";
        }
    }

    public void getInfo() throws FileNotFoundException {
        File file = new File("src/weather/resource/weather.txt");
        Scanner sc = new Scanner(file);
        String dayTime;
        String temp = sc.nextLine();
        labelDate.setText(temp);
        Calendar cal = Calendar.getInstance();
        int cal_ = cal.get(Calendar.DAY_OF_MONTH);
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DAY_OF_MONTH, 1);
        int cal1_ = cal1.get(Calendar.DAY_OF_MONTH);
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DAY_OF_MONTH, 2);
        int cal2_ = cal2.get(Calendar.DAY_OF_MONTH);
        Calendar cal3 = Calendar.getInstance();
        cal3.add(Calendar.DAY_OF_MONTH, 3);
        int cal3_ = cal3.get(Calendar.DAY_OF_MONTH);
        int k = 0;
        byte j = -1;
        while (sc.hasNextLine()) {
            dayTime = sc.nextLine();
            String[] temp1 = dayTime.substring(8).split(" ");
            String line = "";
            switch (dayTime.substring(dayTime.indexOf(" ") + 1)) {
                case "morning":
                    if (cal_ == Integer.parseInt(temp1[0])) {
                        for (int i = 0; i < 3; i++) {
                            line = sc.nextLine();
                            today[0] += line + "\n";
                        }
                        line = line.substring(line.indexOf(" ") + 1);
                        todayImg[0] = setImg(line);
                        today[0] += Main.getAdvice(k);
                        k++;
                    } else if (cal1_ == Integer.parseInt(temp1[0])) {
                        for (int i = 0; i < 3; i++) {
                            line = sc.nextLine();
                            tomorrow[0] += line + "\n";
                        }
                        line = line.substring(line.indexOf(" ") + 1);
                        tomorrowImg[0] = setImg(line);
                        tomorrow[0] += Main.getAdvice(k);
                        k++;
                    } else if (cal2_ == Integer.parseInt(temp1[0])) {
                        for (int i = 0; i < 3; i++) {
                            line = sc.nextLine();
                            after[0] += line + "\n";
                        }
                        line = line.substring(line.indexOf(" ") + 1);
                        afterImg[0] = setImg(line);
                        after[0] += Main.getAdvice(k);
                        k++;

                    }
                    break;
                case "day":
                    if (cal_ == Integer.parseInt(temp1[0])) {
                        for (int i = 0; i < 3; i++) {
                            line = sc.nextLine();
                            today[1] += line + "\n";
                        }
                        line = line.substring(line.indexOf(" ") + 1);
                        todayImg[1] = setImg(line);
                        today[1] += Main.getAdvice(k);
                        k++;
                    } else if (cal1_ == Integer.parseInt(temp1[0])) {
                        for (int i = 0; i < 3; i++) {
                            line = sc.nextLine();
                            tomorrow[1] += line + "\n";
                        }
                        line = line.substring(line.indexOf(" ") + 1);
                        tomorrowImg[1] = setImg(line);
                        tomorrow[1] += Main.getAdvice(k);
                        k++;
                    } else if (cal2_ == Integer.parseInt(temp1[0])) {
                        for (int i = 0; i < 3; i++) {
                            line = sc.nextLine();
                            after[1] += line + "\n";
                        }
                        line = line.substring(line.indexOf(" ") + 1);
                        afterImg[1] = setImg(line);
                        after[1] += Main.getAdvice(k);
                        k++;
                    }
                    break;
                case "evening":
                    if (cal_ == Integer.parseInt(temp1[0])) {
                        for (int i = 0; i < 3; i++) {
                            line = sc.nextLine();
                            today[2] += line + "\n";
                        }
                        line = line.substring(line.indexOf(" ") + 1);
                        if (line.equals("clear sky")) {
                            todayImg[2] = new Image(Main.CLEAR_MOON);
                        } else if (line.equals("few clouds")){
                            todayImg[2] = new Image(Main.PARTLY_CLOUDY_NIGHT);
                        }
                        else {
                            todayImg[2] = setImg(line);
                        }
                        today[2] += Main.getAdvice(k);
                        k++;
                    } else if (cal1_ == Integer.parseInt(temp1[0])) {
                        for (int i = 0; i < 3; i++) {
                            line = sc.nextLine();
                            tomorrow[2] += line + "\n";
                        }
                        line = line.substring(line.indexOf(" ") + 1);
                        if (line.equals("clear sky")) {
                            tomorrowImg[2] = new Image(Main.CLEAR_MOON);
                        } else if (line.equals("few clouds")){
                            tomorrowImg[2] = new Image(Main.PARTLY_CLOUDY_NIGHT);
                        }
                        else {
                            tomorrowImg[2] = setImg(line);
                        }
                        tomorrow[2] += Main.getAdvice(k);
                        k++;
                    } else if (cal2_ == Integer.parseInt(temp1[0])) {
                        for (int i = 0; i < 3; i++) {
                            line = sc.nextLine();
                            after[2] += line + "\n";
                        }
                        line = line.substring(line.indexOf(" ") + 1);
                        if (line.equals("clear sky")) {
                            afterImg[2] = new Image(Main.CLEAR_MOON);
                        } else if (line.equals("few clouds")){
                            afterImg[2] = new Image(Main.PARTLY_CLOUDY_NIGHT);
                        }
                        else {
                            afterImg[2] = setImg(line);
                        }
                        after[2] += Main.getAdvice(k);
                        k++;
                    }
                    break;
                case "night":
                    if (cal1_ == Integer.parseInt(temp1[0])) {
                        for (int i = 0; i < 3; i++) {
                            line = sc.nextLine();
                            today[3] += line + "\n";
                        }
                        line = line.substring(line.indexOf(" ") + 1);
                        if (line.equals("clear sky")) {
                            todayImg[3] = new Image(Main.CLEAR_MOON);
                        } else if (line.equals("few clouds")){
                            todayImg[3] = new Image(Main.PARTLY_CLOUDY_NIGHT);
                        }
                        else {
                            todayImg[3] = setImg(line);
                        }
                        today[3] += Main.getAdvice(k);
                        k++;
                    } else if (cal2_ == Integer.parseInt(temp1[0])) {
                        for (int i = 0; i < 3; i++) {
                            line = sc.nextLine();
                            tomorrow[3] += line + "\n";
                        }
                        line = line.substring(line.indexOf(" ") + 1);
                        if (line.equals("clear sky")) {
                            tomorrowImg[3] = new Image(Main.CLEAR_MOON);
                        } else if (line.equals("few clouds")){
                            tomorrowImg[3] = new Image(Main.PARTLY_CLOUDY_NIGHT);
                        }
                        else {
                            tomorrowImg[3] = setImg(line);
                        }
                        tomorrow[3] += Main.getAdvice(k);
                        k++;
                    } else if (cal3_ == Integer.parseInt(temp1[0])) {
                        for (int i = 0; i < 3; i++) {
                            line = sc.nextLine();
                            after[3] += line + "\n";
                        }
                        line = line.substring(line.indexOf(" ") + 1);
                        if (line.equals("clear sky")) {
                            afterImg[3] = new Image(Main.CLEAR_MOON);
                        } else if (line.equals("few clouds")){
                            afterImg[3] = new Image(Main.PARTLY_CLOUDY_NIGHT);
                        }
                        else {
                            afterImg[3] = setImg(line);
                        }
                        after[3] += Main.getAdvice(k);
                        k++;
                    }
                    break;
            }
        }
    }
}