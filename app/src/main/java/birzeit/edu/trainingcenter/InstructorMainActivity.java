package birzeit.edu.trainingcenter;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

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

        // update tabels in BataBase when this button presseed
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update tabels in BataBase when this button presseed
            }
        });
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
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    //      .................................................Update Query.............................
    private void showCoursesTaught() {
        // Perform query to fetch courses taught by the instructor
        List<Course> courses = getDummyCourses(); // Dummy data for courses taught

        // Clear existing TextViews
        clearDynamicTextViews();

        // Create TextViews for courses taught
        for (Course course : courses) {
            TextView textView = createTextView(course.getCourse_title());
            dynamicContentLayout.addView(textView);
            dynamicTextViews.add(textView);
        }
    }

    //      .................................................Update Query.............................
    private void showPreviouslyTaughtCourses() {
        // Perform query to fetch previously taught courses by the instructor
        List<Course> previouslyTaughtCourses = getDummyPreviouslyTaughtCourses(); // Dummy data for previously taught courses

        // Clear existing TextViews
        clearDynamicTextViews();

        // Create TextViews for previously taught courses
        for (Course course : previouslyTaughtCourses) {
            TextView textView = createTextView(course.getCourse_title());
            dynamicContentLayout.addView(textView);
            dynamicTextViews.add(textView);
        }
    }

    //      .................................................Update Query.............................

    private void showStudentsList() {
        // Perform query to fetch the list of students in a selected course
        List<Student> students = getDummyStudents(); // Dummy data for students list

        // Clear existing TextViews
        clearDynamicTextViews();

        // Create TextViews for students list
        for (Student student : students) {
            TextView textView = createTextView(student.getName());
            dynamicContentLayout.addView(textView);
            dynamicTextViews.add(textView);
        }
    }



    //      .................................................Update Query.............................

    private void showProfile() {
        // Fetch the instructor's profile information
        Instructor instructor = getDummyProfile(); // Dummy data for instructor's profile

        // Clear existing TextViews
        clearDynamicTextViews();

        // Create TextView and EditText for name
        TextView nameLabel = createLabelTextView("Name:");
        EditText nameEditText = createEditableEditText(instructor.getName());
        dynamicContentLayout.addView(nameLabel);
        dynamicContentLayout.addView(nameEditText);

        // Create TextView and EditText for mobile number
        TextView mobileLabel = createLabelTextView("Mobile Number:");
        EditText mobileEditText = createEditableEditText(""+instructor.getMobile_number());
        dynamicContentLayout.addView(mobileLabel);
        dynamicContentLayout.addView(mobileEditText);

        // Create TextView and EditText for address
        TextView addressLabel = createLabelTextView("Address:");
        EditText addressEditText = createEditableEditText(instructor.getAddress());
        dynamicContentLayout.addView(addressLabel);
        dynamicContentLayout.addView(addressEditText);

        // Create TextView and EditText for specialization
        TextView specializationLabel = createLabelTextView("Specialization:");
        EditText specializationEditText = createEditableEditText(instructor.getSpecialization());
        dynamicContentLayout.addView(specializationLabel);
        dynamicContentLayout.addView(specializationEditText);

        // Create TextView and EditText for degree
        TextView degreeLabel = createLabelTextView("Degree:");
        EditText degreeEditText = createEditableEditText(instructor.getDegree());
        dynamicContentLayout.addView(degreeLabel);
        dynamicContentLayout.addView(degreeEditText);

        // Create Edit button
        editButton = createEditButton();
        dynamicContentLayout.addView(editButton);
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





    private List<Course> getDummyCourses() {
        // Dummy data for courses taught
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Course 1", "Description 1"));
        courses.add(new Course("Course 2", "Description 2"));
        courses.add(new Course("Course 3", "Description 3"));
        return courses;
    }

    private List<Course> getDummyPreviouslyTaughtCourses() {
        // Dummy data for previously taught courses
        List<Course> previouslyTaughtCourses = new ArrayList<>();
        previouslyTaughtCourses.add(new Course("Course A", "Description A"));
        previouslyTaughtCourses.add(new Course("Course B", "Description B"));
        previouslyTaughtCourses.add(new Course("Course C", "Description C"));
        return previouslyTaughtCourses;
    }

    private List<Student> getDummyStudents() {
        // Dummy data for students list
        List<Student> students = new ArrayList<>();
        students.add(new Student("John Doe"));
        students.add(new Student("Jane Smith"));
        students.add(new Student("Michael Johnson"));
        return students;
    }

    private Instructor getDummyProfile() {
        // Dummy data for getDummyProfile list
        Instructor instructor = new Instructor("Mohammad mualla",101,"Rammalla","CE", "BSc");
        return instructor;
    }





    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
