package com.liren.sudoku.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLHelper extends android.database.sqlite.SQLiteOpenHelper {
	private final static String DATABASE_NAME = "sudoku";
	private final static int DATABASE_VERSION = 3;
	private final static String TABLE_NAME = "sudoku";
	private final static String FIELD_ID = "ID";
	private final static String FIELD_LEVEL = "Level";
	private final static String FIELD_DATA = "Data";
	private final static String FIELD_TIMECOST = "TimeCost";
	private final static String FIELD_TIPCOUNT = "TipCount";
	private final static String FIELD_ERROR = "Error";
	private final static String FIELD_FINISH = "Finish";
	private final static String FIELD_LASTDATE = "LastDate";
	
	private final static String[] fields = { FIELD_ID, FIELD_LEVEL, FIELD_DATA,
			FIELD_TIMECOST, FIELD_TIPCOUNT, FIELD_ERROR, FIELD_FINISH };
	private final static String[] datas = { 
		"11,71,60,41,51,80,31,90,21," +
		"90,80,31,20,60,71,50,11,40," +
		"41,51,21,90,31,10,70,61,81," +
		"61,41,81,70,10,51,21,31,90," +
		"50,91,70,30,21,61,40,80,11," +
		"20,30,11,81,91,41,61,51,71," +
		"81,20,50,60,70,91,10,41,30," +
		"31,11,40,51,81,21,91,71,60," +
		"71,61,91,11,40,31,80,21,50" ,		
		"30,70,80,60,40,90,51,10,21," +
		"60,51,21,70,80,10,30,91,40," +
		"90,10,40,21,31,51,60,70,81," +
		"80,30,61,40,10,20,91,50,70," +
		"20,40,71,51,91,61,10,80,30," +
		"50,90,10,30,71,80,21,40,60," +
		"10,20,30,81,51,41,70,61,90," +
		"40,61,50,90,20,70,81,31,10," +
		"71,80,91,10,60,30,40,20,50" ,		
		"30,70,80,60,40,90,51,10,21," +
		"60,51,21,70,80,10,30,91,40," +
		"90,10,40,21,31,51,60,70,81," +
		"80,30,61,40,10,20,91,50,70," +
		"20,40,71,51,91,61,10,80,30," +
		"50,90,10,30,71,80,21,40,60," +
		"10,20,30,81,51,41,70,61,90," +
		"40,61,50,90,20,70,81,31,10," +
		"71,80,91,10,60,30,40,20,50" ,		
		"30,70,80,60,40,90,51,10,21," +
		"60,51,21,70,80,10,30,91,40," +
		"90,10,40,21,31,51,60,70,81," +
		"80,30,61,40,10,20,91,50,70," +
		"20,40,71,51,91,61,10,80,30," +
		"50,90,10,30,71,80,21,40,60," +
		"10,20,30,81,51,41,70,61,90," +
		"40,61,50,90,20,70,81,31,10," +
		"71,80,91,10,60,30,40,20,50" ,		
		"30,70,80,60,40,90,51,10,21," +
		"60,51,21,70,80,10,30,91,40," +
		"90,10,40,21,31,51,60,70,81," +
		"80,30,61,40,10,20,91,50,70," +
		"20,40,71,51,91,61,10,80,30," +
		"50,90,10,30,71,80,21,40,60," +
		"10,20,30,81,51,41,70,61,90," +
		"40,61,50,90,20,70,81,31,10," +
		"71,80,91,10,60,30,40,20,50"};

	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "Create table %s (%s integer primary key autoincrement,%s integer,%s text,%s bigint,%s int,%s int,%s int,%s datetime);";
		sql = String.format(sql, TABLE_NAME, FIELD_ID, FIELD_LEVEL, FIELD_DATA,
				FIELD_TIMECOST, FIELD_TIPCOUNT, FIELD_ERROR, FIELD_FINISH,
				FIELD_LASTDATE);
		db.execSQL(sql);

		InitData(db);
		Log.d("D", "Database Create finished.sql:" + sql);
	}

	private void InitData(SQLiteDatabase db) {
		for (int i = 0; i < datas.length; i++) {
			String data = datas[i];
			ContentValues cv = new ContentValues();
			cv.put(FIELD_LEVEL, i + 1);
			cv.put(FIELD_DATA, data);
			cv.put(FIELD_TIMECOST, 0);
			cv.put(FIELD_TIPCOUNT, 3);
			cv.put(FIELD_ERROR, 0);
			cv.put(FIELD_FINISH, 0);
			cv.put(FIELD_LASTDATE, 0);
			long row = db.insert(TABLE_NAME, null, cv);
			Log.d("D", "insert data finished. ret=" + row);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}

	public Cursor select() {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] fields = { FIELD_ID, FIELD_LEVEL, FIELD_DATA, FIELD_TIMECOST,
				FIELD_TIPCOUNT, FIELD_ERROR, FIELD_FINISH };
		String where = FIELD_FINISH + "= ?";
		String[] wherevalue = {"2" };
		Cursor cursor = db.query(TABLE_NAME, fields, where, wherevalue, null, null,
				FIELD_ID);
		return cursor;
	}

	// Query new sudoku in this level
	// new game in this level
	public Cursor select(int level) {
		SQLiteDatabase db = this.getReadableDatabase();
		String where = FIELD_LEVEL + "= ?" + " AND " + FIELD_FINISH + "= ?";
		String[] wherevalue = { Integer.toString(level), "0" };
		Cursor cursor = db.query(TABLE_NAME, fields, where, wherevalue, null,
				null, FIELD_ID);
		return cursor;
	}

	public SudokuModel loadGame(int level) {
		SQLiteDatabase db = this.getReadableDatabase();
		String where = FIELD_LEVEL + "= ?" + " AND " + FIELD_FINISH + "= ?";
		String[] wherevalue = { Integer.toString(level), "0" };
		Cursor cursor = db.query(TABLE_NAME, fields, where, wherevalue, null,
				null, FIELD_ID);
		return ParseSudokuModel(cursor);
	}

	private SudokuModel ParseSudokuModel(Cursor cursor) {
		while (cursor.moveToFirst()) {
			com.liren.sudoku.model.SudokuModel model = new com.liren.sudoku.model.SudokuModel();
			model.id = cursor.getInt(0);
			model.level = cursor.getInt(1);
			model.data = cursor.getString(2);
			model.timecost = cursor.getInt(3);
			model.tipcount = cursor.getInt(4);
			model.error = cursor.getInt(5);
			model.finish = cursor.getInt(6);
			return model;
		}
		return null;
	}

	public SudokuModel loadResumeGame() {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] fields = { FIELD_ID, FIELD_LEVEL, FIELD_DATA, FIELD_TIMECOST,
				FIELD_TIPCOUNT, FIELD_ERROR, FIELD_FINISH };
		String where = FIELD_FINISH + "= ?";
		String[] wherevalue = { "1" };
		Cursor cursor = db.query(TABLE_NAME, fields, where, wherevalue, null,
				null, FIELD_ID);
		return ParseSudokuModel(cursor);
	}

	public long insert(String data, int level, int tip) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(FIELD_LEVEL, level);
		cv.put(FIELD_DATA, data);
		cv.put(FIELD_TIMECOST, 0);
		cv.put(FIELD_TIPCOUNT, tip);
		cv.put(FIELD_ERROR, 0);
		cv.put(FIELD_FINISH, 0);
		cv.put(FIELD_LASTDATE, 0);
		long row = db.insert(TABLE_NAME, null, cv);
		Log.d("D", "insert data finished. ret=" + row);
		return row;
	}

	public void delete(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = FIELD_ID + "= ?";
		String[] wherevalue = { Integer.toString(id) };
		db.delete(TABLE_NAME, where, wherevalue);
	}

	public void update(SudokuModel sudoku) {
		if (sudoku != null) {
			update(sudoku.id, sudoku.timecost, sudoku.tipcount, sudoku.error,
					sudoku.finish, sudoku.data);
		}
	}

	public void abandonGame(){
		SQLiteDatabase db = this.getWritableDatabase();
		String where = FIELD_FINISH + "= ?";
		String[] wherevalue = { Integer.toString(1) };
		ContentValues cv = new ContentValues();
		cv.put(FIELD_FINISH, 0);
		db.update(TABLE_NAME, cv, where, wherevalue);
	}
	
	public void update(int id, Long timecost, int tipcount, int error,
			int finish, String data) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = FIELD_ID + "= ?";
		String[] wherevalue = { Integer.toString(id) };
		ContentValues cv = new ContentValues();
		cv.put(FIELD_TIMECOST, timecost);
		cv.put(FIELD_TIPCOUNT, tipcount);
		cv.put(FIELD_ERROR, error);
		cv.put(FIELD_FINISH, finish);
		cv.put(FIELD_DATA, data);
		db.update(TABLE_NAME, cv, where, wherevalue);
	}
}
