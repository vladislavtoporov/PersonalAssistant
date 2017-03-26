import java.util.*;

/**
 * Created by User on 25.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        Lesson les1 = new Lesson("Algebra","Arslanov", 108);
        Lesson les2 = new Lesson("Physical Culture", "Likhachev");
        ArrayList<Lesson> arr = new ArrayList<>();
        arr.add(les1);
        arr.add(les2);
        les1.addNote(new Note("homework", "zabit", les1));
        les1.addNote(new Note("homework", "Принести тетрадь", les1));
        Day day = new Day(arr, new GregorianCalendar());
        ArrayList<Day> arrd = new ArrayList<>();
        arrd.add(day);
        Timetable tt = new Timetable(arrd);
        System.out.println(tt.getDays());
        tt.next();
        System.out.print(tt.getDays());
    }
}
