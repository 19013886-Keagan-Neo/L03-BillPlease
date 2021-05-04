package sg.edu.rp.c346.id19013886.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // Step 1: Declare the field variables
    TextView tvDisplayTotal;
    EditText etAmount;
    EditText etPax;
    EditText etDiscount;
    ToggleButton tbtnSVS;
    ToggleButton tbtnGST;
    RadioGroup rgPayment;
    Button btnSplit;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Step 2: Link the field variable to UI components in Layout
        tvDisplayTotal = findViewById(R.id.textViewDisplayTotal);
        etAmount = findViewById(R.id.editTextAmount);
        etPax = findViewById(R.id.editTextPax);
        etDiscount = findViewById(R.id.editTextDiscount);
        tbtnSVS = findViewById(R.id.toggleButtonSVS);
        tbtnGST = findViewById(R.id.toggleButtonGST);
        rgPayment = findViewById(R.id.radioButtonPayment);
        btnSplit = findViewById(R.id.buttonSplit);
        btnReset = findViewById(R.id.buttonReset);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double doubleAmount = Double.parseDouble(etAmount.getText().toString());
                Integer intPax = Integer.parseInt(etPax.getText().toString());
                double doubleDiscount = Double.parseDouble(etDiscount.getText().toString());
                Double doubleTotalBill = doubleAmount * ((doubleDiscount + 100) / 100);
                double doubleEachPerson = doubleTotalBill / intPax;
                String stringShowBill = doubleTotalBill.toString();
                String stringShowEachPerson = Double.toString(doubleEachPerson);
                if (etAmount.getText().toString().isEmpty() && etPax.getText().toString().isEmpty()) {
                    tvDisplayTotal.setText("Please fill in all details");
                    tvDisplayTotal.setTextColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Please input something", Toast.LENGTH_SHORT).show();
                } else {
                    tvDisplayTotal.setText("Total Bill: " + stringShowBill + "\n" + "Each Pays: " + stringShowEachPerson);
                }

                int checkRadioId = rgPayment.getCheckedRadioButtonId();
                if(checkRadioId == R.id.radioButtonCash){
                    // Write code when Cash is selected
                    tvDisplayTotal.setText("Total Bill: " + stringShowBill + "\n" + "Each Pays: " + stringShowEachPerson + " in cash");
                } else {
                    // Write code when PayNow is selected
                    tvDisplayTotal.setText("Total Bill: " + stringShowBill + "\n" + "Each Pays: " + stringShowEachPerson + " via Paynow to 912345678");
                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmount.setText("");
                etPax.setText("");
                etDiscount.setText("");
                tvDisplayTotal.setText("");
                tbtnSVS.setChecked(false);
                tbtnGST.setChecked(false);
                rgPayment.check(R.id.radioButtonCash);
            }
        });

    }
}