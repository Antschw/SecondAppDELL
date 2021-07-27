package com.example.secondapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String QUESTION_TABLE = "QUESTION_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ASK_QUESTION = "ASK_QUESTION";
    public static final String COLUMN_ANSWER_1 = "ANSWER_1";
    public static final String COLUMN_ANSWER_2 = "ANSWER_2";
    public static final String COLUMN_ANSWER_3 = "ANSWER_3";
    public static final String COLUMN_ANSWER_4 = "ANSWER_4";
    public static final String COLUMN_RIGHT_ANSWER = "RIGHT_ANSWER";
    public static final String COLUMN_IS_MEDIA = "IS_MEDIA";
    public static final String COLUMN_PATH_MEDIA = "PATH_MEDIA";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "questions.db", null, 1);
    }

    // This is called the first time a database is accessed. There should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement= "CREATE TABLE " + QUESTION_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ASK_QUESTION + " TEXT, " + COLUMN_ANSWER_1 + " TEXT, " + COLUMN_ANSWER_2 + " TEXT, " + COLUMN_ANSWER_3 + " TEXT, " + COLUMN_ANSWER_4 + " TEXT, " + COLUMN_RIGHT_ANSWER + " INT, " + COLUMN_IS_MEDIA + " BOOL, " + COLUMN_PATH_MEDIA + " TEXT)";

        db.execSQL(createTableStatement);
    }

    // This is called if the database version number changes. It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addOne(QuestionModel questionModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ASK_QUESTION, questionModel.getQuestion());
        cv.put(COLUMN_ANSWER_1, questionModel.getAnswer1());
        cv.put(COLUMN_ANSWER_2, questionModel.getAnswer2());
        cv.put(COLUMN_ANSWER_3, questionModel.getAnswer3());
        cv.put(COLUMN_ANSWER_4, questionModel.getAnswer4());
        cv.put(COLUMN_RIGHT_ANSWER, questionModel.getRightAnswer());
        cv.put(COLUMN_IS_MEDIA, questionModel.isMedia());
        cv.put(COLUMN_PATH_MEDIA, questionModel.getPathMedia());

        long insert = db.insert(QUESTION_TABLE, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    // Retrieving data
    public Cursor getdata() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM QUESTION_TABLE", null);
        return cursor;
    }
}
