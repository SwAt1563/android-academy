package birzeit.edu.trainingcenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class API {

    static final String SERVER_URL = "http://10.0.2.2:8000/";


    // for return array of objects
    // GET method
    // return array of objects
    private static JSONArray apiArray(String url, String method, String requestBody) {
        JSONArray jsonArray = null;
        // convert json string to json array
        try {
            ConnectionAsyncTask connectionAsyncTask = new
                    ConnectionAsyncTask(method, requestBody);
            connectionAsyncTask.execute(url);
            String response = connectionAsyncTask.get();
            jsonArray = new JSONArray(response);

        } catch (JSONException e) {
            return null;
        } catch (ExecutionException e) {
            return null;
        } catch (InterruptedException e) {
            return null;
        }

        return jsonArray;
    }

    // for return one object
    // GET, PATCH, DELETE , POST methods
    // DELETE method return empty object
    // POST method return the object that created
    // PATCH method return the object that updated
    // GET method return the object that get ( only one object )
    private static JSONObject apiObject(String url, String method, String requestBody) {
        JSONObject jsonObject= null;
        // convert json string to json array
        try {
            ConnectionAsyncTask connectionAsyncTask = new
                    ConnectionAsyncTask(method, requestBody);
            connectionAsyncTask.execute(url);
            String response = connectionAsyncTask.get();
            if (method.equals("DELETE"))
                return new JSONObject();
            jsonObject = new JSONObject(response);

        } catch (JSONException e) {
            return null;
        } catch (ExecutionException e) {
            return null;
        } catch (InterruptedException e) {
            return null;
        }

        return jsonObject;
    }


    // ############################################################ SIGN IN ############################################################ //
    public static JSONObject ownerSignIn(String email, String password) {
        // Create a JSON object
        JSONObject jsonObject = new JSONObject();

        // Add key-value pairs to the JSON object
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }



        JSONObject response = API.apiObject(SERVER_URL + "account/signin/", "POST", jsonObject.toString());

        if (response == null)
            return null;




        JSONObject user = new JSONObject();
        JSONObject userJson;


        try {
            userJson = response.getJSONObject("user");
            String username = userJson.getString("username");
            String firstName = userJson.getString("first_name");
            String lastName = userJson.getString("last_name");
            String userEmail = userJson.getString("email");
            String userType = userJson.getString("user_type");

            user.put("username", username);
            user.put("first_name", firstName);
            user.put("last_name", lastName);
            user.put("email", userEmail);
            user.put("user_type", userType);

        } catch (JSONException e) {
            return null;
        }


        return user;



    }


    public static JSONObject instructorSignIn(String email, String password) {
        // Create a JSON object
        JSONObject jsonObject = new JSONObject();

        // Add key-value pairs to the JSON object
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JSONObject response = API.apiObject(SERVER_URL + "account/signin/", "POST", jsonObject.toString());

        if (response == null)
            return null;

        JSONObject user = new JSONObject();
        JSONObject userJson;



        try {
            userJson = response.getJSONObject("user");
            String username = userJson.getString("username");
            String firstName = userJson.getString("first_name");
            String lastName = userJson.getString("last_name");
            String userEmail = userJson.getString("email");
            String userType = userJson.getString("user_type");

            user.put("username", username);
            user.put("first_name", firstName);
            user.put("last_name", lastName);
            user.put("email", userEmail);
            user.put("user_type", userType);
            user.put("specialization", response.getString("specialization"));
            user.put("address", response.getString("address"));
            user.put("phone", response.getString("phone"));
            user.put("degree", response.getString("degree"));


        } catch (JSONException e) {
            return null;
        }


        return user;

    }

    public static JSONObject traineeSignIn(String email, String password){
        // Create a JSON object
        JSONObject jsonObject = new JSONObject();

        // Add key-value pairs to the JSON object
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JSONObject response = API.apiObject(SERVER_URL + "account/signin/", "POST", jsonObject.toString());

        if (response == null)
            return null;

        JSONObject user = new JSONObject();
        JSONObject userJson;

        try {
            userJson = response.getJSONObject("user");
            String username = userJson.getString("username");
            String firstName = userJson.getString("first_name");
            String lastName = userJson.getString("last_name");
            String userEmail = userJson.getString("email");
            String userType = userJson.getString("user_type");

            user.put("username", username);
            user.put("first_name", firstName);
            user.put("last_name", lastName);
            user.put("email", userEmail);
            user.put("user_type", userType);
            user.put("address", response.getString("address"));
            user.put("phone", response.getString("phone"));




        } catch (JSONException e) {
            return null;

        }


        return user;

    }

    // ############################################################ END SIGN IN ############################################################ //


    // ############################################################ SIGN UP ############################################################ //

    public static Boolean ownerSignUp(String firstName, String lastName, String username, String email, String password) {
        // Create a JSON object
        JSONObject jsonObject = new JSONObject();

        // Add key-value pairs to the JSON object
        try {
            jsonObject.put("first_name", firstName);
            jsonObject.put("last_name", lastName);
            jsonObject.put("username", username);
            jsonObject.put("email", email);
            jsonObject.put("password", password);

        } catch (JSONException e) {
            return false;
        }

        JSONObject response = API.apiObject(SERVER_URL + "owner/signup/", "POST", jsonObject.toString());

        if (response == null)
            return false;

        return true;
    }


    public static Boolean instructorSignUp(String firstName, String lastName, String username, String email, String password, String specialization, String address, String phone, String degree) {
        // Create a JSON object
        JSONObject jsonObject = new JSONObject();

        // Add key-value pairs to the JSON object
        try {
            jsonObject.put("first_name", firstName);
            jsonObject.put("last_name", lastName);
            jsonObject.put("username", username);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            jsonObject.put("specialization", specialization);
            jsonObject.put("address", address);
            jsonObject.put("phone", phone);
            jsonObject.put("degree", degree);

        } catch (JSONException e) {
            return false;
        }

        JSONObject response = API.apiObject(SERVER_URL + "instructor/signup/", "POST", jsonObject.toString());

        if (response == null)
            return false;

        return true;
    }


    public static Boolean traineeSignUp(String firstName, String lastName, String username, String email, String password, String address, String phone) {
        // Create a JSON object
        JSONObject jsonObject = new JSONObject();

        // Add key-value pairs to the JSON object
        try {
            jsonObject.put("first_name", firstName);
            jsonObject.put("last_name", lastName);
            jsonObject.put("username", username);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            jsonObject.put("address", address);
            jsonObject.put("phone", phone);

        } catch (JSONException e) {
            return false;
        }

        JSONObject response = API.apiObject(SERVER_URL + "trainee/signup/", "POST", jsonObject.toString());

        if (response == null)
            return false;

        return true;
    }

    // ############################################################ END SIGN UP ############################################################ //



    // ############################################################ PROFILE ############################################################ //

    public static Boolean profileUpdate(String firstName, String lastName, String username) {
        // Create a JSON object
        JSONObject jsonObject = new JSONObject();

        // Add key-value pairs to the JSON object
        try {
            jsonObject.put("first_name", firstName);
            jsonObject.put("last_name", lastName);


        } catch (JSONException e) {
            return false;
        }

        JSONObject response = API.apiObject(SERVER_URL + "account/update/" + username + "/", "POST", jsonObject.toString());

        if (response == null)
            return false;

        return true;
    }


    public static JSONObject getOwnerProfile(String username){

        JSONObject response = API.apiObject(SERVER_URL + "owner/profile/" + username + "/", "GET", null);

        if (response == null)
            return null;

        JSONObject user = new JSONObject();
        JSONObject userJson;

        try {
            userJson = response.getJSONObject("user");
            String firstName = userJson.getString("first_name");
            String lastName = userJson.getString("last_name");
            String userEmail = userJson.getString("email");
            String userType = userJson.getString("user_type");

            user.put("first_name", firstName);
            user.put("last_name", lastName);
            user.put("email", userEmail);
            user.put("user_type", userType);

        } catch (JSONException e) {
            return null;
        }

        return user;
    }

    public static JSONObject getInstructorProfile(String username){

            JSONObject response = API.apiObject(SERVER_URL + "instructor/profile/" + username + "/", "GET", null);

            if (response == null)
                return null;

            JSONObject user = new JSONObject();
            JSONObject userJson;

            try {
                userJson = response.getJSONObject("user");
                String firstName = userJson.getString("first_name");
                String lastName = userJson.getString("last_name");
                String userEmail = userJson.getString("email");
                String userType = userJson.getString("user_type");

                String specialization = response.getString("specialization");
                String address = response.getString("address");
                String phone = response.getString("phone");
                String degree = response.getString("degree");

                user.put("first_name", firstName);
                user.put("last_name", lastName);
                user.put("email", userEmail);
                user.put("user_type", userType);
                user.put("specialization", specialization);
                user.put("address", address);
                user.put("phone", phone);
                user.put("degree", degree);

            } catch (JSONException e) {
                return null;
            }

            return user;
    }

    public static JSONObject getTraineeProfile(String username){

                JSONObject response = API.apiObject(SERVER_URL + "trainee/profile/" + username + "/", "GET", null);

                if (response == null)
                    return null;

                JSONObject user = new JSONObject();
                JSONObject userJson;

                try {
                    userJson = response.getJSONObject("user");
                    String firstName = userJson.getString("first_name");
                    String lastName = userJson.getString("last_name");
                    String userEmail = userJson.getString("email");
                    String userType = userJson.getString("user_type");

                    String address = response.getString("address");
                    String phone = response.getString("phone");

                    user.put("first_name", firstName);
                    user.put("last_name", lastName);
                    user.put("email", userEmail);
                    user.put("user_type", userType);
                    user.put("address", address);
                    user.put("phone", phone);

                } catch (JSONException e) {
                    return null;
                }

                return user;
    }

    // ############################################################ END PROFILE ############################################################ //


    // ############################################################ Notifications ############################################################ //

    public static Boolean startCoursesNotifications(){
        JSONObject response = API.apiObject(SERVER_URL + "notification/start_date/", "POST", null);

        if (response == null)
            return false;

        return true;
    }

    public static List<String> getUserNotifications(String username){

        JSONArray response = API.apiArray(SERVER_URL + "notification/list/" + username + "/", "GET", null);

        if (response == null)
            return null;

        List<String> notifications = new ArrayList<String>();

        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject notification = response.getJSONObject(i);
                String notificationText = notification.getString("message");
                notifications.add(notificationText);
            }
        } catch (JSONException e) {
            return null;
        }

        return notifications;
    }

    // ############################################################ END Notifications ############################################################ //


    // ############################################################ Courses ############################################################ //


    public static List<Course> getCourses(){

            JSONArray response = API.apiArray(SERVER_URL + "course/list/", "GET", null);

            if (response == null)
                return null;

            List<Course> courses = Course.parseCourses(response);


            return courses;
    }

    public static List<Course> getRegistrationCourses(){

            JSONArray response = API.apiArray(SERVER_URL + "course/registration_list/", "GET", null);

            if (response == null)
                return null;

            List<Course> courses = Course.parseCourses(response);

            return courses;
    }

    public static Course getCourse(String title){

                JSONObject response = API.apiObject(SERVER_URL + "course/detail/" + title + "/", "GET", null);

                if (response == null)
                    return null;

                Course course = Course.parseCourse(response);

                return course;
    }

    public static Boolean deleteCourse(String title){
        JSONObject response = API.apiObject(SERVER_URL + "course/delete/" + title + "/", "DELETE", null);

        if (response == null)
            return false;

        return true;
    }

    public static Course addCourse(Course object){

        // Create a JSON object
        JSONObject jsonObject = new JSONObject();

        // Add key-value pairs to the JSON object
        try {
            jsonObject.put("title", object.getTitle());
            jsonObject.put("topics", object.getTopics());
            jsonObject.put("instructor", object.getInstructor());
            jsonObject.put("venue", object.getVenue());
            jsonObject.put("start_date", object.getStart_date());
            jsonObject.put("end_date", object.getEnd_date());

            // Convert prerequisites List to a comma-separated string
            StringBuilder prerequisitesBuilder = new StringBuilder();
            List<String> prerequisites = object.getPrerequisites();
            for (int i = 0; i < prerequisites.size(); i++) {
                if (i > 0) {
                    prerequisitesBuilder.append(",");
                }
                prerequisitesBuilder.append(prerequisites.get(i));
            }
            jsonObject.put("prerequisites", prerequisitesBuilder.toString());
        } catch (JSONException e) {
            return null;
        }

        JSONObject response = API.apiObject(SERVER_URL + "course/create/", "POST", jsonObject.toString());

        if (response == null) {
            return null;
        }

        Course course = Course.parseCourse(response);

        return course;


    }


    public static Boolean updateCourse(String courseTitle, Course object) {
        // Create a JSON object
        JSONObject jsonObject = new JSONObject();

        // Add key-value pairs to the JSON object
        try {
            jsonObject.put("title", object.getTitle());
            jsonObject.put("topics", object.getTopics());
            jsonObject.put("instructor", object.getInstructor());
            jsonObject.put("venue", object.getVenue());
            jsonObject.put("start_date", object.getStart_date());
            jsonObject.put("end_date", object.getEnd_date());
            jsonObject.put("is_finish", object.isIs_finish());
            jsonObject.put("is_available", object.isIs_available());

            // Convert prerequisites List to a comma-separated string
            StringBuilder prerequisitesBuilder = new StringBuilder();
            List<String> prerequisites = object.getPrerequisites();
            for (int i = 0; i < prerequisites.size(); i++) {
                if (i > 0) {
                    prerequisitesBuilder.append(",");
                }
                prerequisitesBuilder.append(prerequisites.get(i));
            }
            jsonObject.put("prerequisites", prerequisitesBuilder.toString());
        } catch (JSONException e) {
            return false;
        }

        JSONObject response = API.apiObject(SERVER_URL + "course/update/" + courseTitle + "/", "PATCH", jsonObject.toString());

        if (response == null)
            return false;


        return true;
    }




    // ############################################################ END Courses ############################################################ //


    // ############################################################ Enrollment ############################################################ //

    public static Boolean ownerEnroll(String trainee_username, String course_title, String enrollment_status){

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("trainee_username", trainee_username);
            jsonObject.put("course_title", course_title);
            jsonObject.put("enrollment_status", enrollment_status);
        } catch (JSONException e) {
            return false;
        }

        JSONObject response = API.apiObject(SERVER_URL + "owner/enrollment_status/", "POST", jsonObject.toString());

        if (response == null)
            return false;

        return true;
    }


    // IF IT RETURN FALSE, THEN THE USER IS ALREADY HAS COURSES AT SAME TIME OR THE PRE-REQUISITES ARE NOT COMPLETED
    public static Boolean traineeEnroll(String trainee_username, String course_title){

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("trainee_username", trainee_username);
            jsonObject.put("course_title", course_title);
        } catch (JSONException e) {
            return false;
        }

        JSONObject response = API.apiObject(SERVER_URL + "trainee/enroll/", "POST", jsonObject.toString());

        if (response == null)
            return false;

        return true;
    }



    // ############################################################ END Enrollment ############################################################ //

    // ############################################################ Trainee Course ############################################################ //


    public static List<Course> getTraineeCourses(String username){

        JSONArray response = API.apiArray(SERVER_URL + "trainee/courses/" + username + "/", "GET", null);

        if (response == null)
            return null;

        List<Course> courses = Course.parseCourses(response);

        return courses;
    }

    // ############################################################ END Trainee Course ############################################################ //


    // ############################################################ Instructor Course ############################################################ //


    public static List<Course> getInstructorCurrentCourses(String username){

        JSONArray response = API.apiArray(SERVER_URL + "instructor/current_courses/" + username + "/", "GET", null);

        if (response == null)
            return null;

        List<Course> courses = Course.parseCourses(response);

        return courses;
    }

    public static List<Course> getInstructorPreviousCourses(String username){

        JSONArray response = API.apiArray(SERVER_URL + "instructor/previous_courses/" + username + "/", "GET", null);

        if (response == null)
            return null;

        List<Course> courses = Course.parseCourses(response);

        return courses;
    }

    // ############################################################ END Instructor Course ############################################################ //





}
