package birzeit.edu.trainingcenter;

import java.util.Date;

public class Course {

    private  int id;
    private String course_title;
    private String topics;
    private int number_of_student;
    private String venue;
    private String instructor;
    private Date start_date;
    private Date end_date;
    private Date deadline_registration;

    public Course(String course_title, String topics) {
        this.course_title=course_title;
        this.topics=topics;

    }


    public Course(String course_title, String topics, int number_of_student, String venue, String instructor, Date start_date, Date end_date, Date deadline_registration) {
        this.course_title = course_title;
        this.topics = topics;
        this.number_of_student = number_of_student;
        this.venue = venue;
        this.instructor = instructor;
        this.start_date = start_date;
        this.end_date = end_date;
        this.deadline_registration = deadline_registration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public int getNumber_of_student() {
        return number_of_student;
    }

    public void setNumber_of_student(int number_of_student) {
        this.number_of_student = number_of_student;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getDeadline_registration() {
        return deadline_registration;
    }

    public void setDeadline_registration(Date deadline_registration) {
        this.deadline_registration = deadline_registration;
    }
}
