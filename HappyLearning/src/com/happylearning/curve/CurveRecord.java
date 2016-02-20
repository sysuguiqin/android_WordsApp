package com.happylearning.curve;

public class CurveRecord {

	public String nameenglish;
	public String countname;
	public String englishvocabulary;
	public double meetingtime;
	public double correcttime;
	public double wrongtime;
	public double learnmodel;
	public double gamemodel1;
	public double gamemodel2;
	public double gamemodel3;
	public double ability;
	public double difdegree;

	public CurveRecord() {
	}

	public CurveRecord(String nameenglish, String countname,
			String englishvocabulary, double meetingtime, double correcttime,
			double wrongtime, double learnmodel, double gamemodel1,
			double gamemodel2, double gamemodel3, double ability,
			double difdegree) {
		this.nameenglish = nameenglish;
		this.countname = countname;
		this.englishvocabulary = englishvocabulary;
		this.meetingtime = meetingtime;
		this.correcttime = correcttime;
		this.wrongtime = wrongtime;
		this.learnmodel = learnmodel;
		this.gamemodel1 = gamemodel1;
		this.gamemodel2 = gamemodel2;
		this.gamemodel3 = gamemodel3;
		this.ability = ability;
		this.difdegree = difdegree;
	}

	public void changeCurveRecord(String nameenglish, String countname,
			String englishvocabulary, double meetingtime, double correcttime,
			double wrongtime, double learnmodel, double gamemodel1,
			double gamemodel2, double gamemodel3, double ability,
			double difdegree) {
		this.nameenglish = nameenglish;
		this.countname = countname;
		this.englishvocabulary = englishvocabulary;
		this.meetingtime = meetingtime;
		this.correcttime = correcttime;
		this.wrongtime = wrongtime;
		this.learnmodel = learnmodel;
		this.gamemodel1 = gamemodel1;
		this.gamemodel2 = gamemodel2;
		this.gamemodel3 = gamemodel3;
		this.ability = ability;
		this.difdegree = difdegree;
	}
}
