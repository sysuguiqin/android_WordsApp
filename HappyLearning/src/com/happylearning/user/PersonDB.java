package com.happylearning.user;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonDB extends SQLiteOpenHelper {

	private static final String DB_NAME = "person.db";// ���ݿ��ļ�
	private static final int DB_VERSION = 1;// ���ݿ�汾
	public static final String TABLE_NAME = "person";// ���ݿ��

	public PersonDB(Context c) {
		super(c, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "countname VARCHAR," + "personname VARCHAR,"
				+ "email VARCHAR," + "ability INTEGER," + "password VARCHAR"
				+ ")";
		db.execSQL(SQL_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
}
