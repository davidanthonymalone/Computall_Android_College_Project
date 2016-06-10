package dd.app.computall.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import dd.app.computall.R;
import dd.app.computall.base.Base_Desktop;
import dd.app.computall.choose.Choose;
import dd.app.computall.objects.Desktop;

public class AddComputer extends Base_Desktop {

    private Spinner ramText;
    private Spinner screenText;
    private Spinner cpuText;
    private Spinner storageText;
    private EditText modelText;
    private EditText makeText;
    private EditText priceText;
    private Spinner towerText;
    private Spinner coolingText;
    private EditText idText;
    static AtomicInteger nextId= new AtomicInteger();
    private Button addButton;

    public static List<Desktop> desktopList = new ArrayList<Desktop>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        app.dbManager.open();

        // making links to the widgets in the xml file
        addButton = (Button) findViewById(R.id.addButton);
        ramText =   (Spinner) findViewById(R.id.ramText);
        screenText = (Spinner) findViewById(R.id.screenText2);
        cpuText =   (Spinner) findViewById(R.id.cpuText1);
        storageText = (Spinner) findViewById(R.id.storageText1);
        modelText = (EditText) findViewById(R.id.modelText1);
        makeText = (EditText) findViewById(R.id.makeText1);
        priceText = (EditText) findViewById(R.id.priceText);
        towerText = (Spinner) findViewById(R.id.towerType);
        coolingText = (Spinner) findViewById(R.id.coolingSystem);


