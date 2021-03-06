package com.example.pareddehierro.eftaplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


/**
 * @author Adan
 */
public class MainActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        FragmentInicio.OnFragmentInteractionListener,
        FragmentMapas.OnFragmentInteractionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
                );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        // }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean FragmentTransaction = false;
        Fragment frag = null;


        if (id == R.id.nav_inicio)
        {
            Log.i("NavigationDrawer","Entrando en Inicio");
            frag = new FragmentInicio();
            FragmentTransaction = true;
        }
        else if (id == R.id.nav_mapas)
        {
            Log.i("NavigationDrawer","Entrando en Mapas");
            frag = new FragmentMapas();
            FragmentTransaction = true;
        }
        else if (id == R.id.nav_historia)
        {
            Log.i("NavigationDrawer","Entrando en historia");
            frag = new FragmentHistoria();
            FragmentTransaction = true;
        }
        else if (id == R.id.nav_habilidades)
        {
            Log.i("NavigationDrawer","Entrando en habilidades");
            frag = new FragmentHabilidades();
            FragmentTransaction = true;
        }
        else if (id == R.id.nav_armas)
        {
            Log.i("NavigationDrawer","Entrando en Armas");
            frag = new FragmentArmasP();
            FragmentTransaction = true;
        }
        else if (id == R.id.nav_about)
        {
            Log.i("NavigationDrawer","Entrando en about");
            frag = new FragmentAbout();
            FragmentTransaction = true;
        }
        else if (id == R.id.nav_settings)
        {
            Log.i("NavigationDrawer","Entrando en settings");
            frag = new FragmentSettings();
            FragmentTransaction = true;
        }

        if(FragmentTransaction==true)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, frag).commit();

            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
