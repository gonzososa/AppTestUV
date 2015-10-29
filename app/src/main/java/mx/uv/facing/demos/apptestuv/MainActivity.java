package mx.uv.facing.demos.apptestuv;

import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    int index;

    public static final String TAG_SETTINGS ="Menú Ajustes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        if (getSupportActionBar () != null) {
            getSupportActionBar().setHomeAsUpIndicator (R.drawable.ic_nav_menu);
            getSupportActionBar().setHomeButtonEnabled (true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled (true);
        }

        drawerLayout = (DrawerLayout) findViewById (R.id.drawerLayout);


        navigationView = (NavigationView) findViewById (R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId ()) {
                    case R.id.nav_home:
                        index = menuItem.getOrder ();
                        loadGalleryFragment();
                        break;
                    case R.id.nav_foto:
                        index = menuItem.getOrder ();
                        loadCameraFragment ();
                        break;
                    case R.id.nav_setttings:
                        Snackbar.make(drawerLayout, TAG_SETTINGS, Snackbar.LENGTH_LONG).show ();
                }

                drawerLayout.closeDrawer (GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerLayout == null) return true;

        switch (item.getItemId()) {
            case android.R.id.home:
                    drawerLayout.openDrawer(GravityCompat.START);
                    return true;
            case R.id.menuSettings:
                Snackbar.make (drawerLayout, TAG_SETTINGS, Snackbar.LENGTH_LONG).show ();
                return true;
        }

        return super.onOptionsItemSelected (item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen (GravityCompat.START)) {
            drawerLayout.closeDrawer (GravityCompat.START);
            return;
        }

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate ();
            getSupportFragmentManager().beginTransaction().commit();

            navigationView.getMenu().getItem(index).setChecked (false);
            return;
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void loadGalleryFragment () {
        FragmentManager manager = getSupportFragmentManager ();
        FragmentTransaction trans = manager.beginTransaction();
        GalleryFragment f = new GalleryFragment ();
        trans.addToBackStack (null);
        trans.replace (R.id.main_content, f);
        trans.setTransition (FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.commit ();

    }

    private void loadCameraFragment ()
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        CameraFragment c = new CameraFragment();
        trans.addToBackStack (null);
        trans.replace (R.id.main_content, c);
        trans.setTransition (FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.commit ();
    }
}
