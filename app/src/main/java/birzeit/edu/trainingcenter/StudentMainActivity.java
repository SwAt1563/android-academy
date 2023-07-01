package birzeit.edu.trainingcenter;

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
            case R.id.notification_available_course:
                // Handle notification_available_course action
                viewAvailableCourses();
                break;
            case R.id.view_courses:
                // Handle view_courses action
                viewCourseDetails();
                break;
            case R.id.enroll_in_course:
                // Handle enroll_in_course action
                enrollInCourse();
                break;
            case R.id.registration_notification:
                // Handle registration_notification action
                registrationNotification();
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
            case R.id.update_enrolled_courses_notification:
                // Handle update_enrolled_courses_notification action
                updateEnrolledCoursesNotification();
                break;
        }

        drawerLayout.closeDrawers();
        return true;
    }

    private void viewAvailableCourses() {
        // Perform logic for viewing available courses

        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();
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
            List<String> coursesList = getDummyCourses(); // Dummy data for courses list// .....................TODO: Query
            ArrayAdapter<String> coursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesList);
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

                    // .....................TODO: Query the database based on the selected course
                    // Course Name Label
                    TextView courseNameLabel = new TextView(StudentMainActivity.this);
                    courseNameLabel.setText("Course Name: " + selectedCourse);// .....................TODO: Query
                    courseNameLabel.setTypeface(courseNameLabel.getTypeface(), Typeface.BOLD);
                    courseNameLabel.setTextSize(22); // Increase font size
                    courseNameLabel.setPadding(0, 30, 0, 0); // Add top padding
                    dynamicContentLayout.addView(courseNameLabel);

                    // Course Description Label
                    TextView courseDescriptionLabel = new TextView(StudentMainActivity.this);
                    courseDescriptionLabel.setText("Course Description: Dummy Course Description");// .....................TODO: Query
                    courseDescriptionLabel.setTypeface(courseDescriptionLabel.getTypeface(), Typeface.BOLD);
                    courseDescriptionLabel.setTextSize(22); // Increase font size
                    courseDescriptionLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(courseDescriptionLabel);

                    // Number of Students Label
                    TextView numOfStudentsLabel = new TextView(StudentMainActivity.this);
                    numOfStudentsLabel.setText("Number of Students: Dummy Number of Students");// .....................TODO: Query
                    numOfStudentsLabel.setTypeface(numOfStudentsLabel.getTypeface(), Typeface.BOLD);
                    numOfStudentsLabel.setTextSize(22); // Increase font size
                    numOfStudentsLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(numOfStudentsLabel);

                    // Venue Label
                    TextView venueLabel = new TextView(StudentMainActivity.this);
                    venueLabel.setText("Venue: Dummy Venue");// .....................TODO: Query
                    venueLabel.setTypeface(venueLabel.getTypeface(), Typeface.BOLD);
                    venueLabel.setTextSize(22); // Increase font size
                    venueLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(venueLabel);

                    // Instructor Label
                    TextView instructorLabel = new TextView(StudentMainActivity.this);
                    instructorLabel.setText("Instructor: Dummy Instructor");// .....................TODO: Query
                    instructorLabel.setTypeface(instructorLabel.getTypeface(), Typeface.BOLD);
                    instructorLabel.setTextSize(22); // Increase font size
                    instructorLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(instructorLabel);

                    // Start Date Label
                    TextView startDateLabel = new TextView(StudentMainActivity.this);
                    startDateLabel.setText("Start Date: Dummy Start Date");// .....................TODO: Query
                    startDateLabel.setTypeface(startDateLabel.getTypeface(), Typeface.BOLD);
                    startDateLabel.setTextSize(22); // Increase font size
                    startDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(startDateLabel);

                    // End Date Label
                    TextView endDateLabel = new TextView(StudentMainActivity.this);
                    endDateLabel.setText("End Date: Dummy End Date");// .....................TODO: Query
                    endDateLabel.setTypeface(endDateLabel.getTypeface(), Typeface.BOLD);
                    endDateLabel.setTextSize(22); // Increase font size
                    endDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(endDateLabel);

                    // Deadline Registration Label
                    TextView deadlineLabel = new TextView(StudentMainActivity.this);
                    deadlineLabel.setText("Deadline Registration: Dummy Deadline");// .....................TODO: Query
                    deadlineLabel.setTypeface(deadlineLabel.getTypeface(), Typeface.BOLD);
                    deadlineLabel.setTextSize(22); // Increase font size
                    deadlineLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    dynamicContentLayout.addView(deadlineLabel);


                }
            });

            dynamicContentLayout.addView(DetailsButton);
        }

    // Dummy data for Courses list
    private List<String> getDummyCourses() {
        List<String> Courseslist = new ArrayList<>();
        Courseslist.add("C");
        Courseslist.add("JAVA");
        Courseslist.add("Python");
        return Courseslist;
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
        List<String> coursesList = getDummyCourses(); // Dummy data for courses list// .....................TODO: Query
        ArrayAdapter<String> coursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesList);
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


                String message = "Enrollment in the course: " + selectedCourse + "\nSent to Admin";
                Toast.makeText(StudentMainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        dynamicContentLayout.addView(Enroll);
    }


    private void registrationNotification() {
        // Perform logic for registration notification

        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();
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
        List<String> coursesList = getDummyCourses(); // Dummy data for courses list// .....................TODO: Query
        ArrayAdapter<String> coursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesList);
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

                // Course Name Label
                TextView courseNameLabel = new TextView(StudentMainActivity.this);
                courseNameLabel.setText("Course Name: " + selectedCourse);// .....................TODO: Query
                courseNameLabel.setTypeface(courseNameLabel.getTypeface(), Typeface.BOLD);
                courseNameLabel.setTextSize(22); // Increase font size
                courseNameLabel.setPadding(0, 30, 0, 0); // Add top padding
                dynamicContentLayout.addView(courseNameLabel);

                // Course Description Label
                TextView courseDescriptionLabel = new TextView(StudentMainActivity.this);
                courseDescriptionLabel.setText("Course Description: Dummy Course Description");// .....................TODO: Query
                courseDescriptionLabel.setTypeface(courseDescriptionLabel.getTypeface(), Typeface.BOLD);
                courseDescriptionLabel.setTextSize(22); // Increase font size
                courseDescriptionLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(courseDescriptionLabel);

                // Number of Students Label
                TextView numOfStudentsLabel = new TextView(StudentMainActivity.this);
                numOfStudentsLabel.setText("Number of Students: Dummy Number of Students");// .....................TODO: Query
                numOfStudentsLabel.setTypeface(numOfStudentsLabel.getTypeface(), Typeface.BOLD);
                numOfStudentsLabel.setTextSize(22); // Increase font size
                numOfStudentsLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(numOfStudentsLabel);

                // Venue Label
                TextView venueLabel = new TextView(StudentMainActivity.this);
                venueLabel.setText("Venue: Dummy Venue");// .....................TODO: Query
                venueLabel.setTypeface(venueLabel.getTypeface(), Typeface.BOLD);
                venueLabel.setTextSize(22); // Increase font size
                venueLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(venueLabel);

                // Instructor Label
                TextView instructorLabel = new TextView(StudentMainActivity.this);
                instructorLabel.setText("Instructor: Dummy Instructor");// .....................TODO: Query
                instructorLabel.setTypeface(instructorLabel.getTypeface(), Typeface.BOLD);
                instructorLabel.setTextSize(22); // Increase font size
                instructorLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(instructorLabel);

                // Start Date Label
                TextView startDateLabel = new TextView(StudentMainActivity.this);
                startDateLabel.setText("Start Date: Dummy Start Date");// .....................TODO: Query
                startDateLabel.setTypeface(startDateLabel.getTypeface(), Typeface.BOLD);
                startDateLabel.setTextSize(22); // Increase font size
                startDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(startDateLabel);

                // End Date Label
                TextView endDateLabel = new TextView(StudentMainActivity.this);
                endDateLabel.setText("End Date: Dummy End Date");// .....................TODO: Query
                endDateLabel.setTypeface(endDateLabel.getTypeface(), Typeface.BOLD);
                endDateLabel.setTextSize(22); // Increase font size
                endDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(endDateLabel);

                // Deadline Registration Label
                TextView deadlineLabel = new TextView(StudentMainActivity.this);
                deadlineLabel.setText("Deadline Registration: Dummy Deadline");// .....................TODO: Query
                deadlineLabel.setTypeface(deadlineLabel.getTypeface(), Typeface.BOLD);
                deadlineLabel.setTextSize(22); // Increase font size
                deadlineLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(deadlineLabel);


                //  Grade
                TextView GradeLabel = new TextView(StudentMainActivity.this);
                GradeLabel.setText("Grade: Dummy Grade");// .....................TODO: Query
                GradeLabel.setTypeface(GradeLabel.getTypeface(), Typeface.BOLD);
                GradeLabel.setTextSize(22); // Increase font size
                GradeLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(GradeLabel);

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
        List<String> coursesList = getDummyCourses(); // Dummy data for courses list// .....................TODO: Query
        ArrayAdapter<String> coursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesList);
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

                // Course Name Label
                TextView courseNameLabel = new TextView(StudentMainActivity.this);
                courseNameLabel.setText("Course Name: " + selectedCourse);// .....................TODO: Query
                courseNameLabel.setTypeface(courseNameLabel.getTypeface(), Typeface.BOLD);
                courseNameLabel.setTextSize(22); // Increase font size
                courseNameLabel.setPadding(0, 30, 0, 0); // Add top padding
                dynamicContentLayout.addView(courseNameLabel);

                // Course Description Label
                TextView courseDescriptionLabel = new TextView(StudentMainActivity.this);
                courseDescriptionLabel.setText("Course Description: Dummy Course Description");// .....................TODO: Query
                courseDescriptionLabel.setTypeface(courseDescriptionLabel.getTypeface(), Typeface.BOLD);
                courseDescriptionLabel.setTextSize(22); // Increase font size
                courseDescriptionLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(courseDescriptionLabel);

                // Number of Students Label
                TextView numOfStudentsLabel = new TextView(StudentMainActivity.this);
                numOfStudentsLabel.setText("Number of Students: Dummy Number of Students");// .....................TODO: Query
                numOfStudentsLabel.setTypeface(numOfStudentsLabel.getTypeface(), Typeface.BOLD);
                numOfStudentsLabel.setTextSize(22); // Increase font size
                numOfStudentsLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(numOfStudentsLabel);

                // Venue Label
                TextView venueLabel = new TextView(StudentMainActivity.this);
                venueLabel.setText("Venue: Dummy Venue");// .....................TODO: Query
                venueLabel.setTypeface(venueLabel.getTypeface(), Typeface.BOLD);
                venueLabel.setTextSize(22); // Increase font size
                venueLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(venueLabel);

                // Instructor Label
                TextView instructorLabel = new TextView(StudentMainActivity.this);
                instructorLabel.setText("Instructor: Dummy Instructor");// .....................TODO: Query
                instructorLabel.setTypeface(instructorLabel.getTypeface(), Typeface.BOLD);
                instructorLabel.setTextSize(22); // Increase font size
                instructorLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(instructorLabel);

                // Start Date Label
                TextView startDateLabel = new TextView(StudentMainActivity.this);
                startDateLabel.setText("Start Date: Dummy Start Date");// .....................TODO: Query
                startDateLabel.setTypeface(startDateLabel.getTypeface(), Typeface.BOLD);
                startDateLabel.setTextSize(22); // Increase font size
                startDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(startDateLabel);

                // End Date Label
                TextView endDateLabel = new TextView(StudentMainActivity.this);
                endDateLabel.setText("End Date: Dummy End Date");// .....................TODO: Query
                endDateLabel.setTypeface(endDateLabel.getTypeface(), Typeface.BOLD);
                endDateLabel.setTextSize(22); // Increase font size
                endDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(endDateLabel);

                // Deadline Registration Label
                TextView deadlineLabel = new TextView(StudentMainActivity.this);
                deadlineLabel.setText("Deadline Registration: Dummy Deadline");// .....................TODO: Query
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
        Student student= getDummyProfile();// .....................TODO: Query the database

        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();

        // Create the labels
        TextView nameLabel = createLabel("Name:");
        TextView mobileLabel = createLabel("Mobile Number:");
        TextView addressLabel = createLabel("Address:");

        // Create the edit texts
        EditText nameEditText = createEditText(student.getName());
        EditText mobileEditText = createEditText(student.getMobile_number()+"");
        EditText addressEditText = createEditText(student.getAddress());

        // Create the edit button
        Button editButton = createButton("Edit");

        // Set click listener for the edit button
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Read the values from the edit texts
                String name = nameEditText.getText().toString();
                String mobileNumber = mobileEditText.getText().toString();
                String address = addressEditText.getText().toString();

                // Do something with the values (e.g., update the profile)
                // .....................TODO: Query the database based and update
            }
        });

        // Add the views to the dynamic content layout
        dynamicContentLayout.addView(nameLabel);
        dynamicContentLayout.addView(nameEditText);
        dynamicContentLayout.addView(mobileLabel);
        dynamicContentLayout.addView(mobileEditText);
        dynamicContentLayout.addView(addressLabel);
        dynamicContentLayout.addView(addressEditText);
        dynamicContentLayout.addView(editButton);
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


    private Student getDummyProfile() {
        // Dummy data for getDummyProfile list
        Student student = new Student("Mohammad mualla",101,"Rammalla");
        return student;
    }
    private void updateEnrolledCoursesNotification() {
        // Perform logic for updating enrolled courses notification

        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();
    }
}
