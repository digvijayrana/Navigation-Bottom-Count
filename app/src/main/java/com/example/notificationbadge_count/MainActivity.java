package com.example.notificationbadge_count;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.notificationbadge_count.databinding.ActivityMainBinding;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        BadgeDrawable badge_dashboard = binding.navView.getOrCreateBadge(R.id.navigation_dashboard);
        badge_dashboard.setBackgroundColor(Color.BLUE);
        badge_dashboard.setBadgeTextColor(Color.YELLOW);
        badge_dashboard.setMaxCharacterCount(5);
        badge_dashboard.setNumber(200);
        badge_dashboard.setVisible(true);

        BadgeDrawable badge_notification = binding.navView.getOrCreateBadge(R.id.navigation_notifications);
        badge_notification.setNumber(18);
        badge_notification.setVisible(true);

        binding.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navigation_notifications:
                        BadgeDrawable badge_notification = binding.navView.getBadge(R.id.navigation_notifications);
                        badge_notification.clearNumber();
                        badge_notification.setVisible(false);
                        navController.navigate(R.id.navigation_notifications);
                        break;

                    case R.id.navigation_home:
                        navController.navigate(R.id.navigation_home);
                        break;

                    case R.id.navigation_dashboard:
                        BadgeDrawable badge_dashboard = binding.navView.getBadge(R.id.navigation_dashboard);
                        badge_dashboard.clearNumber();
                        badge_dashboard.setVisible(false);
                        navController.navigate(R.id.navigation_dashboard);
                        break;
                }



                return true;
            }
        });


    }

    //avoid memory leak;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
