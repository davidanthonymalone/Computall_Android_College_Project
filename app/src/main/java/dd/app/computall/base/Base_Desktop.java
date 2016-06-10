package dd.app.computall.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


import dd.app.computall.activities.Database;
import dd.app.computall.activities.AddComputer;
import dd.app.computall.R;
import dd.app.computall.activities.Login;
import dd.app.computall.activities.SearchComputer;
import dd.app.computall.choose.Choose;
import dd.app.computall.main.ComputerApp;



//base class
public class Base_Desktop extends AppCompatActivity
{
    public Long ID;
    public String make;
    public String model;
    public int ram;
    public int storage;
    public int screen;
    public int price;
    public String cpu;
    public String towerType;
    public String coolingSystem;

    //new computer app from the compter application class

    public ComputerApp app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (ComputerApp) getApplication();
    }



   // inflates the menu in the action bar

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }


    // if statement for the menu option in the action bar.
    @Override
    public boolean onPrepareOptionsMenu (Menu menu){
        super.onPrepareOptionsMenu(menu);
        MenuItem database = menu.findItem(R.id.pcDatabase);
        MenuItem main =  menu.findItem(R.id.addc);
        MenuItem home = menu.findItem(R.id.home);
        MenuItem reset = menu.findItem(R.id.menuReset);
        MenuItem searchmenu = menu.findItem(R.id.searchmenu);
        MenuItem about = menu.findItem(R.id.about);
        MenuItem logout = menu.findItem(R.id.menuLogout);


        app.dbManager.open();

        if(app.dbManager.getDatabase().isEmpty())
        {
            database.setEnabled(false);
            reset.setEnabled(false);
        }
        else {
            database.setEnabled(true);
            reset.setEnabled(true);
        }
        if(this instanceof Choose){
            home.setVisible(false);
            if(!app.dbManager.getDatabase().isEmpty())
            {
                database.setVisible(true);
            }
        }
        else {
            main.setVisible(true);
            home.setVisible(true);
            database.setVisible(false);
        }
        if(this instanceof AddComputer) {
            main.setVisible(false);
            if (!app.dbManager.getDatabase().isEmpty()) {
                database.setVisible(true);
            } else {
                database.setVisible(true);
            }
            if (this instanceof Database) {
                database.setVisible(false);
            }


           /* if(this instanceof AboutPage){
                about.setVisible(false);
                if(!app.dbManager.getDatabase().isEmpty())
                {
                    database.setVisible(true);
                }
            }*/



                if (this instanceof SearchComputer) {
                    searchmenu.setVisible(false);
                    database.setVisible(true);
                    if (!app.dbManager.getDatabase().isEmpty()) {
                        database.setVisible(true);
                    } else {
                        database.setVisible(true);
                    }

                }
            }
            return true;
        }



  public void reset(MenuItem item) //When menu Item reset is clicked
{
    app.dbManager.getDatabase().clear(); //Get everything in db and clear it
    app.dbManager.deleteDatabase(); //Reset the db
}


    public void logout(MenuItem item)
    {
        SharedPreferences.Editor editor = getSharedPreferences("loginPrefs", 0).edit();
        editor.putBoolean("loggedin", false);
        editor.commit();


        startActivity(new Intent(Base_Desktop.this, Login.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }
}