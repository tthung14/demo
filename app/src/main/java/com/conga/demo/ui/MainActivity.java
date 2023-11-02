package com.conga.demo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.conga.demo.R;
import com.conga.demo.interfaces.IDateView;
import com.conga.demo.presenter.DatePresenter;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IDateView {
    private EditText edtDate;
    private ImageButton imgDate;
    private DatePresenter datePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePresenter = new DatePresenter(this);
        initView();
    }
    private void initView() {
        edtDate = findViewById(R.id.edtDate);
        imgDate = findViewById(R.id.imgDate);

        imgDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgDate:
                datePresenter.onDate();
                break;
        }
    }

    @Override
    public void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            datePresenter.onDateSelected(selectedDay, selectedMonth, selectedYear);
        }, year, month, day);

        datePickerDialog.show();
    }

    @Override
    public void showDateSuccess(String date) {
        edtDate.setText(date);
    }
}