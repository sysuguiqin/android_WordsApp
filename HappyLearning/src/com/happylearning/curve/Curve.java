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
 * --单词表
 --元素：单词 单词发音  中文释义 英文释义  英文例句  词频等级  单词长度（单词长度函数） 难度等级（词频*10+长度*100）   单词组别   
 --难度等级计算函数       词频*10+长度*100
 --单词组别计算函数       词义*0+词根*100+词音*0+词性*0
 --用户表
 --元素：账号  姓名  邮箱  能力
 --记忆曲线 ： 用户学习表
 --注册用户的同时,创建用户学习表
 --元素： 账号  单词   学习次数  正确次数  错误次数  正确率=（1+正确次数）/（1+学习次数），[学习次数要求=难度等级*用户能力/100]（取整数） 学习准确率要求 单词学习优先级（确定原则：学习次数*60+正确率*10000+难度等级，优先级低）、组别优先级（单词优先级之和最小最高）
 --学习次数 
 --每次在传统模式下学习过一次，就为学习一次
 --游戏模式下需要完整的完成一次，才能算学习一次
 --正确次数/错误次数
 --传统模式下，点击确认，即正确一次，否则为错误一次
 --游戏模式下，在第三轮正确拼写一次，才能算是正确一次，否则为错误一次
 --每次修改都会修正正确率
 --学习次数要求，计算公式 学习次数要求=词频*10+长度*100*用户能力/100
 --首次创建学习表即初始化
 --学习优先级（确定原则：学习次数*60+正确率*10000+难度等级，优先级低）
 --每次学习都会更新
 --组别优先级（单词优先级之和最小最高）
 --先分组计算各自的优先级，然后排序，然后根据前五组的标识符，选择前五组的所有单词
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

	// 构造函数，构造 用户表、单词表、用户学习记忆曲线表
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
			// 单词学习次数要求=词频*10+长度*100*用户能力/100
			requesttime = (int) ((vocabulary.frequencyrank * freWeight + vocabulary.vocabularylength
					* lenWeight)
					* ability / leaWeight);
			// 单词学习优先级（确定原则：学习次数*60+正确率*8000+难度等级，优先级低）
			wordorder = (int) (vocabulary.frequencyrank * freWeight + vocabulary.vocabularylength
					* lenWeight);
			// 组别优先级（单词学习优先级之和最小最高）
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
		// 遍历单词表的所有单词
		List<VocabularyRecord> dictionary = new ArrayList<VocabularyRecord>();
		dictionary = vocabularyDB.query();
		// 遍历用户单词学习情况表中所有的组别及各自的组别优先级
		ArrayList<Map<String, Object>> groupOrders = new ArrayList<Map<String, Object>>();
		groupOrders = personlearnDB.queryGroupOrder(countname);
		person = new PersonRecord();
		person = personDB.querySomeone(countname);

		// 根据单词表中单词，逐个完成用户的记忆曲线
		for (VocabularyRecord vocabulary : dictionary) {
			nameenglish = countname + vocabulary.englishvocabulary;
			personlearn = new PersonVocabularyRecord();
			personlearn = personlearnDB.querySomeone(nameenglish);
			// 单词学习次数要求=词频*10+长度*100*用户能力/100
			requesttime = (int) ((vocabulary.frequencyrank * freWeight + vocabulary.vocabularylength
					* lenWeight)
					* person.ability / leaWeight);
			// 单词学习优先级（确定原则：学习次数*60+正确率*8000+难度等级，优先级低）
			wordorder = (int) (personlearn.meetingtime * learntWeight
					+ personlearn.correcttime * corrWeight + (vocabulary.frequencyrank
					* freWeight + vocabulary.vocabularylength * lenWeight));
			// 组别优先级（单词学习优先级之和最小最高）
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
			System.out.println("Curve数组有内容：" + j); // 数组有内容
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
		// System.out.println("Curve初始化词库返回的内容"+personalCurve.toString());
		return personalCurve;

	}

}
