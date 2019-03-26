package com.example.learnchatapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AppScreenActivity extends AppCompatActivity {

    private Toolbar actionbar;
    private ViewPager vpAppScreen;
    private TabLayout tabsAppScreen;
    private TabsAdapter tabsAdapter;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public void init(){

        actionbar = findViewById(R.id.actionBar);
        setSupportActionBar(actionbar);
        getSupportActionBar().setTitle(R.string.ChatApp);

        vpAppScreen = (ViewPager) findViewById(R.id.vpAppScreen);
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        vpAppScreen.setAdapter(tabsAdapter);

        tabsAppScreen = (TabLayout) findViewById(R.id.tabsAppScreen);
        tabsAppScreen.setupWithViewPager(vpAppScreen);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_screen);

        init();
    }




    @Override
    protected void onStart() { //daha once gris yapılmamıssa appscreen deil girişe yöneltme

        if(currentUser == null){

            Intent welcomeIntent = new Intent(AppScreenActivity.this,MainActivity.class);
            startActivity(welcomeIntent);
            finish();
        }

        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //menu olusturduk
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.mainLogout){

            auth.signOut();
            Intent loginIntent = new Intent(AppScreenActivity.this,LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }

        return true;
    }
}
