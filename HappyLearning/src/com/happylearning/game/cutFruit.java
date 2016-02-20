package com.happylearning.game;

import java.io.InputStream;
import java.util.ArrayList;

import com.happylearning.R;
import com.happylearning.curve.Curve;
import com.happylearning.curve.CurveRecord;
import com.happylearning.curve.MyApp;
import com.happylearning.main.Choose;
import com.happylearning.main.Logon;
import com.happylearning.study.OperateVocabularyDB;
import com.happylearning.study.Study;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder.Callback;
import android.view.Display;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class cutFruit extends Activity {

	private int order = 0;
	private int curveindex;
	private int playtime;
	private String countname;
	private double time;

	private Context context = this;
	private ArrayList<CurveRecord> personCurve;
	private MyApp myApp;
	private String englishvocabulary;
	CutAV mCut = null;

	// 创建屏幕
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		personCurve = new ArrayList<CurveRecord>();
		myApp = (MyApp) getApplication();
		Bundle bundle = this.getIntent().getExtras();
		countname = bundle.getString("countName");
		time = bundle.getDouble("time") + 1;
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Display display = getWindowManager().getDefaultDisplay();

		if (time > 1) {
			myApp.changePersonalCurve();
		}
		mCut = new CutAV(this, display.getWidth(), display.getHeight());
		setContentView(mCut);

	}

	// 处理点击动作
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mCut.UpdateTouchEvent(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			mCut.UpdateTouchEvent(x, y);
			break;
		case MotionEvent.ACTION_UP:
			break;
		default:
			break;
		}
		return false;
	}

	public class CutAV extends SurfaceView implements Callback, Runnable {
		private int mScreenWidth = 0;
		private int mScreenHeight = 0;
		private static final int GO_GO = 0;
		private static final int GO_CUT = 1;
		private static final int GO_END = 2;
		private static final int GO_NEXT = 3;
		private int status;
		private int cut_state = GO_GO;
		private flash tu = null;
		private int tuPosX = 0;
		private int tuPosY = 0;
		private Bitmap tu1 = null;
		private Bitmap tu2 = null;
		private fruit goButton = null;
		private fruit backButton = null;
		private int moveCut = 20;
		private int moveCount = 0;
		private int moveX = 0;
		private int moveY = 10;
		private int cutCount = 0;
		private Thread mThread = null;
		private Context mContext = null;
		private Canvas mCanvas = null;
		private Paint paint = null;
		private SurfaceHolder surfaceHolder = null;
		private boolean isRunning = false;

		public CutAV(Context context, int screenWidth, int screenHeight) {
			super(context);
			mContext = context;
			mCanvas = new Canvas();
			paint = new Paint();
			paint.setColor(Color.WHITE);
			mScreenWidth = screenWidth;
			mScreenHeight = screenHeight;
			surfaceHolder = getHolder();
			surfaceHolder.addCallback(this);
			setFocusable(true);
			personCurve = myApp.getCurve();
			initWords();
			cut_state = GO_GO;
		}

		protected void doDraw() {
			switch (cut_state) {
			case GO_GO:
				moveX = 10 * cutCount;
				tuPosX += moveX;
				tuPosY += moveY;
				mCanvas.drawColor(Color.BLACK);
				mCanvas.drawText("你划中图片的次数为：" + cutCount, 20, 10, paint);
				mCanvas.drawText("连续划中" + playtime + "次才能通关哦！！！！", 20, 310,
						paint);
				goButton = new fruit(mContext, R.drawable.reader, tuPosX,
						tuPosY);
				goButton.DrawImageButton(mCanvas, paint);
				tu.DrawAnimation(mCanvas, paint, tuPosX, tuPosY);
				if (moveCount > 10) {
					cut_state = GO_GO;
					moveCount = 0;
					moveCut = 20;
				}
				if (tuPosX >= mScreenWidth) {
					tuPosX = 0;
				}
				if (tuPosY >= 240) {
					moveY = -20;
				} else if (tuPosY <= 20) {
					moveY = 20;
				}
				if (cutCount == playtime) {
					cut_state = GO_END;
				}
				break;
			case GO_CUT:
				mCanvas.drawColor(Color.WHITE);
				mCanvas.drawText("你划中图片的次数为：" + cutCount, 20, 10, paint);
				mCanvas.drawText("连续划中" + playtime + "次就可以通关哦！！！！", 20, 310,
						paint);
				moveCut += 2;
				moveCount++;
				mCanvas.drawBitmap(tu1, tuPosX, tuPosY - moveCut, paint);
				mCanvas.drawBitmap(tu2, tuPosX, tuPosY + moveCut, paint);
				if (moveCount > 10) {
					cut_state = GO_GO;
					moveCount = 0;
					moveCut = 20;
				}
				if (tuPosX >= mScreenWidth) {
					tuPosX = 0;
				}
				if (tuPosY >= 240) {
					moveY = -20;
				} else if (tuPosY <= 20) {
					moveY = 20;
				}
				if (cutCount == playtime) {
					cut_state = GO_END;
				}
				break;
			case GO_END:
				mCanvas.drawColor(Color.WHITE);
				mCanvas.drawText("恭喜你！！！！！", 160, 150, paint);
				backButton.DrawImageButton(mCanvas, paint);
				break;
			case GO_NEXT:
				surfaceDestroyed(surfaceHolder);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("countName", countname);
				bundle.putDouble("Time", 1);
				intent.putExtras(bundle);
				intent.setClass(context, ligature.class);
				finish();
				context.startActivity(intent);
			default:
				break;
			}

		}

		public void initWords() {
			status = GO_NEXT;
			while (order < personCurve.size()) {
				if (personCurve.get(order).gamemodel1 == 0) {
					curveindex = order;
					englishvocabulary = personCurve.get(order).englishvocabulary;
					playtime = englishvocabulary.length() * 1;
					init(englishvocabulary);
					status = GO_GO;
					break;
				}
				order++;
			}
		}

		private void init(String imgname) {
			tuPosX = mScreenWidth;
			tuPosY = mScreenHeight / 2;
			int imgid = context.getResources().getIdentifier(imgname,
					"drawable", context.getPackageName());
			tu = new flash(mContext, new int[] { imgid }, true);
			tu1 = ReadBitMap(mContext, imgid);
			tu2 = ReadBitMap(mContext, imgid);
			backButton = new fruit(mContext, imgid, 160, 150);
		}

		public Bitmap ReadBitMap(Context context, int resId) {
			BitmapFactory.Options opt = new BitmapFactory.Options();
			opt.inPreferredConfig = Bitmap.Config.RGB_565;
			opt.inPurgeable = true;
			opt.inInputShareable = true;
			InputStream is = context.getResources().openRawResource(resId);
			return BitmapFactory.decodeStream(is, null, opt);
		}

		public void UpdateTouchEvent(int x, int y) {
			switch (cut_state) {
			case GO_GO:
				if (goButton.IsClick(x, y)) {
					cut_state = GO_CUT;
					cutCount++;
				}
				break;
			case GO_END:
				if (backButton.IsClick(x, y)) {
					myApp.changeCurve(curveindex, 1, 0, 0, 0, 1, 1, 0);
					initWords();
					cutCount = 0;
					if (status != 3) {
						cut_state = GO_GO;
					} else {
						cut_state = GO_NEXT;
					}
				}
				break;
			}
		}

		public void run() {
			while (isRunning) {
				synchronized (surfaceHolder) {
					mCanvas = surfaceHolder.lockCanvas();
					doDraw();
					surfaceHolder.unlockCanvasAndPost(mCanvas);
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void surfaceChanged(SurfaceHolder holder, int arg1, int arg2,
				int arg3) {

		}

		public void surfaceCreated(SurfaceHolder surfaceHolder) {
			isRunning = true;
			mThread = new Thread(this);
			mThread.start();
		}

		public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
			isRunning = false;
		}
	}
}
