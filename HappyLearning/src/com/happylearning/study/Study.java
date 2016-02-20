package com.happylearning.study;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.happylearning.R;
import com.happylearning.curve.Curve;
import com.happylearning.curve.CurveRecord;
import com.happylearning.curve.MyApp;
import com.happylearning.main.Choose;
import com.happylearning.main.Logon;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Study extends Activity {

	private static String englishvocabulary;
	private String vocabularysoundmark;
	private String chineseexplain;
	private String englishexplain;
	private String englishsentence;
	private String countname;
	private VocabularyRecord vocabulary;
	private OperateVocabularyDB vocabularyDB;
	private double time;
	private ArrayList<CurveRecord> personCurve;
	private TextView wordtv;
	private TextView saccenttv;
	private TextView schinesetv;
	private TextView senglishtv;
	private TextView senglsentv;
	private Button nextBtn;
	private Button exitBtn;
	private Button returnBtn;
	private ImageButton readerBtn;
	TextToSpeech specker;
	private int order = 0;
	private int curveindex = 0;
	private MyApp myApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study);
		Bundle bundle = this.getIntent().getExtras();
		countname = bundle.getString("countName");
		time = bundle.getDouble("time");

		wordtv = (TextView) findViewById(R.id.swordtv);
		saccenttv = (TextView) findViewById(R.id.saccenttv);
		schinesetv = (TextView) findViewById(R.id.schinesetv);
		senglishtv = (TextView) findViewById(R.id.senglishtv);
		senglsentv = (TextView) findViewById(R.id.senglsentv);
		nextBtn = (Button) findViewById(R.id.sgoonBtn);
		exitBtn = (Button) findViewById(R.id.sexitzBtn);
		returnBtn = (Button) findViewById(R.id.sreturnBtn);
		readerBtn = (ImageButton) findViewById(R.id.sreaderBtn);
		personCurve = new ArrayList<CurveRecord>();
		myApp = (MyApp) this.getApplication();
		specker = new TextToSpeech(this, new OnInitListener() {
			@Override
			public void onInit(int status) {
				// TODO Auto-generated method stub
				if (status == TextToSpeech.SUCCESS) {
					int result = specker.setLanguage(Locale.ENGLISH);
					// 设置只能朗读英文
					if (result == TextToSpeech.LANG_MISSING_DATA
							|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
						// 要是结果没值，就在后台打印出来
						readerBtn.setEnabled(false);
					}
				} else {
					readerBtn.setEnabled(true);
				}
			}
		});
		order = 0;
		init();

		// 跳转到模式选择界面
		returnBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("countName", countname);
				bundle.putDouble("time", time);
				intent.putExtras(bundle);
				intent.setClass(Study.this, Choose.class);
				finish();
				startActivity(intent);
			}

		});
		// 跳转到用户登陆界面
		exitBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Study.this, Logon.class);
				finish();
				startActivity(intent);
			}
		});
		// 更新单词
		nextBtn.setOnClickListener(new OnClickListener() {
			private double correcttime;
			private double wrongtime;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new Builder(Study.this);
				builder.setMessage("已经掌握单词");
				builder.setTitle("提示");
				builder.setPositiveButton("是",
						new android.content.DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								correcttime = 1;
								wrongtime = 0;
							}
						});
				builder.setNegativeButton("否",
						new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {

							}
						});
				myApp.changeCurve(curveindex, 0, 0, 0, 1, 1, correcttime * 3,
						wrongtime * 3);
				builder.create().show();
				init();
			}
		});
		// 点击阅读单词
		readerBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				specker.speak(englishvocabulary, TextToSpeech.QUEUE_FLUSH, null);
			}
		});

	}

	public void init() {

		personCurve = myApp.getCurve();
		System.out.println("学习模式初始化内容：" + personCurve);
		vocabularysoundmark = "NULL";
		chineseexplain = "NULL";
		englishexplain = "NULL";
		englishsentence = "NULL";
		while (order < personCurve.size()) {
			if (personCurve.get(order).learnmodel == 0) {
				this.curveindex = order;
				// System.out.println("学习模式单词数组单词数量：" + personCurve.size());
				englishvocabulary = personCurve.get(order).englishvocabulary;
				// System.out.println("学习模式单词数组单词：" + englishvocabulary);
				final Context context = this;
				vocabularyDB = new OperateVocabularyDB(context);
				vocabulary = vocabularyDB.querySomeone(englishvocabulary);
				// System.out.println("学习模式单词内容：" +
				// vocabulary.englishvocabulary);
				if (vocabulary != null) {
					vocabularysoundmark = vocabulary.vocabularysoundmark;
					chineseexplain = vocabulary.chineseexplain;
					englishexplain = vocabulary.englishexplain;
					englishsentence = vocabulary.englishsentence;
					break;
				}
			} else {
				order++;
				;
			}

		}
		wordtv.setText(englishvocabulary);
		saccenttv.setText(vocabularysoundmark);
		schinesetv.setText(chineseexplain);
		senglishtv.setText(englishexplain);
		senglsentv.setText(englishsentence);

	}
}