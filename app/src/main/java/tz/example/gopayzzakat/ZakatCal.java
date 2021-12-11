package tz.example.gopayzzakat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ZakatCal extends AppCompatActivity {

    private EditText Weight, Value;
    private Spinner spinnerType;
    private Button btnCalculate;

    private final int typeWear = 200;
    private final int typeKeep = 85;

    private final String Wear = "Wear";
    private final String Keep = "Keep";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakat_cal);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String getWeight = sharedPref.getString("weight", "");
        String getValue = sharedPref.getString("value", "");

        Weight = findViewById(R.id.Weight);
        Value = findViewById(R.id.Value);

        Weight.setText(getWeight);
        Value.setText(getValue);

        spinnerType = findViewById(R.id.Type);

        btnCalculate = findViewById(R.id.btnCalculate);


        String[] items = new String[]{Keep, Wear};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerType.setAdapter(adapter);

        btnCalculate.setOnClickListener(view -> {

            if (Weight.getText().toString().length() == 0) {
                Weight.setError("Please input weight in number.");
                Toast.makeText(this, "Missing Weigh of Goldt", Toast.LENGTH_SHORT).show();
                return;
            }

            if (Value.getText().toString().length() == 0) {
                Value.setError("Please input price in number");
                Toast.makeText(this, "Missing Current Value Price", Toast.LENGTH_SHORT).show();
                return;
            }


            double weight = Double.parseDouble(Weight.getText().toString());
            double value = Double.parseDouble(Value.getText().toString());

            int type;
            String spinValue = spinnerType.getSelectedItem().toString();
            if (spinValue.equals(Keep)) {
                type = typeKeep;
            } else if (spinValue.equals(Wear)) {
                type = typeWear;
            } else {
                type = 0;
            }

            double ttlValue = weight * value;
            double uruf = weight - type;
            double payable = uruf <= 0 ? 0 : value * uruf;
            double ttlZakat = payable * 0.025;

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Receipts");
            builder.setMessage("" +
                    "Total Value of Gold :RM " + ttlValue +
                    "\nTotal Zakat Payable :RM " + payable +
                    "\nTotal Uruf :RM " + uruf +
                    "\nTotal Zakat :RM " + ttlZakat);

            // add the buttons
            builder.setPositiveButton("Continue", null);

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("weight", String.valueOf(weight));
            editor.putString("value", String.valueOf(value));
            editor.apply();
        });

    }
}