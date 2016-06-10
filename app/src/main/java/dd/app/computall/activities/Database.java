package dd.app.computall.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dd.app.computall.R;
import dd.app.computall.base.Base_Desktop;
import dd.app.computall.objects.Desktop;

import dd.app.computall.choose.Choose;


public class Database extends Base_Desktop
        {

    public void onBackPressed()
    {
        Intent intent = new Intent(Database.this, Choose.class);
        startActivity(intent);
        app.dbManager.close();
    }


//--------------------------------------------------------------------------------------------------

   public  ListView listview;
    public int pos;
    List<ListMenuItemView> item;

    android.widget.ArrayAdapter<String> listAdapter;


//--------------------------------------------------------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_list_computers);
        // listview from xml
        listview = (ListView) findViewById(R.id.reportList);

        final ArrayAdapter adapter = new ArrayAdapter(this,  app.dbManager.getDatabase());
        listview.setAdapter(adapter);
        listAdapter = adapter;


//--------------------------------------------------------------------------------------------------



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            // on click gets the view that is clicked, pos and


            @Override
            public void onItemClick(final AdapterView<?> parent, final View v, final int pos,
                                    final long id)
            {
                //dialogue box that will ask user if they are sure they want to delete the items positions tostring which is the id
                final String delete = parent.getItemAtPosition(pos).toString();
                final  AlertDialog.Builder alert = new AlertDialog.Builder(Database.this);
                alert.setTitle("Delete or Update");
                alert.setMessage("Do you want to delete or update this item \n");

                AlertDialog.Builder alert1 = new AlertDialog.Builder(Database.this);

                alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                                Database.this);

                        alertDialog2.setTitle("Confirm Delete...");

                        alertDialog2.setMessage("Are you sure you want delete the file with the contents :\n\n" + parent.getItemAtPosition(pos).toString());

                        alertDialog2.setIcon(R.drawable.delete);

                        alertDialog2.setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // if yes is selected there is some sql used to delete the item at that row
                                        Long ID = app.dbManager.getDatabase().get(pos).id;
                                        adapter.remove(adapter.getItem(pos));
                                        app.dbManager.database.execSQL("DELETE FROM desktop WHERE id = " + ID);
                                        Toast.makeText(getApplicationContext(), "Item Successfully Deleted", Toast.LENGTH_SHORT).show();
                                        listview.removeViewInLayout(v);
                                        listview.deferNotifyDataSetChanged();
                                    }
                                });

                        alertDialog2.setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Write your code here to execute after dialog
                                        Toast.makeText(getApplicationContext(),
                                                "Deletion Cancelled", Toast.LENGTH_SHORT)
                                                .show();
                                        dialog.cancel();
                                    }
                                });

                        alertDialog2.show();


                    }
                });

