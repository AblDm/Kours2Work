import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task implements Repeatable {


    private int id;
    private String title;
    private String description;
    private Type type;

    private LocalDateTime dateTime;
    private static int idGenerator = 0;


    public Task (String title, String description, Type type, LocalDateTime taskDateTime) {

        this.id = idGenerator++;

        if (!title.isEmpty () || !title.isBlank ()) {
            this.title = title;
        } else {
            throw new RuntimeException ("Поле название не было заполнено, попробуейте ввести задачу ещё раз");
        }
        if (!description.isEmpty () || !description.isBlank ()) {
            this.description = description;
        }

        if (!(type == null)) {
            this.type = type;
        } else {
            throw new RuntimeException ("Задача может соответствовать только одному из типов Личные задачи или Рабочие задачи, попробуейте ввести задачу ещё раз");
        }
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