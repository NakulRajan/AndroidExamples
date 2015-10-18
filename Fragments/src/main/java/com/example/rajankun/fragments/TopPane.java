package com.example.rajankun.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TopPane extends Fragment{

    OnButtonClickedListner mButtonClickedListner;

    public interface OnButtonClickedListner {
         void OnTopPanelButtonClicked();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =  inflater.inflate(R.layout.top_panel, container, false);
        Button button = (Button) view.findViewById(R.id.replace_panel);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonClickedListner.OnTopPanelButtonClicked();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mButtonClickedListner = (OnButtonClickedListner) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
