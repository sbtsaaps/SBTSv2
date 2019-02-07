package sbts.dmw.com.sbtrackingsystem.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import sbts.dmw.com.sbtrackingsystem.R;
import sbts.dmw.com.sbtrackingsystem.classes.SessionManager;
import sbts.dmw.com.sbtrackingsystem.fragments.AttendeeHome;
import sbts.dmw.com.sbtrackingsystem.fragments.MapsFragment;
import sbts.dmw.com.sbtrackingsystem.fragments.StudentList;

public class AttendeeNavigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    SessionManager sessionManager;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee_navigation);


        navigationView = findViewById(R.id.attendeeNavigationView);
        navigationView.setNavigationItemSelectedListener(this);

        sessionManager = new SessionManager(this);

        /*HashMap<String, String> user = sessionManager.getUserDetails();
        final String email = user.get(SessionManager.EMAIL);*/

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.attendeeDrawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_logout: {
                sessionManager.logout();
                break;
            }

            case R.id.nav_studentsPresent: {
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_frame, new StudentList()).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                toolbar.setTitle("Student List");
                break;
            }

            case R.id.nav_profile: {

                getSupportFragmentManager().beginTransaction().replace(R.id.nav_frame, new AttendeeHome()).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                toolbar.setTitle("Profile");
                break;
            }
            case R.id.nav_map: {

                getSupportFragmentManager().beginTransaction().replace(R.id.nav_frame, new MapsFragment()).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                toolbar.setTitle("Map");
                break;

            }
            default: {
                break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