        List<String> towerType = new ArrayList<String>();
        // setting arrays for the spinners, declaring spinner styles
        ArrayAdapter<String> adaper = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, towerType);
        adaper.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //lit for the spinner
        towerText.setAdapter(adaper);
        towerText.setPrompt("TowerSize");
        adaper.add("Tower Size");
        adaper.add("Mini");
        adaper.add("Full Size");




        List<String> cpuList = new ArrayList<String>();
        // setting arrays for the spinners, declaring spinner styles
        ArrayAdapter<String> cadaper = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cpuList);
        cadaper.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //lit for the spinner
        cpuText.setAdapter(cadaper);
        cpuText.setPrompt("TowerSize");
        cadaper.add("CPU Type");
        cadaper.add("Intel i7");
        cadaper.add("Intel i5");
        cadaper.add("Intel i3");
        cadaper.add("AMD A8");
        cadaper.add("AMD A10");





        List<String> storage = new ArrayList<String>();
        // setting arrays for the spinners, declaring spinner styles
        ArrayAdapter<String> stadaper = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, storage);
        stadaper.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //lit for the spinner
        storageText.setAdapter(stadaper);
        storageText.setPrompt("TowerSize");
        stadaper.add("Storage in GB");
        stadaper.add("500");
        stadaper.add("750");
        stadaper.add("1000");



        // setting arrays for the spinners, declaring spinner styles
        List<String> coolingSystem = new ArrayList<String>();
        ArrayAdapter<String> adapers = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, coolingSystem);
        adapers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//array list for spinner
        coolingText.setAdapter(adapers);
        coolingText.setPrompt("Cooling System");
        adapers.add("Cooling System");
        adapers.add("Fan Cooled");
        adapers.add("Liquid Cooling");



        List<String> screenSizeList = new ArrayList<String>();
        ArrayAdapter<String> sadapers = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, screenSizeList);
        sadapers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//array list for spinner
        screenText.setAdapter(sadapers);
        sadapers.add("Screen Size in inches");
        sadapers.add("11");
        sadapers.add("13");
        sadapers.add("15");
        sadapers.add("17");



        List<String> spinnerList = new ArrayList<String>();
        ArrayAdapter<String> spadapers = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerList);
        spadapers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//array list for spinner
        ramText.setAdapter(spadapers);
        spadapers.add("Ram in GB");
        spadapers.add("2");
        spadapers.add("4");
        spadapers.add("6");
        spadapers.add("8");
    }



    // creates menu option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {getMenuInflater().inflate(R.menu.menu_main_screen, menu);

        menu.findItem(R.id.addc).setVisible(false);

        return true;
    }

    // what menu does on option selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.pcDatabase : startActivity (new Intent(this, Database.class));
                break;
            case R.id.home: startActivity (new Intent(this, Choose.class));
                break;
            case R.id.searchmenu: startActivity(new Intent(this, SearchComputer.class));
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




    //what to do with back button pressed
    public void addButtonPressed(View view) {


        //Assigning different values to the different attributes from input form the user
        String towerz =  towerText.getSelectedItem().toString();


        String coolz =  coolingText.getSelectedItem().toString();


        String screen =  screenText.getSelectedItem().toString();



        String st = storageText.getSelectedItem().toString();
        String ramz = ramText.getSelectedItem().toString();
        String make = makeText.getText().toString();
        String model = modelText.getText().toString();
        String cpu = cpuText.getSelectedItem().toString();
        double price = 0;

        String ptext = priceText.getText().toString();
        if (!ptext.equals(""))
            price = Double.parseDouble(ptext);

        if(ramz == "Ram in GB" ||  screen == "Screen Size in inches" || st == "Storage in GB"  ){
            if(ramz == "Ram in GB" ){
                Toast.makeText(getApplicationContext(), "Ram is a required field, please pick one", Toast.LENGTH_SHORT).show();


            }else if(screen == "Screen Size in inches" ){
                Toast.makeText(getApplicationContext(), "Screen is a required field, please pick one", Toast.LENGTH_SHORT).show();


            }
            else if(st == "Storage in GB" ){
                Toast.makeText(getApplicationContext(), "Storage is a required field, please pick one", Toast.LENGTH_SHORT).show();


            }

        }else {

            double screenz = Double.parseDouble(screen);
            int ram = Integer.parseInt(ramz);
            int  storage = Integer.parseInt(st);




            // various validation so when the user presses add that the fields are full
            if (make != "" && model != "" && cpu != "" && ram > 0 && screenz > 0 && storage > 0 && price > 0 && coolz != "" && towerz != " ") {


                if (towerz == "Tower Size") {
                    Toast.makeText(getApplicationContext(), "Tower size is a required field, please pick one", Toast.LENGTH_SHORT).show();

                } else if (cpu == "CPU Type") {
                    Toast.makeText(getApplicationContext(), "CPU is a required field, please pick one", Toast.LENGTH_SHORT).show();


                }   else if (coolz == "Cooling System") {
                    Toast.makeText(getApplicationContext(), "Cooling System is a required field, please pick one", Toast.LENGTH_SHORT).show();

                } else if (make == "") {
                    Toast.makeText(getApplicationContext(), "Make is a required field, please pick one", Toast.LENGTH_SHORT).show();


                } else if (model == "") {
                    Toast.makeText(getApplicationContext(), "Model is a required field, please pick one", Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        int i = 0;
                        //adding a object of desktop to the database
                        app.dbManager.add(new Desktop(make, model, ram, storage, screenz, price, cpu, towerz, coolz));
                        Toast toastys = Toast.makeText(this, "You have added the comuter " + make + " " + model + " to the database", Toast.LENGTH_LONG);
                        toastys.show();
                        /*addButton = (Button) findViewById(R.id.addButton);
        ramText =   (Spinner) findViewById(R.id.ramText);
        screenText = (Spinner) findViewById(R.id.screenText2);
        cpuText =   (Spinner) findViewById(R.id.cpuText1);
        storageText = (Spinner) findViewById(R.id.storageText1);
        modelText = (EditText) findViewById(R.id.modelText1);
        makeText = (EditText) findViewById(R.id.makeText1);
        priceText = (EditText) findViewById(R.id.priceText);
        towerText = (Spinner) findViewById(R.id.towerType);
        coolingText = (Spinner) findViewById(R.id.coolingSystem);*/
                        makeText.setText(null);
                        modelText.setText(null);
                        priceText.setText(null);
                        towerText.setSelection(0);
                        storageText.setSelection(0);
                        screenText.setSelection(0);
                        cpuText.setSelection(0);
                        coolingText.setSelection(0);
                        ramText.setSelection(0);





                    } catch (Exception e) {
                        Intent intent = new Intent(AddComputer.this, AddComputer.class);
                        startActivity(intent);


                    }
                }

            }
        }







    }
}