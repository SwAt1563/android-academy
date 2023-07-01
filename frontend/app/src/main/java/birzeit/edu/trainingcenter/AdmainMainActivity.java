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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class AdmainMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private LinearLayout dynamicContentLayout;
    private List<TextView> dynamicTextViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admain_main);

        dynamicContentLayout = findViewById(R.id.dynamic_content_layout2);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //As the start main  page to search for the users:
        viewProfiles();
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
            case R.id.create_course:
                // Handle create_course action
                createCourse();
                break;
            case R.id.edit_course:
                // Handle edit_course action
                editCourse();
                break;
            case R.id.delete_course:
                // Handle delete_course action
                deleteCourse();
                break;
            case R.id.make_course_available:
                // Handle make_course_available action
                makeCourseAvailable();
                break;
            case R.id.accept_reject_registration:
                // Handle accept_reject_registration action
                acceptRejectRegistration();
                break;
            case R.id.view_profiles:
                // Handle view_profiles action
                viewProfiles();
                break;
            case R.id.view_course_history:
                // Handle view_course_history action
                viewCourseHistory();
                break;
        }

        drawerLayout.closeDrawers();
        return true;
    }

    private void createCourse() {
        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();

        // Course Title
        TextView titleTextView = new TextView(this);
        titleTextView.setText("Course Title:");
        dynamicContentLayout.addView(titleTextView);

        EditText titleEditText = new EditText(this);
        titleEditText.setHint("Enter course title");
        dynamicContentLayout.addView(titleEditText);

        // Course Description
        TextView descriptionTextView = new TextView(this);
        descriptionTextView.setText("Description:");
        dynamicContentLayout.addView(descriptionTextView);

        EditText descriptionEditText = new EditText(this);
        descriptionEditText.setHint("Enter course description");
        dynamicContentLayout.addView(descriptionEditText);

        // Course Venue
        TextView venueTextView = new TextView(this);
        venueTextView.setText("Venue:");
        dynamicContentLayout.addView(venueTextView);

        EditText venueEditText = new EditText(this);
        venueEditText.setHint("Enter course venue");
        dynamicContentLayout.addView(venueEditText);

        // Instructor
        TextView instructorTextView = new TextView(this);
        instructorTextView.setText("Instructor:");
        dynamicContentLayout.addView(instructorTextView);

        Spinner instructorSpinner = new Spinner(this);
        //TODO :.........................Make A Query for Instructors............................................................
        //TODO :Replace getDummyInstructorList()
        List<String> instructorList = getDummyInstructorList(); // Dummy data for instructor list
        ArrayAdapter<String> instructorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, instructorList);
        instructorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        instructorSpinner.setAdapter(instructorAdapter);
        dynamicContentLayout.addView(instructorSpinner);

        // Start Date
        TextView startDateTextView = new TextView(this);
        startDateTextView.setText("Start Date:");
        dynamicContentLayout.addView(startDateTextView);

        Spinner startDateSpinner = new Spinner(this);
        List<String> startDateList = getAllPossibleDates(); // Get all possible dates
        ArrayAdapter<String> startDateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, startDateList);
        startDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startDateSpinner.setAdapter(startDateAdapter);
        dynamicContentLayout.addView(startDateSpinner);

        // End Date
        TextView endDateTextView = new TextView(this);
        endDateTextView.setText("End Date:");
        dynamicContentLayout.addView(endDateTextView);

        // Set up the end date spinner with all possible dates
        Spinner endDateSpinner = new Spinner(this);
        List<String> endDateList = getAllPossibleDates();
        ArrayAdapter<String> endDateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, endDateList);
        endDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endDateSpinner.setAdapter(endDateAdapter);
        dynamicContentLayout.addView(endDateSpinner);

        // Deadline Registration
        TextView DeadlineDateTextView = new TextView(this);
        DeadlineDateTextView.setText("Deadline Registration Date:");
        dynamicContentLayout.addView(DeadlineDateTextView);


        // Set up the deadline spinner with all possible dates
        Spinner deadlineSpinner = new Spinner(this);
        List<String> deadlineList = getAllPossibleDates();
        ArrayAdapter<String> deadlineAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, deadlineList);
        deadlineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deadlineSpinner.setAdapter(deadlineAdapter);
        dynamicContentLayout.addView(deadlineSpinner);


        // Button to read the entered data
        Button readButton = new Button(this);
        readButton.setText("Create Course");
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseTitle = titleEditText.getText().toString().trim();
                String courseDescription = descriptionEditText.getText().toString().trim();
                String courseVenue = venueEditText.getText().toString().trim();
                String selectedInstructor = instructorSpinner.getSelectedItem().toString();
                String selectedStartDate = startDateSpinner.getSelectedItem().toString();
                String endDate = endDateSpinner.getSelectedItem().toString();
                String startDate = startDateSpinner.getSelectedItem().toString();
                String deadline = deadlineSpinner.getSelectedItem().toString();


                //TODO :.........................Add Course to the dataBase............................................................

            }
        });
        dynamicContentLayout.addView(readButton);
    }

    // Dummy data for instructor list
    private List<String> getDummyInstructorList() {
        List<String> instructorList = new ArrayList<>();
        instructorList.add("Mohammad Mualla");
        instructorList.add("Tareq G Zidan");
        instructorList.add("Eng:Q.A");
        return instructorList;
    }

    private List<String> getAllPossibleDates() {
        List<String> dateList = new ArrayList<>();

        // Add logic to populate the dateList with all possible dates
        // For demonstration purposes, let's assume the range is from 2023-01-01 to 2023-12-31
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        while (calendar.get(Calendar.YEAR) == 2023) {
            Date date = calendar.getTime();
            String dateString = dateFormat.format(date);
            dateList.add(dateString);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return dateList;
    }



    private void editCourse() {
// Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();

        // Courses
        TextView CoursesTextView = new TextView(this);
        CoursesTextView.setText("Courses:");
        CoursesTextView.setTypeface(CoursesTextView.getTypeface(), Typeface.BOLD);
        dynamicContentLayout.addView(CoursesTextView);

        Spinner CoursesSpinner = new Spinner(this);
        //TODO :.........................Make A Query for Courses............................................................
        //TODO :Replace getDummyCourses()
        List<String> CoursesList = getDummyCourses(); // Dummy data for Courses list
        ArrayAdapter<String> CoursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CoursesList);
        CoursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CoursesSpinner.setAdapter(CoursesAdapter);
        dynamicContentLayout.addView(CoursesSpinner);

        // Course Title
        TextView titleTextView = new TextView(this);
        titleTextView.setText("Course Title:");
        titleTextView.setTypeface(titleTextView.getTypeface(), Typeface.BOLD);
        dynamicContentLayout.addView(titleTextView);

        EditText titleEditText = new EditText(this);
        titleEditText.setHint("Enter course title");
        dynamicContentLayout.addView(titleEditText);

        // Course Description
        TextView descriptionTextView = new TextView(this);
        descriptionTextView.setText("Description:");
        descriptionTextView.setTypeface(descriptionTextView.getTypeface(), Typeface.BOLD);
        dynamicContentLayout.addView(descriptionTextView);

        EditText descriptionEditText = new EditText(this);
        descriptionEditText.setHint("Enter course description");
        dynamicContentLayout.addView(descriptionEditText);

        // Course Venue
        TextView venueTextView = new TextView(this);
        venueTextView.setText("Venue:");
        venueTextView.setTypeface(venueTextView.getTypeface(), Typeface.BOLD);
        dynamicContentLayout.addView(venueTextView);

        EditText venueEditText = new EditText(this);
        venueEditText.setHint("Enter course venue");
        dynamicContentLayout.addView(venueEditText);

        // Instructor
        TextView instructorTextView = new TextView(this);
        instructorTextView.setText("Instructor:");
        instructorTextView.setTypeface(instructorTextView.getTypeface(), Typeface.BOLD);
        dynamicContentLayout.addView(instructorTextView);

        Spinner instructorSpinner = new Spinner(this);
        //TODO :.........................Make A Query for Instructors............................................................
        //TODO :Replace getDummyInstructorList()
        List<String> instructorList = getDummyInstructorList(); // Dummy data for instructor list
        ArrayAdapter<String> instructorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, instructorList);
        instructorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        instructorSpinner.setAdapter(instructorAdapter);
        dynamicContentLayout.addView(instructorSpinner);

        // Start Date
        TextView startDateTextView = new TextView(this);
        startDateTextView.setText("Start Date:");
        startDateTextView.setTypeface(startDateTextView.getTypeface(), Typeface.BOLD);
        dynamicContentLayout.addView(startDateTextView);

        Spinner startDateSpinner = new Spinner(this);
        List<String> startDateList = getAllPossibleDates(); // Get all possible dates
        ArrayAdapter<String> startDateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, startDateList);
        startDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startDateSpinner.setAdapter(startDateAdapter);
        dynamicContentLayout.addView(startDateSpinner);

        // End Date
        TextView endDateTextView = new TextView(this);
        endDateTextView.setText("End Date:");
        endDateTextView.setTypeface(endDateTextView.getTypeface(), Typeface.BOLD);
        dynamicContentLayout.addView(endDateTextView);

        // Set up the end date spinner with all possible dates
        Spinner endDateSpinner = new Spinner(this);
        List<String> endDateList = getAllPossibleDates();
        ArrayAdapter<String> endDateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, endDateList);
        endDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endDateSpinner.setAdapter(endDateAdapter);
        dynamicContentLayout.addView(endDateSpinner);

        // Deadline Registration
        TextView DeadlineDateTextView = new TextView(this);
        DeadlineDateTextView.setText("Deadline Registration Date:");
        DeadlineDateTextView.setTypeface(DeadlineDateTextView.getTypeface(), Typeface.BOLD);
        dynamicContentLayout.addView(DeadlineDateTextView);


        // Set up the deadline spinner with all possible dates
        Spinner deadlineSpinner = new Spinner(this);
        List<String> deadlineList = getAllPossibleDates();
        ArrayAdapter<String> deadlineAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, deadlineList);
        deadlineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deadlineSpinner.setAdapter(deadlineAdapter);
        dynamicContentLayout.addView(deadlineSpinner);


        // Button to read the entered data
        Button readButton = new Button(this);
        readButton.setText("Edit Course");
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseTitle = titleEditText.getText().toString().trim();
                String courseDescription = descriptionEditText.getText().toString().trim();
                String courseVenue = venueEditText.getText().toString().trim();
                String selectedInstructor = instructorSpinner.getSelectedItem().toString();
                String selectedStartDate = startDateSpinner.getSelectedItem().toString();
                String endDate = endDateSpinner.getSelectedItem().toString();
                String startDate = startDateSpinner.getSelectedItem().toString();
                String deadline = deadlineSpinner.getSelectedItem().toString();
                String selectedCourses = CoursesSpinner.getSelectedItem().toString();



                //TODO :.........................Edit Course in the dataBase............................................................

            }
        });
        dynamicContentLayout.addView(readButton);
    }

    // Dummy data for Courses list
    private List<String> getDummyCourses() {
        List<String> Courseslist = new ArrayList<>();
        Courseslist.add("C");
        Courseslist.add("JAVA");
        Courseslist.add("Python");
        return Courseslist;
    }

    private void deleteCourse() {
       // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();
// Courses
        TextView CoursesTextView = new TextView(this);
        CoursesTextView.setText("Courses:");
        CoursesTextView.setTypeface(CoursesTextView.getTypeface(), Typeface.BOLD);

        dynamicContentLayout.addView(CoursesTextView);

        Spinner CoursesSpinner = new Spinner(this);
        //TODO :.........................Make A Query for Courses............................................................
        //TODO :Replace getDummyCourses()
        List<String> CoursesList = getDummyCourses(); // Dummy data for Courses list
        ArrayAdapter<String> CoursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CoursesList);
        CoursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CoursesSpinner.setAdapter(CoursesAdapter);
        dynamicContentLayout.addView(CoursesSpinner);

        // Button to read the entered data
        Button readButton = new Button(this);
        readButton.setText("Delete Course");
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String selectedCourses = CoursesSpinner.getSelectedItem().toString();

                //TODO :.........................delete Course in the dataBase............................................................

            }
        });
        dynamicContentLayout.addView(readButton);

    }

    private void makeCourseAvailable() {
        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();
// Courses
        TextView CoursesTextView = new TextView(this);
        CoursesTextView.setText("Courses:");
        CoursesTextView.setTypeface(CoursesTextView.getTypeface(), Typeface.BOLD);

        dynamicContentLayout.addView(CoursesTextView);

        Spinner CoursesSpinner = new Spinner(this);
        //TODO :.........................Make A Query for Courses............................................................
        //TODO :Replace getDummyCourses()
        List<String> CoursesList = getDummyCourses(); // Dummy data for Courses list
        ArrayAdapter<String> CoursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CoursesList);
        CoursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CoursesSpinner.setAdapter(CoursesAdapter);
        dynamicContentLayout.addView(CoursesSpinner);

        // Button to read the entered data
        Button readButton = new Button(this);
        readButton.setText("Make Course Available for Registration");
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCourses = CoursesSpinner.getSelectedItem().toString();

                //TODO :.........................Make Course Available for Registration in the dataBase............................................................

            }
        });
        dynamicContentLayout.addView(readButton);

    }

    private void acceptRejectRegistration() {
        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();
        ListView RegistrationListView = new ListView(this);

        //Dummy list
        //TODO : .............fetch the Registration from the database...............................................................................................
        List<String> RegistrationList = Arrays.asList("Mohammad: C", "Ali: Python", "SWAT: Java", "QUTAYIBA: AI"); // Replace with your actual course list

        //CourseAdapter: We can use this class for any list of cheacke boxes
        CourseAdapter RegistrationListAdapter = new CourseAdapter(this, RegistrationList);
        RegistrationListView.setAdapter(RegistrationListAdapter);
        dynamicContentLayout.addView(RegistrationListView);


        // Button to Accept the entered data
        Button readButton = new Button(this);
        readButton.setText("Accept");
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To get the checked boxes for the Accept Registration
                Set<String> AcceptRegistration = RegistrationListAdapter.getSelectedCourses();
                for (String Accept : AcceptRegistration) {

                    //TODO :.........................add Accepted Registration to the dataBase............................................................
                }

                // Clear the dynamic content layout
                dynamicContentLayout.removeAllViews();

                //New view
                acceptRejectRegistration();

            }
        });
        dynamicContentLayout.addView(readButton);




        // Button to Reject the entered data
        Button readButton2 = new Button(this);
        readButton2.setText("Reject");
        readButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To get the checked boxes for the Reject Registration
                Set<String> RejectRegistration = RegistrationListAdapter.getSelectedCourses();
                for (String Reject : RejectRegistration) {

                    //TODO :.........................delete Rejected Registration from the dataBase............................................................
                }

                // Clear the dynamic content layout
                dynamicContentLayout.removeAllViews();

                //New view
                acceptRejectRegistration();

            }
        });
        dynamicContentLayout.addView(readButton2);




    }

    private void viewProfiles() {
        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();

        // Enter ID label
        TextView idLabel = new TextView(this);
        idLabel.setText("Enter ID:");
        idLabel.setTypeface(idLabel.getTypeface(), Typeface.BOLD);
        dynamicContentLayout.addView(idLabel);

        // ID EditText
        EditText idEditText = new EditText(this);
        dynamicContentLayout.addView(idEditText);

        // View Profile button
        Button viewProfileButton = new Button(this);
        viewProfileButton.setText("View User Profile");
        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredId = idEditText.getText().toString().trim();

                //............................... TODO: Query the database based on the entered ID and retrieve the profile data

                // Clear the dynamic content layout
                dynamicContentLayout.removeAllViews();

                // Create labels for profile information
                TextView nameLabel = new TextView(AdmainMainActivity.this);
                nameLabel.setText("Name: Dummy Name");//............................... TODO: Query
                nameLabel.setTypeface(nameLabel.getTypeface(), Typeface.BOLD);
                nameLabel.setTextSize(22); // Increase font size
                nameLabel.setPadding(0, 30, 0, 0); // Add top padding
                LinearLayout.LayoutParams nameLayoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                nameLayoutParams.setMargins(0, 0, 0, 30); // Add bottom margin
                nameLabel.setLayoutParams(nameLayoutParams);
                dynamicContentLayout.addView(nameLabel);

                TextView mobileLabel = new TextView(AdmainMainActivity.this);
                mobileLabel.setText("Mobile Number: Dummy Mobile Number");//............................... TODO: Query
                mobileLabel.setTypeface(mobileLabel.getTypeface(), Typeface.BOLD);
                mobileLabel.setTextSize(22); // Increase font size
                mobileLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                LinearLayout.LayoutParams mobileLayoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mobileLayoutParams.setMargins(0, 0, 0, 30); // Add bottom margin
                mobileLabel.setLayoutParams(mobileLayoutParams);
                dynamicContentLayout.addView(mobileLabel);

                TextView addressLabel = new TextView(AdmainMainActivity.this);
                addressLabel.setText("Address: Dummy Address");//............................... TODO: Query
                addressLabel.setTypeface(addressLabel.getTypeface(), Typeface.BOLD);
                addressLabel.setTextSize(22); // Increase font size
                addressLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                LinearLayout.LayoutParams addressLayoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                addressLayoutParams.setMargins(0, 0, 0, 30); // Add bottom margin
                addressLabel.setLayoutParams(addressLayoutParams);
                dynamicContentLayout.addView(addressLabel);

                // Check if the user is a student or not//............................... TODO: Query
                boolean isStudent = true; // Replace with logic to determine if the user is a student

                if (!isStudent) {
                    TextView specializationLabel = new TextView(AdmainMainActivity.this);
                    specializationLabel.setText("Specialization: Dummy Specialization");//............................... TODO: Query
                    specializationLabel.setTypeface(specializationLabel.getTypeface(), Typeface.BOLD);
                    specializationLabel.setTextSize(22); // Increase font size
                    specializationLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    LinearLayout.LayoutParams specializationLayoutParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    specializationLayoutParams.setMargins(0, 0, 0, 20); // Add bottom margin
                    specializationLabel.setLayoutParams(specializationLayoutParams);
                    dynamicContentLayout.addView(specializationLabel);

                    TextView degreeLabel = new TextView(AdmainMainActivity.this);
                    degreeLabel.setText("Degree: Dummy Degree");//............................... TODO: Query
                    degreeLabel.setTypeface(degreeLabel.getTypeface(), Typeface.BOLD);
                    degreeLabel.setTextSize(22); // Increase font size
                    degreeLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    LinearLayout.LayoutParams degreeLayoutParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    degreeLayoutParams.setMargins(0, 0, 0, 30); // Add bottom margin
                    degreeLabel.setLayoutParams(degreeLayoutParams);
                    dynamicContentLayout.addView(degreeLabel);
                }

                // ....................................TODO: Populate the labels with actual data retrieved from the database
            }
        });

        dynamicContentLayout.addView(viewProfileButton);
    }


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
                TextView courseNameLabel = new TextView(AdmainMainActivity.this);
                courseNameLabel.setText("Course Name: " + selectedCourse);// .....................TODO: Query
                courseNameLabel.setTypeface(courseNameLabel.getTypeface(), Typeface.BOLD);
                courseNameLabel.setTextSize(22); // Increase font size
                courseNameLabel.setPadding(0, 30, 0, 0); // Add top padding
                dynamicContentLayout.addView(courseNameLabel);

                // Course Description Label
                TextView courseDescriptionLabel = new TextView(AdmainMainActivity.this);
                courseDescriptionLabel.setText("Course Description: Dummy Course Description");// .....................TODO: Query
                courseDescriptionLabel.setTypeface(courseDescriptionLabel.getTypeface(), Typeface.BOLD);
                courseDescriptionLabel.setTextSize(22); // Increase font size
                courseDescriptionLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(courseDescriptionLabel);

                // Number of Students Label
                TextView numOfStudentsLabel = new TextView(AdmainMainActivity.this);
                numOfStudentsLabel.setText("Number of Students: Dummy Number of Students");// .....................TODO: Query
                numOfStudentsLabel.setTypeface(numOfStudentsLabel.getTypeface(), Typeface.BOLD);
                numOfStudentsLabel.setTextSize(22); // Increase font size
                numOfStudentsLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(numOfStudentsLabel);

                // Venue Label
                TextView venueLabel = new TextView(AdmainMainActivity.this);
                venueLabel.setText("Venue: Dummy Venue");// .....................TODO: Query
                venueLabel.setTypeface(venueLabel.getTypeface(), Typeface.BOLD);
                venueLabel.setTextSize(22); // Increase font size
                venueLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(venueLabel);

                // Instructor Label
                TextView instructorLabel = new TextView(AdmainMainActivity.this);
                instructorLabel.setText("Instructor: Dummy Instructor");// .....................TODO: Query
                instructorLabel.setTypeface(instructorLabel.getTypeface(), Typeface.BOLD);
                instructorLabel.setTextSize(22); // Increase font size
                instructorLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(instructorLabel);

                // Start Date Label
                TextView startDateLabel = new TextView(AdmainMainActivity.this);
                startDateLabel.setText("Start Date: Dummy Start Date");// .....................TODO: Query
                startDateLabel.setTypeface(startDateLabel.getTypeface(), Typeface.BOLD);
                startDateLabel.setTextSize(22); // Increase font size
                startDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(startDateLabel);

                // End Date Label
                TextView endDateLabel = new TextView(AdmainMainActivity.this);
                endDateLabel.setText("End Date: Dummy End Date");// .....................TODO: Query
                endDateLabel.setTypeface(endDateLabel.getTypeface(), Typeface.BOLD);
                endDateLabel.setTextSize(22); // Increase font size
                endDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(endDateLabel);

                // Deadline Registration Label
                TextView deadlineLabel = new TextView(AdmainMainActivity.this);
                deadlineLabel.setText("Deadline Registration: Dummy Deadline");// .....................TODO: Query
                deadlineLabel.setTypeface(deadlineLabel.getTypeface(), Typeface.BOLD);
                deadlineLabel.setTextSize(22); // Increase font size
                deadlineLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(deadlineLabel);
            }
        });

        dynamicContentLayout.addView(showHistoryButton);
    }

}
