package com.example.exercice2;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editSurface, editPieces,editName,editAddress;
    CheckBox checkboxPool;
    Button buttonCalculate;
    TextView textResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.edit_name);
        editAddress = findViewById(R.id.edit_address);
        editSurface = findViewById(R.id.edit_surface);
        editPieces = findViewById(R.id.edit_pieces);
        checkboxPool = findViewById(R.id.checkbox_pool);
        buttonCalculate = findViewById(R.id.button_calculate);
        textResult = findViewById(R.id.text_result);


        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTax();
            }
        });
    }

    private void calculateTax() {
        // Get values from input fields
        String nameText = editName.getText().toString();
        String addressText = editAddress.getText().toString();
        String surfaceText = editSurface.getText().toString();
        String piecesText = editPieces.getText().toString();

        // Validate inputs
        if (surfaceText.isEmpty() || piecesText.isEmpty() || nameText.isEmpty() || addressText.isEmpty()) {
            textResult.setText("Assurez-vous de remplir toutes les informations requises.");
            return;
        }

        int surface = Integer.parseInt(surfaceText);
        int pieces = Integer.parseInt(piecesText);
        boolean hasPool = checkboxPool.isChecked();

        // Calculate tax based on formulas
        double baseTax = surface * 2;
        double additionalTax = pieces * 50;
        if (hasPool) {
            additionalTax += 100; // Add extra 100 for pool
        }
        double totalTax = baseTax + additionalTax;

        // Display results
        String resultText = "Impot de base: " + baseTax + "\n"
                + "Impot supplémentaire: " + additionalTax + "\n"
                + "Impot Total: " + totalTax;
        textResult.setText(resultText);
    }
}
