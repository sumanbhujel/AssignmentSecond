package com.example.assignmentsecond;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignmentsecond.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class UserRegistration extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    EditText editTextN, editTextD, editTextP, editTextE;
    Button btnSubmit, btnView;
    RadioGroup radioGroup;
    Spinner spinner;
    AutoCompleteTextView autoCompleteTextView;

    String name, gender, dob, phone, email, country, image;
    String[] countries = {"Select Country", "Nepal", "India", "SriLanka", "Bhutan", "Pakistan", "Afghanistan", "Maldives"};
    String[] autoword = {"image1", "image2", "image3"};
    List<User> userList = new ArrayList<>();

    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener mydatepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);
            String mydateFormat = "y-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mydateFormat, Locale.getDefault());
            editTextD.setText(simpleDateFormat.format(calendar.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        editTextN = findViewById(R.id.etName);
        editTextD = findViewById(R.id.etDob);
        editTextE = findViewById(R.id.etEmail);
        editTextP = findViewById(R.id.etPhone);
        radioGroup = findViewById(R.id.rgGender);
        spinner = findViewById(R.id.spCountry);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnView = findViewById(R.id.btnView);

        radioGroup.setOnCheckedChangeListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_values, countries);
        spinner.setAdapter(adapter);
        setSpinnerValue();

        autoCompleteTextView = findViewById(R.id.acImage);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.select_dialog_item, autoword
        );
        autoCompleteTextView.setAdapter(stringArrayAdapter);
        autoCompleteTextView.setThreshold(1);

        btnSubmit.setOnClickListener(this);
        editTextD.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.rbMale) {
            //Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();
            gender = "Male";
        }
        if (i == R.id.rbFemale) {
            gender = "Female";
        }
        if (i == R.id.rbOthers) {
            gender = "Other";
        }

    }

    private void setSpinnerValue() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                country = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(UserRegistration.this, "Please Select Country", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private boolean validate() {
        /*Calendar minAge = Calendar.getInstance();
        minAge.add(Calendar.YEAR,-18);

        if(minAge.before((dob))){
            Toast.makeText(this, "Age should be 18+", Toast.LENGTH_SHORT).show();
        }*/


        if (TextUtils.isEmpty(name)) {
            editTextN.setError("Enter Name");
            editTextN.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(country)) {
            Toast.makeText(this, "Please select country", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (spinner.getSelectedItem().toString().trim().equals("Select Country")) {
            Toast.makeText(this, "Please Select Country", Toast.LENGTH_SHORT).show();
            spinner.setFocusable(true);
            return false;
        }

        if (TextUtils.isEmpty(dob)) {
            editTextD.setError("Enter dob");
            editTextD.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            editTextE.setError("Enter Email");
            editTextE.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextE.setError("Invalid Email");
            editTextE.requestFocus();
            return false;

        }
        if (TextUtils.isEmpty(phone)) {
            editTextP.setError("Enter Phone");
            editTextP.requestFocus();
            return false;
        }
        if (!TextUtils.isDigitsOnly(phone)) {
            editTextP.setError("Invalid Number");
            editTextP.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(image)) {
            autoCompleteTextView.setError("Enter Image name");
            autoCompleteTextView.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {

        name = editTextN.getText().toString();
        dob = editTextD.getText().toString();
        email = editTextE.getText().toString();
        phone = editTextP.getText().toString();
        image = autoCompleteTextView.getText().toString();

        if (view.getId() == R.id.etDob) {
            new DatePickerDialog(this, mydatepicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        }

        if (view.getId() == R.id.btnSubmit) {
            if (validate()) {
                String imageurl = "@drawable/"+image;
                int resID = getResources().getIdentifier(imageurl,null,getPackageName());
                String imgID = String.valueOf(resID);

                userList.add(new User(name, gender, country, dob, email, phone, imgID));
                Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show();
            }
        }

        if (view.getId() == R.id.btnView) {

            Intent intent = new Intent(this, RecyclerViewActivity.class);
            intent.putExtra("allusers", (Serializable) userList);
            startActivity(intent);

        }

    }
}
