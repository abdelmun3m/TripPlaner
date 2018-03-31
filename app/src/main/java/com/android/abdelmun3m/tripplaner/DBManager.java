package com.android.abdelmun3m.tripplaner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Asmaa on 3/23/2018.
 */

public class DBManager {
    private Context context;
    static DBManager.DataBaseHelper dbHelper;
    int idCounter = 0;

    public DBManager(Context _context){
        dbHelper = new DBManager.DataBaseHelper(_context);
        context = _context;
    }

    public long insertTrip(String name,String startPoint,String endPoint,String notes){
        idCounter++;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.UID, idCounter);
        contentValues.put(DataBaseHelper.TripName, name);
        contentValues.put(DataBaseHelper.StartPoint, startPoint);
        contentValues.put(DataBaseHelper.EndPoint,endPoint);
        contentValues.put(DataBaseHelper.Notes, notes);
        long id = db.insert(DataBaseHelper.TRIP_TABLE_NAME, null, contentValues);
        return id;
    }

    public String[] getAllTrips(){
        String[] sponsers = null;
        int i=0;
        Cursor c;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {
                DataBaseHelper.TripName,
                DataBaseHelper.StartPoint,
                DataBaseHelper.EndPoint,
                DataBaseHelper.Notes
        };
        //query (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        c = db.query(DataBaseHelper.TRIP_TABLE_NAME, columns, null, null, null, null, null);
        sponsers = new String[c.getCount()];
        while ( c.moveToNext()) {
            String result = "";
            result = c.getString(0);
            result += ",";
            result += c.getString(1);
            result += ",";
            result += c.getString(2);
            result += ",";
            result += c.getString(3);
            sponsers[i++]=result;
        }
        return sponsers;
    }

    static class DataBaseHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "trips.db";

        private static final String TRIP_TABLE_NAME = "trip";
        private static final String UID = "_id";
        private static final String TripName = "Trip_Name";
        private static final String StartPoint = "Start_Point";

        private static final String EndPoint = "End_Point";
        private static final String Notes = "notes";

        private static final String CREATE_TRIP_TABLE = "CREATE TABLE " + TRIP_TABLE_NAME + " (" +UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TripName+" TEXT,"+ StartPoint +" TEXT,"+EndPoint +"TEXT,"+ Notes + " TEXT);";

       /* private static final String CREATE_TRIP_TABLE = "CREATE TABLE " + TRIP_TABLE_NAME + " (" +UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TripName+" TEXT,"+ StartPoint +" TEXT);";*/

        public DataBaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(CREATE_TRIP_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

        }
    }

}
