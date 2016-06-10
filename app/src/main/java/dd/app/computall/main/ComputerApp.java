package dd.app.computall.main;

import android.app.Application;
import android.widget.Toast;

import dd.app.computall.database.DBManager;
import dd.app.computall.objects.Desktop;


public class ComputerApp extends Application {


    // declaring attributes
    public String make;
    public String model;
    public int ram;
    public int storage;
    public int screen;
    public int price;
    public String cpu;

    public DBManager dbManager;

    public boolean newDesktop(Desktop desktop)

    {
        // doing a check to see if everything is validated before its being added.  If true it addes, if not it lets user know to enter all fields
        boolean check = ram>0 && screen > 00 && model !="" && make != "" && storage != 0;
        if (true)
        {

            dbManager.add(desktop);

            if(dbManager.getDatabase().isEmpty())
            {
                Toast toast = Toast.makeText(this, "The Database Appears to be Empty", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else
        {
            Toast toast = Toast.makeText(this, "Enter all required specifications", Toast.LENGTH_LONG);
            toast.show();
        }
        return check;
    }

    // opens database on opening
    @Override
    public void onCreate()
    {
        super.onCreate();
        dbManager = new DBManager(this);


    }


}
