package com.example.vaxfinder.user.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaxfinder.R;
import com.example.vaxfinder.user.Activity.ShowDetailsActivity;
import com.example.vaxfinder.user.Domain.PersonList;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    Context context;
    ArrayList<PersonList> arrayList;
    public PersonAdapter(Context context, ArrayList<PersonList> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_layout, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final PersonList temp = arrayList.get(position);

        holder.personImg.setImageResource(arrayList.get(position).getImg());
        holder.txtName.setText((CharSequence) arrayList.get(position).getName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDetailsActivity.class);
                intent.putExtra("haii",temp.getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageView personImg;
        private LinearLayout parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            personImg = itemView.findViewById(R.id.personImage);
            parent = itemView.findViewById(R.id.parent);

        }
    }
}
