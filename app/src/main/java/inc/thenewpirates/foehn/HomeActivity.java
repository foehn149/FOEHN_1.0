package inc.thenewpirates.foehn;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class HomeActivity extends AppCompatActivity {
    DrawerLayout mdrawerlayout;
    Toolbar toolbar;
    NavigationView navView;
    FragmentTransaction transaction;
    FragmentManager fm;
    ActionBarDrawerToggle drawerToggle;
    Intent intent = new Intent();
    private GoogleApiClient client;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //  getSupportActionBar().setDisplayShowHomeEnabled(true);
        // getSupportActionBar().setLogo(R.mipmap.appicon);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        textview = (TextView) findViewById(R.id.cusupport);
        // Find our drawer view
        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Setup drawer toggle
        setNavigationDrawer();
        drawerToggle = new ActionBarDrawerToggle(
                this,
                mdrawerlayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }


    public void supportprocess(View view) {

        int id = view.getId();
        if (id == R.id.cusupport) {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to = {"support@foehn.comli.com"};
            intent.putExtra(intent.EXTRA_EMAIL, to);
            intent.setType("message/rfc822");
            intent = Intent.createChooser(intent, "Send Email");
            startActivity(intent);

        }

    }

    public void feedbackprocess(View view) throws NullPointerException {

        int id = view.getId();
        if (id == R.id.cuimagebutton) {

            Fragment frag1 = new FeedbackFragment();
            getSupportActionBar().setTitle("Feedback");
            transaction = getSupportFragmentManager().beginTransaction();
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_home, frag1).commit();
            mdrawerlayout.closeDrawers();
        }

    }
public void donateprocess(View view)throws NullPointerException {
    int id = view.getId();
    Fragment frag1 = null;
    if (id == R.id.food) {
        frag1 = new FoodDonateFragment();
        getSupportActionBar().setTitle("Food Donate");
    } else if (id == R.id.orphan) {
        frag1 = new OrphanDonateFragment();
        getSupportActionBar().setTitle("Orphan Donate");
    } else if (id == R.id.education) {
        frag1 = new EducationDonateFragment();
        getSupportActionBar().setTitle("Education Donate");
    } else if (id == R.id.health) {
        frag1 = new HealthDonateFragment();
        getSupportActionBar().setTitle("Health Donate");
    } else if (id == R.id.naturalcalamities) {

        frag1 = new NaturalCalamitiesDonateFragment();
        getSupportActionBar().setTitle("Natural Calamities Donate");
    }
    if (frag1 != null) {
        transaction = getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_home, frag1).commit();
        mdrawerlayout.closeDrawers();

    }
}


    @Override
    public void onBackPressed() throws NullPointerException {
        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert mdrawerlayout != null;
        if (mdrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mdrawerlayout.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }

    }


    public void setNavigationDrawer() throws NullPointerException {
        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                Fragment frag2 = null;
                int id = menuItem.getItemId();
                if (id == R.id.nav_home) {
                    intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.HomeActivity");
                    startActivity(intent);

                } else if (id == R.id.nav_donation) {
                    frag2 = new DonationFragment();
                    getSupportActionBar().setTitle("Donation");
                } else if (id == R.id.nav_store) {
                    frag2 = new StoreFragment();
                    getSupportActionBar().setTitle("Store");
                } else if (id == R.id.nav_contactus) {
                    frag2 = new ContactusFragment();
                    getSupportActionBar().setTitle("Contact us");
                } else if (id == R.id.nav_login) {
                    intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.LoginActivity");
                    startActivity(intent);
                }

                if (frag2 != null) {
                    transaction = getSupportFragmentManager().beginTransaction();
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_home, frag2).commit();
                    mdrawerlayout.closeDrawers();
                    return true;
                }
                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        int id = item.getItemId();
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else if (id == R.id.home) {
            //NavUtils.navigateUpFromSameTask(this);
            mdrawerlayout.openDrawer(GravityCompat.START);
        } else if (id == R.id.visit_foehn) {
            intent.setClassName("inc.thenewpirates.foehn", "inc.thenewpirates.foehn.WebviewActivity");
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Home Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://inc.thenewpirates.foehn/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }


    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Home Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://inc.thenewpirates.foehn/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);

        client.disconnect();
    }
}