package dd.app.computall.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBDesigner extends SQLiteOpenHelper {

    // creating database
    private static final String DATABASE_NAME = "desktop.db";
    private static final int 	DATABASE_VERSION = 2;
    //Creating Table in database
    private static final String DATABASE_CREATE_TABLE_DESKTOP = "create table desktop "
            + "(id integer primary key autoincrement,"
            + "make text not null,"
            + "model text not null,"
            + "ram integer not null,"
            + "storage integer not null,"
            + "screen integer not null,"
            + "price integer not null,"
            + "cpu text not null,"
            + "towerType text not null,"
            + "coolingSystem text not null);";



    private DBDesigner dbHelper;

    public SQLiteDatabase getDbConnection(){
        return this.getWritableDatabase();
    }



    public DBDesigner(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {database.execSQL(DATABASE_CREATE_TABLE_DESKTOP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBDesigner.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS desktop");
        onCreate(db);
    }
}
