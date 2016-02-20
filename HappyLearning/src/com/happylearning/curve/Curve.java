package com.happylearning.curve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.happylearning.study.OperateVocabularyDB;
import com.happylearning.study.VocabularyRecord;
import com.happylearning.user.OperatePersonDB;
import com.happylearning.user.PersonDB;
import com.happylearning.user.PersonRecord;

/*
 * --���ʱ�
 --Ԫ�أ����� ���ʷ���  �������� Ӣ������  Ӣ������  ��Ƶ�ȼ�  ���ʳ��ȣ����ʳ��Ⱥ����� �Ѷȵȼ�����Ƶ*10+����*100��   �������   
 --�Ѷȵȼ����㺯��       ��Ƶ*10+����*100
 --���������㺯��       ����*0+�ʸ�*100+����*0+����*0
 --�û���
 --Ԫ�أ��˺�  ����  ����  ����
 --�������� �� �û�ѧϰ��
 --ע���û���ͬʱ,�����û�ѧϰ��
 --Ԫ�أ� �˺�  ����   ѧϰ����  ��ȷ����  �������  ��ȷ��=��1+��ȷ������/��1+ѧϰ��������[ѧϰ����Ҫ��=�Ѷȵȼ�*�û�����/100]��ȡ������ ѧϰ׼ȷ��Ҫ�� ����ѧϰ���ȼ���ȷ��ԭ��ѧϰ����*60+��ȷ��*10000+�Ѷȵȼ������ȼ��ͣ���������ȼ����������ȼ�֮����С��ߣ�
 --ѧϰ���� 
 --ÿ���ڴ�ͳģʽ��ѧϰ��һ�Σ���Ϊѧϰһ��
 --��Ϸģʽ����Ҫ���������һ�Σ�������ѧϰһ��
 --��ȷ����/�������
 --��ͳģʽ�£����ȷ�ϣ�����ȷһ�Σ�����Ϊ����һ��
 --��Ϸģʽ�£��ڵ�������ȷƴдһ�Σ�����������ȷһ�Σ�����Ϊ����һ��
 --ÿ���޸Ķ���������ȷ��
 --ѧϰ����Ҫ�󣬼��㹫ʽ ѧϰ����Ҫ��=��Ƶ*10+����*100*�û�����/100
 --�״δ���ѧϰ����ʼ��
 --ѧϰ���ȼ���ȷ��ԭ��ѧϰ����*60+��ȷ��*10000+�Ѷȵȼ������ȼ��ͣ�
 --ÿ��ѧϰ�������
 --������ȼ����������ȼ�֮����С��ߣ�
 --�ȷ��������Ե����ȼ���Ȼ������Ȼ�����ǰ����ı�ʶ����ѡ��ǰ��������е���
 * */

public class Curve {

	private VocabularyRecord vocabulary;
	private OperateVocabularyDB vocabularyDB;
	private PersonVocabularyRecord personlearn;
	private OperatePersonalVocabularyDB personlearnDB;
	private PersonRecord person;
	private OperatePersonDB personDB;
	private CurveRecord curveRec;

	private double freWeight = 10;
	private double lenWeight = 100;
	private double leaWeight = 100;
	private double requestrate = 0.90;
	private double learntWeight = 60;
	private double corrWeight = 8000;
	private String nameenglish;
	private double requesttime;
	private double wordorder;
	private double grouporder;
	private double learntime;
	private double correcttime;
	private double wrongtime;
	private double correctrate;
	private String isok;
	public ArrayList<CurveRecord> personalCurve;
	public int wordssize = 0;

	// ���캯�������� �û������ʱ��û�ѧϰ�������߱�
	public Curve(Context context) {
		personDB = new OperatePersonDB(context);
		vocabularyDB = new OperateVocabularyDB(context);
		personlearnDB = new OperatePersonalVocabularyDB(context);
		personalCurve = new ArrayList<CurveRecord>();
	}

	public void initCurve(String countname, double ability) {
		List<VocabularyRecord> dictionary = new ArrayList<VocabularyRecord>();
		dictionary = vocabularyDB.query();
		ArrayList<Map<String, Object>> groupOrders = new ArrayList<Map<String, Object>>();
		groupOrders = vocabularyDB.queryGroupOrder();

		for (VocabularyRecord vocabulary : dictionary) {

			nameenglish = countname + vocabulary.englishvocabulary;
			// ����ѧϰ����Ҫ��=��Ƶ*10+����*100*�û�����/100
			requesttime = (int) ((vocabulary.frequencyrank * freWeight + vocabulary.vocabularylength
					* lenWeight)
					* ability / leaWeight);
			// ����ѧϰ���ȼ���ȷ��ԭ��ѧϰ����*60+��ȷ��*8000+�Ѷȵȼ������ȼ��ͣ�
			wordorder = (int) (vocabulary.frequencyrank * freWeight + vocabulary.vocabularylength
					* lenWeight);
			// ������ȼ�������ѧϰ���ȼ�֮����С��ߣ�
			for (Map<String, Object> map : groupOrders) {
				if (map.get("group").toString()
						.contentEquals(vocabulary.englishgroup)) {
					grouporder = (Double) map.get("score");
				}
			}
			personlearn = new PersonVocabularyRecord(nameenglish, countname,
					vocabulary.englishvocabulary, 0, 0, 0, 0, requesttime,
					requestrate, wordorder, vocabulary.englishgroup,
					grouporder, "N");
			personlearnDB.add(personlearn);
		}
	}

