package com.happylearning.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.widget.Toast;

public class OperateVocabularyDB {

	private VocabularyDB vocabularydb;
	private SQLiteDatabase db;

	public OperateVocabularyDB(Context context) {
		vocabularydb = new VocabularyDB(context);
		db = vocabularydb.getWritableDatabase();
	}

	public void add(VocabularyRecord contact) {
		ContentValues cv = new ContentValues();
		cv.put("englishvocabulary", contact.englishvocabulary);
		cv.put("vocabularysoundmark", contact.vocabularysoundmark);
		cv.put("chineseexplain", contact.chineseexplain);
		cv.put("englishexplain", contact.englishexplain);
		cv.put("englishsentence", contact.englishsentence);
		cv.put("representpicture", contact.representpicture);
		cv.put("vocabularylength", contact.vocabularylength);
		cv.put("frequencyrank", contact.frequencyrank);
		cv.put("englishgroup", contact.englishgroup);
		db.insert(vocabularydb.TABLE_NAME, null, cv);
	}

	public void addTogether() {
		add(new VocabularyRecord("paddle", "['pædl]", "n.划桨;明轮翼 vt.用桨划；拌",
				"a blade of a paddle wheel or water wheel",
				"Each man had a paddle for an hour and then a rest", "picture",
				6, 6, "10"));
		add(new VocabularyRecord("puddle", "['pʌdl]", "n.水坑;雨水坑",
				"wade or dabble in a puddle",
				"Children love splashing through puddles.", "picture", 6, 10,
				"10"));
		add(new VocabularyRecord("muddle", "['mʌdl]", "v.胡乱，混杂",
				"make into a puddle To cause confusion in", " ", "picture", 6,
				11, "10"));
		add(new VocabularyRecord("meddle", "['medl]", "v.干涉",
				"intrude in other people's affairs or business",
				"Someone's been meddling with my CD player.", "picture", 6, 4,
				"10"));
		add(new VocabularyRecord("coddle", "['kɒdl]", "v.悉心照料、娇惯",
				"treat with excessive indulgence To pamper,spoil,or coddle.",
				" ", "picture", 6, 7, "10"));
		add(new VocabularyRecord(
				"heddle",
				"['hedl]",
				"n.综片综线,综丝",
				"The lift of the heddles can be achieved by using cam or dobby operated harnesses and each has its own limitations. ",
				" ", "picture", 6, 30, "10"));
		add(new VocabularyRecord("doddle", "[doddle]", "n.轻而易举的事;不费吹灰之力的事",
				"an easy task", " ", "picture", 6, 10, "10"));
		add(new VocabularyRecord("waddle", "['wɒdl]", "adv.像鸭子一样摇摇摆摆地走",
				"walk unsteadily To move in a rolling,clumsy manner", " ",
				"picture", 6, 11, "10"));
		add(new VocabularyRecord("twaddle", "['twɒdl]", "n.废话",
				"pretentious or silly talk or writing",
				"This is all hopeless twaddle that I am saying about her",
				"picture", 7, 30, "10"));
		add(new VocabularyRecord("cuddle", "['kʌdl]", "v.拥抱, 怀抱",
				"move or arrange oneself in a comfortable and cozy position",
				"She likes to cuddle her doll.", "picture", 6, 7, "10"));
		add(new VocabularyRecord("addle", "['ædl]", "v.使腐坏,使糊涂adj.腐坏的",
				"become rotten	Eggs addle quickly in hot weather.", " ",
				"picture", 5, 18, "10"));
		add(new VocabularyRecord("saddle", "['sædl]", "n.鞍, 鞍状物",
				"put a saddle on",
				"Tell him to catch the ponies and saddle up.", "picture", 6, 2,
				"10"));
		add(new VocabularyRecord("riddle", "['rɪdl]", "n.谜",
				"a difficult problem	", "The affair rests a riddle.",
				"picture", 4, 6, "10"));
		add(new VocabularyRecord("fuddle", "['fʌdl]", "v.灌醉,使迷糊,使烂醉;狂饮",
				"consume alcohol", " ", "picture", 6, 18, "10"));
		add(new VocabularyRecord("huddle", "['hʌdl]", "v.挤作一团, (使)聚成一堆",
				"crouch or curl up",
				"The cat huddled itself up on the cushion", "picture", 6, 7,
				"10"));
		add(new VocabularyRecord("saddler", "['sædlə(r)]", "n.制造马鞍的人,马具商,乘用之马",
				"a maker and repairer and seller of equipment for horses", " ",
				"picture", 7, 20, "10"));
		add(new VocabularyRecord("babble", "['bæbl]", "v.说蠢话，牙牙学语",
				"to talk foolishly", " ", "picture", 6, 4, "20"));
		add(new VocabularyRecord("bubble", "['bʌbl]", "n.泡 vi.冒泡，沸腾",
				"an impracticable and illusory idea",
				"The boy was blowing bubbles.", "picture", 6, 3, "20"));
		add(new VocabularyRecord("dabble", "['dæbl]", "v.涉足，浅赏",
				"work with in an amateurish manner",
				"She dabbled her toes in the river. ", "picture", 6, 9, "20"));
		add(new VocabularyRecord("dribble", "['drɪbl]", "v.往下滴，淌",
				"saliva spilling from the mouth",
				"Water dribbles from eaves. ", "picture", 7, 18, "20"));
		add(new VocabularyRecord("gabble", "['ɡæbl]", "v.急促而不清楚地说",
				"rapid and indistinct speech",
				"He spent his time gabbling away in bars. ", "picture", 6, 10,
				"20"));
		add(new VocabularyRecord("gobble", "['ɡɒbl]", "v.贪婪地吃.吞没",
				"eat hastily without proper chewing",
				"The boy gobbled up an ice-cream cone. ", "picture", 6, 6, "20"));
		add(new VocabularyRecord("hobble", "['hɒbl]", "v.蹒跚，跛行",
				"a shackle for the ankles or feet", "To limp or hobble. ",
				"picture", 6, 12, "20"));
		add(new VocabularyRecord("nibble", "['nɪbl]", "一点点地咬，慢慢啃",
				"a small byte", "I nibble the Bread. ", "picture", 6, 12, "20"));
		add(new VocabularyRecord("pebble", "['pebl]", "n.卵石，细砾",
				"a small smooth rounded rock",
				"The street was paved with pebbles. ", "picture", 6, 5, "20"));
		add(new VocabularyRecord("quibble", "['kwɪbl]", "n.遁词，吹毛求疵的反对意见",
				"argue over petty things",
				"Don't quibble about unimportant things with me. ", "picture",
				7, 13, "20"));
		add(new VocabularyRecord("rabble", "[ˈræbl]", "n.乌合之众 vt.聚众闹事",
				"a disorderly crowd of people",
				"He doesn't mix with the rabble here.", "picture", 6, 8, "20"));
		add(new VocabularyRecord(
				"rubble",
				"[ˈrʌbl]",
				"n.碎石，瓦砾",
				"the remains of something that has been destroyed or broken up",
				"Rubble covered the pavement. ", "picture", 6, 17, "20"));
		add(new VocabularyRecord(
				"scribble",
				"['skrɪbl]",
				"v.乱写、乱涂",
				"poor handwriting",
				"A figure,design,or scribble drawn or written absent - mindedly. ",
				"picture", 8, 9, "20"));
		add(new VocabularyRecord("scribbler", "[ˈskrɪblə(r)]",
				"n.潦草书写的人,三流作家,小文人", "informal terms for journalists", " ",
				"picture", 9, 20, "20"));
		add(new VocabularyRecord("minuend", "['mɪnjʊend]", "n.被减数",
				"the number from which the subtrahend is subtracted", " ",
				"picture", 7, 20, "30"));
		add(new VocabularyRecord(
				"gendarme",
				"[ˈʒɑ:ndɑ:m]",
				"n.警官,宪兵",
				"a French policeman",
				"\"There are only,\" said the gendarme, \"a governor, a garrison, turnkeys, and good thick walls\"",
				"picture", 8, 20, "30"));
		add(new VocabularyRecord("crescendo", "[krə'ʃendəʊ]", "n.（音乐）渐强，高潮",
				"grow louder",
				"The advertising campaign reached a crescendo at Christmas.",
				"picture", 9, 20, "30"));
		add(new VocabularyRecord(
				"calender",
				"['kælɪndə]",
				"n.研光机",
				"press between rollers or plates so as to smooth",
				"High finish to paper achieved by damping the web as it passes through the calender stack.",
				"picture", 8, 20, "30"));
		add(new VocabularyRecord("fiendish", "[ˈfi:ndɪʃ]", "adj.极凶恶的",
				"extremely evil or cruel",
				"A fiendish blizzard;a fiendish problem.", "picture", 8, 18,
				"30"));
		add(new VocabularyRecord("agenda", "[ə'dʒendə]", "n.议程", "agenda item",
				"Let 's turn to item 6 on the agenda.", "picture", 6, 17, "30"));
		add(new VocabularyRecord(
				"stipend",
				"[ˈstaɪpend]",
				"n.薪水，薪俸，养老金",
				"a sum of money allotted on a regular basis",
				"An Anglican cleric holding the honorary title of prebend without a stipend",
				"picture", 7, 15, "30"));
		add(new VocabularyRecord("append", "[ə'pend]", "n.附加",
				"add to the very end",
				"A new clause was appended to the treaty.", "picture", 6, 15,
				"30"));
		add(new VocabularyRecord(
				"vender",
				"['vendə]",
				"n.小贩,卖主,自动售货机",
				"someone who promotes or exchanges goods or services for money",
				"The vender shall maintain the equipment in good order.",
				"picture", 6, 14, "30"));
		add(new VocabularyRecord("lender", "[ˈlendə(r)]", "n.出借人,贷方",
				"someone who lends money or gives credit in business matters",
				"Neither a borrower nor a lender be.", "picture", 6, 12, "30"));
		add(new VocabularyRecord(
				"fender",
				"['fendə(r)]",
				"n.挡泥板，护舷的垫子等",
				"a low metal guard to confine falling coals to a hearth	cosmetic fenders on cars.",
				" ", "picture", 6, 12, "30"));
		add(new VocabularyRecord("bend", "[bend]", "vi.弯曲,n.弯曲",
				"an angular or rounded shape ", " ", "picture", 6, 12, "30"));
		add(new VocabularyRecord("blend", "[blend]", "vt.混合 vi.混合；协调",
				"an occurrence of thorough mixing", "", "picture", 6, 12, "30"));
		add(new VocabularyRecord("offender", "[ə'fendə(r)]", "n.罪犯,无礼的人,得罪人的人",
				"a person who transgresses moral or civil law",
				"Gave the offender a light sentence.", "picture", 8, 6, "30"));
		add(new VocabularyRecord("befriend", "[bɪ'frend]", "vt.待人如友,帮助",
				"become friends with	Befriend",
				"The voices in the choir blend well.", "picture", 5, 6, "30"));
		add(new VocabularyRecord("lend", "[lend]", "vt.把..借给",
				"bestow a quality on",
				"Consumer lending had pyramided since the war.", "picture", 4,
				3, "30"));
		add(new VocabularyRecord("send", "[send]", "vt.送；派遣；发射",
				"transport commercially", "The hot pavement sent up shimmers.",
				"picture", 4, 2, "30"));
		add(new VocabularyRecord("mend", "[mend]", "vt.改正，修正；改进",
				"the act of putting something in working order again",
				"She mended the broken doll.", "picture", 4, 3, "30"));
		add(new VocabularyRecord("extend", "[ɪk'stend]", "vt.延长；扩大；致",
				"extend in scope or range or area",
				"His power extends to other lands.", "picture", 6, 1, "30"));
		add(new VocabularyRecord("spend", "[spend]", "vt.用钱，花费；度过", "pay out",
				"My wife spent money without stint.", "picture", 5, 1, "30"));
	}

