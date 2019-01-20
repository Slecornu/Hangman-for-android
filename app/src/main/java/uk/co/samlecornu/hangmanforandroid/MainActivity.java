package uk.co.samlecornu.hangmanforandroid;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //VARS AND OBJ's================================================================================
    private Context context;
    private Activity activity;
    private FragmentManager frag_manager;
    private Menu_Interface menu_interface;
    private Settings_Interface settings_interface;
    private Game_Interface game_interface;
    private Word_Bank word_bank;
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //INITIALISING LAYOUT-----------------------------------------------------------------------
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //------------------------------------------------------------------------------------------

        //INITIALISING VARS AND OBJ's---------------------------------------------------------------
        activity = MainActivity.this;
        context = activity.getApplicationContext();
        frag_manager = getFragmentManager();
        word_bank = new Word_Bank(context);
        //------------------------------------------------------------------------------------------

        //App Launching
        if(savedInstanceState == null){
            load_menu();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.nav_home){
            /**check if they're in game! */
            load_menu();
        }else if(id == R.id.nav_settings){
            load_settings();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //FRAG LOADERS==================================================================================
    private void load_menu(){
        menu_interface = new Menu_Interface();
        frag_manager.beginTransaction().replace(R.id.content_frame, menu_interface, "menu_interface").commit();
    }
    private void load_settings(){
        settings_interface = new Settings_Interface();
        frag_manager.beginTransaction().replace(R.id.content_frame, settings_interface, "settings_interface").commit();
    }
    public void load_game(int difficulty, int score){
        String word = ""; // 1 = easy, 2 = medium, 3 = hard, 4 = random.
        switch (difficulty){
            case 1:  word = word_bank.getSmallWord();
                break;
            case 2:  word = word_bank.getMediumWord();
                break;
            case 3:  word = word_bank.getLargeWord();
                break;
            case 4:  word = word_bank.getRandomWord();
                break;
        }
        game_interface = new Game_Interface();
        Bundle bundle = new Bundle();
        bundle.putString("word", word);
        bundle.putInt("difficulty", difficulty);
        bundle.putInt("score", score);
        game_interface.setArguments(bundle);
        frag_manager.beginTransaction().replace(R.id.content_frame, game_interface, "game_interface").commit();
    }
    //==============================================================================================
}
