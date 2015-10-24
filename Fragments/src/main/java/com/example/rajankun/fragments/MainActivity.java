package com.example.rajankun.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/** Fragments example, where we programmatically render two fragments one below another
 * and then replace the fragment at the bottom when the user clicks on a button in top
 * fragment.
 *
 * This Activity implements the interface that is defined inside the fragment. So that fragment
 * knows how to communicate with the other fragment through  Activity Class.
 *
 * Ref: http://developer.android.com/training/basics/fragments/index.html
 */

public class MainActivity extends AppCompatActivity implements TopPane.OnButtonClickedListner {

    FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            return;
        }

        mFragmentManager = getSupportFragmentManager();

        //creating the top and bottom panes
        TopPane topPane = new TopPane();
        BottomPane bottomPane = new BottomPane();

        FragmentTransaction fragmentTransaction= mFragmentManager.beginTransaction();

        fragmentTransaction
                .add(R.id.top_fragment, topPane)
                .add(R.id.bottom_fragment, bottomPane)
                .commit();

    }

    @Override
    public void OnTopPanelButtonClicked() {
       //swapping the bottom view
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        ReplacedPane replacedPane = new ReplacedPane();
        fragmentTransaction.replace(R.id.bottom_fragment, replacedPane);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
