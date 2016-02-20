package com.happylearning.main;

import java.util.ArrayList;

import com.happylearning.R;
import com.happylearning.curve.Curve;
import com.happylearning.curve.CurveRecord;
import com.happylearning.curve.MyApp;
import com.happylearning.game.bobac;
import com.happylearning.game.cutFruit;
import com.happylearning.game.ligature;
import com.happylearning.study.Study;
import com.happylearning.study.Vocabulary;
import com.happylearning.user.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Choose extends Activity {

	private String countname;
	private double time;
	private ImageButton personBtn;
	private ImageButton gameBtn;
	private ImageButton studyBtn;
	private Button exitBtn;
	final Context context = this;
	private Curve curve;
	private MyApp myApp;
	private ArrayList<CurveRecord> personalCurve;

	// 创建活动
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose);
		Bundle bundle = this.getIntent().getExtras();
		countname = bundle.getString("countName");
		time = bundle.getDouble("time") + 1;

		personBtn = (ImageButton) findViewById(R.id.personbtn);
		gameBtn = (ImageButton) findViewById(R.id.gamebtn);
		studyBtn = (ImageButton) findViewById(R.id.studybtn);
		exitBtn = (Button) findViewById(R.id.mexitbtn);
		final Context context = this;
		personalCurve = new ArrayList<CurveRecord>();

		if (time > 1) {
			myApp.changePersonalCurve();
		}
		Handler handler = new Handler();
		handler.post(r);

		// 跳转到单词学习界面
		studyBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (countname.contains("administer")) {
					AlertDialog.Builder builder = new Builder(Choose.this);
					builder.setMessage("请选择要进入的界面");
					builder.setTitle("提示");
					builder.setPositiveButton(
							"单词修改",
							new android.content.DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									Intent intent = new Intent();
									Bundle bundle = new Bundle();
									bundle.putString("countName", countname);
									bundle.putDouble("time", time);
									intent.putExtras(bundle);
									intent.setClass(Choose.this,
											Vocabulary.class);
									finish();
									startActivity(intent);

								}
							});
					builder.setNegativeButton(
							"单词学习",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent = new Intent();
									Bundle bundle = new Bundle();
									bundle.putString("countName", countname);
									bundle.putDouble("time", time);
									intent.putExtras(bundle);
									intent.setClass(Choose.this, Study.class);
									finish();
									startActivity(intent);
								}
							});
					builder.create().show();

				} else {
					Intent intent = new Intent();
					Bundle bundle = new Bundle();
					bundle.putString("countName", countname);
					intent.putExtras(bundle);
					intent.setClass(Choose.this, Study.class);
					finish();
					startActivity(intent);
				}
			}
		});

		gameBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("countName", countname);
				bundle.putDouble("time", time);
				intent.putExtras(bundle);
				intent.setClass(Choose.this, bobac.class);
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
				intent.setClass(Choose.this, Logon.class);
				finish();
				startActivity(intent);

			}
		});
		personBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("countName", countname);
				bundle.putDouble("time", time);
				intent.putExtras(bundle);
				intent.setClass(Choose.this, User.class);
				finish();
				startActivity(intent);

			}
		});
	}

	Runnable r = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			curve = new Curve(context);
			personalCurve = new ArrayList<CurveRecord>();
			personalCurve = curve.initwords(countname, 18);
			myApp = (MyApp) getApplication();
			// System.out.println("创建用户之后初始化程序的共享变量："+personalCurve.toString());
			myApp.setCurve(personalCurve);
		}
	};
}