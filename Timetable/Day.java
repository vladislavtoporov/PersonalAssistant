import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by User on 25.03.2017.
 */
public class Day {
    private ArrayList<Lesson> lessons;
    private GregorianCalendar date;

    public Day(ArrayList<Lesson> lessons, GregorianCalendar date) {
        this.lessons = lessons;
        this.date = date;
    }

    public Day() {
        getLessonsFromDB();
        date = new GregorianCalendar();
    }

    public Day(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
        this.date = new GregorianCalendar();
    }

    public Day(GregorianCalendar date) {
        getLessonsFromDB();
        this.date = date;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public void getLessonsFromDB(){
        //DataBase method
    }

    public void addLesson(Lesson lesson){
        lessons.add(lesson);
    }
}
