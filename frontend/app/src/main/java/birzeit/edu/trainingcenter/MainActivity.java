package birzeit.edu.trainingcenter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

//...................................LOGIN...................
public class MainActivity extends AppCompatActivity {
    private Button signupButton;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPrefManager sp = SharedPrefManager.getInstance(MainActivity.this);


        signupButton = findViewById(R.id.buttonSignUp);
        login = findViewById(R.id.buttonLogin);

        TextView emailField = findViewById(R.id.editTextEmail);
        TextView passwordField = findViewById(R.id.editTextPassword);



        // check box
        CheckBox checkBox = findViewById(R.id.checkBoxRememberMe);


        // set inital value of email field
        if (sp.readString("remember_me", "").equals("true")) {
            checkBox.setChecked(false);
            sp.writeString("remember_me", "false");
            emailField.setText(sp.readString("saved_email", ""));
        }



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO .................................

                Boolean done = API.startCoursesNotifications();

                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                String user_type = API.getUserTypeByEmail(email);

                if (user_type.equals("admin")) {

                    JSONObject user = API.ownerSignIn(email, password);
                    if (user == null) {
                        Toast.makeText(MainActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    String username, firstName, lastName, userEmail, userType;

                    try {
                        username = user.getString("username");
                        firstName = user.getString("first_name");
                        lastName = user.getString("last_name");
                        userEmail = user.getString("email");
                        userType = user.getString("user_type");
                    } catch (JSONException e) {
                        return;
                    }

                    sp.writeString("username", username);
                    sp.writeString("first_name", firstName);
                    sp.writeString("last_name", lastName);
                    sp.writeString("email", userEmail);
                    sp.writeString("user_type", userType);

                    // check box if checked
                    if (checkBox.isChecked()) {
                        sp.writeString("remember_me", "true");
                        sp.writeString("saved_email", userEmail);
                    } else {
                        sp.writeString("remember_me", "false");
                    }

                    Intent intent = new Intent(MainActivity.this, AdmainMainActivity.class);
                    startActivity(intent);
                } else if (user_type.equals("instructor")) {

                    JSONObject user = API.instructorSignIn(email, password);
                    if (user == null) {
                        Toast.makeText(MainActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String username, firstName, lastName, userEmail, userType;

                    try {
                        username = user.getString("username");
                        firstName = user.getString("first_name");
                        lastName = user.getString("last_name");
                        userEmail = user.getString("email");
                        userType = user.getString("user_type");
                    } catch (JSONException e) {
                        return;
                    }

                    sp.writeString("username", username);
                    sp.writeString("first_name", firstName);
                    sp.writeString("last_name", lastName);
                    sp.writeString("email", userEmail);
                    sp.writeString("user_type", userType);

                    // check box if checked
                    if (checkBox.isChecked()) {
                        sp.writeString("remember_me", "true");
                        sp.writeString("saved_email", userEmail);
                    } else {
                        sp.writeString("remember_me", "false");
                    }



                    Intent intent = new Intent(MainActivity.this, InstructorMainActivity.class);
                    startActivity(intent);
                } else if (user_type.equals("trainee")) {

                    JSONObject user = API.traineeSignIn(email, password);
                    if (user == null) {
                        Toast.makeText(MainActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String username, firstName, lastName, userEmail, userType;

                    try {
                        username = user.getString("username");
                        firstName = user.getString("first_name");
                        lastName = user.getString("last_name");
                        userEmail = user.getString("email");
                        userType = user.getString("user_type");
                    } catch (JSONException e) {
                        return;
                    }

                    sp.writeString("username", username);
                    sp.writeString("first_name", firstName);
                    sp.writeString("last_name", lastName);
                    sp.writeString("email", userEmail);
                    sp.writeString("user_type", userType);

                    // check box if checked
                    if (checkBox.isChecked()) {
                        sp.writeString("remember_me", "true");
                        sp.writeString("saved_email", userEmail);
                    } else {
                        sp.writeString("remember_me", "false");
                    }



                    Intent intent = new Intent(MainActivity.this, StudentMainActivity.class);
                    startActivity(intent);
                }else {

                    Toast.makeText(MainActivity.this, "User not exist, Wrong Email", Toast.LENGTH_SHORT).show();

                }


            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }


}
