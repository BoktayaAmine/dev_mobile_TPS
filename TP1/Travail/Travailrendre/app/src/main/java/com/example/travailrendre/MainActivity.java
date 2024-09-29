package com.example.travailrendre;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText fullName, address, email, ville, phone;
    private Button envoyer;

    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullName = findViewById(R.id.fullName);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        ville = findViewById(R.id.ville);
        phone = findViewById(R.id.phone);
        envoyer = findViewById(R.id.envoyer);

        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullNameText = fullName.getText().toString();
                String addressText = address.getText().toString();
                String villeText = ville.getText().toString();
                String phoneText = phone.getText().toString();
                String emailText = email.getText().toString();


                if (fullNameText.isEmpty() || addressText.isEmpty() || villeText.isEmpty() || phoneText.isEmpty() || emailText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir toutes les infos", Toast.LENGTH_SHORT).show();
                    return;
                }

                result = "Nom et Prenom : " + fullNameText + "\n" + "Email : " + emailText + "\n" + "Phone : " + phoneText + "\n" + "Address : " +addressText + "\n" + "Ville : "+ villeText  ;

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("result", result);
                startActivity(intent);
            }
        });
    }
}
