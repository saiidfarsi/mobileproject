// PrescriptionAdapter.java
package com.example.getwellapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class presAdapter extends RecyclerView.Adapter<presAdapter.ViewHolder> {

    private List<listViewpress> prescriptionList;
    private Context context;

    public presAdapter(List<listViewpress> prescriptionList, Context context) {
        this.prescriptionList = prescriptionList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescriptions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        listViewpress prescription = prescriptionList.get(position);
        holder.tvPrescriptionName.setText(prescription.getName());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, MainActivity3.class);
            intent.putExtra("prescriptionId", prescription.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return prescriptionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPrescriptionName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPrescriptionName = itemView.findViewById(R.id.prescription);
        }
    }
}
