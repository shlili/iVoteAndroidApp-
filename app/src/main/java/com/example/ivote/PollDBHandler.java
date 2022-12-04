package com.example.ivote;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PollDBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "votingDB";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "poll";

    private static final String ID_COL = "id";

    private static final String NAME_COL = "name";

    private static final String STATUS_COL = "status";

    private static final String CREATED_ON_COL = "created_on";

    private static final String ACTIVE_UNTIL_COL = "open_until";

    public PollDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + STATUS_COL + " BOOLEAN,"
                + CREATED_ON_COL + " DEFAULT CURRENT_TIMESTAMP,"
                + ACTIVE_UNTIL_COL + " DATETIME)";

        db.execSQL(query);
    }

    public void createNewPoll(
            String name,
            Date activeUntil
    ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_COL, name);
        values.put(STATUS_COL, true);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        values.put(CREATED_ON_COL, formattedDate);

        values.put(ACTIVE_UNTIL_COL, activeUntil.toString());

        db.insert(TABLE_NAME, null, values);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
