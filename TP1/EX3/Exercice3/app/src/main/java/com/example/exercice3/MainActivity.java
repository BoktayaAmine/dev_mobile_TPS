package com.example.exercice3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CheckBox checkOption1, checkOption2, checkOption3, checkOption4;
    RadioButton radioYes, radioNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkOption1 = findViewById(R.id.check_mvc_option1);
        checkOption2 = findViewById(R.id.check_mvc_option2);
        checkOption3 = findViewById(R.id.check_mvc_option3);
        checkOption4 = findViewById(R.id.check_mvc_option4);

        radioYes = findViewById(R.id.radio_yes);
        radioNo = findViewById(R.id.radio_no);

        Button nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "1. Le pattern MVC :\n";

                if (checkOption1.isChecked()) result+="Multiple Versions Combined\n";
                if (checkOption2.isChecked()) result+="Model View Controller\n";
                if (checkOption3.isChecked()) result+="Main Value Compiled\n";
                if (checkOption4.isChecked()) result+="Mandatory Validated Controls\n";

                if (radioYes.isChecked()) result+="2. la syntaxe $[x] est permise dans une JSP: OUI\n";
                else if (radioNo.isChecked()) result+="2. la syntaxe $[x] est permise dans une JSP: NON\n";

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("result", result.toString());
                startActivity(intent);
            }
        });

        Button quitButton = findViewById(R.id.button_quit);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
