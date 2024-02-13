package com.example.equestpractical;

import android.app.AlertDialog;
import android.content.Context;
import androidx.annotation.NonNull;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);

        holder.tvItem.setText(user.getFirstName() + " " + user.getLastName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUserDetailsDialog(holder.itemView.getContext(), user);
            }
        });

    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
        }
    }
    private void showUserDetailsDialog(Context context, User user){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.user_item, null);

        ImageView avatarImageView;
        TextView tvId, tvName, tvGender, tvEmail;

        avatarImageView = dialogView.findViewById(R.id.imgAvatar);
        tvId = dialogView.findViewById(R.id.tvId);
        tvName = dialogView.findViewById(R.id.tvName);
        tvGender = dialogView.findViewById(R.id.tvGen);
        tvEmail = dialogView.findViewById(R.id.tvEmail);

        Picasso.get().load(user.getAvatarUrl()).transform(new CircleTransformation()).into(avatarImageView);
        tvId.setText("ID:  " + String.valueOf(user.getId()));
        tvName.setText("Name:  " + user.getFirstName() + " " + user.getLastName());
        tvGender.setText("Gender:  " + user.getGender());
        tvEmail.setText("Email:  " + user.getEmail());

        builder.setView(dialogView);

        builder.setTitle("User Details");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        builder.create().show(); 

    }
}
