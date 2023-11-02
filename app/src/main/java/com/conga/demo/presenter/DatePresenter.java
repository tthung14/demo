package com.conga.demo.presenter;

import com.conga.demo.interfaces.IDateView;
import com.conga.demo.model.DateModel;

public class DatePresenter {
    private IDateView iDateView;
    private DateModel dateModel;

    public DatePresenter(IDateView iDateView) {
        this.iDateView = iDateView;
    }

    public void onDate() {
        iDateView.showDatePickerDialog();
    }
    public void onDateSelected(int day, int month, int year) {
        dateModel = new DateModel(day, month, year);

        iDateView.showDateSuccess(dateModel.formatDate());
    }
}
