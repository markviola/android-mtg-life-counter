package com.example.tony.mtglifecounter;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;


public class DBManager extends SQLiteOpenHelper{

    private static final String TAG = "Tony message";

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "MTGDatabase.db";
    public static final String TABLE_PLAYERS = "players";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PLAYERNAME = "_playerName";
    public static final String COLUMN_LIFE = "_life";
    public static final String COLUMN_POISON = "_poison";

    public static final String TABLE_GAMESTATE = "gameState";
    public static final String COLUMN_STATENAME = "_stateName";
    public static final String COLUMN_STATE = "_state";


    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "In onCreate DBManager");
        String playerQuery = "CREATE TABLE " + TABLE_PLAYERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PLAYERNAME + " TEXT, " +
                COLUMN_LIFE + " TEXT, " +
                COLUMN_POISON + " TEXT " +
                ");";
        db.execSQL(playerQuery);

        String stateQuery = "CREATE TABLE " + TABLE_GAMESTATE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_STATENAME + " TEXT, " +
                COLUMN_STATE + " TEXT " +
                ");";
        db.execSQL(stateQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        onCreate(db);
    }

    //Check if database is empty
    public boolean isEmpty(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PLAYERS + " WHERE 1";
        Cursor c = db.rawQuery(query, null);

        return c.getCount() == 0;
    }

    //Add player to the database
    public void addPlayer(Player player){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLAYERNAME, player.get_playerName());
        values.put(COLUMN_LIFE, player.get_life());
        values.put(COLUMN_POISON, player.get_poison());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PLAYERS, null, values);
        db.close();
    }

    //Delete player from the database
    public void deletePlayer(String playerName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLAYERS + " WHERE " + COLUMN_PLAYERNAME + "=\"" + playerName + "\";");
    }

    //Add player to the database
    public void addState(String stateName, String state){
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATENAME, stateName);
        values.put(COLUMN_STATE, state);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_GAMESTATE, null, values);
        db.close();
    }

    public String dbGetName(int playerNumber){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT _id, _playerName FROM " + TABLE_PLAYERS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getInt(c.getColumnIndex("_id")) == playerNumber){
                String playerName = c.getString(c.getColumnIndex("_playerName"));
                db.close();
                return playerName;
            }
            c.moveToNext();
        }

        db.close();
        return "ERROR NAME";
    }

    public String dbGetLife(int playerNumber){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT _id, _life FROM " + TABLE_PLAYERS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getInt(c.getColumnIndex("_id")) == playerNumber){
                String life = c.getString(c.getColumnIndex("_life"));
                db.close();
                return life;
            }
            c.moveToNext();
        }
        db.close();
        return "ERROR LIFE";
    }

    public String dbGetPoison(int playerNumber){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PLAYERS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getInt(c.getColumnIndex("_id")) == playerNumber){
                String poison = c.getString(c.getColumnIndex("_poison"));
                db.close();
                return poison;
            }
            c.moveToNext();
        }
        db.close();
        return "ERROR POISON";
    }

    public String dbGetState(String stateName){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_GAMESTATE + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("_stateName")).equals(stateName)){
                String state = c.getString(c.getColumnIndex("_state"));
                db.close();
                return state;
            }
            c.moveToNext();
        }
        db.close();
        return "ERROR STATE";
    }

    public void updateLife(int playerNumber, String life){
        String query = "UPDATE " + TABLE_PLAYERS + " SET _life = \'" + life + "\' WHERE _id = " +
                Integer.toString(playerNumber);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public void updatePoison(int playerNumber, String poison){
        String query = "UPDATE " + TABLE_PLAYERS + " SET _poison = \'" + poison +
                "\' WHERE _id = " + Integer.toString(playerNumber);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public void updatePlayerName(int playerNumber, String playerName){
        String query = "UPDATE " + TABLE_PLAYERS + " SET _playerName = \'" + playerName +
                "\' WHERE _id = " + Integer.toString(playerNumber);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public void updateState(String stateName, String newState){
        String query = "UPDATE " + TABLE_GAMESTATE + " SET _state = \'" + newState +
                "\' WHERE _stateName = \'" + stateName + "\'";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }
}
