package com.happylearning.curve;

public class PersonVocabularyRecord {

	public int _id;
	public String nameenglish;
	public String countname;
	public String englishvocabulary;
	public double meetingtime;
	public double correcttime;
	public double wrongtime;
	public double corretrate;
	public double requesttime;
	public double requestrate;
	public double wordorder;
	public String englishgroup;
	public double grouporder;
	public String gethang;

	public PersonVocabularyRecord() {
	}

	public PersonVocabularyRecord(String nameenglish, String countname,
			String englishvocabulary, double meetingtime, double correcttime,
			double wrongtime, double corretrate, double requesttime,
			double requestrate, double wordorder, String englishgroup,
			double grouporder, String gethang) {
		this.nameenglish = nameenglish;
		this.countname = countname;
		this.englishvocabulary = englishvocabulary;
		this.meetingtime = meetingtime;
		this.correcttime = correcttime;
		this.wrongtime = wrongtime;
		this.corretrate = corretrate;
		this.requesttime = requesttime;
		this.requestrate = requestrate;
		this.wordorder = wordorder;
		this.englishgroup = englishgroup;
		this.grouporder = grouporder;
		this.gethang = gethang;
	}

	public void changePersonVocabularyRecord(String nameenglish,
			String countname, String englishvocabulary, double meetingtime,
			double correcttime, double wrongtime, double corretrate,
			double requesttime, double requestrate, double wordorder,
			String englishgroup, double grouporder, String gethang) {
		this.nameenglish = nameenglish;
		this.countname = countname;
		this.englishvocabulary = englishvocabulary;
		this.meetingtime = meetingtime;
		this.correcttime = correcttime;
		this.wrongtime = wrongtime;
		this.corretrate = corretrate;
		this.requesttime = requesttime;
		this.requestrate = requestrate;
		this.wordorder = wordorder;
		this.englishgroup = englishgroup;
		this.grouporder = grouporder;
		this.gethang = gethang;
	}
}
