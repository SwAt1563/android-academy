package birzeit.edu.trainingcenter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;



import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class StudentMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private LinearLayout dynamicContentLayout;
    private List<TextView> dynamicTextViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        dynamicContentLayout = findViewById(R.id.dynamic_content_layout3);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view3);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        //At the start page run the Profile page for the student
        viewProfile();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.trainee_notification:
                // Handle notification_available_course action
                viewTraineeNotifications();
                break;
            case R.id.view_courses:
                // Handle view_courses action
                viewCourseDetails();
                break;
            case R.id.enroll_in_course:
                // Handle enroll_in_course action
                enrollInCourse();
                break;

            case R.id.view_studied_courses:
                // Handle view_studied_courses action
                viewStudiedCourses();
                break;
            case R.id.view_course_history:
                // Handle view_course_history action
                viewCourseHistory();
                break;
            case R.id.view_profile:
                // Handle view_profile action
                viewProfile();
                break;

            case R.id.logout2:
                // Handle create_course action
                logout();
                break;

        }

        drawerLayout.closeDrawers();
        return true;
    }

    private void viewTraineeNotifications() {
        // Perform logic for viewing available courses

        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(StudentMainActivity.this);
        String trainee_username = sharedPrefManager.readString("username", "");

        List<String> notificationList = API.getUserNotifications(trainee_username);//-------TODO


        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();

        // Create TextViews for students list
        for (String notification : notificationList) {


            // Course Name Label
            TextView notificationLabel = new TextView(StudentMainActivity.this);
            notificationLabel.setText(notification);// .....................TODO: Query
            notificationLabel.setTypeface(notificationLabel.getTypeface(), Typeface.BOLD);
            notificationLabel.setTextSize(22); // Increase font size
            notificationLabel.setPadding(0, 30, 0, 0); // Add top padding
            dynamicContentLayout.addView(notificationLabel);

        }



    }

    private void viewCourseDetails() {
            // Clear the dynamic content layout
            dynamicContentLayout.removeAllViews();

            // Courses Label
            TextView coursesLabel = new TextView(this);
            coursesLabel.setText("Courses:");
            coursesLabel.setTypeface(coursesLabel.getTypeface(), Typeface.BOLD);
            coursesLabel.setTextSize(18); // Increase font size
            coursesLabel.setPadding(0, 16, 0, 0); // Add top padding
            dynamicContentLayout.addView(coursesLabel);

            // Spinner for Courses
            Spinner coursesSpinner = new Spinner(this);
            List<Course> coursesList = API.getCourses();//-------TODO
            List<String> coursesTitles = new ArrayList<String>();
            for (Course course : coursesList) {
                coursesTitles.add(course.getTitle());
            }
            ArrayAdapter<String> coursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesTitles);
            coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            coursesSpinner.setAdapter(coursesAdapter);
            dynamicContentLayout.addView(coursesSpinner);




        // Show Details
            Button DetailsButton = new Button(this);
            DetailsButton.setText("Show Details");
            DetailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Clear the dynamic content layout
                    dynamicContentLayout.removeAllViews();

                    String selectedCourse = coursesSpinner.getSelectedItem().toString();

                    Course course = API.getCourse(selectedCourse);//-------TODO


                    // Course Name Label
                    TextView courseNameLabel = new TextView(StudentMainActivity.this);
                    courseNameLabel.setText("Course Name: " + course.getTitle());// .....................TODO: Query
                    courseNameLabel.setTypeface(courseNameLabel.getTypeface(), Typeface.BOLD);
                    courseNameLabel.setTextSize(22); // Increase font size
                    courseNameLabel.setPadding(0, 30, 0, 0); // Add top padding
                    dynamicContentLayout.addView(courseNameLabel);

                    // Course Description Label
                    TextView courseDescriptionLabel = new TextView(StudentMainActivity.this);
                    courseDescriptionLabel.setText("Course Topics: " + course.getTopics());// .....................TODO: Query
                    courseDescriptionLabel.setTypeface(courseDescriptionLabel.getTypeface(), Typeface.BOLD);
                    courseDescriptionLabel.setTextSize(22); // Increase font size
                    courseDescriptionLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(courseDescriptionLabel);

                    // Number of Students Label
                    TextView numOfStudentsLabel = new TextView(StudentMainActivity.this);
                    numOfStudentsLabel.setText("Number of Students: " + String.valueOf(course.getTrainees_count()));// .....................TODO: Query
                    numOfStudentsLabel.setTypeface(numOfStudentsLabel.getTypeface(), Typeface.BOLD);
                    numOfStudentsLabel.setTextSize(22); // Increase font size
                    numOfStudentsLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(numOfStudentsLabel);

                    // Venue Label
                    TextView venueLabel = new TextView(StudentMainActivity.this);
                    venueLabel.setText("Venue: " + course.getVenue());// .....................TODO: Query
                    venueLabel.setTypeface(venueLabel.getTypeface(), Typeface.BOLD);
                    venueLabel.setTextSize(22); // Increase font size
                    venueLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(venueLabel);

                    // Instructor Label
                    TextView instructorLabel = new TextView(StudentMainActivity.this);
                    instructorLabel.setText("Instructor: " + course.getInstructor());// .....................TODO: Query
                    instructorLabel.setTypeface(instructorLabel.getTypeface(), Typeface.BOLD);
                    instructorLabel.setTextSize(22); // Increase font size
                    instructorLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(instructorLabel);

                    // Start Date Label
                    TextView startDateLabel = new TextView(StudentMainActivity.this);
                    startDateLabel.setText("Start Date: " + course.getStart_date());// .....................TODO: Query
                    startDateLabel.setTypeface(startDateLabel.getTypeface(), Typeface.BOLD);
                    startDateLabel.setTextSize(22); // Increase font size
                    startDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(startDateLabel);

                    // End Date Label
                    TextView endDateLabel = new TextView(StudentMainActivity.this);
                    endDateLabel.setText("End Date: " + course.getEnd_date());// .....................TODO: Query
                    endDateLabel.setTypeface(endDateLabel.getTypeface(), Typeface.BOLD);
                    endDateLabel.setTextSize(22); // Increase font size
                    endDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(endDateLabel);

                    // Deadline Registration Label
                    TextView deadlineLabel = new TextView(StudentMainActivity.this);
                    deadlineLabel.setText("Registration Available: " + String.valueOf(course.isIs_available()));// .....................TODO: Query
                    deadlineLabel.setTypeface(deadlineLabel.getTypeface(), Typeface.BOLD);
                    deadlineLabel.setTextSize(22); // Increase font size
                    deadlineLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(deadlineLabel);


                }
            });

            dynamicContentLayout.addView(DetailsButton);
        }



    private void enrollInCourse() {
        // Perform logic for enrolling in a course

        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();

        // Courses Label
        TextView coursesLabel = new TextView(this);
        coursesLabel.setText("Courses:");
        coursesLabel.setTypeface(coursesLabel.getTypeface(), Typeface.BOLD);
        coursesLabel.setTextSize(18); // Increase font size
        coursesLabel.setPadding(0, 16, 0, 0); // Add top padding
        dynamicContentLayout.addView(coursesLabel);

        // Spinner for Courses
        Spinner coursesSpinner = new Spinner(this);
        List<Course> coursesList = API.getRegistrationCourses();//-------TODO
        List<String> coursesTitles = new ArrayList<String>();
        for (Course course : coursesList) {
            coursesTitles.add(course.getTitle());
        }
        ArrayAdapter<String> coursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesTitles);
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesSpinner.setAdapter(coursesAdapter);
        dynamicContentLayout.addView(coursesSpinner);

        // Show Enroll Button
        Button Enroll = new Button(this);
        Enroll.setText("Enroll in the course");
        //dynamicContentLayout.addView(showHistoryButton);
        Enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the dynamic content layout
                enrollInCourse();

                String selectedCourse = coursesSpinner.getSelectedItem().toString();

                // .....................TODO: Query the database based on the selected course
                SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(StudentMainActivity.this);
                String trainee_username = sharedPrefManager.readString("username", "");

                Boolean done = API.traineeEnroll(trainee_username, selectedCourse);//.....................TODO: Query


                if(done){
                    String message = "Enrollment in the course: " + selectedCourse + "\nSent to Admin";
                    Toast.makeText(StudentMainActivity.this, message, Toast.LENGTH_SHORT).show();
                }else{
                    String message = "Enrollment in the course: " + selectedCourse +
                            "\nFailed, check if you finish it prequisites, of if you have other course in same time";
                    Toast.makeText(StudentMainActivity.this, message, Toast.LENGTH_SHORT).show();
                }



            }
        });

        dynamicContentLayout.addView(Enroll);
    }




    private void viewStudiedCourses() {
        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();

        // Courses Label
        TextView coursesLabel = new TextView(this);
        coursesLabel.setText("Studied Courses:");
        coursesLabel.setTypeface(coursesLabel.getTypeface(), Typeface.BOLD);
        coursesLabel.setTextSize(18); // Increase font size
        coursesLabel.setPadding(0, 16, 0, 0); // Add top padding
        dynamicContentLayout.addView(coursesLabel);

        // Spinner for Courses
        Spinner coursesSpinner = new Spinner(this);

        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(StudentMainActivity.this);
        String trainee_username = sharedPrefManager.readString("username", "");

        List<Course> coursesList = API.getTraineeCourses(trainee_username); // .....................TODO: Query
        List<String> coursesTitles = new ArrayList<String>();
        for (Course course : coursesList) {
            coursesTitles.add(course.getTitle());
        }
        ArrayAdapter<String> coursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesTitles);
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesSpinner.setAdapter(coursesAdapter);
        dynamicContentLayout.addView(coursesSpinner);

        //In case student Enroll after showing the details
        Button Back = new Button(this);


        // Show Details
        Button DetailsButton = new Button(this);
        DetailsButton.setText("Show Details");
        DetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the dynamic content layout
                dynamicContentLayout.removeAllViews();

                String selectedCourse = coursesSpinner.getSelectedItem().toString();

                // .....................TODO: Query the database based on the selected course

                Course course = API.getCourse(selectedCourse);// .....................TODO: Query

                // Course Name Label
                TextView courseNameLabel = new TextView(StudentMainActivity.this);
                courseNameLabel.setText("Course Title: " + course.getTitle());// .....................TODO: Query
                courseNameLabel.setTypeface(courseNameLabel.getTypeface(), Typeface.BOLD);
                courseNameLabel.setTextSize(22); // Increase font size
                courseNameLabel.setPadding(0, 30, 0, 0); // Add top padding
                dynamicContentLayout.addView(courseNameLabel);

                // Course Description Label
                TextView courseDescriptionLabel = new TextView(StudentMainActivity.this);
                courseDescriptionLabel.setText("Course Topics: " + course.getTopics());// .....................TODO: Query
                courseDescriptionLabel.setTypeface(courseDescriptionLabel.getTypeface(), Typeface.BOLD);
                courseDescriptionLabel.setTextSize(22); // Increase font size
                courseDescriptionLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(courseDescriptionLabel);

                // Number of Students Label
                TextView numOfStudentsLabel = new TextView(StudentMainActivity.this);
                numOfStudentsLabel.setText("Number of Students: " + String.valueOf(course.getTrainees_count()));// .....................TODO: Query
                numOfStudentsLabel.setTypeface(numOfStudentsLabel.getTypeface(), Typeface.BOLD);
                numOfStudentsLabel.setTextSize(22); // Increase font size
                numOfStudentsLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(numOfStudentsLabel);

                // Venue Label
                TextView venueLabel = new TextView(StudentMainActivity.this);
                venueLabel.setText("Venue: " + course.getVenue());// .....................TODO: Query
                venueLabel.setTypeface(venueLabel.getTypeface(), Typeface.BOLD);
                venueLabel.setTextSize(22); // Increase font size
                venueLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(venueLabel);

                // Instructor Label
                TextView instructorLabel = new TextView(StudentMainActivity.this);
                instructorLabel.setText("Instructor: " + course.getInstructor());// .....................TODO: Query
                instructorLabel.setTypeface(instructorLabel.getTypeface(), Typeface.BOLD);
                instructorLabel.setTextSize(22); // Increase font size
                instructorLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(instructorLabel);

                // Start Date Label
                TextView startDateLabel = new TextView(StudentMainActivity.this);
                startDateLabel.setText("Start Date: " + course.getStart_date());// .....................TODO: Query
                startDateLabel.setTypeface(startDateLabel.getTypeface(), Typeface.BOLD);
                startDateLabel.setTextSize(22); // Increase font size
                startDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(startDateLabel);

                // End Date Label
                TextView endDateLabel = new TextView(StudentMainActivity.this);
                endDateLabel.setText("End Date: " + course.getEnd_date());// .....................TODO: Query
                endDateLabel.setTypeface(endDateLabel.getTypeface(), Typeface.BOLD);
                endDateLabel.setTextSize(22); // Increase font size
                endDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(endDateLabel);

                // Deadline Registration Label
                TextView deadlineLabel = new TextView(StudentMainActivity.this);
                deadlineLabel.setText("Registration Available: " + String.valueOf(course.isIs_available()));// .....................TODO: Query
                deadlineLabel.setTypeface(deadlineLabel.getTypeface(), Typeface.BOLD);
                deadlineLabel.setTextSize(22); // Increase font size
                deadlineLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(deadlineLabel);



                // Show Back Button
                Back.setText("Back");
                Back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewStudiedCourses();

                    }
                });

                dynamicContentLayout.addView(Back);
            }
        });

        dynamicContentLayout.addView(DetailsButton);    }

    // courses that trainee finished
    private void viewCourseHistory() {
        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();

        // Courses Label
        TextView coursesLabel = new TextView(this);
        coursesLabel.setText("Courses:");
        coursesLabel.setTypeface(coursesLabel.getTypeface(), Typeface.BOLD);
        coursesLabel.setTextSize(18); // Increase font size
        coursesLabel.setPadding(0, 16, 0, 0); // Add top padding
        dynamicContentLayout.addView(coursesLabel);

        // Spinner for Courses
        Spinner coursesSpinner = new Spinner(this);
        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(StudentMainActivity.this);
        String trainee_username = sharedPrefManager.readString("username", "");

        List<Course> coursesList = API.getTraineeCourses(trainee_username); // Dummy data for courses list// .....................TODO: Query
        List<Course> finishedCoursesList = new ArrayList<Course>();
        for (Course course : coursesList) {
            if (course.isIs_finish()) {
                finishedCoursesList.add(course);
            }
        }
        List<String> coursesTitles = new ArrayList<>();
        for (Course course : finishedCoursesList) {
            coursesTitles.add(course.getTitle());
        }
        ArrayAdapter<String> coursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesTitles);
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesSpinner.setAdapter(coursesAdapter);
        dynamicContentLayout.addView(coursesSpinner);

        // Show History Button
        Button showHistoryButton = new Button(this);
        Button Back = new Button(this);

        showHistoryButton.setText("Show History");
        //dynamicContentLayout.addView(showHistoryButton);
        showHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the dynamic content layout
                dynamicContentLayout.removeAllViews();

                String selectedCourse = coursesSpinner.getSelectedItem().toString();

                // .....................TODO: Query the database based on the selected course and retrieve the course history data

                Course course = API.getCourse(selectedCourse);

                // Course Name Label
                TextView courseNameLabel = new TextView(StudentMainActivity.this);
                courseNameLabel.setText("Course Title: " + course.getTitle());// .....................TODO: Query
                courseNameLabel.setTypeface(courseNameLabel.getTypeface(), Typeface.BOLD);
                courseNameLabel.setTextSize(22); // Increase font size
                courseNameLabel.setPadding(0, 30, 0, 0); // Add top padding
                dynamicContentLayout.addView(courseNameLabel);

                // Course Description Label
                TextView courseDescriptionLabel = new TextView(StudentMainActivity.this);
                courseDescriptionLabel.setText("Course Topics: " + course.getTopics());// .....................TODO: Query
                courseDescriptionLabel.setTypeface(courseDescriptionLabel.getTypeface(), Typeface.BOLD);
                courseDescriptionLabel.setTextSize(22); // Increase font size
                courseDescriptionLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(courseDescriptionLabel);

                // Number of Students Label
                TextView numOfStudentsLabel = new TextView(StudentMainActivity.this);
                numOfStudentsLabel.setText("Number of Students: " + String.valueOf(course.getTrainees_count()));// .....................TODO: Query
                numOfStudentsLabel.setTypeface(numOfStudentsLabel.getTypeface(), Typeface.BOLD);
                numOfStudentsLabel.setTextSize(22); // Increase font size
                numOfStudentsLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(numOfStudentsLabel);

                // Venue Label
                TextView venueLabel = new TextView(StudentMainActivity.this);
                venueLabel.setText("Venue: " + course.getVenue());// .....................TODO: Query
                venueLabel.setTypeface(venueLabel.getTypeface(), Typeface.BOLD);
                venueLabel.setTextSize(22); // Increase font size
                venueLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(venueLabel);

                // Instructor Label
                TextView instructorLabel = new TextView(StudentMainActivity.this);
                instructorLabel.setText("Instructor: " + course.getInstructor());// .....................TODO: Query
                instructorLabel.setTypeface(instructorLabel.getTypeface(), Typeface.BOLD);
                instructorLabel.setTextSize(22); // Increase font size
                instructorLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(instructorLabel);

                // Start Date Label
                TextView startDateLabel = new TextView(StudentMainActivity.this);
                startDateLabel.setText("Start Date: " + course.getStart_date());// .....................TODO: Query
                startDateLabel.setTypeface(startDateLabel.getTypeface(), Typeface.BOLD);
                startDateLabel.setTextSize(22); // Increase font size
                startDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(startDateLabel);

                // End Date Label
                TextView endDateLabel = new TextView(StudentMainActivity.this);
                endDateLabel.setText("End Date: " + course.getEnd_date());// .....................TODO: Query
                endDateLabel.setTypeface(endDateLabel.getTypeface(), Typeface.BOLD);
                endDateLabel.setTextSize(22); // Increase font size
                endDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(endDateLabel);

                // Deadline Registration Label
                TextView deadlineLabel = new TextView(StudentMainActivity.this);
                deadlineLabel.setText("Registration Available: " + String.valueOf(course.isIs_available()));// .....................TODO: Query
                deadlineLabel.setTypeface(deadlineLabel.getTypeface(), Typeface.BOLD);
                deadlineLabel.setTextSize(22); // Increase font size
                deadlineLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(deadlineLabel);

                // Show Back Button
                Back.setText("Back");
                Back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewCourseHistory();

                    }
                });

                dynamicContentLayout.addView(Back);

            }
        });

        dynamicContentLayout.addView(showHistoryButton);
    }

    public void viewProfile() {
        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(StudentMainActivity.this);
        String trainee_username = sharedPrefManager.readString("username", "");
        String trainee_first_name = sharedPrefManager.readString("first_name", "");
        String trainee_last_name = sharedPrefManager.readString("last_name", "");
        String trainee_email = sharedPrefManager.readString("email", "");
        String trainee_mobile_number = sharedPrefManager.readString("mobile_number", "");
        String trainee_address = sharedPrefManager.readString("address", "");

        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();

        // Create the labels
        TextView usernameLabel = createLabel("Username:");
        TextView firstNameLabel = createLabel("First Name:");
        TextView lastNameLabel = createLabel("Last Name:");
        TextView emailLabel = createLabel("Email:");
        TextView mobileLabel = createLabel("Mobile Number:");
        TextView addressLabel = createLabel("Address:");

        // Create the edit texts
        EditText usernameEditText = createEditText(trainee_username);
        EditText firstNameEditText = createEditText(trainee_first_name);
        EditText lastNameEditText = createEditText(trainee_last_name);
        EditText emailEditText = createEditText(trainee_email);
        EditText mobileEditText = createEditText(trainee_mobile_number);
        EditText addressEditText = createEditText(trainee_address);

        // Set the other edit texts as read-only
        emailEditText.setEnabled(false);
        usernameEditText.setEnabled(false);
        mobileEditText.setEnabled(false);
        addressEditText.setEnabled(false);

        // Create the edit button
        Button editButton = createButton("Edit");

        // Set click listener for the edit button
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Read the values from the edit texts

                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();




                Boolean done = API.profileUpdate( firstName, lastName, trainee_username);

                if(done){
                    sharedPrefManager.writeString("first_name", firstName);
                    sharedPrefManager.writeString("last_name", lastName);
                    Toast.makeText(StudentMainActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(StudentMainActivity.this, "Profile Not Updated", Toast.LENGTH_SHORT).show();
                }


                // Do something with the values (e.g., update the profile)
                // .....................TODO: Query the database based and update
            }
        });

        // Add the views to the dynamic content layout
        dynamicContentLayout.addView(usernameLabel);
        dynamicContentLayout.addView(usernameEditText);
        dynamicContentLayout.addView(firstNameLabel);
        dynamicContentLayout.addView(firstNameEditText);
        dynamicContentLayout.addView(lastNameLabel);
        dynamicContentLayout.addView(lastNameEditText);
        dynamicContentLayout.addView(emailLabel);
        dynamicContentLayout.addView(emailEditText);
        dynamicContentLayout.addView(mobileLabel);
        dynamicContentLayout.addView(mobileEditText);
        dynamicContentLayout.addView(addressLabel);
        dynamicContentLayout.addView(addressEditText);
        dynamicContentLayout.addView(editButton);
    }

    private void logout(){
        Intent intent = new Intent(StudentMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private TextView createLabel(String text) {
        TextView label = new TextView(this);
        label.setText(text);
        label.setTextSize(22);
        label.setPadding(30, 0, 30, 0);
        return label;
    }

    private EditText createEditText(String initialValue) {
        EditText editText = new EditText(this);
        editText.setText(initialValue);
        editText.setTextSize(22);
        editText.setPadding(30, 0, 30, 0);
        editText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return editText;
    }

    private Button createButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextSize(22);
        button.setPadding(30, 0, 30, 0);
        return button;
    }




}
