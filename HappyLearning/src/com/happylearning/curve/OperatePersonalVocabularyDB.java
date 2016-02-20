package com.happylearning.curve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OperatePersonalVocabularyDB {

	private PersonalVocabularyDB personalvocabularydb;
	private SQLiteDatabase db;

	public OperatePersonalVocabularyDB(Context context) {
		personalvocabularydb = new PersonalVocabularyDB(context);
		db = personalvocabularydb.getWritableDatabase();
	}

	public void add(PersonVocabularyRecord contact) {
		ContentValues cv = new ContentValues();
		cv.put("nameenglish ", contact.nameenglish);
		cv.put("countname ", contact.countname);
		cv.put("englishvocabulary ", contact.englishvocabulary);
		cv.put("correcttime ", contact.correcttime);
		cv.put("wrongtime ", contact.wrongtime);
		cv.put("corretrate ", contact.corretrate);
		cv.put("requesttime ", contact.requesttime);
		cv.put("requestrate ", contact.requestrate);
		cv.put("wordorder ", contact.wordorder);
		cv.put("englishgroup ", contact.englishgroup);
		cv.put("grouporder ", contact.grouporder);
		cv.put("gethang ", contact.gethang);
		db.insert(personalvocabularydb.TABLE_NAME, null, cv);
	}

	public void updateSomeone(PersonVocabularyRecord contact) {
		ContentValues cv = new ContentValues();
		cv.put("nameenglish", contact.nameenglish);
		cv.put("countname", contact.countname);
		cv.put("englishvocabulary", contact.englishvocabulary);
		cv.put("correcttime", contact.correcttime);
		cv.put("wrongtime", contact.wrongtime);
		cv.put("corretrate", contact.corretrate);
		cv.put("requesttime", contact.requesttime);
		cv.put("requestrate", contact.requestrate);
		cv.put("wordorder", contact.wordorder);
		cv.put("englishgroup", contact.englishgroup);
		cv.put("grouporder", contact.grouporder);
		cv.put("gethang", contact.gethang);
		db.update("personalvocabulary", cv, "nameenglish = ?",
				new String[] { contact.nameenglish });
	}

	public void deleteContact(String nameenglish) {
		db.delete("personalvocabulary", "nameenglish = ?",
				new String[] { nameenglish });
	}

	public List<PersonVocabularyRecord> query(String countname) {
		ArrayList<PersonVocabularyRecord> contacts = new ArrayList<PersonVocabularyRecord>();
		Cursor c = queryCursor(countname);
		while (c.moveToNext()) {
			PersonVocabularyRecord contact = new PersonVocabularyRecord();
			contact._id = c.getInt(c.getColumnIndex("_id"));
			contact.nameenglish = c.getString(c.getColumnIndex("nameenglish"));
			contact.countname = c.getString(c.getColumnIndex("countname"));
			contact.englishvocabulary = c.getString(c
					.getColumnIndex("englishvocabulary"));
			contact.meetingtime = c.getDouble(c.getColumnIndex("meetingtime"));
			contact.correcttime = c.getDouble(c.getColumnIndex("correcttime"));
			contact.wrongtime = c.getDouble(c.getColumnIndex("wrongtime"));
			contact.corretrate = c.getDouble(c.getColumnIndex("corretrate"));
			contact.requesttime = c.getDouble(c.getColumnIndex("requesttime"));
			contact.requestrate = c.getDouble(c.getColumnIndex("requestrate"));
			contact.wordorder = c.getDouble(c.getColumnIndex("wordorder"));
			contact.englishgroup = c
					.getString(c.getColumnIndex("englishgroup"));
			contact.grouporder = c.getDouble(c.getColumnIndex("grouporder"));
			contact.gethang = c.getString(c.getColumnIndex("gethang"));
			contacts.add(contact);
		}
		c.close();
		return contacts;
	}

	public List<PersonVocabularyRecord> queryWords(String countname) {
		ArrayList<PersonVocabularyRecord> contacts = new ArrayList<PersonVocabularyRecord>();
		Cursor c = queryGroupWords(countname);
		if (c != null) {
			while (c.moveToNext()) {
				PersonVocabularyRecord contact = new PersonVocabularyRecord();
				contact._id = c.getInt(c.getColumnIndex("_id"));
				contact.nameenglish = c.getString(c
						.getColumnIndex("nameenglish"));
				contact.countname = c.getString(c.getColumnIndex("countname"));
				contact.englishvocabulary = c.getString(c
						.getColumnIndex("englishvocabulary"));
				contact.meetingtime = c.getDouble(c
						.getColumnIndex("meetingtime"));
				contact.correcttime = c.getDouble(c
						.getColumnIndex("correcttime"));
				contact.wrongtime = c.getDouble(c.getColumnIndex("wrongtime"));
				contact.corretrate = c
						.getDouble(c.getColumnIndex("corretrate"));
				contact.requesttime = c.getDouble(c
						.getColumnIndex("requesttime"));
				contact.requestrate = c.getDouble(c
						.getColumnIndex("requestrate"));
				contact.wordorder = c.getDouble(c.getColumnIndex("wordorder"));
				contact.englishgroup = c.getString(c
						.getColumnIndex("englishgroup"));
				contact.grouporder = c
						.getDouble(c.getColumnIndex("grouporder"));
				contact.gethang = c.getString(c.getColumnIndex("gethang"));
				contacts.add(contact);
			}
		}
		c.close();
		return contacts;
	}

	public PersonVocabularyRecord querySomeone(String nameenglish) {
		Cursor c = queryTheCursor(nameenglish);
		while (c.moveToNext()) {
			PersonVocabularyRecord contact = new PersonVocabularyRecord();
			contact._id = c.getInt(c.getColumnIndex("_id"));
			contact.nameenglish = c.getString(c.getColumnIndex("nameenglish"));
			contact.countname = c.getString(c.getColumnIndex("countname"));
			contact.englishvocabulary = c.getString(c
					.getColumnIndex("englishvocabulary"));
			contact.meetingtime = c.getDouble(c.getColumnIndex("meetingtime"));
			contact.correcttime = c.getDouble(c.getColumnIndex("correcttime"));
			contact.wrongtime = c.getDouble(c.getColumnIndex("wrongtime"));
			contact.corretrate = c.getDouble(c.getColumnIndex("corretrate"));
			contact.requesttime = c.getDouble(c.getColumnIndex("requesttime"));
			contact.requestrate = c.getDouble(c.getColumnIndex("requestrate"));
			contact.wordorder = c.getDouble(c.getColumnIndex("wordorder"));
			contact.englishgroup = c
					.getString(c.getColumnIndex("englishgroup"));
			contact.grouporder = c.getDouble(c.getColumnIndex("grouporder"));
			contact.gethang = c.getString(c.getColumnIndex("gethang"));
			if (contact.nameenglish.equals(nameenglish)) {
				return contact;
			}
		}
		c.close();
		return null;
	}

	public ArrayList<Map<String, Object>> queryGroupOrder(String countname) {
		ArrayList<Map<String, Object>> groups = new ArrayList<Map<String, Object>>();
		;
		Cursor c = queryGroup(countname);
		while (c.moveToNext()) {
			Map<String, Object> group = new HashMap<String, Object>();
			group.put(c.getString(c.getColumnIndex("englishgroup")),
					c.getDouble(c.getColumnIndex("score")));
			groups.add(group);
		}
		c.close();
		return groups;
	}// —È÷§

	private Cursor queryTheCursor(String nameenglish) {
		// TODO Auto-generated method stub
		Cursor c = db.rawQuery(
				"SELECT * FROM personalvocabulary where nameenglish = ?",
				new String[] { nameenglish });
		return c;
	}

	private Cursor queryCursor(String countname) {
		// TODO Auto-generated method stub
		Cursor c = db.rawQuery(
				"SELECT * FROM personalvocabulary where countname = ? ",
				new String[] { countname });
		return c;
	}

	private Cursor queryGroup(String countname) {
		// TODO Auto-generated method stub
		Cursor c = db
				.rawQuery(
						"SELECT englishgroup , sum(wordorder) score FROM personalvocabulary where gethang = 'N' and countname = ? group by englishgroup ",
						new String[] { countname });
		return c;
	}

	private Cursor queryGroupWords(String countname) {
		// TODO Auto-generated method stub
		Cursor c = db
				.rawQuery(
						"SELECT * FROM personalvocabulary WHERE gethang = ? and countname = ? order by grouporder , wordorder",
						new String[] { "N", countname });
		return c;
	}

	public void closeDB() {
		db.close();
	}
}
