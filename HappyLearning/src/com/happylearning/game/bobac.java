package com.happylearning.game;

import java.util.ArrayList;
import java.util.Locale;

import com.happylearning.R;
import com.happylearning.curve.CurveRecord;
import com.happylearning.curve.MyApp;
import com.happylearning.main.Choose;
import com.happylearning.main.Logon;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class bobac extends Activity implements OnClickListener {

	private ImageButton returnbtn;
	private ImageButton startbtn;
	private ImageButton overbtn;
	private ImageButton exitbtn;
	private ImageButton abtn;
	private ImageButton bbtn;
	private ImageButton cbtn;
	private ImageButton dbtn;
	private ImageButton ebtn;
	private ImageButton fbtn;
	private ImageButton gbtn;
	private ImageButton hbtn;
	private ImageButton ibtn;
	private ImageButton jbtn;
	private ImageButton kbtn;
	private ImageButton lbtn;
	private ImageButton mbtn;
	private ImageButton nbtn;
	private ImageButton obtn;
	private ImageButton pbtn;
	private ImageButton qbtn;
	private ImageButton rbtn;
	private ImageButton sbtn;
	private ImageButton tbtn;
	private ImageButton ubtn;
	private ImageButton vbtn;
	private ImageButton wbtn;
	private ImageButton xbtn;
	private ImageButton ybtn;
	private ImageButton zbtn;
	protected ImageButton btn;
	TextToSpeech specker;

	private int wordorder = 0;
	private String word;
	private String countname;
	private String showText;
	private double time;
	private int order;
	private int isOver;
	private int curveindex = 0;
	private int isWrong = 0;
	private boolean isruning = false;
	private ArrayList<CurveRecord> personCurve;
	private Context context = this;
	private MyApp myApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.bobac);
		personCurve = new ArrayList<CurveRecord>();
		myApp = (MyApp) getApplication();
		personCurve = myApp.getCurve();
		Bundle bundle = this.getIntent().getExtras();
		countname = bundle.getString("countName");
		time = bundle.getDouble("time");

		abtn = (ImageButton) findViewById(R.id.aBtn);
		bbtn = (ImageButton) findViewById(R.id.bBtn);
		cbtn = (ImageButton) findViewById(R.id.cBtn);
		dbtn = (ImageButton) findViewById(R.id.dBtn);
		ebtn = (ImageButton) findViewById(R.id.eBtn);
		fbtn = (ImageButton) findViewById(R.id.fBtn);
		gbtn = (ImageButton) findViewById(R.id.gBtn);
		hbtn = (ImageButton) findViewById(R.id.hBtn);
		ibtn = (ImageButton) findViewById(R.id.iBtn);
		jbtn = (ImageButton) findViewById(R.id.jBtn);
		kbtn = (ImageButton) findViewById(R.id.kBtn);
		lbtn = (ImageButton) findViewById(R.id.lBtn);
		mbtn = (ImageButton) findViewById(R.id.mBtn);
		nbtn = (ImageButton) findViewById(R.id.nBtn);
		obtn = (ImageButton) findViewById(R.id.oBtn);
		pbtn = (ImageButton) findViewById(R.id.pBtn);
		qbtn = (ImageButton) findViewById(R.id.qBtn);
		rbtn = (ImageButton) findViewById(R.id.rBtn);
		sbtn = (ImageButton) findViewById(R.id.sBtn);
		tbtn = (ImageButton) findViewById(R.id.tBtn);
		ubtn = (ImageButton) findViewById(R.id.uBtn);
		vbtn = (ImageButton) findViewById(R.id.vBtn);
		wbtn = (ImageButton) findViewById(R.id.wBtn);
		xbtn = (ImageButton) findViewById(R.id.xBtn);
		ybtn = (ImageButton) findViewById(R.id.yBtn);
		zbtn = (ImageButton) findViewById(R.id.zBtn);
		exitbtn = (ImageButton) findViewById(R.id.bexitBtn);
		startbtn = (ImageButton) findViewById(R.id.bstartBtn);
		overbtn = (ImageButton) findViewById(R.id.boverBtn);
		returnbtn = (ImageButton) findViewById(R.id.breturnBtn);

		abtn.setOnClickListener(this);
		bbtn.setOnClickListener(this);
		cbtn.setOnClickListener(this);
		dbtn.setOnClickListener(this);
		ebtn.setOnClickListener(this);
		fbtn.setOnClickListener(this);
		gbtn.setOnClickListener(this);
		hbtn.setOnClickListener(this);
		ibtn.setOnClickListener(this);
		jbtn.setOnClickListener(this);
		kbtn.setOnClickListener(this);
		lbtn.setOnClickListener(this);
		mbtn.setOnClickListener(this);
		nbtn.setOnClickListener(this);
		obtn.setOnClickListener(this);
		pbtn.setOnClickListener(this);
		qbtn.setOnClickListener(this);
		rbtn.setOnClickListener(this);
		sbtn.setOnClickListener(this);
		tbtn.setOnClickListener(this);
		ubtn.setOnClickListener(this);
		vbtn.setOnClickListener(this);
		wbtn.setOnClickListener(this);
		xbtn.setOnClickListener(this);
		ybtn.setOnClickListener(this);
		zbtn.setOnClickListener(this);
		exitbtn.setOnClickListener(this);
		startbtn.setOnClickListener(this);
		overbtn.setOnClickListener(this);
		returnbtn.setOnClickListener(this);
		order = 0;

		specker = new TextToSpeech(this, new OnInitListener() {
			@Override
			public void onInit(int status) {
				// TODO Auto-generated method stub
				if (status == TextToSpeech.SUCCESS) {
					int result = specker.setLanguage(Locale.ENGLISH);// 设置只能朗读英文
					if (result == TextToSpeech.LANG_MISSING_DATA
							|| result == TextToSpeech.LANG_NOT_SUPPORTED) {// 要是结果没值，就在后台打印出来
						System.out.println("lanageTag not use");
					} else {// 模拟机在启动时朗读下面的英文
						specker.speak("Welcome to use TextToSpeech!",
								TextToSpeech.QUEUE_FLUSH, null);
					}
				}
			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		btn = (ImageButton) v;
		switch (btn.getId()) {
		case R.id.aBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'a') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.bBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'b') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.cBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'c') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.dBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'd') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.eBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'e') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.fBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'f') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.gBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'g') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.hBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'h') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.iBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'i') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.jBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'j') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.kBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'k') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.lBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'l') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.mBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'm') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.nBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'n') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.oBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'a') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.pBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'p') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.qBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'q') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.rBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'r') {
					wordorder++;
				}
			} else {

				isWrong = isWrong + 1;
			}
			break;
		case R.id.sBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 's') {
					wordorder++;
				}
			} else {

				isWrong = isWrong + 1;
			}
			break;
		case R.id.tBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 't') {
					wordorder++;
				}
			} else {

				isWrong = isWrong + 1;
			}
			break;
		case R.id.uBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'u') {
					wordorder++;
				}
			} else {

				isWrong = isWrong + 1;
			}
			break;
		case R.id.vBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'v') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.wBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'w') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.xBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'x') {
					wordorder++;
				}
			} else {

				isWrong = isWrong + 1;
			}
			break;
		case R.id.yBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'y') {
					wordorder++;
				}
			} else {

				isWrong = isWrong + 1;
			}
			break;
		case R.id.zBtn:
			if (wordorder < word.length()) {
				if (word.charAt(wordorder) == 'z') {
					wordorder++;
				}
			} else {
				isWrong = isWrong + 1;
			}
			break;
		case R.id.boverBtn:
			if (word.length() != wordorder) {
				isWrong = isWrong + 1;
			}

			if (isWrong == 0) {
				showText = "恭喜你，拼对了，本次的单词为:" + word;
			} else {
				showText = "没关系，仔细听，就会了。本次的单词为：" + word;
			}
			Toast toast = Toast.makeText(context, showText, Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.show();
			myApp.changeCurve(curveindex, 0, 0, 1, 0, 1, 1 - isWrong, isWrong);
			isruning = false;
			break;
		case R.id.bstartBtn:
			isWrong = 0;
			init();
			break;
		case R.id.bexitBtn:
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("countName", countname);
			intent.putExtras(bundle);
			intent.setClass(bobac.this, Logon.class);
			startActivity(intent);
			finish();
			break;
		case R.id.breturnBtn:
			Intent intent1 = new Intent();
			Bundle bundle1 = new Bundle();
			bundle1.putString("countName", countname);
			intent1.putExtras(bundle1);
			intent1.setClass(bobac.this, Choose.class);
			startActivity(intent1);
			finish();
			break;
		default:
			break;
		}

	}

	public void init() {
		isOver = 0;
		while (order < personCurve.size()) {
			if (personCurve.get(order).gamemodel3 == 0) {
				curveindex = order;
				word = personCurve.get(order).englishvocabulary;
				System.out.println("游戏模式3" + word);
				wordorder = 0;
				isOver = 1;
				isruning = true;
				order++;
				break;
			} else {
				order++;
			}
		}
		if (isOver == 0) {
			isruning = false;
			AlertDialog.Builder builder = new Builder(bobac.this);
			builder.setMessage("是否继续下一轮");
			builder.setTitle("提示");
			builder.setPositiveButton("是",
					new android.content.DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putString("countName", countname);
							bundle.putDouble("time", time);
							intent.putExtras(bundle);
							intent.setClass(bobac.this, cutFruit.class);
							finish();
							startActivity(intent);
						}
					});
			builder.setNegativeButton("否",
					new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putString("countName", countname);
							bundle.putDouble("time", time);
							intent.putExtras(bundle);
							intent.setClass(bobac.this, Choose.class);
							finish();
							startActivity(intent);
						}
					});
			builder.create().show();
		} else {
			handler.post(r);

		}
	}

	Handler handler = new Handler();
	Runnable r = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 每隔100ms更新一次
			if (isruning) {
				specker.speak(word, TextToSpeech.QUEUE_FLUSH, null);
				handler.postDelayed(r, 5000);
			}
		}
	};

}
