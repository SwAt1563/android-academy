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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class SignupActivity extends AppCompatActivity {

    private Spinner userTypeSpinner;
    private LinearLayout adminLayout, traineeLayout, instructorLayout;
    // Admin fields
    private EditText  adminEmailField, adminFirstNameField, adminLastNameField, adminPasswordField, adminConfirmPasswordField;
    // Trainee fields
    private EditText  emailField, firstNameField, lastNameField, passwordField, confirmPasswordField, mobileNumberField, addressField;
    // Instructor fields
    private EditText  instructorEmailField, instructorFirstNameField, instructorLastNameField, instructorPasswordField, instructorConfirmPasswordField, instructorMobileNumberField, instructorAddressField, specializationField;
    private Button signupButton;
    private Spinner degreeSpinner;

    private String selectedDegree;
    private String UserType;

    private String checkFields(String firstName, String lastName, String email, String password, String confirmPassword) {
        if (!isValidFirstName(firstName)) {
            return "First name must be between 3 and 20 characters.";
        }
        if (!isValidLastName(lastName)) {
            return "Last name must be between 3 and 20 characters.";
        }
        if (!isValidEmail(email)) {
            return "Invalid email format.";
        }
        if (!isValidPassword(password)) {
            return "Password must be between 8 and 15 characters and contain at least one number, one lowercase letter, and one uppercase letter.";
        }
        if (!password.equals(confirmPassword)) {
            return "Passwords do not match.";
        }
        return "accepted"; // All fields are accepted
    }

        private boolean isValidFirstName(String firstName) {
            int minLength = 3;
            int maxLength = 20;
            return firstName.length() >= minLength && firstName.length() <= maxLength;
        }

        private boolean isValidLastName(String lastName) {
            int minLength = 3;
            int maxLength = 20;
            return lastName.length() >= minLength && lastName.length() <= maxLength;
        }

        private boolean isValidEmail(String email) {
            // Use a regular expression pattern to validate email format
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            return email.matches(emailRegex);
        }

        private boolean isValidPassword(String password) {
            int minLength = 8;
            int maxLength = 15;
            String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{" + minLength + "," + maxLength + "}$";
            return password.matches(passwordRegex);
        }


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
                selectedDegree =  parent.getItemAtPosition(position).toString();
                // Do something with the selected degree
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where nothing is selected
            }
        });







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







//.......................Update DataBase........................................................................
        //Swatch sign up to author activities
            signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (UserType.equals("Admin")) {
                        String adminEmail = adminEmailField.getText().toString();
                        String adminFirstName = adminFirstNameField.getText().toString();
                        String adminLastName = adminLastNameField.getText().toString();
                        String adminPassword = adminPasswordField.getText().toString();
                        String adminConfirmPassword = adminConfirmPasswordField.getText().toString();
                        String adminUserName = adminFirstName + "_" + adminLastName;


                        String checkFields = checkFields(adminFirstName, adminLastName, adminEmail, adminPassword, adminConfirmPassword);

                        if (checkFields.equals("accepted")) {
                            // Add the user to the database

                            Boolean done = API.ownerSignUp(adminFirstName, adminLastName, adminUserName, adminEmail, adminPassword);

                            if (done) {
                                Toast.makeText(SignupActivity.this, "Admin added successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignupActivity.this, "Failed to add admin", Toast.LENGTH_SHORT).show();
                            }



                        } else {
                            Toast.makeText(SignupActivity.this, checkFields, Toast.LENGTH_SHORT).show();
                        }


                    } else if (UserType.equals("Trainee")) {

                        String email = emailField.getText().toString();
                        String firstName = firstNameField.getText().toString();
                        String lastName = lastNameField.getText().toString();
                        String password = passwordField.getText().toString();
                        String confirmPassword = confirmPasswordField.getText().toString();
                        String mobileNumber = mobileNumberField.getText().toString();
                        String address = addressField.getText().toString();
                        String userName = firstName + "_" + lastName;

                        String checkFields = checkFields(firstName, lastName, email, password, confirmPassword);

                        if (checkFields.equals("accepted")) {
                            // Add the user to the database
                            Boolean done = API.traineeSignUp(firstName, lastName, userName, email, password, address, mobileNumber);

                            if (done) {
                                Toast.makeText(SignupActivity.this, "Trainee added successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignupActivity.this, "Failed to add trainee", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(SignupActivity.this, checkFields, Toast.LENGTH_SHORT).show();
                        }


                    } else if (UserType.equals("Instructor")) {

                        String email = instructorEmailField.getText().toString();
                        String firstName = instructorFirstNameField.getText().toString();
                        String lastName = instructorLastNameField.getText().toString();
                        String password = instructorPasswordField.getText().toString();
                        String confirmPassword = instructorConfirmPasswordField.getText().toString();
                        String mobileNumber = instructorMobileNumberField.getText().toString();
                        String address = instructorAddressField.getText().toString();
                        String userName = firstName + "_" + lastName;
                        String specialization = specializationField.getText().toString();
                        String degree = selectedDegree;

                        String checkFields = checkFields(firstName, lastName, email, password, confirmPassword);

                        if (checkFields.equals("accepted")) {
                            // Add the user to the database
                            Boolean done = API.instructorSignUp(firstName, lastName, userName, email, password, specialization, address, mobileNumber, degree);

                            if (done) {
                                Toast.makeText(SignupActivity.this, "Instructor added successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignupActivity.this, "Failed to add instructor", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(SignupActivity.this, checkFields, Toast.LENGTH_SHORT).show();
                        }

                    }

                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);


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
