package com.example.getwellapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class MedicinAdapter extends ArrayAdapter<medicin> {
    private Context context;
    private List<medicin> medicineList;

    public MedicinAdapter(Context context, List<medicin> medicineList) {
        super(context, 0, medicineList);
        this.context = context;
        this.medicineList = medicineList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_main3, parent, false);
        }

        medicin medicine = medicineList.get(position);

        TextView nameTextView = view.findViewById(R.id.editTextText);
        nameTextView.setText(medicine.getName());

        TextView amountTakenTextView = view.findViewById(R.id.editTextDate);
        amountTakenTextView.setText(medicine.getAmountTaken());

        TextView frequencyTextView = view.findViewById(R.id.editTextDateEnd);
        frequencyTextView.setText(medicine.getFrequency());

        return view;
    }
}