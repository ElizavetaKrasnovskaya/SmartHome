package com.bsuir.smarthome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bsuir.smarthome.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class PersonActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText person;
    private TextView email;
    private TextView changePassword;
    private Button buttonSave;
    private Button buttonLogout;
    private ProgressBar progressBar;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        person = findViewById(R.id.person);
        email = findViewById(R.id.et_email);
        buttonSave = findViewById(R.id.btn_save);
        buttonLogout = findViewById(R.id.btn_logout);
        changePassword = findViewById(R.id.et_password);
        progressBar = findViewById(R.id.progressbar);

        String userName = user.getDisplayName();
        if(userName != null){
            person.setText(userName);
        }

        email.setText(user.getEmail());

        buttonSave.setOnClickListener(this::onClick);
        buttonLogout.setOnClickListener(this::onClick);
        changePassword.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_save){
            String name = person.getText().toString();
            if(name.isEmpty()){
                person.setError("Name required");
                person.requestFocus();
                return;
            }

            UserProfileChangeRequest updates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build();

            progressBar.setVisibility(View.VISIBLE);

            user.updateProfile(updates)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()){
                            Toast.makeText(PersonActivity.this, "Profile updated",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(PersonActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        if(view.getId() == R.id.btn_logout){
            startActivity(new Intent(PersonActivity.this, LoginActivity.class));
        }

        if(view.getId() == R.id.et_password){
            startActivity(new Intent(PersonActivity.this, ResetPasswordActivity.class));
        }
    }
}