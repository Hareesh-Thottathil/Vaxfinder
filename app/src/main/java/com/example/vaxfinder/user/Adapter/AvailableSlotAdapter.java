package com.example.vaxfinder.user.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaxfinder.R;
import com.example.vaxfinder.admin.Domain.SlotList;
import com.example.vaxfinder.user.Domain.PersonList;

import java.util.ArrayList;

public class AvailableSlotAdapter extends RecyclerView.Adapter<AvailableSlotAdapter.ViewHolder> {

    Context context;
    ArrayList<SlotList> slotList;

    ArrayList<PersonList> arrayList;

    public AvailableSlotAdapter(Context context, ArrayList<SlotList> slotList) {
        this.context = context;
        this.slotList = slotList;
    }

    @NonNull
    @Override
    public AvailableSlotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slot_items_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull AvailableSlotAdapter.ViewHolder holder, int position) {

        final SlotList temp = slotList.get(position);

        holder.txtVType.setText(slotList.get(position).getvType());
        holder.txtcenterOnPost.setText(slotList.get(position).getCenterOnPost());
        holder.txtLoc.setText(slotList.get(position).getLoc());
        holder.txtDateTime.setText(slotList.get(position).getSlotDateTime());
        holder.txtAvailStatus.setText("Availability Status: " + slotList.get(position).getAvailStatus());
        holder.txtBookingStatus.setText("Booking Status: " + slotList.get(position).getBookingstatus());

        String Date = slotList.get(position).getSlotDateTime();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.book_slot_dialogbox);

                EditText txtname = dialog.findViewById(R.id.PersonName);
                Button bookBtn = dialog.findViewById(R.id.bookBtn);
                Button backBtn = dialog.findViewById(R.id.bookBackBtn);
                dialog.show();

                bookBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "booking is confirmed!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                backBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return slotList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtVType, txtLoc, txtDateTime, txtAvailStatus, txtBookingStatus, txtcenterOnPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVType = itemView.findViewById(R.id.txtVaccineType);
            txtLoc = itemView.findViewById(R.id.txtLocation);
            txtDateTime = itemView.findViewById(R.id.txtDateTime);
            txtBookingStatus = itemView.findViewById(R.id.txtBookingStatus);
            txtAvailStatus = itemView.findViewById(R.id.txtAvailabilityStatus);
            txtcenterOnPost = itemView.findViewById(R.id.txtcenterOnPost);

        }
    }
}
