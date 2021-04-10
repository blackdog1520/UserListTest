package com.blackdev.userlisttest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class userListAdapter extends RecyclerView.Adapter<userListAdapter.MyHolder> {

    Context context;

    public userListAdapter(Context context, List<PojoUsers> list) {
        this.context = context;
        this.list = list;
    }

    List<PojoUsers> list;


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_user_layout,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        try { PojoUsers data = list.get(position);
            Log.i("TEST","ds "+data.getLastName());
        holder.id.setText(data.getId().toString());
        holder.email.setText(data.getEmail());

        String[] temp =  data.getEmail().split("\\.",2);
        String name1 = temp[0].substring(0,1).toUpperCase()+temp[0].substring(1) + " " +temp[1].split("@", 2)[0];
        holder.name.setText(name1);

            Picasso.get()
                    .load(data.getAvatar())
                    .into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void updateList(List<PojoUsers> newList) {
        list = newList;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        CircularImageView imageView;
        TextView id,name,email;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.userProfile);
            id = itemView.findViewById(R.id.userID);
            name = itemView.findViewById(R.id.userName);
            email = itemView.findViewById(R.id.userEmail);
        }
    }
}
