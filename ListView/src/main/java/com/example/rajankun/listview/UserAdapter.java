package com.example.rajankun.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple user adapter.
 */
public class UserAdapter extends ArrayAdapter{

    public UserAdapter(Context context, ArrayList<UserModel> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        UserModel userModel = (UserModel) getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.userlist, parent, false);
        }

        TextView userName = (TextView) convertView.findViewById(R.id.userName);
        TextView userAge = (TextView) convertView.findViewById(R.id.userAge);
        TextView userAddress = (TextView) convertView.findViewById(R.id.userAddress);

        userName.setText(userModel.mName);
        userAge.setText(userModel.mAge.toString());
        userAddress.setText(userModel.mAddress);

        return convertView;
    }
}
