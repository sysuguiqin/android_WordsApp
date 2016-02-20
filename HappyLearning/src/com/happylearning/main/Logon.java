package com.happylearning.main;

import com.happylearning.R;
import com.happylearning.curve.MyApp;
import com.happylearning.study.Vocabulary;
import com.happylearning.user.OperatePersonDB;
import com.happylearning.user.PersonRecord;
import com.happylearning.user.User;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Logon extends Activity {

	private ImageButton visitBtn;
	private ImageButton loginBtn;
	private ImageButton registBtn;
	private ImageButton exitBtn;
	private EditText countEdit;
	private EditText passEdit;
	private Context context = this;
	private OperatePersonDB personDB;
	private PersonRecord person;
	private MyApp myApp;

	// 创建活动
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logon);
		// 初始化控件
		exitBtn = (ImageButton) findViewById(R.id.exitbtn);
		visitBtn = (ImageButton) findViewById(R.id.visitbtn);
		loginBtn = (ImageButton) findViewById(R.id.logonbtn);
		registBtn = (ImageButton) findViewById(R.id.registerbtn);
		countEdit = (EditText) findViewById(R.id.counted);
		passEdit = (EditText) findViewById(R.id.passworded);
		myApp = (MyApp) getApplication();

		// 退出整个程序
		exitBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				myApp.changePersonalCurve();
				finish();
			}

		});
		// 以游客身份访问，使用简易版的游戏模式、学习模式、个人资料
		visitBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("countName", "visitor");
				bundle.putDouble("Time", 0);
				intent.putExtras(bundle);
				intent.setClass(Logon.this, Choose.class);
				finish();
				startActivity(intent);
			}
		});
		registBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("countName", "register");
				intent.putExtras(bundle);
				intent.setClass(Logon.this, User.class);
				finish();
				startActivity(intent);
			}
		});
		personDB = new OperatePersonDB(this);

		loginBtn.setOnClickListener(new OnClickListener() {
			// private List<member> persons;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = countEdit.getText().toString();
				String pass = passEdit.getText().toString();
				// System.out.println(name + "    " + pass);
				if (name == null || pass == null || "".equals(name)
						|| "".equals(pass)) {
					Toast toast = Toast.makeText(context, "账号或密码请填写完整！",
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				} else {
					person = personDB.querySomeone(name);
					if (person != null && person.countname.equals(name)
							&& person.password.equals(pass)) {
						countEdit.setText("");
						passEdit.setText("");
						Intent intent = new Intent();
						Bundle bundle = new Bundle();
						bundle.putString("countName", name);
						bundle.putDouble("Time", 1);
						intent.putExtras(bundle);
						intent.setClass(Logon.this, Choose.class);
						finish();
						startActivity(intent);
					} else {

						AlertDialog.Builder builder = new Builder(Logon.this);
						builder.setMessage("密码或账号错误");
						builder.setTitle("提示");
						builder.setPositiveButton(
								"修改",
								new android.content.DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										countEdit.setText("");
										passEdit.setText("");
										dialog.dismiss();
										countEdit.requestFocus();

									}
								});
						builder.setNegativeButton(
								"取消",
								new android.content.DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								});
						builder.create().show();
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fullscreen, menu);
		return true;
	}

}
