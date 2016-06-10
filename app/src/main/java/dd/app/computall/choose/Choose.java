package dd.app.computall.choose;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import dd.app.computall.activities.AboutPage;
import dd.app.computall.activities.Database;
import dd.app.computall.activities.AddComputer;
import dd.app.computall.R;
import dd.app.computall.activities.SearchComputer;
import dd.app.computall.base.Base_Desktop;


public class Choose extends Base_Desktop {

    private Button choosePC;
    private Button chooseStock;
    private Button chooseSearch;
    private SharedPreferences settings;
    private TextView name;
    private TextView number;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
        choosePC = (Button) findViewById(R.id.pickPC);
        chooseStock = (Button) findViewById(R.id.viewStock);
        chooseSearch = (Button) findViewById(R.id.openSearch);

        settings = getSharedPreferences("loginPrefs", 0);
        String username = settings.getString("username", "");

        SpannableString spanString = new SpannableString(username);
        spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
        spanString.setSpan(new StyleSpan(Typeface.ITALIC), 0, spanString.length(), 0);

        name = (TextView) findViewById(R.id.name);
        name.setText(username);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pcDatabase:
                startActivity(new Intent(this, Database.class));
                break;
            case R.id.addc:
                startActivity(new Intent(this, AddComputer.class));
                break;
            case R.id.searchmenu: startActivity(new Intent(this, SearchComputer.class));
                break;
            case R.id.about: startActivity(new Intent(this, AboutPage.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void chooseDBPressed(View view) {


        if (app.dbManager.getDatabase().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Database is Empty, Please Enter some Content", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(Choose.this, Database.class);
            startActivity(intent);
        }
    }

    public void choosePcPressed(View view) {

        Intent intent = new Intent(Choose.this, AddComputer.class);
        startActivity(intent);
    }


    public void chooseSearchPressed(View view) {


        Intent intent = new Intent(Choose.this, SearchComputer.class);
        startActivity(intent);


    }

    public void chooseAboutPressed(View view) {

        Intent intent = new Intent(Choose.this, AboutPage.class);
        startActivity(intent);
    }

    public void reset(MenuItem item) //When menu Item reset is clicked
    {
        app.dbManager.getDatabase().clear(); //Get everything in db and clear it
        app.dbManager.deleteDatabase(); //Reset the db
    }


    // method so that asks the user for confirmation before they delete the database
    private AlertDialog AskOption()
    {
        AlertDialog DeleteDatabaseDialogue =new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.delete)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int deleteButton) {
                        app.dbManager.deleteDatabase();
                        dialog.dismiss();
                    }

                })
                        // cancel so it doesn't delete
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int Cancel) {

                        dialog.dismiss();

                    }
                })
                .create();
        return DeleteDatabaseDialogue;

    }

    //goes deletes stock if there is anything in stock
    public void chooseStockPressed(View view) {

        if(app.dbManager.getDatabase().isEmpty()) {

            Toast toastys = Toast.makeText(this, "There is nothing in stock", Toast.LENGTH_LONG);
            toastys.show();
        }
        else{
            AlertDialog diaBox = AskOption();
            diaBox.show();

        }
    }
    }

