package dd.app.computall.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import dd.app.computall.R;
import dd.app.computall.base.Base_Desktop;
import dd.app.computall.choose.Choose;

/**
 * Created by User on 08/04/2016.
 */
public class AboutPage extends Base_Desktop {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        super.onCreateOptionsMenu(menu);

        menu.removeItem(R.id.searchmenu);
        menu.removeItem(R.id.addc);
        menu.removeItem(R.id.home);
        menu.removeItem(R.id.menuReset);
        menu.removeItem(R.id.about);
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
            case R.id.home: startActivity(new Intent(this, Choose.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void chooseWebPressed(View view) {

        Intent intent = new Intent(this, Web.class);
        startActivity(intent);
    }

    public void reset(MenuItem item) //When menu Item reset is clicked
    {
        app.dbManager.getDatabase().clear(); //Get everything in db and clear it
        app.dbManager.deleteDatabase(); //Reset the db
    }
}
