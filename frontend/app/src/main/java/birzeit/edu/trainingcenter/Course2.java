package birzeit.edu.trainingcenter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Course2 {
    private String title;
    private String topics;
    private String instructor;
    private String venue;
    private List<String> prerequisites;
    private String start_date;
    private String end_date;
    private int trainees_count;
    private List<String> trainees;
    private boolean is_finish;
    private boolean is_available;

    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", topics='" + topics + '\'' +
                ", instructor='" + instructor + '\'' +
                ", venue='" + venue + '\'' +
                ", prerequisites=" + prerequisites +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", trainees_count=" + trainees_count +
                ", trainees=" + trainees +
                ", is_finish=" + is_finish +
                ", is_available=" + is_available +
                '}';
    }

    public Course2() {
    }

    public Course2(String title, String topics, String instructor, String venue, List<String> prerequisites,
                  String start_date, String end_date, int trainees_count, List<String> trainees,
                  boolean is_finish, boolean is_available) {
        setTitle(title);
        setTopics(topics);
        setInstructor(instructor);
        setVenue(venue);
        setPrerequisites(prerequisites);
        setStart_date(start_date);
        setEnd_date(end_date);
        setTrainees_count(trainees_count);
        setTrainees(trainees);
        setIs_finish(is_finish);
        setIs_available(is_available);
    }




    public static Course2 parseCourse(JSONObject object) {
        Course2 course = new Course2();

        try {
            JSONObject jsonObject = object;
            course.setTitle(jsonObject.getString("title"));
            course.setTopics(jsonObject.getString("topics"));
            course.setInstructor(jsonObject.getString("instructor"));
            course.setVenue(jsonObject.getString("venue"));
            course.setStart_date(jsonObject.getString("start_date"));
            course.setEnd_date(jsonObject.getString("end_date"));
            course.setTrainees_count(jsonObject.getInt("trainees_count"));
            course.setIs_finish(jsonObject.getBoolean("is_finish"));
            course.setIs_available(jsonObject.getBoolean("is_available"));

            List<String> prerequisites = new ArrayList<>();
            JSONArray prerequisitesArray = jsonObject.getJSONArray("prerequisites");
            for (int i = 0; i < prerequisitesArray.length(); i++) {
                prerequisites.add(prerequisitesArray.getString(i));
            }
            course.setPrerequisites(prerequisites);

            List<String> trainees = new ArrayList<>();
            JSONArray traineesArray = jsonObject.getJSONArray("trainees");
            for (int i = 0; i < traineesArray.length(); i++) {
                trainees.add(traineesArray.getString(i));
            }
            course.setTrainees(trainees);
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing exception
        }

        return course;
    }

    public static List<Course2> parseCourses(JSONArray coursesList) {
        List<Course2> courses = new ArrayList<>();

        try {
            JSONArray jsonArray = coursesList;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Course2 course = new Course2();
                course.setTitle(jsonObject.getString("title"));
                course.setTopics(jsonObject.getString("topics"));
                course.setInstructor(jsonObject.getString("instructor"));
                course.setVenue(jsonObject.getString("venue"));
                course.setStart_date(jsonObject.getString("start_date"));
                course.setEnd_date(jsonObject.getString("end_date"));
                course.setTrainees_count(jsonObject.getInt("trainees_count"));
                course.setIs_finish(jsonObject.getBoolean("is_finish"));
                course.setIs_available(jsonObject.getBoolean("is_available"));

                JSONArray prerequisitesArray = jsonObject.getJSONArray("prerequisites");
                List<String> prerequisites = new ArrayList<>();
                for (int j = 0; j < prerequisitesArray.length(); j++) {
                    prerequisites.add(prerequisitesArray.getString(j));
                }
                course.setPrerequisites(prerequisites);

                JSONArray traineesArray = jsonObject.getJSONArray("trainees");
                List<String> trainees = new ArrayList<>();
                for (int j = 0; j < traineesArray.length(); j++) {
                    trainees.add(traineesArray.getString(j));
                }
                course.setTrainees(trainees);

                courses.add(course);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing exception
        }

        return courses;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getTrainees_count() {
        return trainees_count;
    }

    public void setTrainees_count(int trainees_count) {
        this.trainees_count = trainees_count;
    }

    public List<String> getTrainees() {
        return trainees;
    }

    public void setTrainees(List<String> trainees) {
        this.trainees = trainees;
    }

    public boolean isIs_finish() {
        return is_finish;
    }

    public void setIs_finish(boolean is_finish) {
        this.is_finish = is_finish;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }




}
