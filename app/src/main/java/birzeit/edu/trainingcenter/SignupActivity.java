package birzeit.edu.trainingcenter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class SignupActivity extends AppCompatActivity {

    private Spinner userTypeSpinner;
    private LinearLayout adminLayout, traineeLayout, instructorLayout;
    // Admin fields
    private EditText adminEmailField, adminFirstNameField, adminLastNameField, adminPasswordField, adminConfirmPasswordField;
    // Trainee fields
    private EditText emailField, firstNameField, lastNameField, passwordField, confirmPasswordField, mobileNumberField, addressField;
    // Instructor fields
    private EditText instructorEmailField, instructorFirstNameField, instructorLastNameField, instructorPasswordField, instructorConfirmPasswordField;
    private EditText instructorMobileNumberField, instructorAddressField, specializationField, degreeField;
    private Button signupButton;
    private Spinner degreeSpinner;
    private String UserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupButton = findViewById(R.id.signupButton);

        userTypeSpinner = findViewById(R.id.userTypeSpinner);
        adminLayout = findViewById(R.id.adminLayout);
        traineeLayout = findViewById(R.id.traineeLayout);
        instructorLayout = findViewById(R.id.instructorLayout);

        // Admin fields
        adminEmailField = findViewById(R.id.adminEmailField);
        adminFirstNameField = findViewById(R.id.adminFirstNameField);
        adminLastNameField = findViewById(R.id.adminLastNameField);
        adminPasswordField = findViewById(R.id.adminPasswordField);
        adminConfirmPasswordField = findViewById(R.id.adminConfirmPasswordField);

        // Trainee fields
        emailField = findViewById(R.id.emailField);
        firstNameField = findViewById(R.id.firstNameField);
        lastNameField = findViewById(R.id.lastNameField);
        passwordField = findViewById(R.id.passwordField);
        confirmPasswordField = findViewById(R.id.confirmPasswordField);
        mobileNumberField = findViewById(R.id.mobileNumberField);
        addressField = findViewById(R.id.addressField);

        // Instructor fields
        instructorEmailField = findViewById(R.id.instructorEmailField);
        instructorFirstNameField = findViewById(R.id.instructorFirstNameField);
        instructorLastNameField = findViewById(R.id.instructorLastNameField);
        instructorPasswordField = findViewById(R.id.instructorPasswordField);
        instructorConfirmPasswordField = findViewById(R.id.instructorConfirmPasswordField);
        instructorMobileNumberField = findViewById(R.id.mobileNumberField2);
        instructorAddressField = findViewById(R.id.addressField2);
        specializationField = findViewById(R.id.specializationField);
        degreeSpinner = findViewById(R.id.degreeSpinner);


        // instructor
        //degreeSpinner for instructor
        ArrayAdapter<CharSequence> degreeAdapter = ArrayAdapter.createFromResource(this,
                R.array.degree_array, android.R.layout.simple_spinner_item);
        degreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        degreeSpinner.setAdapter(degreeAdapter);
        degreeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDegree = parent.getItemAtPosition(position).toString();
                // Do something with the selected degree
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where nothing is selected
            }
        });




        // instructor
        // Set up the instructor courses that the instructor can teach
        ListView coursesListView = findViewById(R.id.coursesListView);
        List<String> courseList = Arrays.asList("C", "Python", "Java", "AI"); // Replace with your actual course list
        CourseAdapter courseAdapter = new CourseAdapter(this, courseList);
        coursesListView.setAdapter(courseAdapter);

        // To get the checked boxes for the courses that the instructor can teach
        Set<String> selectedCourses = courseAdapter.getSelectedCourses();
        for (String course : selectedCourses) {
            // Add to database
        }






        // Set up the user type spinner
        ArrayAdapter<CharSequence> userTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.user_types, android.R.layout.simple_spinner_item);
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(userTypeAdapter);




        // Set up the listener for user type selection
        userTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedUserType = parent.getItemAtPosition(position).toString();
                showFieldsForUserType(selectedUserType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform signup logic here
            }
        });





//.......................Update DataBase........................................................................
        //Swatch sign up to author activities
            signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (UserType.equals("Admin")) {
                        Intent intent = new Intent(SignupActivity.this, AdmainMainActivity.class);
                        startActivity(intent);

                    } else if (UserType.equals("Trainee")) {
                        Intent intent = new Intent(SignupActivity.this, StudentMainActivity.class);
                        startActivity(intent);

                    } else if (UserType.equals("Instructor")) {
                        Intent intent = new Intent(SignupActivity.this, InstructorMainActivity.class);
                        startActivity(intent);
                    }


                }
            });
        }




    //Control layout for the diffrente 3 users
    private void showFieldsForUserType(String userType) {
        UserType=userType;
        if (userType.equals("Admin")) {
            adminLayout.setVisibility(View.VISIBLE);
            traineeLayout.setVisibility(View.GONE);
            instructorLayout.setVisibility(View.GONE);
        } else if (userType.equals("Trainee")) {
            adminLayout.setVisibility(View.GONE);
            traineeLayout.setVisibility(View.VISIBLE);
            instructorLayout.setVisibility(View.GONE);
        } else if (userType.equals("Instructor")) {
            adminLayout.setVisibility(View.GONE);
            traineeLayout.setVisibility(View.GONE);
            instructorLayout.setVisibility(View.VISIBLE);
        }
    }





}
