/**
 * Created by User on 25.03.2017.
 */
public class Note {
    private String name;
    private String content;
    private Lesson lesson;

    public Note(String name, String content, Lesson lesson) {
        this.name = name;
        this.content = content;
        this.lesson = lesson;
    }

    public Note(Lesson lesson) {
        name = "";
        content = "";
        this.lesson = lesson;
    }

    public Note(String name, Lesson lesson) {
        this.name = name;
        content = "";
        this.lesson = lesson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
