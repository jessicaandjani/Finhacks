package com.example.finhacks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * Created by asus on 8/26/2017.
 */
public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private GridViewWithHeaderAndFooter gridView;
    private ArrayAdapter<Integer> adapter;
    private Integer[] drawablesResource = {R.drawable.contact, R.drawable.joint_account };
    private String[] categoryNames = {"Contact", "Joint Account"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Toolbar Section
        toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        //Drawer Layout
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        Menu navMenu = nvDrawer.getMenu();
        setupDrawerContent(nvDrawer);
        //Grid View
        gridView=(GridViewWithHeaderAndFooter) findViewById(R.id.category);
        setGridViewHeaderAndFooter();
        adapter = new CategoryAdapter(this, R.layout.activity_category, drawablesResource, categoryNames);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intentContact = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intentContact);
                        break;
                    case 1:
                        Intent intentGroup = new Intent(MainActivity.this, GroupActivity.class);
                        startActivity(intentGroup);
                }
            }
        });
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    private void setGridViewHeaderAndFooter() {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View headerView = layoutInflater.inflate(R.layout.header_home, null, false);

        //Locate Views
        ImageView headerImage = (ImageView)headerView.findViewById(R.id.header_home);
        headerImage.setImageResource(R.drawable.register_background);
        gridView.addHeaderView(headerView);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intentHome = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intentHome);
                break;
            case R.id.nav_login:
                Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.nav_profile:
                Intent intentProfile = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intentProfile);
                break;
            case R.id.nav_logout:
                Intent intentLogout = new Intent(MainActivity.this, LoginActivity.class);
                intentLogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentLogout);
                break;
            default:
                Intent intentDefault = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intentDefault);
        }
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }

}
