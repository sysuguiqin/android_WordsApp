package com.happylearning.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import com.happylearning.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ligatureModel extends View {

	public final int GAMETIME = 300;
	public final int UPTIME = 1;
	public int unitSize = 42;
	public int unitNum = 8;
	public int blankUintNum = 2;
	public final int row = unitNum;
	public final int col = unitNum;
	public final int V_LINE = 1;
	public final int H_LINE = 1;
	public final int ONE_C_LINE = 2;
	public final int TWO_C_LINE = 3;
	public int lineType = 0;
	private int selY;
	private int selX;
	public int much = 0;
	public int PROCESS_VALUE = 300;
	public int CURRENT_TYPE = 0;
	private Point C_POINT;
	private Point P_POINT;
	Point[] p;
	public static boolean CURRENT_CH = false;
	public boolean isLine = false;
	public float width;
	public float height;
	public int grid[][] = new int[row][col];
	private Rect selRect = new Rect();
	public int imageTypeNum = 9;

	public int[] imageType = new int[] { R.drawable.a, R.drawable.b,
			R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f,
			R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j,
			R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n,
			R.drawable.o, R.drawable.p };

	public Bitmap[] image;
	public List<Integer> type = new ArrayList<Integer>();
	LinkedList<Line> li;

	public ligatureModel(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setFocusable(true);
		this.setFocusableInTouchMode(true);
		initGameView();
	}

	public ligatureModel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setFocusable(true);
		this.setFocusableInTouchMode(true);
		initGameView();
	}

	public void initImageType(int[] imageTypePicture) {
		this.imageType = imageTypePicture;
	}

	public void initGameView() {
		int count = ((row - blankUintNum) * (col - blankUintNum))
				/ imageTypeNum;
		for (int j = 0; j < imageTypeNum; j++) {
			for (int i = 0; i < count; i++) {
				type.add(imageType[j]);
			}
		}
		Random ad = new Random();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
					grid[i][j] = 0;
				} else {
					if (type != null && type.size() > 0) {
						int index = ad.nextInt(type.size());
						grid[i][j] = type.get(index);
						type.remove(index);
					}
				}
			}
		}
		much = (row - blankUintNum) * (col - blankUintNum);
	}

	public void fillImage(Context context) {

		image = new Bitmap[imageTypeNum];
		for (int i = 0; i < imageTypeNum; i++) {
			Bitmap bitmap = Bitmap.createBitmap((int) width, (int) height,
					Bitmap.Config.ARGB_8888);
			Drawable drw;
			Canvas canvas = new Canvas(bitmap);
			drw = context.getResources().getDrawable(imageType[i]);
			drw.setBounds(1, 1, unitSize, unitSize);
			drw.draw(canvas);
			image[i] = bitmap;
		}
	}

	public void select(int x, int y) {
		invalidate(selRect);
		selX = Math.min(Math.max(x, 0), unitNum - 1);
		selY = Math.min(Math.max(y, 0), unitNum - 1);
		selRect.set((int) (selX * width), (int) (selY * height), (int) (selX
				* width + width), (int) (selY * height + height));
		invalidate(selRect);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Paint background = new Paint();
		background.setColor(Color.WHITE);
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);

		Paint light = new Paint();
		light.setColor(getResources().getColor(R.color.light));

		// 画出网格
		for (int i = 0; i < unitNum; i++) {
			canvas.drawLine(0, i * height, getWidth(), i * height, light);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1,
					light);
			canvas.drawLine(i * width, 0, i * width, getHeight(), light);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), light);
		}

		if (ligatureModel.CURRENT_CH) {
			Paint selected = new Paint();
			selected.setColor(getResources().getColor(R.color.puzzle_selected));
			canvas.drawRect(selRect, selected);
		}

		for (int i = 0; i < unitNum; i++) {
			for (int j = 0; j < unitNum; j++) {
				if (grid[i][j] != 0) {
					canvas.drawBitmap(
							image[Arrays.binarySearch(imageType, grid[i][j])],
							i * width, j * height, null);
				}
			}
		}

		if (isLine) {
			Paint lineColor = new Paint();
			switch (lineType) {
			case TWO_C_LINE:
				lineColor.setColor(Color.RED);
				canvas.drawLine(p[0].x * width + width / 2, p[0].y * height
						+ height / 2, p[1].x * width + width / 2, p[1].y
						* height + height / 2, lineColor);
				canvas.drawLine(p[1].x * width + width / 2, p[1].y * height
						+ height / 2, p[2].x * width + width / 2, p[2].y
						* height + height / 2, lineColor);
				canvas.drawLine(p[3].x * width + width / 2, p[3].y * height
						+ height / 2, p[2].x * width + width / 2, p[2].y
						* height + height / 2, lineColor);
				break;
			case ONE_C_LINE:
				lineColor.setColor(Color.GREEN);
				canvas.drawLine(p[0].x * width + width / 2, p[0].y * height
						+ height / 2, p[1].x * width + width / 2, p[1].y
						* height + height / 2, lineColor);
				canvas.drawLine(p[1].x * width + width / 2, p[1].y * height
						+ height / 2, p[2].x * width + width / 2, p[2].y
						* height + height / 2, lineColor);
				break;
			case V_LINE:
				lineColor.setColor(Color.BLUE);
				canvas.drawLine(p[0].x * width + width / 2, p[0].y * height
						+ height / 2, p[1].x * width + width / 2, p[1].y
						* height + height / 2, lineColor);
				break;
			default:
				break;
			}
		}
		super.onDraw(canvas);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w / row;
		height = h / col;
		// getRect(1,1,selRect);
		fillImage(this.getContext());
		super.onSizeChanged(w, h, oldw, oldh);
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return super.onTouchEvent(event);

		int selX = (int) (event.getX() / width);
		int selY = (int) (event.getY() / height);

		if (grid[selX][selY] == 0)
			return true;
		else {
			if (CURRENT_CH == false) {
				select(selX, selY);
				CURRENT_CH = true;
				P_POINT = new Point(selX, selY);
			} else {
				C_POINT = new Point(selX, selY);
				lineType = 0;
				if (checkLink(P_POINT, C_POINT)) {
					isLine = true;
					much = much - 2;
					if (0 < PROCESS_VALUE
							&& (PROCESS_VALUE + UPTIME) < GAMETIME) {
						PROCESS_VALUE = PROCESS_VALUE + UPTIME;
					}
					invalidate();
					mRedrawHandler.sleep(300);
				}
				CURRENT_CH = false;
			}
		}
		return true;
	}

	public void reset() {
		CURRENT_CH = false;
		CURRENT_TYPE = 0;
		C_POINT = null;
		P_POINT = null;
		lineType = 0;
		isLine = false;
		Point[] p = null;
		initGameView();
		invalidate();
	}

	public void rearrange() {
		CURRENT_CH = false;
		CURRENT_TYPE = 0;
		C_POINT = null;
		P_POINT = null;
		lineType = 0;
		isLine = false;
		Point[] p = null;
		List<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] != 0) {
					temp.add(grid[i][j]);
				}
			}
		}
		type.clear();
		Random ad = new Random();
		for (int i = 0; i < temp.size(); i++) {
			type.add(temp.get(i));
		}
		temp.clear();
		temp = null;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] != 0) {
					int index = ad.nextInt(type.size());
					grid[i][j] = type.get(index);
					type.remove(index);
				}
			}
		}
		invalidate();
	}

	private RefreshHandler mRedrawHandler = new RefreshHandler();

	class RefreshHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			isLine = false;
			grid[P_POINT.x][P_POINT.y] = 0;
			grid[C_POINT.x][C_POINT.y] = 0;
			ligatureModel.this.invalidate();
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);// 移除信息队列中最顶部的信息（从顶部取出信息）
			sendMessageDelayed(obtainMessage(0), delayMillis);// 获得顶部信息并延时发送
		}
	};

	public class Point {
		public int x;
		public int y;

		public Point() {
		}

		public Point(int newx, int newy) {
			this.x = newx;
			this.y = newy;
		}

		public boolean equals(Point p) {
			if (p.x == x && p.y == y)
				return true;
			else
				return false;
		}
	}

	class Line {
		public Point a;
		public Point b;
		public int direct;

		public Line() {
		}

		public Line(int direct, Point a, Point b) {
			this.direct = direct;
			this.a = a;
			this.b = b;
		}
	}

	private LinkedList<Line> scan(Point a, Point b) {
		li = new LinkedList<Line>();
		for (int y = a.y; y >= 0; y--)
			if (grid[a.x][y] == 0 && grid[b.x][y] == 0
					&& isVertical(new Point(a.x, y), new Point(b.x, y)))
				li.add(new Line(0, new Point(a.x, y), new Point(b.x, y)));

		for (int y = a.y; y < row; y++)
			if (grid[a.x][y] == 0 && grid[b.x][y] == 0
					&& isVertical(new Point(a.x, y), new Point(b.x, y)))
				li.add(new Line(0, new Point(a.x, y), new Point(b.x, y)));

		for (int x = a.x; x >= 0; x--)
			if (grid[x][a.y] == 0 && grid[x][b.y] == 0
					&& isHorizon(new Point(x, a.y), new Point(x, b.y)))
				li.add(new Line(1, new Point(x, a.y), new Point(x, b.y)));

		for (int x = a.x; x < col; x++)
			if (grid[x][a.y] == 0 && grid[x][b.y] == 0
					&& isHorizon(new Point(x, a.y), new Point(x, b.y)))
				li.add(new Line(1, new Point(x, a.y), new Point(x, b.y)));

		return li;
	}

	public boolean checkLink(Point a, Point b) {
		if (grid[a.x][a.y] != grid[b.x][b.y])// 如果图案不同，直接为false
			return false;
		else if (a.y == b.y && isVertical(a, b))
			return true;
		else if (a.x == b.x && isHorizon(a, b))
			return true;
		else if (hasOneCorner(a, b))
			return true;
		else
			return hasTwoCorner(a, b);
	}

	private boolean isHorizon(Point a, Point b) {
		if (a.x == b.x && a.y == b.y)
			return false;
		int x_start = a.y <= b.y ? a.y : b.y;
		int x_end = a.y <= b.y ? b.y : a.y;
		for (int x = x_start + 1; x < x_end; x++)
			if (grid[a.x][x] != 0) {
				return false;
			}
		p = new Point[] { a, b };
		lineType = H_LINE;
		return true;
	}

	private boolean isVertical(Point a, Point b) {
		if (a.x == b.x && a.y == b.y)
			return false;
		int y_start = a.x <= b.x ? a.x : b.x;
		int y_end = a.x <= b.x ? b.x : a.x;
		for (int y = y_start + 1; y < y_end; y++)
			if (grid[y][a.y] != 0)
				return false;
		p = new Point[] { a, b };
		lineType = V_LINE;
		return true;
	}

	private boolean hasOneCorner(Point a, Point b) {
		Point c = new Point(a.x, b.y);
		Point d = new Point(b.x, a.y);
		if (grid[c.x][c.y] == 0) {
			boolean method1 = isHorizon(a, c) && isVertical(b, c);
			p = new Point[] { a, new Point(c.x, c.y), b };
			lineType = ONE_C_LINE;
			return method1;
		}
		if (grid[d.x][d.y] == 0) {
			boolean method2 = isVertical(a, d) && isHorizon(b, d);
			p = new Point[] { a, new Point(d.x, d.y), b };
			lineType = ONE_C_LINE;
			return method2;
		} else {
			return false;
		}
	}

	private boolean hasTwoCorner(Point a, Point b) {
		li = scan(a, b);
		if (li.isEmpty())
			return false;
		for (int index = 0; index < li.size(); index++) {
			Line line = (Line) li.get(index);
			if (line.direct == 1) {
				if (isVertical(a, line.a) && isVertical(b, line.b)) {
					p = new Point[] { a, line.a, line.b, b };
					lineType = TWO_C_LINE;
					return true;
				}
			} else if (isHorizon(a, line.a) && isHorizon(b, line.b)) {
				p = new Point[] { a, line.a, line.b, b };
				lineType = TWO_C_LINE;
				return true;
			}
		}
		return false;
	}
}
