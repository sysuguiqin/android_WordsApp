package com.happylearning.game;

import java.util.ArrayList;

import com.happylearning.R;
import com.happylearning.curve.CurveRecord;
import com.happylearning.curve.MyApp;
import com.happylearning.main.Choose;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ligature extends Activity {
	/** Called when the activity is first created. */
	private ProgressBar pb;
	private TextView show_RemainTime;
	private ligatureModel gm;
	public static final int EXIT = Menu.FIRST;
	public static final int REARRANGE = Menu.FIRST + 1;
	public static final int RETURN = REARRANGE + 1;
	private int dormant = 1000;
	public int extent = -1;
	private boolean isCancel = true;
	private ArrayList<CurveRecord> personCurve;
	private MyApp myApp;
	private String countname;
	private double time;
	private int firstindex;
	private int group;
	private int size = 6;
	private int[] imageTypePicture;
	private Context context = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ligature);
		personCurve = new ArrayList<CurveRecord>();
		myApp = (MyApp) getApplication();
		personCurve = myApp.getCurve();
		Bundle bundle = this.getIntent().getExtras();
		countname = bundle.getString("countName");
		time = bundle.getDouble("time");
		show_RemainTime = (TextView) findViewById(R.id.show_remainTime);
		gm = (ligatureModel) findViewById(R.id.gm);
		group = 1;
		imageTypePicture = new int[] { R.drawable.a, R.drawable.b,
				R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f,
				R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j,
				R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n,
				R.drawable.o, R.drawable.p, R.drawable.q, R.drawable.r };
		gm.initImageType(initImageType());
		pb = (ProgressBar) findViewById(R.id.pb);
		pb.setMax(gm.GAMETIME);
		pb.incrementProgressBy(extent);
		pb.setProgress(gm.PROCESS_VALUE);
		mRedrawHandler.sleep(dormant);
	}

	private RefreshHandler mRedrawHandler = new RefreshHandler();

	class RefreshHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			if (isCancel) {
				run();
			}
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);// 移除信息队列中最顶部的信息（从顶部取出信息）
			sendMessageDelayed(obtainMessage(0), delayMillis);// 获得顶部信息并延时发送
		}
	};

	public int[] initImageType() {
		int order = 0;
		int imgid;
		for (int i = (group - 1) * size; i < group * size; i++) {
			if (i < personCurve.size()) {
				imgid = context.getResources().getIdentifier(
						personCurve.get(i).englishvocabulary, "drawable",
						context.getPackageName());
				imageTypePicture[order] = imgid;
				order++;
			} else {
				break;
			}
		}
		return imageTypePicture;
	}

	public void run() {
		if (gm.PROCESS_VALUE == 0 && gm.much != 0) {
			gm.setEnabled(false);
			myApp.changegroupCurve((group - 1) * 6, group * 6, 0, 0, 1, 0, 1,
					0, 1);
			dialogForFail().show();
		} else if (gm.PROCESS_VALUE != 0 && gm.much == 0) {
			if (group <= 1) {
				gm.setEnabled(false);
				myApp.changegroupCurve((group - 1) * 6, group * 6, 0, 0, 1, 0,
						1, 1, 0);
				dialogForSucceed().show();
			} else {
				Toast toast = Toast.makeText(context, "恭喜你，成功进入下一轮游戏！",
						Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("countName", countname);
				bundle.putDouble("time", time);
				intent.putExtras(bundle);
				intent.setClass(ligature.this, bobac.class);
				finish();
				startActivity(intent);
			}
		} else if (gm.PROCESS_VALUE > 0 && gm.much != 0) {
			gm.PROCESS_VALUE--;
			pb.setProgress(gm.PROCESS_VALUE);
			show_RemainTime.setText(String.valueOf(gm.PROCESS_VALUE));
			mRedrawHandler.sleep(dormant);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, RETURN, 0, "延长时间");
		menu.add(0, REARRANGE, 0, "重新布局");
		menu.add(0, EXIT, 0, "再玩一次");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case RETURN:
			gm.PROCESS_VALUE = gm.PROCESS_VALUE + 20;
			pb.setProgress(gm.PROCESS_VALUE);
			break;
		case REARRANGE:
			gm.rearrange();
			gm.PROCESS_VALUE = gm.PROCESS_VALUE - 5;
			pb.setProgress(gm.PROCESS_VALUE);
			break;
		case EXIT:
			newPlay();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStop() {
		isCancel = false;
		pb = null;
		gm = null;
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		isCancel = false;
		super.onDestroy();
	}

	@Override
	protected void onStart() {
		isCancel = false;
		newPlay();
		isCancel = true;
		super.onStart();

	}

	@Override
	protected void onRestart() {
		gm.reset();
		super.onRestart();
	}

	public void newPlay() {
		gm.reset();
		pb.setProgress(gm.GAMETIME);
		gm.PROCESS_VALUE = gm.GAMETIME;
		mRedrawHandler.sleep(dormant);
		gm.setEnabled(true);
	}

	public AlertDialog dialogForSucceed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("真厉害，继续挑战下一关吧！")
				.setPositiveButton("升级难度",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								dormant = dormant - 200;
								group = group + 1;
								gm.initImageType(initImageType());
								newPlay();
							}
						})
				.setNeutralButton("再次挑战",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								group = group + 1;
								gm.initImageType(initImageType());
								newPlay();
							}
						});
		return builder.create();
	}

	public AlertDialog dialogForFail() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("别灰心，再来一次，一定能赢！")
				.setPositiveButton("降低难度",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								dormant = dormant + 200;
								newPlay();
							}
						})
				.setNeutralButton("再次挑战",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								newPlay();
							}
						});
		return builder.create();
	}

}