package dd.app.computall.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dd.app.computall.objects.Desktop;


public class DBManager {

    public DBDesigner DBhelper;
    public SQLiteDatabase database;

    public List<Desktop> getDatabase() {
        ArrayList<Desktop> desktops = new ArrayList<Desktop>();
        //cursor gets all dd.app.computall.objects and closes when all are loaded
        Cursor cursor = database.rawQuery("SELECT * FROM desktop", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Desktop d = toDesktop(cursor);
            desktops.add(d);
            cursor.moveToNext();
        }
        cursor.close();
        return desktops;
    }

    public DBManager(Context context) {
        DBhelper = new DBDesigner(context);
    }

    //closes db
    public void close() {
        database.close();
    }


    // opens database
    public void open() throws SQLException {
        database = DBhelper.getWritableDatabase();
    }

    //adds to database
    public void add(Desktop d) {
        ContentValues values = new ContentValues();
        values.put("make", d.make);
        values.put("model", d.model);
        values.put("ram", d.ram);
        values.put("storage", d.storage);
        values.put("screen", d.screen);
        values.put("price", d.price);
        values.put("cpu", d.cpu);
        values.put("TowerType", d.towerType);
        values.put("coolingSystem", d.coolingSystem);


        database.insert("desktop", null, values);

    }


    private Desktop toDesktop(Cursor cursor) {

        Desktop pojo = new Desktop();
        pojo.id = cursor.getInt(0);
        pojo.make = cursor.getString(1);
        pojo.model = cursor.getString(2);
        pojo.ram = cursor.getInt(3);
        pojo.storage = cursor.getInt(4);
        pojo.screen = cursor.getInt(5);
        pojo.price = cursor.getInt(6);
        pojo.cpu = cursor.getString(7);
        pojo.towerType = cursor.getString(8);
        pojo.coolingSystem = cursor.getString(9);
        return pojo;

    }


    public Cursor makese(String make) {

        SQLiteDatabase db = DBhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM desktop WHERE make LIKE '%" + make + "%'", null);
        return cursor;
    }


    public Cursor modelse(String model) {

        SQLiteDatabase db = DBhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM desktop WHERE model LIKE '%" + model + "%'", null);
        return cursor;
    }



    public Cursor cpuse(String cpu) {

        SQLiteDatabase db = DBhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM desktop WHERE cpu LIKE '%" + cpu + "%'", null);
        return cursor;
    }

    public Cursor towerse(String tower) {

        SQLiteDatabase db = DBhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM desktop WHERE towerType LIKE '%" + tower + "%'", null);
        return cursor;
    }

    public Cursor coolse(String cool) {

        SQLiteDatabase db = DBhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM desktop WHERE coolingSystem LIKE '%" + cool + "%'", null);
        return cursor;
    }

   /* public Cursor allse(String all) {

        SQLiteDatabase db = DBhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM desktop WHERE make LIKE '%" + all + "%', " + "SELECT * FROM desktop WHERE model LIKE '%" + all +
                "%', " + "SELECT * FROM desktop WHERE cpu LIKE '%" + all + "%', " + "SELECT * FROM desktop WHERE towerType LIKE '%" + all +
                "%', " + "SELECT * FROM desktop WHERE coolingSystem  LIKE '%" + all + "%' ", null);
        return cursor;
    }*/

    public Cursor allse(String all) {

        SQLiteDatabase db = DBhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM desktop WHERE make LIKE '%" + all + "%' " + "OR model LIKE '%" + all +
                "%' " + "OR ram LIKE '%" + all + "%' " + "OR storage LIKE '%" + all + "%' " + "OR screen LIKE '%" + all +
                "%' " + "OR price LIKE '%" + all + "%' " +  "OR cpu LIKE '%" + all + "%' " + "OR towerType LIKE '%" + all +
                "%' " + "OR coolingSystem LIKE '%" + all + "%' ", null);
        return cursor;
    }



    /*public Desktop[] searchmake(){
        String query = "Select * from desktop";
        Cursor cursor =  database.rawQuery(query, null);
        ArrayList<Desktop> maketerms = new ArrayList<Desktop>();
        if(cursor.moveToFirst()){
            do{
                String make = cursor.getString(cursor.getColumnIndexOrThrow("make"));
                maketerms.add(make.);
            }while(cursor.moveToNext());
        }
        cursor.close();
        String[] s = new String[maketerms.size()];
        s = maketerms.toArray(searchmake());
        return s;
    }*/



    //deletes all database
    public void deleteDatabase() {
        database.delete("desktop", null, null);
    }
}
