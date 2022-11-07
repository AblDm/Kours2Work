import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        TaskService taskService = new TaskService ();

            try (Scanner scanner = new Scanner(System.in)) {
                label:
                while (true) {
                    printMenu();
                    System.out.print("Выберите пункт меню: ");
                    if (scanner.hasNextInt()) {
                        int menu = scanner.nextInt();
                        switch (menu) {
                            case 1:
                                addTask (taskService, scanner);
                                break;
                            case 2:
                                removeTask(taskService, scanner);
                                break;
                            case 3:
                                getTaskByDay(taskService, scanner);
                                break;
                            case 0:
                                break label;
                        }
                    } else {
                        scanner.next();
                        System.out.println("Выберите пункт меню из списка!");
                    }
                }
            }
        }


        private static void removeTask (TaskService taskService, Scanner scanner){
            System.out.println ("Введите идентификационный номер задачи для удаления:");
            int id = scanner.nextInt ();
            taskService.remove (id);
        }
        private static void getTaskByDay (TaskService taskService, Scanner scanner){
            System.out.println ("Введите дату задачи в формате дд.мм.гггг");
            scanner.nextLine ();
            String date = scanner.nextLine ();
            LocalDate taskDate =LocalDate.parse (date, DateTimeFormatter.ofPattern ("dd.MM.yyyy"));
            var allTaskByDay = taskService.getAllByDate (taskDate);
            System.out.println ("Список задач:");
            for (Task task : allTaskByDay) {
                System.out.println (task);
            }
    }

    private static LocalDate getDateFromUser (Scanner scanner) {
        LocalDate result = null;
        boolean forceUserToAnswer = true;
        while (forceUserToAnswer){
            try {
                System.out.println ("Введите дату задачи в формате дд.мм.гггг");
                String date = scanner.nextLine ();
                result =LocalDate.parse (date, DateTimeFormatter.ofPattern ("dd.MM.yyyy"));
                forceUserToAnswer = false;
            } catch (Exception e){
                System.out.println ("Попробуйте ввести ещё раз");
            }
        } return result;
    }

    private static LocalTime getTimeFromUser (Scanner scanner) {
        LocalTime result1 = null;
        boolean forceUserToAnswer1 = true;
        while (forceUserToAnswer1){
            try {
                System.out.println ("Введите время задачи в формате чч:мм");
                String time = scanner.nextLine ();
                result1 =LocalTime.parse (time, DateTimeFormatter.ofPattern ("HH:mm"));
                forceUserToAnswer1 = false;
            } catch (Exception e){
                System.out.println ("Попробуйте ввести ещё раз");
            }
        } return result1;
    }

        private static void addTask(TaskService taskService, Scanner scanner) {

            System.out.print("Введите название задачи: ");
            String name = scanner.next ();
            if (name.isBlank ()) {
                System.out.println ("Введённые вами данные не распознаны");
                return;
            }
            scanner.nextLine ();

            System.out.println ("Введите описание задачи");
            String description = scanner.nextLine ();
            if (description.isBlank ()) {
                System.out.println ("Введённые вами данные не распознаны");
                return;
            }

            LocalDate taskDate = getDateFromUser (scanner);


            LocalTime taskTime =getTimeFromUser (scanner);

            LocalDateTime resultDate = LocalDateTime.of (taskDate, taskTime);

            System.out.println ("Введите тип задачи: Личный (1) Рабочий (2)");
            int type = scanner.nextInt ();
            Type taskType = type==1 ? Type.PRIVATE : Type.WORK;

            System.out.println ("Введите повторяемость задачи: ");
            System.out.println (" 0 - не повторяется ");
            System.out.println (" 1 - Ежедневная ");
            System.out.println (" 2 - Недельная ");
            System.out.println (" 3 - Месячная ");
            System.out.println (" 4 - Годовая ");
            int typeTask = scanner.nextInt ();
            switch (typeTask){
                case 0:
                    taskService.add (new Task (name, description, taskType, resultDate));
                break;
                case 1:
                    taskService.add (new DailyTask (name, description, taskType, resultDate));
                    break;
                case 2:
                    taskService.add (new WeeklyTask (name, description, taskType, resultDate));
                    break;
                case 3:
                    taskService.add (new MonthlyTask (name, description, taskType, resultDate));
                    break;
                case 4:
                    taskService.add (new YearlyTask (name, description, taskType, resultDate));
                    break;
                default:
                    throw new RuntimeException ("Товарищ! нет такой задачи");
            }

        }

        private static void printMenu() {
            System.out.println(
                    """
                            1. Добавить задачу
                            2. Удалить задачу
                            3. Получить задачу на указанный день
                            0. Выход
                            """
            );
        }
    }
