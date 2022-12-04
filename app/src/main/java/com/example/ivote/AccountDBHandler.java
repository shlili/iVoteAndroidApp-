package com.example.ivote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountDBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "votingDB";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "account";

    private static final String ID_COL = "id";

    private static final String NAME_COL = "name";

    private static final String SURNAME_COL = "surname";

    private static final String EMAIL_COL = "email";

    private static final String PASSWORD_COL = "password";

    private static final String ACCOUNT_TYPE_COL = "accountType";

    public AccountDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + SURNAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT,"
                + ACCOUNT_TYPE_COL + " TEXT)";

        db.execSQL(query);
    }

    public void addNewAccount (
            String name,
            String surname,
            String email,
            String password,
            String accountType
    ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_COL, name);
        values.put(SURNAME_COL, surname);
        values.put(EMAIL_COL, email);
        values.put(PASSWORD_COL, password);
        values.put(ACCOUNT_TYPE_COL, accountType); // it can be user/admin

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String loginToAccount(String email, String password) {
        String[] columns = {
                ID_COL,
                PASSWORD_COL,
                ACCOUNT_TYPE_COL
        };

        SQLiteDatabase db = this.getReadableDatabase();

        String selection = EMAIL_COL + " = ?";

        String[] selectionArgs = {email};

        Cursor cursor = db.query(
                TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int cursorCount = cursor.getCount();
        int index;

        cursor.moveToFirst();

        index = cursor.getColumnIndexOrThrow("password");
        String fetchedPassword = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow("accountType");
        String fetchedAccountType = cursor.getString(index);

        cursor.close();

        if (cursorCount == 0) {
            return "User doesn't exist";
        }

        if (cursorCount > 0 && !fetchedPassword.equals(password)) {
            return "Incorrect password.";
        }

        if (cursorCount > 0 && fetchedPassword.equals(password) && fetchedAccountType.equals("admin")) {
            return "Admin successfully logged in.";
        }

        if (cursorCount > 0 && fetchedPassword.equals(password) && fetchedAccountType.equals("user")) {
            return "Successfully logged in.";
        }

        db.close();

        return "Something went wrong.";
    }
}
