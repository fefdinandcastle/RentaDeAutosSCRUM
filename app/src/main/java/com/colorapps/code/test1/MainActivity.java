package com.colorapps.code.test1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.colorapps.code.test1.Fragments.AlbumsFragment;
import com.colorapps.code.test1.Fragments.ArtistsFragment;
import com.colorapps.code.test1.Fragments.SongsFragment;
import com.colorapps.code.test1.Fragments.TabsFragment;
import com.colorapps.code.test1.Helpers.MusicRetriever;
import com.colorapps.code.test1.Model.Album;
import com.colorapps.code.test1.Model.Song;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TabsFragment.OnFragmentInteractionListener,SongsFragment.OnFragmentInteractionListener,AlbumsFragment.OnFragmentInteractionListener,ArtistsFragment.OnFragmentInteractionListener{

    private static final int MY_PERMISSION_REQUEST=1;
    public static ArrayList<Song> songs;
    public static ArrayList<Album> albums;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.NightMode);
        }else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager=getSupportFragmentManager();
        songs =new ArrayList<Song>();
        albums=new ArrayList<Album>();
        requestPermission();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setFragment(new TabsFragment());
    }

    public void setFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.main_fragment, fragment);
        t.commit();
    }

    public void requestPermission(){
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }
            else{
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }
        }
        else{
            try {
                MusicRetriever.loadAlbums(MainActivity.this);
                Toast.makeText(getApplicationContext(),"Hay: "+MusicRetriever.getAlbums().size()+" Canciones pedir permsio",Toast.LENGTH_SHORT ).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_REQUEST: {
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(MainActivity.this,"Permiso concedido",Toast.LENGTH_SHORT).show();
                        try {
                            MusicRetriever.loadAlbums(MainActivity.this);
                            Toast.makeText(getApplicationContext(),"Hay: "+albums.size()+" Canciones despues permiso",Toast.LENGTH_SHORT ).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Permiso no concedido",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem itemSwitch = menu.findItem(R.id.nav_item1);
        itemSwitch.setActionView(R.layout.menu_switch);
        final ToggleButton sw = (ToggleButton) menu.findItem(R.id.nav_item1).getActionView().findViewById(R.id.toggle);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            sw.setChecked(true);
        }
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
            }

            public void restartApp(){
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                MainActivity.this.finish();
            }

        });


        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.nav_item1:
                Toast.makeText(this,"Tocaste",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    public void startFragment(){
        Fragment fragment;
        fragmentTransaction=fragmentManager.beginTransaction();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }




}