	public boolean delete(String englishvocabulary) {
		try {
			db.delete("vocabulary", "englishvocabulary = ?",
					new String[] { englishvocabulary });
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void update(VocabularyRecord contact) {
		ContentValues cv = new ContentValues();
		cv.put("vocabularysoundmark", contact.vocabularysoundmark);
		cv.put("chineseexplain", contact.chineseexplain);
		cv.put("englishexplain", contact.englishexplain);
		cv.put("englishsentence", contact.englishsentence);
		cv.put("representpicture", contact.representpicture);
		cv.put("vocabularylength", contact.vocabularylength);
		cv.put("frequencyrank", contact.frequencyrank);
		cv.put("englishgroup", contact.englishgroup);
		db.update("vocabulary", cv, "englishvocabulary = ?",
				new String[] { contact.englishvocabulary });
	}

	public List<VocabularyRecord> query() {
		ArrayList<VocabularyRecord> contacts = new ArrayList<VocabularyRecord>();
		Cursor c = queryTheCursor();
		while (c.moveToNext()) {
			VocabularyRecord contact = new VocabularyRecord();
			contact._id = c.getInt(c.getColumnIndex("_id"));
			contact.englishvocabulary = c.getString(c
					.getColumnIndex("englishvocabulary"));
			contact.vocabularysoundmark = c.getString(c
					.getColumnIndex("vocabularysoundmark"));
			contact.chineseexplain = c.getString(c
					.getColumnIndex("chineseexplain"));
			contact.englishexplain = c.getString(c
					.getColumnIndex("englishexplain"));
			contact.englishsentence = c.getString(c
					.getColumnIndex("englishsentence"));
			contact.representpicture = c.getString(c
					.getColumnIndex("representpicture"));
			contact.vocabularylength = c.getDouble(c
					.getColumnIndex("vocabularylength"));
			contact.frequencyrank = c.getDouble(c
					.getColumnIndex("frequencyrank"));
			contact.englishgroup = c
					.getString(c.getColumnIndex("englishgroup"));
			contacts.add(contact);
		}
		c.close();
		return contacts;
	}

	public ArrayList<Map<String, Object>> queryGroupOrder() {
		ArrayList<Map<String, Object>> groups = new ArrayList<Map<String, Object>>();
		;
		Cursor c = queryGroup();
		while (c.moveToNext()) {
			Map<String, Object> group = new HashMap<String, Object>();
			group.put("group", c.getString(c.getColumnIndex("englishgroup")));
			group.put("score", c.getDouble(c.getColumnIndex("score")));
			groups.add(group);
		}
		c.close();
		return groups;
	}// 验证

	public VocabularyRecord querySomeone(String englishvocabulary) {
		Cursor c = queryTheCursor();
		while (c.moveToNext()) {
			VocabularyRecord contact = new VocabularyRecord();
			contact._id = c.getInt(c.getColumnIndex("_id"));
			contact.englishvocabulary = c.getString(c
					.getColumnIndex("englishvocabulary"));
			contact.vocabularysoundmark = c.getString(c
					.getColumnIndex("vocabularysoundmark"));
			contact.chineseexplain = c.getString(c
					.getColumnIndex("chineseexplain"));
			contact.englishexplain = c.getString(c
					.getColumnIndex("englishexplain"));
			contact.englishsentence = c.getString(c
					.getColumnIndex("englishsentence"));
			contact.representpicture = c.getString(c
					.getColumnIndex("representpicture"));
			contact.vocabularylength = c.getDouble(c
					.getColumnIndex("vocabularylength"));
			contact.frequencyrank = c.getDouble(c
					.getColumnIndex("frequencyrank"));
			contact.englishgroup = c
					.getString(c.getColumnIndex("englishgroup"));
			if (contact.englishvocabulary.equals(englishvocabulary)) {
				return contact;
			}
		}
		c.close();
		return null;
	}

	private Cursor queryTheCursor() {
		// TODO Auto-generated method stub
		Cursor c = db.rawQuery("SELECT * FROM vocabulary", null);
		return c;
	}

	private Cursor queryGroup() {
		// TODO Auto-generated method stub
		Cursor c = db
				.rawQuery(
						"SELECT englishgroup, sum(vocabularylength+frequencyrank) score FROM vocabulary group by englishgroup",
						null);
		return c;
	}

	public void closeDB() {
		db.close();
	}
}