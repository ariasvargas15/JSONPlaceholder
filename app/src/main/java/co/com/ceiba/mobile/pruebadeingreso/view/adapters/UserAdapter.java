package co.com.ceiba.mobile.pruebadeingreso.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User;
import co.com.ceiba.mobile.pruebadeingreso.view.activities.PostActivity;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<User> users;
    private Context context;

    public UserAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);

        holder.name.setText(user.getName());
        holder.phone.setText(user.getPhone());
        holder.email.setText(user.getEmail());

        holder.viewPost.setOnClickListener(view -> {
            Intent in = new Intent(context, PostActivity.class);
            in.putExtra("user", user);
            context.startActivity(in);
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void updateList(List<User> temp) {
        users = (ArrayList<User>) temp;
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView phone;
        private TextView email;
        private Button viewPost;

        public UserViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            viewPost = itemView.findViewById(R.id.btn_view_post);

        }
    }
}
