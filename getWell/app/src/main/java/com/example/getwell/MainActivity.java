package com.example.getwell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button signUpButton = findViewById(R.id.sign_up_button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, register.class);

                startActivity(intent);
            }
        });
    }

    public void loginButtonClicked(View view) {
        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Perform login operation using the entered username and password
    }

}
