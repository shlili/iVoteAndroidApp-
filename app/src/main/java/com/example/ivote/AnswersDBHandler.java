package com.example.ivote;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnswersDBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "voitngDB";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "answers";

    private static final String ID_COL = "id";

    private static final String ANSWER_COL = "answer";

    private static final String IS_CORRECT_COL = "isCorrect";

    private static final String POLL_ID_COL = "pollId";

    public AnswersDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ANSWER_COL + " TEXT,"
                + IS_CORRECT_COL + " BOOLEAN,"
                + POLL_ID_COL + " INTEGER,"
                + "FOREIGN KEY(pollId) REFERENCES poll(id))";

        db.execSQL(query);
    }

    public void createNewAnswer(
            String answer,
            Boolean isCorrect,
            Integer pollId
    ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ANSWER_COL, answer);
        values.put(IS_CORRECT_COL, isCorrect);
        values.put(POLL_ID_COL, pollId);

        db.insert(TABLE_NAME, null, values);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
