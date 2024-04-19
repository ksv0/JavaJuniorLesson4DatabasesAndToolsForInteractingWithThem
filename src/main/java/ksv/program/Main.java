package ksv.program;


import ksv.program.models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Создайте базу данных (например, SchoolDB).
 * <p>
 * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
 * <p>
 * Настройте Hibernate для работы с вашей базой данных.
 * <p>
 * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
 * <p>
 * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
 * <p>
 * Убедитесь, что каждая операция выполняется в отдельной транзакции.
 */
public class Main {
    public static void main(String[] args) {

        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            Course course = Course.createCourse();
            System.out.println("Course: " + course);
            session.save(course);

            Course retrievedCourse = session.get(Course.class, course.getId());
            System.out.println("Retrieved course: " + retrievedCourse);

            retrievedCourse.setTitle("Java");
            session.update(retrievedCourse);

            try {
                Course retrievedCourseToDelete = session.get(Course.class, 1L);
                session.delete(retrievedCourseToDelete);
            } catch (Exception e) {
                System.out.println("Course not found");
            }

            session.getTransaction().commit();


        }
    }
}