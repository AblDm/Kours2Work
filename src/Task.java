import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task implements Repeatable {


    private int id;
    private String title;
    private String description;
    private Type type;

    private LocalDateTime dateTime;
    private static int idGenerator = 0;
//добавить ошибки если пустые поля или нулл
    public Task(String title, String description, Type type, LocalDateTime taskDateTime) {
        this.id = idGenerator++;
        this.title = title;
        this.description = description;
        this.type = type;
        this.dateTime = taskDateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(Type type) {
        this.type = type;
    }



    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean isAvailable(LocalDate inputDate) {
        return inputDate.isEqual (getDateTime ().toLocalDate ());
    }

    @Override
    public String toString() {
        return "Задача в календаре: {" +
                "идентификационный номер:" + id +
                ", название: '" + title + '\'' +
                ", описание: '" + description + '\'' +
                ", тип:" + type +
                ", дата: " + dateTime +
                '}';
    }
}