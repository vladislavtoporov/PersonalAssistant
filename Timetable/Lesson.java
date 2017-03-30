import java.util.ArrayList;

/**
 * Created by User on 25.03.2017.
 */
public class Lesson {
    private String name;
    private String teacher;
    private int classNum;
    private ArrayList<Note> notes;

    public Lesson(String name, String teacher, int classNum) {
        this.name = name;
        this.teacher = teacher;
        this.classNum = classNum;
        notes = new ArrayList<>();
        updateNotesFromDB();
    }

    public Lesson(String name) {
        this.name = name;
        this.teacher = "";
        notes = new ArrayList<>();
        updateNotesFromDB();
    }

    public Lesson(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
        notes = new ArrayList<>();
        updateNotesFromDB();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public void updateNotesFromDB(){
        //DataBase method
    }

    public ArrayList<Note> getArchivedNotes(){
        //DataBase method
        return null;
    }

    public void addNote(Note note) {
        notes.add(note);
    }
}
