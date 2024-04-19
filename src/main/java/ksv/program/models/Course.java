package ksv.program.models;



import javax.persistence.*;

import java.util.Random;

@Entity
@Table(name = "courses")
public class Course {
    // region поля
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "course_name")
    private String title;
    @Column(name = "duration")
    @Enumerated(EnumType.STRING)
    private Duration duration;
    // endregion

    // region статические переменные

    transient private static final String[] tagOfCourses = {
            "Программирование",
            "Информационные технологии",
            "Дизайн",
            "Маркетинг",
            "Аналитика",
            "ИТ-архитектура",
            "Тестирование",
            "Машинное обучение",
            "Менеджмент",
            "Игры",
            "Blockchain",
            "Кино и музыка",
            "Школьникам",
            "Цифровые профессии"};
    transient private static final Random r = new Random();
    // endregion

    // region конструкторы
    private Course(int id, String title, Duration duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Course(String title, Duration duration) {
        this.title = title;
        this.duration = duration;
    }

    public Course() {
    }
    // endregion

    // region геттеры и сеттеры

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration.getDuration();
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setDuration(int duration) {
        switch (duration) {
            case 9:
                this.duration = Duration.NINE_MONTHS;
                break;
            case 12:
                this.duration = Duration.ONE_YEAR;
                break;
            case 18:
                this.duration = Duration.ONE_AND_HALF_YEAR;
                break;
            case 24:
                this.duration = Duration.TWO_YEARS;
                break;
            case 36:
                this.duration = Duration.THREE_YEARS;
                break;
            default:

        }
    }

    // endregion

    // region методы

    public static Course createCourse() {
        return new Course( tagOfCourses[r.nextInt(tagOfCourses.length)], Duration.values()[r.nextInt(Duration.values().length)]);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
    // endregion

    enum Duration {// просто захотелось вспомнить как это создавать(и как работать с ним), а то только мимолетом упоминается с тех пор как изучил
        NINE_MONTHS(9),
        ONE_YEAR(12),
        ONE_AND_HALF_YEAR(18),
        TWO_YEARS(24),
        THREE_YEARS(36);

        private final int duration;

        Duration(int i) {
            this.duration = i;
        }

        public int getDuration() {
            return duration;
        }
        @Override
        public String toString() {
            return duration + " месяцев";
        }
    }
}
