package com.wdullaer.datetimepickerexample;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private TextView dateTextView;
    private CheckBox modeDarkDate;
    private CheckBox modeCustomAccentDate;
    private CheckBox vibrateDate;
    private CheckBox dismissDate;
    private CheckBox titleDate;
    private CheckBox showYearFirst;

    public DatePickerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.datepicker_layout, container, false);

        // Find our View instances
        dateTextView = (TextView) view.findViewById(R.id.date_textview);
        Button dateButton = (Button) view.findViewById(R.id.date_button);
        modeDarkDate = (CheckBox) view.findViewById(R.id.mode_dark_date);
        modeCustomAccentDate = (CheckBox) view.findViewById(R.id.mode_custom_accent_date);
        vibrateDate = (CheckBox) view.findViewById(R.id.vibrate_date);
        dismissDate = (CheckBox) view.findViewById(R.id.dismiss_date);
        titleDate = (CheckBox) view.findViewById(R.id.title_date);
        showYearFirst = (CheckBox) view.findViewById(R.id.show_year_first);

        // Show a datepicker when the dateButton is clicked
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        DatePickerFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setThemeDark(modeDarkDate.isChecked());
                dpd.vibrate(vibrateDate.isChecked());
                dpd.dismissOnPause(dismissDate.isChecked());
                dpd.showYearPickerFirst(showYearFirst.isChecked());
                if (modeCustomAccentDate.isChecked()) {
                    dpd.setAccentColor(Color.parseColor("#9C27B0"));
                }
                if(titleDate.isChecked()) {
                    dpd.setTitle("DatePicker Title");
                }
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");
        if(dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        dateTextView.setText(date);
    }
}
