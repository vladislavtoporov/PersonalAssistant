import java.util.*;

/**
 * Created by User on 25.03.2017.
 */
public class Timetable {
    private ArrayList<Day> days;
    private boolean isEven;

    public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

    public boolean isEven() {
        return isEven;
    }

    public Timetable(ArrayList<Day> days) {
        this.days = days;
        this.isEven = days.get(0).getDate().WEEK_OF_MONTH % 2 == 0;
    }

    public Timetable(){
        fillDay();
        this.isEven = days.get(0).getDate().WEEK_OF_MONTH % 2 == 0;
    }

    private void calculateDay(boolean next){
        int move;
        if (next){
            move = 7;
        }
        else
            move = -7;
        for (Day day : days) {
            day.getDate().add(day.getDate().DAY_OF_MONTH, move);
        }
        this.isEven = !isEven();
    }

    public void fillDay(){
        //DataBase method
    }

    public void previous(){
        calculateDay(false);
    }

    public void next(){
        calculateDay(true);
    }

}
