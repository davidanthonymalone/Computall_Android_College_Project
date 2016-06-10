package dd.app.computall.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import dd.app.computall.R;
import dd.app.computall.base.Base_Desktop;
import dd.app.computall.choose.Choose;

/**
 * Created by User on 06/04/2016.
 */
public class SearchComputer extends Base_Desktop
{


    private EditText searchView;
    private EditText modelEditText;
    private EditText ramEditText;
    private EditText storageEditText;
    private EditText screenEditText;
    private EditText priceEditText;
    private EditText cpuEditText;
    private EditText towerEditText;
    private EditText coolingEditText;
    private EditText allEditText;
    ArrayAdapter sAdapter;


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list_computers);
        searchView = (EditText)findViewById(R.id.searchView);
        modelEditText= (EditText)findViewById(R.id.modelText);
        cpuEditText = (EditText)findViewById(R.id.editText6);
        towerEditText = (EditText)findViewById(R.id.editText7);
        coolingEditText = (EditText)findViewById(R.id.editText8);
        allEditText = (EditText)findViewById(R.id.editText9);


    }



    public void searchDBButtonPressed(View view)

    {
        String text = allEditText.getText().toString();
        final Cursor cursor = app.dbManager.allse(text);
        AlertDialog.Builder alertBuild = new AlertDialog.Builder(this);
        if (text.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter the query you want to search for", Toast.LENGTH_SHORT).show();


        } else {
            alertBuild.setTitle("You searched for: " + text + " -- Results shown below");

            sAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item);

            while (cursor.moveToNext()) {
                sAdapter.add("ID: " + cursor.getString(0) + "\n" + "Make: " + cursor.getString(1) + "\n" + "Model: " + cursor.getString(2) + "\n" + "Ram: " + cursor.getInt(3)
                        + " GB" + "\n" + "Storage: " + cursor.getInt(4)  + " GB" + "\n" + "Screen Size: " + cursor.getInt(5)  + " Inches" + "\n" + "Price: " + "€ " + cursor.getString(6) +  "\n" + "CPU: " + cursor.getString(7) + "\n"
                        + "Tower Type: " + cursor.getString(8) + "\n" + "Cooling System: " + cursor.getString(9) + "\n");
            }


            alertBuild.setAdapter(sAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    if (cursor.moveToPosition(which)) {


                        dialog.dismiss();
                    }

                }
            });

            alertBuild.show();
        }
    }



    public void searchMakeButtonPressed(View view) {
        String text = searchView.getText().toString();
        final Cursor cursor = app.dbManager.makese(text);
        if (text.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter the make you want to search for", Toast.LENGTH_SHORT).show();


        } else {
            AlertDialog.Builder alertBuild = new AlertDialog.Builder(this);

            alertBuild.setTitle("You searched for: " + text + " -- Results shown below");

            sAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item);

            while (cursor.moveToNext()) {
                sAdapter.add("ID: " + cursor.getString(0) + "\n" + "Make: " + cursor.getString(1) + "\n" + "Model: " + cursor.getString(2) + "\n" + "Ram: " + cursor.getInt(3)
                        + " GB" + "\n" + "Storage: " + cursor.getInt(4)  + " GB" + "\n" + "Screen Size: " + cursor.getInt(5)  + " Inches" + "\n" + "Price: " + "€ " + cursor.getString(6) +  "\n" + "CPU: " + cursor.getString(7) + "\n"
                        + "Tower Type: " + cursor.getString(8) + "\n" + "Cooling System: " + cursor.getString(9) + "\n");
            }


            alertBuild.setAdapter(sAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    if (cursor.moveToPosition(which)) {


                        dialog.dismiss();
                    }

                }
            });

            alertBuild.show();
        }
    }
    //----------------------------------------------------------------------------------------------------------
    public void searchModelButtonPressed(View view) {
        String text1 = modelEditText.getText().toString();
        final Cursor cursor = app.dbManager.modelse(text1);
        if (text1.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter the model you want to search for", Toast.LENGTH_SHORT).show();


        } else {
        AlertDialog.Builder alertBuild = new AlertDialog.Builder(this);

            alertBuild.setTitle("You searched for: " + text1 + " -- Results shown below");

            sAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item);

            while (cursor.moveToNext()) {
                sAdapter.add("ID: " + cursor.getString(0) + "\n" + "Make: " + cursor.getString(1) + "\n" + "Model: " + cursor.getString(2) + "\n" + "Ram: " + cursor.getInt(3)
                        + " GB" + "\n" + "Storage: " + cursor.getInt(4)  + " GB" + "\n" + "Screen Size: " + cursor.getInt(5)  + " Inches" + "\n" + "Price: " + "€ " + cursor.getString(6) +  "\n" + "CPU: " + cursor.getString(7) + "\n"
                        + "Tower Type: " + cursor.getString(8) + "\n" + "Cooling System: " + cursor.getString(9) + "\n");
            }


            alertBuild.setAdapter(sAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    if (cursor.moveToPosition(which)) {


                        dialog.dismiss();
                    }

                }
            });

            alertBuild.show();
        }
    }



    //----------------------------------------------------------------------------------------------------------
    public void searchCPUButtonPressed(View view) {
        String text = cpuEditText.getText().toString();
        final Cursor cursor = app.dbManager.cpuse(text);
        if (text.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter the cpu you want to search for", Toast.LENGTH_SHORT).show();


        } else {
            AlertDialog.Builder alertBuild = new AlertDialog.Builder(this);

            alertBuild.setTitle("You searched for: " + text + " -- Results shown below");

            sAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item);

            while (cursor.moveToNext()) {
                sAdapter.add("ID: " + cursor.getString(0) + "\n" + "Make: " + cursor.getString(1) + "\n" + "Model: " + cursor.getString(2) + "\n" + "Ram: " + cursor.getInt(3)
                        + " GB" + "\n" + "Storage: " + cursor.getInt(4)  + " GB" + "\n" + "Screen Size: " + cursor.getInt(5)  + " Inches" + "\n" + "Price: " + "€ " + cursor.getString(6) +  "\n" + "CPU: " + cursor.getString(7) + "\n"
                        + "Tower Type: " + cursor.getString(8) + "\n" + "Cooling System: " + cursor.getString(9) + "\n");
            }


            alertBuild.setAdapter(sAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    if (cursor.moveToPosition(which)) {


                        dialog.dismiss();
                    }

                }
            });

            alertBuild.show();
        }
    }

    //----------------------------------------------------------------------------------------------------------
    public void searchTowerButtonPressed(View view) {
        String text1 = towerEditText.getText().toString();
        final Cursor cursor = app.dbManager.towerse(text1);
        if (text1.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter the tower type you want to search for", Toast.LENGTH_SHORT).show();


        } else {
            AlertDialog.Builder alertBuild = new AlertDialog.Builder(this);

            alertBuild.setTitle("You searched for: " + text1 + " -- Results shown below");

            sAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item);

            while (cursor.moveToNext()) {
                sAdapter.add("ID: " + cursor.getString(0) + "\n" + "Make: " + cursor.getString(1) + "\n" + "Model: " + cursor.getString(2) + "\n" + "Ram: " + cursor.getInt(3)
                        + " GB" + "\n" + "Storage: " + cursor.getInt(4)  + " GB" + "\n" + "Screen Size: " + cursor.getInt(5)  + " Inches" + "\n" + "Price: " + "€ " + cursor.getString(6) +  "\n" + "CPU: " + cursor.getString(7) + "\n"
                        + "Tower Type: " + cursor.getString(8) + "\n" + "Cooling System: " + cursor.getString(9) + "\n");
            }


            alertBuild.setAdapter(sAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    if (cursor.moveToPosition(which)) {


                        dialog.dismiss();
                    }

                }
            });

            alertBuild.show();
        }
    }



    //----------------------------------------------------------------------------------------------------------
    public void searchCoolButtonPressed(View view) {
        String text = coolingEditText.getText().toString();
        final Cursor cursor = app.dbManager.coolse(text);
        if (text.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter the Cooling System you want to search for", Toast.LENGTH_SHORT).show();


        } else {
            AlertDialog.Builder alertBuild = new AlertDialog.Builder(this);

            alertBuild.setTitle("You searched for: " + text + " -- Results shown below");

            sAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item);

            while (cursor.moveToNext()) {
                sAdapter.add("ID: " + cursor.getString(0) + "\n" + "Make: " + cursor.getString(1) + "\n" + "Model: " + cursor.getString(2) + "\n" + "Ram: " + cursor.getInt(3)
                        + " GB" + "\n" + "Storage: " + cursor.getInt(4)  + " GB" + "\n" + "Screen Size: " + cursor.getInt(5)  + " Inches" + "\n" + "Price: " + "€ " + cursor.getString(6) +  "\n" + "CPU: " + cursor.getString(7) + "\n"
                        + "Tower Type: " + cursor.getString(8) + "\n" + "Cooling System: " + cursor.getString(9) + "\n");
            }


            alertBuild.setAdapter(sAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    if (cursor.moveToPosition(which)) {


                        dialog.dismiss();
                    }

                }
            });

            alertBuild.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        super.onCreateOptionsMenu(menu);

        menu.removeItem(R.id.searchmenu);
        menu.removeItem(R.id.addc);
        menu.removeItem(R.id.home);
        menu.removeItem(R.id.menuReset);
        menu.removeItem(R.id.searchmenu);
        menu.removeItem(R.id.about);
        menu.removeItem(R.id.menuLogout);


        if (!app.dbManager.getDatabase().isEmpty())
        {
            menu.getItem(2).setEnabled(true);
        }
        else
        {
            menu.getItem(2).setEnabled(false);
        }


        return true;
    }

    // what menu does on option selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

            case R.id.pcDatabase: startActivity(new Intent(this, Database.class));
                break;
            case R.id.home: startActivity(new Intent(this, Choose.class));
                break;
            case R.id.addc: startActivity(new Intent(this, AddComputer.class));
                break;
            case R.id.about: startActivity(new Intent(this, AboutPage.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    public void reset(MenuItem item) //When menu Item reset is clicked
    {
        app.dbManager.getDatabase().clear(); //Get everything in db and clear it
        app.dbManager.deleteDatabase(); //Reset the db
    }
}