	public void changeCurveParameter(double abiWeight, double freWeight,
			double leaWeight, double requestrate, double learntWeight,
			double corrWeight) {
		this.freWeight = freWeight;
		this.leaWeight = leaWeight;
		this.learntWeight = learntWeight;
		this.corrWeight = corrWeight;
		this.requestrate = requestrate;
	}

	public void updateWholeCurve(String countname) {
		// �������ʱ�����е���
		List<VocabularyRecord> dictionary = new ArrayList<VocabularyRecord>();
		dictionary = vocabularyDB.query();
		// �����û�����ѧϰ����������е���𼰸��Ե�������ȼ�
		ArrayList<Map<String, Object>> groupOrders = new ArrayList<Map<String, Object>>();
		groupOrders = personlearnDB.queryGroupOrder(countname);
		person = new PersonRecord();
		person = personDB.querySomeone(countname);

		// ���ݵ��ʱ��е��ʣ��������û��ļ�������
		for (VocabularyRecord vocabulary : dictionary) {
			nameenglish = countname + vocabulary.englishvocabulary;
			personlearn = new PersonVocabularyRecord();
			personlearn = personlearnDB.querySomeone(nameenglish);
			// ����ѧϰ����Ҫ��=��Ƶ*10+����*100*�û�����/100
			requesttime = (int) ((vocabulary.frequencyrank * freWeight + vocabulary.vocabularylength
					* lenWeight)
					* person.ability / leaWeight);
			// ����ѧϰ���ȼ���ȷ��ԭ��ѧϰ����*60+��ȷ��*8000+�Ѷȵȼ������ȼ��ͣ�
			wordorder = (int) (personlearn.meetingtime * learntWeight
					+ personlearn.correcttime * corrWeight + (vocabulary.frequencyrank
					* freWeight + vocabulary.vocabularylength * lenWeight));
			// ������ȼ�������ѧϰ���ȼ�֮����С��ߣ�
			for (Map<String, Object> map : groupOrders) {
				if (map.containsKey(vocabulary.englishgroup)) {
					grouporder = Double.parseDouble(map.values().toString());
				}
			}
			PersonVocabularyRecord newpersonlearn = new PersonVocabularyRecord();
			newpersonlearn = new PersonVocabularyRecord(nameenglish, countname,
					vocabulary.englishvocabulary, personlearn.meetingtime,
					personlearn.correcttime, personlearn.wrongtime,
					personlearn.corretrate, requesttime, requestrate,
					wordorder, vocabulary.englishgroup, grouporder, "N");
			personlearnDB.add(newpersonlearn);
		}
	}

	public void updatePartCurve(String countname, String word, double leatime,
			double cortime, double wrotime) {

		personlearn = new PersonVocabularyRecord();
		vocabulary = vocabularyDB.querySomeone(word);
		nameenglish = countname + word;
		personlearn = personlearnDB.querySomeone(nameenglish);
		learntime = leatime + personlearn.meetingtime;
		correcttime = cortime + personlearn.correcttime;
		wrongtime = wrotime + personlearn.wrongtime;
		correctrate = (correcttime + 1) / (learntime + 1);
		wordorder = (int) (personlearn.meetingtime * learntWeight
				+ personlearn.correcttime * corrWeight + (vocabulary.frequencyrank
				* freWeight + vocabulary.vocabularylength * lenWeight));
		isok = "N";
		if (learntime >= personlearn.requestrate) {
			if (correctrate >= personlearn.requestrate) {
				isok = "Y";
			}
		}
		PersonVocabularyRecord newpersonlearn = new PersonVocabularyRecord(
				nameenglish, countname, word, learntime, correcttime,
				wrongtime, correctrate, personlearn.requesttime,
				personlearn.requestrate, wordorder, personlearn.englishgroup,
				personlearn.grouporder, isok);
		personlearnDB.updateSomeone(newpersonlearn);

	}

	public ArrayList<CurveRecord> initwords(String countname, int nums) {
		person = new PersonRecord();
		person = personDB.querySomeone(countname);
		vocabulary = new VocabularyRecord();
		List<PersonVocabularyRecord> groupwords = new ArrayList<PersonVocabularyRecord>();
		groupwords = personlearnDB.queryWords(countname);
		for (int j = 0; j < nums && j < groupwords.size(); j++) {
			System.out.println("Curve���������ݣ�" + j); // ����������
			if (groupwords.get(j).gethang.contains("N")) {
				vocabulary = vocabularyDB
						.querySomeone(groupwords.get(j).englishvocabulary);
				wordorder = (int) (vocabulary.frequencyrank * freWeight + vocabulary.vocabularylength
						* lenWeight);
				curveRec = new CurveRecord(groupwords.get(j).nameenglish,
						countname, groupwords.get(j).englishvocabulary,
						groupwords.get(j).meetingtime,
						groupwords.get(j).correcttime,
						groupwords.get(j).wrongtime, 0, 0, 0, 0,
						person.ability, wordorder);
				personalCurve.add(curveRec);
				this.wordssize = j;
			} else {
				nums += 1;
			}
		}
		// System.out.println("Curve��ʼ���ʿⷵ�ص�����"+personalCurve.toString());
		return personalCurve;

	}

}
