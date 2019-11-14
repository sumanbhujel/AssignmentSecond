package com.example.assignmentsecond.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentsecond.R;
import com.example.assignmentsecond.UserDetails;
import com.example.assignmentsecond.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyHolder> {

    List<User> userList = new ArrayList<>();
    private Context context;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.users_layout, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        final User users = userList.get(position);
        int imgId = Integer.valueOf(users.getImage());
        holder.imageView.setImageResource(imgId);
        holder.textView.setText(users.getName());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserDetails.class);
                intent.putExtra("name", users.getName());
                intent.putExtra("gender", users.getGender());
                intent.putExtra("dob", users.getDob());
                intent.putExtra("country", users.getCountry());
                intent.putExtra("email", users.getEmail());
                intent.putExtra("phone", users.getPhone());
                intent.putExtra("image", users.getImage());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rvImage);
            textView = itemView.findViewById(R.id.rvName);
        }
    }
}



