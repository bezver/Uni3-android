package com.example.usermanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.usermanager.Core.Models.User;

import java.util.ArrayList;
import java.util.Comparator;

public class UserAdapter extends ArrayAdapter<User> {
    private Context context;
    private ArrayList<User> userList;
    private int layout;

    UserAdapter(Context context, int resource, ArrayList<User> users) {
        super(context, resource, users);
        this.context = context;
        this.userList = users;
        this.layout = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(this.layout, parent,false);
        }
        User user = userList.get(position);

        ImageView userIcon = (ImageView) convertView.findViewById(R.id.userIcon);
        TextView fullName = (TextView) convertView.findViewById(R.id.fullName);
        TextView email = (TextView) convertView.findViewById(R.id.email);

        userIcon.setImageResource(user.getIconKey());
        fullName.setText(user.getFirstname() + " " + user.getLastname());
        email.setText(user.getEmail());

        return convertView;
    }
}
