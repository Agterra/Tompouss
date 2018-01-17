package fr.company.agterra.tompouss;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainNavigationActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private ArrayList<Fragment> fragmentViews;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:

                    selectedFragment = fragmentViews.get(0);

                    break;

                case R.id.navigation_dashboard:

                    selectedFragment = fragmentViews.get(1);

                    break;

                case R.id.navigation_edt:

                    selectedFragment = fragmentViews.get(2);

                    break;

            }

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.content, selectedFragment);

            fragmentTransaction.commit();

            return true;

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_navigation);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        this.fragmentViews = new ArrayList<>(3);

        NewsFragment newsFragment = new NewsFragment();

        this.fragmentViews.add(newsFragment);

        GradesFragment gradesFragment = new GradesFragment();

        this.fragmentViews.add(gradesFragment);

        EDTFragment edtFragment = new EDTFragment();

        this.fragmentViews.add(edtFragment);

    }

}
