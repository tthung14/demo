package com.conga.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtId, edtName, edtGender, edtAddress, edtNationality;
    private Button btnSearch, btnAdd, btnClear;
    private DBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }
    private void initView() {
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtGender = findViewById(R.id.edtGender);
        edtAddress = findViewById(R.id.edtAddress);
        edtNationality = findViewById(R.id.edtNationality);
        btnSearch = findViewById(R.id.btnSearch);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);

        mDbHelper = new DBHelper(this);

        btnSearch.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:
                search();
                break;
            case R.id.btnAdd:
                add();
                break;
            case R.id.btnClear:
                clear();
                break;
        }
    }

    private void search() {
        Integer id = Integer.parseInt(String.valueOf(edtId.getText()));
        if (mDbHelper.chkPersonById(id)) {
            edtName.setText(mDbHelper.getPersonById(id).getName());
            edtGender.setText(mDbHelper.getPersonById(id).getGender());
            edtAddress.setText(mDbHelper.getPersonById(id).getAddress());
            edtNationality.setText(mDbHelper.getPersonById(id).getNationality());
        }
        else
            Toast.makeText(MainActivity.this, "Person is not existed", Toast.LENGTH_SHORT).show();
    }

    private void add() {
        Integer id = Integer.parseInt(String.valueOf(edtId.getText()));
        if (!mDbHelper.chkPersonById(id)) {
            Person person = new Person();
            person.setId(id);
            person.setName(String.valueOf(edtName.getText()));
            person.setGender(String.valueOf(edtGender.getText()));
            person.setAddress(String.valueOf(edtAddress.getText()));
            person.setNationality(String.valueOf(edtNationality.getText()));
            mDbHelper.addPerson(person);
            Toast.makeText(MainActivity.this, "Add Person is Successfully", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(MainActivity.this, "ID Person is existed", Toast.LENGTH_SHORT).show();
    }

    private void clear() {
        edtId.setText("");
        edtName.setText("");
        edtGender.setText("");
        edtAddress.setText("");
        edtNationality.setText("");
    }
}