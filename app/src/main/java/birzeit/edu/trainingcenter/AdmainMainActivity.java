package birzeit.edu.trainingcenter;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        // Perform logic for editing a course
    }

    private void deleteCourse() {
        // Perform logic for deleting a course
    }

    private void makeCourseAvailable() {
        // Perform logic for making a course available
    }

    private void acceptRejectRegistration() {
        // Perform logic for accepting or rejecting a registration
    }

    private void viewProfiles() {
        // Perform logic for viewing profiles
    }

    private void viewCourseHistory() {
        // Perform logic for viewing the course history
    }
}
