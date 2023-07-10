package birzeit.edu.trainingcenter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

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

            case R.id.logout3:
                // Handle create_course action
                logout();
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
        descriptionTextView.setText("Topics:");
        dynamicContentLayout.addView(descriptionTextView);

        EditText descriptionEditText = new EditText(this);
        descriptionEditText.setHint("Enter course Topics");
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

        List<String> instructorList = API.allInstructorsUsernames(); // Dummy data for instructor list
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


        // Prerequisites


        // label for prerequisites
        TextView prerequisitesTextView = new TextView(this);
        prerequisitesTextView.setText("Prerequisites:");
        dynamicContentLayout.addView(prerequisitesTextView);

        List<Course> allCourses = API.getCourses();
        List<String> allCoursesTitles = new ArrayList<>();
        for (Course course : allCourses) {
            allCoursesTitles.add(course.getTitle());
        }

        ListView prerequisitesListView = new ListView(this);

        //CourseAdapter: We can use this class for any list of cheacke boxes
        CourseAdapter prerequisitesListAdapter = new CourseAdapter(this, allCoursesTitles);
        prerequisitesListView.setAdapter(prerequisitesListAdapter);
        dynamicContentLayout.addView(prerequisitesListView);








        // Button to read the entered data
        Button readButton = new Button(this);
        readButton.setText("Create Course");
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseTitle = titleEditText.getText().toString().trim();
                String courseTopics = descriptionEditText.getText().toString().trim();
                String courseVenue = venueEditText.getText().toString().trim();
                String selectedInstructor = instructorSpinner.getSelectedItem().toString();
                String endDate = endDateSpinner.getSelectedItem().toString();
                String startDate = startDateSpinner.getSelectedItem().toString();
                Set<String> AcceptCourses = prerequisitesListAdapter.getSelectedCourses();
                List<String> prerequisitesList = new ArrayList<>(AcceptCourses);

                Course course = new Course(
                        courseTitle,
                        courseTopics,
                        selectedInstructor,
                        courseVenue,
                        prerequisitesList,
                        startDate,
                        endDate,
                        0, // trainees_count (set as 0 initially)
                        new ArrayList<>(), // trainees (empty list initially)
                        false, // is_finish (set as false initially)
                        false // is_available (set as false initially)
                );


                Course response = API.addCourse(course);

                if (response == null) {
                    Toast.makeText(getApplicationContext(), "Failed to create course", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(), "Course created successfully", Toast.LENGTH_SHORT).show();
                }





                //TODO :.........................Add Course to the dataBase............................................................

                createCourse();

            }
        });
        dynamicContentLayout.addView(readButton);
    }

    // Dummy data for instructor list


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
        List<Course> CoursesList = API.getCourses();
        List<String> CoursesTitles = new ArrayList<>();
        for (Course course : CoursesList) {
            CoursesTitles.add(course.getTitle());
        }
        ArrayAdapter<String> CoursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CoursesTitles);
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
        List<String> instructorList = API.allInstructorsUsernames(); // Dummy data for instructor list
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

        // Registration Available
        TextView availableTextView = new TextView(this);
        availableTextView.setText("Registration Available:");
        availableTextView.setTypeface(availableTextView.getTypeface(), Typeface.BOLD);
        dynamicContentLayout.addView(availableTextView);

        // isFinished
        TextView isFinishedTextView = new TextView(this);
        isFinishedTextView.setText("isFinished:");
        isFinishedTextView.setTypeface(isFinishedTextView.getTypeface(), Typeface.BOLD);
        dynamicContentLayout.addView(isFinishedTextView);


        // prerequisites spinner
        TextView prerequisitesTextView = new TextView(this);
        prerequisitesTextView.setText("Prerequisites:");
        prerequisitesTextView.setTypeface(prerequisitesTextView.getTypeface(), Typeface.BOLD);
        dynamicContentLayout.addView(prerequisitesTextView);

        Spinner prerequisitesSpinner = new Spinner(this);
        //TODO :.........................Make A Query for prerequisites............................................................
        //TODO :Replace getDummyCourses()
        List<Course> prerequisitesList = API.getCourses();
        List<String> prerequisitesTitles = new ArrayList<>();
        for (Course course : prerequisitesList) {
            prerequisitesTitles.add(course.getTitle());
        }
        ArrayAdapter<String> prerequisitesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, prerequisitesTitles);
        prerequisitesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prerequisitesSpinner.setAdapter(prerequisitesAdapter);
        dynamicContentLayout.addView(prerequisitesSpinner);






        CoursesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // selected courses
                String selectedCourses = CoursesSpinner.getSelectedItem().toString();
                //TODO :.........................Make A Query for Courses............................................................

                Course course = API.getCourse(selectedCourses);
                titleEditText.setText(course.getTitle());
                descriptionEditText.setText(course.getTopics());
                venueEditText.setText(course.getVenue());
                instructorSpinner.setSelection(instructorAdapter.getPosition(course.getInstructor()));
                startDateSpinner.setSelection(startDateAdapter.getPosition(course.getStart_date()));
                endDateSpinner.setSelection(endDateAdapter.getPosition(course.getEnd_date()));
                availableTextView.setText(String.valueOf(course.isIs_available()));
                isFinishedTextView.setText(String.valueOf(course.isIs_finish()));

                List<String> prerequisites = course.getPrerequisites();
                List<Integer> selectedPrerequisiteIndices = new ArrayList<>();

                // Iterate through the prerequisites list
                for (String prerequisite : prerequisites) {
                    for (int i = 0; i < prerequisitesList.size(); i++) {
                        if (prerequisitesList.get(i).equals(prerequisite)) {
                            selectedPrerequisiteIndices.add(i);
                            break;
                        }
                    }
                }

                // Set the selected prerequisites in the spinner
                for (int index : selectedPrerequisiteIndices) {
                    prerequisitesSpinner.setSelection(index);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        // Button to read the entered data
        Button readButton = new Button(this);
        readButton.setText("Edit Course");
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseTitle = titleEditText.getText().toString().trim();
                String courseTopics= descriptionEditText.getText().toString().trim();
                String courseVenue = venueEditText.getText().toString().trim();
                String selectedInstructor = instructorSpinner.getSelectedItem().toString();
                String endDate = endDateSpinner.getSelectedItem().toString();
                String startDate = startDateSpinner.getSelectedItem().toString();
                Boolean registrationAvailable = Boolean.valueOf(availableTextView.getText().toString());
                Boolean isFinished = Boolean.valueOf(isFinishedTextView.getText().toString());
                String selectedCourse = CoursesSpinner.getSelectedItem().toString();

                List<String> selectedPrerequisites = new ArrayList<>();
                int selectedPosition = prerequisitesSpinner.getSelectedItemPosition();

                if (selectedPosition != Spinner.INVALID_POSITION) {
                    String selectedPrerequisite = prerequisitesSpinner.getItemAtPosition(selectedPosition).toString();
                    selectedPrerequisites.add(selectedPrerequisite);
                }

                Course oldCourse = API.getCourse(selectedCourse);

                Course course = new Course(
                        courseTitle,
                        courseTopics,
                        selectedInstructor,
                        courseVenue,
                        selectedPrerequisites,
                        startDate,
                        endDate,
                        oldCourse.getTrainees_count(), // trainees_count (set as 0 initially)
                        oldCourse.getTrainees(), // trainees (empty list initially)
                        isFinished, // is_finish (set as false initially)
                        registrationAvailable // is_available (set as false initially)
                );


                Boolean done = API.updateCourse(selectedCourse, course);

                if (done) {
                    Toast.makeText(getApplicationContext(), "Course Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Course Update Failed", Toast.LENGTH_SHORT).show();
                }

                editCourse();




                //TODO :.........................Edit Course in the dataBase............................................................

            }
        });
        dynamicContentLayout.addView(readButton);
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

        List<Course> coursesList = API.getCourses();
        List<String> coursesTitles = new ArrayList<>();
        for (Course course : coursesList) {
            coursesTitles.add(course.getTitle());
        }
        ArrayAdapter<String> CoursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesTitles);
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

                Boolean done = API.deleteCourse(selectedCourses);

                if (done) {
                    Toast.makeText(getApplicationContext(), "Course Deleted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Course Not Deleted", Toast.LENGTH_SHORT).show();
                }

                deleteCourse();

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
        List<Course> coursesList = API.getCourses_not_available();
        List<String> coursesTitles = new ArrayList<>();
        for (Course course : coursesList) {
            coursesTitles.add(course.getTitle());
        }
        ArrayAdapter<String> CoursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesTitles);
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
                for (Course course : coursesList) {
                    if(course.getTitle().equals(selectedCourses)){
                        course.setIs_available(true);
                        Boolean done = API.updateCourse(course.getTitle(), course);

                        if (done) {
                            Toast.makeText(getApplicationContext(), "Course Made Available Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Course Not Made Available", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }

                makeCourseAvailable();

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
        List<String> RegistrationList = API.traineeEnrollmentList();


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
                    String[] parts = Accept.split(",");
                    String courseName = parts[0];
                    String traineeName = parts[1];

                    Boolean done = API.ownerEnroll(traineeName, courseName, "Approved");

                    if (done) {
                        Toast.makeText(getApplicationContext(), "Registration Accepted Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Registration Not Accepted", Toast.LENGTH_SHORT).show();
                    }

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
                    String[] parts = Reject.split(",");
                    String courseName = parts[0];
                    String traineeName = parts[1];

                    Boolean done = API.ownerEnroll(traineeName, courseName, "Rejected");

                    if (done) {
                        Toast.makeText(getApplicationContext(), "Registration Rejected Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Registration Not Rejected", Toast.LENGTH_SHORT).show();
                    }

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
        idLabel.setText("Enter Username:");
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
                String enteredUsername = idEditText.getText().toString().trim();

                //............................... TODO: Query the database based on the entered ID and retrieve the profile data

                String user_type = API.getUserType(enteredUsername);

                JSONObject profileData = null;

                if (user_type.equals("owner")) {
                    profileData = API.getOwnerProfile(enteredUsername);
                } else if (user_type.equals("instructor")) {
                    profileData = API.getInstructorProfile(enteredUsername);
                } else if (user_type.equals("trainee")) {
                    profileData = API.getTraineeProfile(enteredUsername);
                }else {
                    Toast.makeText(getApplicationContext(), "User Not Found", Toast.LENGTH_SHORT).show();
                    viewProfiles();
                }

                String name, mobile, address;

                try {
                    name = profileData.getString("first_name") + "_" + profileData.getString("last_name");
                    mobile = profileData.getString("phone");
                    address = profileData.getString("address");

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                // Clear the dynamic content layout
                dynamicContentLayout.removeAllViews();

                // Create labels for profile information
                TextView nameLabel = new TextView(AdmainMainActivity.this);
                nameLabel.setText("Name: " + name);//............................... TODO: Query
                nameLabel.setTypeface(nameLabel.getTypeface(), Typeface.BOLD);
                nameLabel.setTextSize(22); // Increase font size
                nameLabel.setPadding(0, 30, 0, 0); // Add top padding
                LinearLayout.LayoutParams nameLayoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                nameLayoutParams.setMargins(0, 0, 0, 30); // Add bottom margin
                nameLabel.setLayoutParams(nameLayoutParams);
                dynamicContentLayout.addView(nameLabel);

                TextView mobileLabel = new TextView(AdmainMainActivity.this);
                mobileLabel.setText("Mobile Number: " + mobile);//............................... TODO: Query
                mobileLabel.setTypeface(mobileLabel.getTypeface(), Typeface.BOLD);
                mobileLabel.setTextSize(22); // Increase font size
                mobileLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                LinearLayout.LayoutParams mobileLayoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mobileLayoutParams.setMargins(0, 0, 0, 30); // Add bottom margin
                mobileLabel.setLayoutParams(mobileLayoutParams);
                dynamicContentLayout.addView(mobileLabel);

                TextView addressLabel = new TextView(AdmainMainActivity.this);
                addressLabel.setText("Address: " + address);//............................... TODO: Query
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
                    String specialization, degree;

                    try {
                        specialization = profileData.getString("specialization");
                        degree = profileData.getString("degree");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    TextView specializationLabel = new TextView(AdmainMainActivity.this);
                    specializationLabel.setText("Specialization: " + specialization);//............................... TODO: Query
                    specializationLabel.setTypeface(specializationLabel.getTypeface(), Typeface.BOLD);
                    specializationLabel.setTextSize(22); // Increase font size
                    specializationLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                    LinearLayout.LayoutParams specializationLayoutParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    specializationLayoutParams.setMargins(0, 0, 0, 20); // Add bottom margin
                    specializationLabel.setLayoutParams(specializationLayoutParams);
                    dynamicContentLayout.addView(specializationLabel);

                    TextView degreeLabel = new TextView(AdmainMainActivity.this);
                    degreeLabel.setText("Degree: " + degree);//............................... TODO: Query
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

    private void logout(){
        Intent intent = new Intent(AdmainMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
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
        List<Course> coursesList = API.getCourses();
        List<String> coursesTitles = new ArrayList<>();
        for (Course course : coursesList) {
            coursesTitles.add(course.getTitle());
        }
        ArrayAdapter<String> coursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesTitles);
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

                Course course = API.getCourse(selectedCourse);

                // Course Name Label
                TextView courseNameLabel = new TextView(AdmainMainActivity.this);
                courseNameLabel.setText("Course Title: " + course.getTitle());// .....................TODO: Query
                courseNameLabel.setTypeface(courseNameLabel.getTypeface(), Typeface.BOLD);
                courseNameLabel.setTextSize(22); // Increase font size
                courseNameLabel.setPadding(0, 30, 0, 0); // Add top padding
                dynamicContentLayout.addView(courseNameLabel);

                // Course Description Label
                TextView courseDescriptionLabel = new TextView(AdmainMainActivity.this);
                courseDescriptionLabel.setText("Course Topics: " + course.getTopics());// .....................TODO: Query
                courseDescriptionLabel.setTypeface(courseDescriptionLabel.getTypeface(), Typeface.BOLD);
                courseDescriptionLabel.setTextSize(22); // Increase font size
                courseDescriptionLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(courseDescriptionLabel);

                // Number of Students Label
                TextView numOfStudentsLabel = new TextView(AdmainMainActivity.this);
                numOfStudentsLabel.setText("Number of Students: " + String.valueOf(course.getTrainees_count()));// .....................TODO: Query
                numOfStudentsLabel.setTypeface(numOfStudentsLabel.getTypeface(), Typeface.BOLD);
                numOfStudentsLabel.setTextSize(22); // Increase font size
                numOfStudentsLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(numOfStudentsLabel);

                // Venue Label
                TextView venueLabel = new TextView(AdmainMainActivity.this);
                venueLabel.setText("Venue: " + course.getVenue());// .....................TODO: Query
                venueLabel.setTypeface(venueLabel.getTypeface(), Typeface.BOLD);
                venueLabel.setTextSize(22); // Increase font size
                venueLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(venueLabel);

                // Instructor Label
                TextView instructorLabel = new TextView(AdmainMainActivity.this);
                instructorLabel.setText("Instructor: " + course.getInstructor());// .....................TODO: Query
                instructorLabel.setTypeface(instructorLabel.getTypeface(), Typeface.BOLD);
                instructorLabel.setTextSize(22); // Increase font size
                instructorLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(instructorLabel);

                // Start Date Label
                TextView startDateLabel = new TextView(AdmainMainActivity.this);
                startDateLabel.setText("Start Date: " + course.getStart_date());// .....................TODO: Query
                startDateLabel.setTypeface(startDateLabel.getTypeface(), Typeface.BOLD);
                startDateLabel.setTextSize(22); // Increase font size
                startDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(startDateLabel);

                // End Date Label
                TextView endDateLabel = new TextView(AdmainMainActivity.this);
                endDateLabel.setText("End Date: " + course.getEnd_date());// .....................TODO: Query
                endDateLabel.setTypeface(endDateLabel.getTypeface(), Typeface.BOLD);
                endDateLabel.setTextSize(22); // Increase font size
                endDateLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(endDateLabel);

                // Deadline Registration Label
                TextView deadlineLabel = new TextView(AdmainMainActivity.this);
                deadlineLabel.setText("Registration Available: " + String.valueOf(course.isIs_available()));// .....................TODO: Query
                deadlineLabel.setTypeface(deadlineLabel.getTypeface(), Typeface.BOLD);
                deadlineLabel.setTextSize(22); // Increase font size
                deadlineLabel.setPadding(0, 0, 0, 30); // Add bottom padding
                dynamicContentLayout.addView(deadlineLabel);
            }
        });

        dynamicContentLayout.addView(showHistoryButton);
    }

}
