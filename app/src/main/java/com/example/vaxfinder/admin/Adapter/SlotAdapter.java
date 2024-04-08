package com.example.vaxfinder.admin.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaxfinder.R;
import com.example.vaxfinder.admin.Domain.SlotList;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.ViewHolder> {

    Context context;
    ArrayList<SlotList> slotList;

    public SlotAdapter(Context context, ArrayList<SlotList> slotList) {
        this.context = context;
        this.slotList = slotList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slot_items_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final SlotList temp = slotList.get(position);

        holder.txtVType.setText(slotList.get(position).getvType());
        holder.txtcenterOnPost.setText(slotList.get(position).getCenterOnPost());
        holder.txtLoc.setText(slotList.get(position).getLoc());
        holder.txtDateTime.setText(slotList.get(position).getSlotDateTime());
        holder.txtAvailStatus.setText("Availability Status: " + slotList.get(position).getAvailStatus());
        holder.txtBookingStatus.setText("Booking Status: " + slotList.get(position).getBookingstatus());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Show alert dialog for confirmation
                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setMessage("Are you sure you want to delete this item?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteItem(position);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return slotList.size();
    }

    private void deleteItem(int position) {
        slotList.remove(position);
        notifyItemRemoved(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtVType, txtLoc, txtDateTime, txtAvailStatus, txtBookingStatus,txtcenterOnPost;

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
