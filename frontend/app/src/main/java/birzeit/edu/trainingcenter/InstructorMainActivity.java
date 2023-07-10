package birzeit.edu.trainingcenter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InstructorMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private LinearLayout dynamicContentLayout;
    private List<TextView> dynamicTextViews;
    Button editButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_main);
        dynamicContentLayout = findViewById(R.id.dynamic_content_layout);
        dynamicTextViews = new ArrayList<>();

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        /// Start with profile information
        showProfile();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.instructor_notifications:
                // Handle courses taught action
                viewInstructorNotifications();
                break;
            case R.id.nav_courses_taught:
                // Handle courses taught action
                showCoursesTaught();
                break;
            case R.id.nav_previously_taught:
                // Handle previously taught courses action
                showPreviouslyTaughtCourses();
                break;
            case R.id.nav_students_list:
                // Handle students list action
                showStudentsList();
                break;
            case R.id.nav_profile:
                // Handle profile action
                showProfile();
                break;
            case R.id.logout1:
                // Handle create_course action
                logout();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void viewInstructorNotifications() {
        // Perform logic for viewing available courses

        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(InstructorMainActivity.this);
        String instructor_username = sharedPrefManager.readString("username", "");

        List<String> notificationList = API.getUserNotifications(instructor_username);//-------TODO

        // Clear the dynamic content layout
        dynamicContentLayout.removeAllViews();

        // Create TextViews for students list
        for (String notification : notificationList) {


            // Course Name Label
            TextView notificationLabel = new TextView(InstructorMainActivity.this);
            notificationLabel.setText(notification);// .....................TODO: Query
            notificationLabel.setTypeface(notificationLabel.getTypeface(), Typeface.BOLD);
            notificationLabel.setTextSize(22); // Increase font size
            notificationLabel.setPadding(0, 30, 0, 0); // Add top padding
            dynamicContentLayout.addView(notificationLabel);
            dynamicTextViews.add(notificationLabel);

        }



    }

    //      .................................................Update Query.............................
    private void showCoursesTaught() {
        // Perform query to fetch courses taught by the instructor
        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(InstructorMainActivity.this);
        String instructor_username = sharedPrefManager.readString("username", "");
        List<Course> courses = API.getInstructorCurrentCourses(instructor_username);  //TODO......Query


        // Clear existing TextViews
        clearDynamicTextViews();

        // Create TextViews for courses taught
        for (Course course : courses) {
            TextView textView = createTextView(course.getTitle());
            dynamicContentLayout.addView(textView);
            dynamicTextViews.add(textView);
        }
    }

    //      .................................................Update Query.............................
    private void showPreviouslyTaughtCourses() {
        // Perform query to fetch previously taught courses by the instructor
        // Perform query to fetch courses taught by the instructor
        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(InstructorMainActivity.this);
        String instructor_username = sharedPrefManager.readString("username", "");
        List<Course> previouslyTaughtCourses = API.getInstructorPreviousCourses(instructor_username); //TODO......Query

        // Clear existing TextViews
        clearDynamicTextViews();

        // Create TextViews for previously taught courses
        for (Course course : previouslyTaughtCourses) {
            TextView textView = createTextView(course.getTitle());
            dynamicContentLayout.addView(textView);
            dynamicTextViews.add(textView);
        }
    }

    //      .................................................Update Query.............................

    private void showStudentsList() {


        // Clear existing TextViews
        clearDynamicTextViews();



        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(InstructorMainActivity.this);
        String instructor_username = sharedPrefManager.readString("username", "");


        // Spinner for Courses
        Spinner coursesSpinner = new Spinner(this);
        List<Course> coursesList = API.getInstructorCurrentCourses(instructor_username); //TODO......Query
        List<String> coursesTitles = new ArrayList<>();
        for (Course course : coursesList) {
            coursesTitles.add(course.getTitle());
        }
        ArrayAdapter<String> coursesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursesTitles);
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesSpinner.setAdapter(coursesAdapter);
        dynamicContentLayout.addView(coursesSpinner);

        // Show Students Button
        Button showStudentsButton = new Button(this);
        showStudentsButton.setText("Show Students");
        //dynamicContentLayout.addView(showHistoryButton);
        showStudentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the dynamic content layout
                dynamicContentLayout.removeAllViews();

                String selectedCourse = coursesSpinner.getSelectedItem().toString();

                // .....................TODO: Query the database based on the selected course and retrieve the course history data

                Course course = API.getCourse(selectedCourse); //TODO......Query
                List<String> students = course.getTrainees();


                // Create TextViews for students list
                for (String student : students) {
                    TextView textView = createTextView(student);
                    dynamicContentLayout.addView(textView);
                    dynamicTextViews.add(textView);
                }


            }
        });

        dynamicContentLayout.addView(showStudentsButton);
    }



    //      .................................................Update Query.............................

    private void showProfile() {
        // Fetch the instructor's profile information
        // Perform query to fetch courses taught by the instructor
        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(InstructorMainActivity.this);
        String instructor_username = sharedPrefManager.readString("username", "");

        JSONObject instructor = API.getInstructorProfile(instructor_username); //TODO......Query
        String username;
        String firstName;
        String lastName;
        String mobile_number;
        String address;
        String specialization;
        String degree;
        String email;

        try {

            username = instructor.getString("username");
            firstName = instructor.getString("firstName") ;
            lastName = instructor.getString("lastName");
            mobile_number = instructor.getString("phone");
            address = instructor.getString("address");
            specialization = instructor.getString("specialization");
            degree = instructor.getString("degree");
            email = instructor.getString("email");

        } catch (JSONException e) {
            return;
        }


        // Clear existing TextViews
        clearDynamicTextViews();

        // Create TextView and EditText for name
        TextView firstNameLabel = createLabelTextView("firstname:");
        EditText firstNameEditText = createEditableEditText(firstName);
        dynamicContentLayout.addView(firstNameLabel);
        dynamicContentLayout.addView(firstNameEditText);

        // Create TextView and EditText for name
        TextView lastNameLabel = createLabelTextView("lastname:");
        EditText lastNameEditText = createEditableEditText(lastName);
        dynamicContentLayout.addView(lastNameLabel);
        dynamicContentLayout.addView(lastNameEditText);

        // Create TextView and EditText for mobile number
        TextView mobileLabel = createLabelTextView("Mobile Number:");
        EditText mobileEditText = createEditableEditText(mobile_number);
        dynamicContentLayout.addView(mobileLabel);
        dynamicContentLayout.addView(mobileEditText);

        // Create TextView and EditText for address
        TextView addressLabel = createLabelTextView("Address:");
        EditText addressEditText = createEditableEditText(address);
        dynamicContentLayout.addView(addressLabel);
        dynamicContentLayout.addView(addressEditText);

        // Create TextView and EditText for specialization
        TextView specializationLabel = createLabelTextView("Specialization:");
        EditText specializationEditText = createEditableEditText(specialization);
        dynamicContentLayout.addView(specializationLabel);
        dynamicContentLayout.addView(specializationEditText);

        // Create TextView and EditText for degree
        TextView degreeLabel = createLabelTextView("Degree:");
        EditText degreeEditText = createEditableEditText(degree);
        dynamicContentLayout.addView(degreeLabel);
        dynamicContentLayout.addView(degreeEditText);

        // Create TextView and EditText for email
        TextView emailLabel = createLabelTextView("Email:");
        EditText emailEditText = createEditableEditText(email);
        dynamicContentLayout.addView(emailLabel);
        dynamicContentLayout.addView(emailEditText);

        // Create Edit button
        editButton = createEditButton();
        dynamicContentLayout.addView(editButton);

        // update tabels in BataBase when this button presseed
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update tabels in BataBase when this button presseed
                //TODO..........Update Query
                // Read the values from the edit texts

                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();



                Boolean done = API.profileUpdate( firstName, lastName, username);

                if(done){
                    sharedPrefManager.writeString("first_name", firstName);
                    sharedPrefManager.writeString("last_name", lastName);
                    Toast.makeText(InstructorMainActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InstructorMainActivity.this, "Profile Not Updated", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    private void logout(){

        Intent intent = new Intent(InstructorMainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void clearDynamicTextViews() {
        // Remove all existing TextViews from the dynamicContentLayout
        dynamicContentLayout.removeAllViews();

        // Clear the dynamicTextViews list
        dynamicTextViews.clear();
    }




    // .................. Function to add to view ........................
    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        // Add any additional customization to the TextView here
        return textView;
    }

    private TextView createLabelTextView(String label) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(label);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        // Add any additional customization to the TextView here
        return textView;
    }

    private EditText createEditableEditText(String initialValue) {
        EditText editText = new EditText(this);
        editText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        editText.setText(initialValue);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        // Add any additional customization to the EditText here
        return editText;
    }

    private Button createEditButton() {
        Button button = new Button(this);
        button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText("Edit");
        // Add any additional customization to the button here
        return button;
    }

// ............................ End function to add .............................






    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