//--------------------------------------------------------------------------------------------------



                //if cancel is selected then nothing happens
                alert.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Long ID = app.dbManager.getDatabase().get(pos).id;
                                CharSequence upSel[] = new CharSequence[] {"Make", "Model", "Price", "Ram", "Screen Size", "Storage", "CPU", "Tower Type", "Cooling System"};

                                AlertDialog.Builder builder = new AlertDialog.Builder(Database.this);
                                builder.setTitle("Select Column You Wish to Update");
                                builder.setItems(upSel, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (which==0)
                                        {
                                            final Long ID = app.dbManager.getDatabase().get(pos).id;
                                            if (ID==app.dbManager.getDatabase().get(pos).id) {
                                               final EditText et = new EditText(Database.this);
                                                et.setInputType(InputType.TYPE_CLASS_TEXT);

                                                AlertDialog alertDialog = new AlertDialog.Builder(
                                                        Database.this).create();
                                                alertDialog.setTitle("Update Make");
                                                alertDialog.setView(et);
                                                alertDialog.setButton("Confirm", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        String upmake =  et.getText().toString();
                                                        app.dbManager.database.execSQL("UPDATE desktop set make = " + "'" + upmake + "'" + " WHERE id = " + ID);
                                                        Toast.makeText(getApplicationContext(), "Make is now " +  upmake, Toast.LENGTH_SHORT).show();
                                                        if(upmake.isEmpty()==false)
                                                        {

                                                        }
                                                    }
                                                });
                                                alertDialog.show();
                                            }
                                        }




                                        if (which==1)
                                        {
                                            final Long ID = app.dbManager.getDatabase().get(pos).id;
                                            if (ID==app.dbManager.getDatabase().get(pos).id) {
                                                final EditText et = new EditText(Database.this);
                                                et.setInputType(InputType.TYPE_CLASS_TEXT);
                                                AlertDialog alertDialog = new AlertDialog.Builder(
                                                        Database.this).create();
                                                alertDialog.setTitle("Update Model");
                                                alertDialog.setView(et);
                                                alertDialog.setButton("Confirm", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        String upmodel =  et.getText().toString();
                                                        app.dbManager.database.execSQL("UPDATE desktop set model = " + "'" + upmodel + "'" + " WHERE id = " + ID);
                                                        Toast.makeText(getApplicationContext(), "You have update model to " + upmodel, Toast.LENGTH_SHORT).show();
                                                        app.dbManager.close();
                                                        app.dbManager.open();
                                                        if(upmodel.isEmpty()==false)
                                                        {
                                                            Intent a = new Intent(Database.this, Database.class);
                                                            startActivity(a);
                                                        }
                                                    }
                                                });
                                                alertDialog.show();
                                                listview.deferNotifyDataSetChanged();

                                            }
                                        }



                                        if (which==2)
                                        {
                                            final Long ID = app.dbManager.getDatabase().get(pos).id;
                                            if (ID==app.dbManager.getDatabase().get(pos).id) {
                                                final EditText et = new EditText(Database.this);
                                                et.setInputType(InputType.TYPE_CLASS_NUMBER);
                                                AlertDialog alertDialog = new AlertDialog.Builder(
                                                        Database.this).create();
                                                alertDialog.setTitle("Update Price");
                                                alertDialog.setView(et);
                                                alertDialog.setButton("Confirm", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        String temp = et.getText().toString();
                                                        Double upprice;
                                                        upprice = Double.parseDouble(temp);
                                                        app.dbManager.database.execSQL("UPDATE desktop set price = " + "'" + upprice + "'" + " WHERE id = " + ID);
                                                        Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                                                        if(upprice.toString().isEmpty()==false) {
                                                            Intent a = new Intent(Database.this, Database.class);
                                                            startActivity(a);
                                                        }
                                                    }
                                                });
                                                alertDialog.show();
                                                listview.deferNotifyDataSetChanged();

                                            }
                                        }



                                        if (which==3) {
                                            final Long ID = app.dbManager.getDatabase().get(pos).id;
                                            if (ID == app.dbManager.getDatabase().get(pos).id) {
                                                CharSequence ram[] = new CharSequence[] {"2", "4", "6", "8"};

                                                AlertDialog.Builder builder = new AlertDialog.Builder(Database.this);
                                                builder.setTitle("Choose a ram amount");
                                                builder.setItems(ram, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {


                                                        if (which == 0) {
                                                            int i = 2;

                                                            app.dbManager.database.execSQL("UPDATE desktop set ram = " + "'" + i + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the ram to " + i, Toast.LENGTH_SHORT).show();
                                                            if(Integer.toString(i).isEmpty()==false)
                                                            {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }


                                                        if (which == 1) {
                                                            int ii = 4;
                                                            app.dbManager.database.execSQL("UPDATE desktop set ram = " + "'" + ii + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the ram to " + ii, Toast.LENGTH_SHORT).show();
                                                            if(Integer.toString(ii).isEmpty()==false)
                                                            {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }


                                                        if (which == 2) {
                                                            int iii = 6;
                                                            app.dbManager.database.execSQL("UPDATE desktop set ram = " + "'" + iii + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the ram to " + iii, Toast.LENGTH_SHORT).show();
                                                            if(Integer.toString(iii).isEmpty()==false)
                                                            {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }


                                                        if (which == 3) {
                                                            int iv = 8;
                                                            app.dbManager.database.execSQL("UPDATE desktop set ram = " + "'" + iv + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the ram to " + iv, Toast.LENGTH_SHORT).show();
                                                            if(Integer.toString(iv).isEmpty()==false)
                                                            {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }
                                                    }
                                                });
                                                builder.show();
                                            }
                                        }




                                        if (which==4)
                                        {
                                            final Long ID = app.dbManager.getDatabase().get(pos).id;
                                        if (ID == app.dbManager.getDatabase().get(pos).id) {
                                            CharSequence screen[] = new CharSequence[] {"11", "13", "15", "17"};

                                            AlertDialog.Builder builder = new AlertDialog.Builder(Database.this);
                                            builder.setTitle("Choose a screen size");
                                            builder.setItems(screen, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {


                                                    if (which==0)
                                                    {
                                                        int i = 11;
                                                        app.dbManager.database.execSQL("UPDATE desktop set screen = " + "'" + i + "'" + " WHERE id = " + ID);
                                                        Toast.makeText(getApplicationContext(), "You updated the screen to " + i, Toast.LENGTH_SHORT).show();
                                                        if(Integer.toString(i).isEmpty()==false)
                                                        {
                                                            Intent a = new Intent(Database.this, Database.class);
                                                            startActivity(a);
                                                        }
                                                    }


                                                    if (which==1)
                                                    {
                                                        int ii = 13;
                                                        app.dbManager.database.execSQL("UPDATE desktop set screen = " + "'" + ii + "'" + " WHERE id = " + ID);
                                                        Toast.makeText(getApplicationContext(), "You updated the screen to " + ii, Toast.LENGTH_SHORT).show();
                                                        if(Integer.toString(ii).isEmpty()==false)
                                                        {
                                                            Intent a = new Intent(Database.this, Database.class);
                                                            startActivity(a);
                                                        }
                                                    }


                                                    if (which==2)
                                                    {
                                                        int iii = 15;
                                                        app.dbManager.database.execSQL("UPDATE desktop set screen = " + "'" + iii + "'" + " WHERE id = " + ID);
                                                        Toast.makeText(getApplicationContext(), "You updated the screen to " + iii, Toast.LENGTH_SHORT).show();
                                                        if(Integer.toString(iii).isEmpty()==false)
                                                        {
                                                            Intent a = new Intent(Database.this, Database.class);
                                                            startActivity(a);
                                                        }
                                                    }


                                                    if (which==3)
                                                    {
                                                        int iv = 17;
                                                        app.dbManager.database.execSQL("UPDATE desktop set screen = " + "'" + iv + "'" + " WHERE id = " + ID);
                                                        Toast.makeText(getApplicationContext(), "You updated the screen to " + iv, Toast.LENGTH_SHORT).show();
                                                        if(Integer.toString(iv).isEmpty()==false)
                                                        {
                                                            Intent a = new Intent(Database.this, Database.class);
                                                            startActivity(a);
                                                        }
                                                    }
                                                }
                                            });
                                            builder.show();
                                        }
                                    }



                                        if (which==5)
                                        {


                                            final Long ID = app.dbManager.getDatabase().get(pos).id;
                                            if (ID == app.dbManager.getDatabase().get(pos).id) {
                                                CharSequence store[] = new CharSequence[] {"500", "750", "1000"};

                                                AlertDialog.Builder builder = new AlertDialog.Builder(Database.this);
                                                builder.setTitle("Choose a Storage amount");
                                                builder.setItems(store, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (which==0)

                                                        {
                                                            int i = 500;
                                                            app.dbManager.database.execSQL("UPDATE desktop set storage = " + "'" + i + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the storage to " + i, Toast.LENGTH_SHORT).show();
                                                            if(Integer.toString(i).isEmpty()==false)
                                                            {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }
                                                        if (which==1)

                                                        {
                                                            int ii = 750;
                                                            app.dbManager.database.execSQL("UPDATE desktop set storage = " + "'" + ii + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the storage to " + ii, Toast.LENGTH_SHORT).show();
                                                            if(Integer.toString(ii).isEmpty()==false)
                                                            {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }
                                                        if (which==2)

                                                        {
                                                            int iii = 1000;
                                                            app.dbManager.database.execSQL("UPDATE desktop set storage = " + "'" + iii + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the storage to " + iii, Toast.LENGTH_SHORT).show();
                                                            if(Integer.toString(iii).isEmpty()==false)
                                                            {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }

                                                    }
                                                });
                                                builder.show();
                                            }

                                        }


                                        if (which==6)
                                        {
                                            final Long ID = app.dbManager.getDatabase().get(pos).id;
                                            if (ID == app.dbManager.getDatabase().get(pos).id) {
                                                CharSequence cpu[] = new CharSequence[] {"AMD A8", "AMD A10", "Intel i3", "Intel i5", "Intel i7"};

                                                AlertDialog.Builder builder = new AlertDialog.Builder(Database.this);
                                                builder.setTitle("Choose a CPU");
                                                builder.setItems(cpu, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (which==0)
                                                        {
                                                            String i = "AMD A8";
                                                            app.dbManager.database.execSQL("UPDATE desktop set cpu = " + "'" + i + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the cpu to " + i, Toast.LENGTH_SHORT).show();
                                                            if(i.isEmpty()==false) {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }


                                                        if (which==1)
                                                        {
                                                            String ii = "AMD A10";
                                                            app.dbManager.database.execSQL("UPDATE desktop set cpu = " + "'" + ii + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the cpu to " + ii, Toast.LENGTH_SHORT).show();
                                                            if(ii.isEmpty()==false) {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }


                                                        if (which==2)
                                                        {
                                                            String iii = "Intel i3";
                                                            app.dbManager.database.execSQL("UPDATE desktop set cpu = " + "'" + iii + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the cpu to " + iii, Toast.LENGTH_SHORT).show();
                                                            if(iii.isEmpty()==false) {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }


                                                        if (which==3)
                                                        {
                                                            String iv = "Intel i5";
                                                            app.dbManager.database.execSQL("UPDATE desktop set cpu = " + "'" + iv + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the cpu to " + iv, Toast.LENGTH_SHORT).show();
                                                           if(iv.isEmpty()==false) {
                                                               Intent a = new Intent(Database.this, Database.class);
                                                               startActivity(a);
                                                           }
                                                        }


                                                        if (which==4)
                                                        {
                                                            String v = "Intel i7";
                                                            app.dbManager.database.execSQL("UPDATE desktop set cpu = " + "'" + v + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the cpu to " + v, Toast.LENGTH_SHORT).show();
                                                            if(v.isEmpty()==false) {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }

                                                    }
                                                });
                                                builder.show();
                                            }

                                        }



                                        if (which==7)
                                        {
                                            final Long ID = app.dbManager.getDatabase().get(pos).id;
                                            if (ID == app.dbManager.getDatabase().get(pos).id) {
                                                CharSequence tower[] = new CharSequence[] {"Mini", "Full Size"};

                                                AlertDialog.Builder builder = new AlertDialog.Builder(Database.this);
                                                builder.setTitle("Choose a Tower Type");
                                                builder.setItems(tower, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {


                                                        if (which==0)
                                                        {
                                                            String i = "Mini";
                                                            app.dbManager.database.execSQL("UPDATE desktop set TowerType = " + "'" + i + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the TowerType to " + i, Toast.LENGTH_SHORT).show();
                                                            if(i.isEmpty()==false) {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }


                                                        if (which==1)
                                                        {
                                                            String ii = "Full Size";
                                                            app.dbManager.database.execSQL("UPDATE desktop set TowerType = " + "'" + ii + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the TowerType to " + ii, Toast.LENGTH_SHORT).show();
                                                            if(ii.isEmpty()==false) {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }


                                                    }
                                                });
                                                builder.show();
                                            }

                                        }


                                        if (which==8)
                                        {
                                            final Long ID = app.dbManager.getDatabase().get(pos).id;
                                            if (ID == app.dbManager.getDatabase().get(pos).id) {
                                                CharSequence cooltype[] = new CharSequence[] {"Fan Cooled", "Liquid Cooling"};

                                                AlertDialog.Builder builder = new AlertDialog.Builder(Database.this);
                                                builder.setTitle("Choose a Cooling Type");
                                                builder.setItems(cooltype, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (which==0)

                                                        {
                                                            String i = "Fan Cooled";
                                                            app.dbManager.database.execSQL("UPDATE desktop set coolingSystem = " + "'" + i + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the coolingSystem to " + i, Toast.LENGTH_SHORT).show();
                                                            if(i.isEmpty()==false) {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }
                                                        if (which==1)

                                                        {
                                                            String ii = "Liquid Cooling";
                                                            app.dbManager.database.execSQL("UPDATE desktop set coolingSystem = " + "'" + ii + "'" + " WHERE id = " + ID);
                                                            Toast.makeText(getApplicationContext(), "You updated the coolingSystem to " + ii, Toast.LENGTH_SHORT).show();
                                                            if(ii.isEmpty()==false) {
                                                                Intent a = new Intent(Database.this, Database.class);
                                                                startActivity(a);
                                                            }
                                                        }
                                                    }
                                                });
                                                builder.show();
                                            }
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });
                //shows dialogue
                alert.show();
            }
        });
    }

//--------------------------------------------------------------------------------------------------


//creates option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

//--------------------------------------------------------------------------------------------------

    //returns optionitem selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.home :  startActivity(new Intent(this, Choose.class));
                break;
            case R.id.addc: startActivity(new Intent(this, AddComputer.class));
                break;
            case R.id.searchmenu: startActivity(new Intent(this, SearchComputer.class));
                break;
            case R.id.about: startActivity(new Intent(this, AboutPage.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


//--------------------------------------------------------------------------------------------------


            //array adapter
    class ArrayAdapter extends android.widget.ArrayAdapter
    {
        private Context context;
        public List<Desktop> desktopList;


        public ArrayAdapter(Context context, List<Desktop> desktopList)
        {

            //lays out row computers xml launc file
            super(context, R.layout.row_computers, desktopList);
            this.context   = context;
            this.desktopList = desktopList;

        }

//--------------------------------------------------------------------------------------------------

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View     view       = inflater.inflate(R.layout.row_computers, parent, false);

            Desktop desktop =       desktopList.get(position);
            TextView modelView      =           (TextView) view.findViewById(R.id.model_row);
            TextView ramView        =          (TextView) view.findViewById(R.id.row_ram);
            TextView storageView    =           (TextView) view.findViewById(R.id.row_storage);
            TextView cpuView        =          (TextView) view.findViewById(R.id.cpu_row);
            TextView makeView       =           (TextView) view.findViewById(R.id.row_make);
            TextView priceView      =           (TextView) view.findViewById(R.id.row_price);
            TextView screenView     =           (TextView) view.findViewById(R.id.screen_row);
            TextView towerView      =           (TextView) view.findViewById(R.id.row_tower);
            TextView coolingView    =           (TextView) view.findViewById(R.id.row_cooling);

            //displays text for the different views
            String temp = "";
            makeView.setText(desktop.make);
            modelView.setText(desktop.model);
            ramView.setText(temp.replace("$", "") + desktop.ram + " GB");
            cpuView.setText(desktop.cpu);
            storageView.setText(temp.replace("$", "") + desktop.storage + " GB");
            screenView.setText(temp.replace("$", "") + desktop.screen + " inches");
            priceView.setText(temp.replace("$", "") + "€ " + desktop.price);
            towerView.setText(desktop.towerType);
            coolingView.setText(desktop.coolingSystem);
            return view;
        }

//--------------------------------------------------------------------------------------------------

        public void reset(MenuItem item) //When menu Item reset is clicked
        {
            app.dbManager.getDatabase().clear(); //Get everything in db and clear it
            app.dbManager.deleteDatabase(); //Reset the db
        }


//--------------------------------------------------------------------------------------------------

        @Override
        public int getCount()
        {
            return desktopList.size();
        }

    }
}

