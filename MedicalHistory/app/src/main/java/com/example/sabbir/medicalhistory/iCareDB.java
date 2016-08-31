package com.example.sabbir.medicalhistory;

/**
 * Created by SABBIR on 1/23/2016.
 */

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import java.util.ArrayList;
        import java.util.HashMap;

/**
 * Created by Mobile App Develop on 12-1-16.
 */
public class iCareDB extends SQLiteOpenHelper {
    static final String DATA_BASE_NAME="I_CARE_DATABASE";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "DOCTOR_PROFILE";

    public iCareDB(Context context) {
        super(context, DATA_BASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +

                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + " DOCTOR_NAME TEXT(100)," + " SPECIALIZED TEXT(100),"+" DATE TEXT(100));");

        Log.d("CREATE TABLE", "Create Table Successfully.");
    }

    public long InsertData( String doctor_name, String specialized,String date) {

        try {

            SQLiteDatabase db;

            db = this.getWritableDatabase(); // Write Data



            ContentValues Val = new ContentValues();



            Val.put("DOCTOR_NAME", doctor_name);

            Val.put("SPECIALIZED", specialized);

            Val.put("DATE", date);

            long rows = db.insert(TABLE_NAME, null, Val);

            db.close();

            return rows; // return rows inserted.

        } catch (Exception e) {

            return -1;

        }

    }


    public ArrayList<HashMap<String, String>> SelectAllData() {
        try {
            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT  * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("DOCTOR_NAME", cursor.getString(1));
                        map.put("SPECIALIZED", cursor.getString(2));
                        map.put("DATE", cursor.getString(3));
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

// Re Create on method  onCreate

        onCreate(db);
    }
    public void delete(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME, id + "=?", new String[]{id});
        db.close();
    }
    public long DeleteData(int strMemberID) {

        try {

            SQLiteDatabase db;

            db = this.getWritableDatabase(); // Write Data


            long rows = db.delete(TABLE_NAME, "MemberID = ?",
                    new String[]{String.valueOf(strMemberID)});



            db.close();

            return rows; // return rows deleted.

        } catch (Exception e) {

            return -1;
        }

    }
}