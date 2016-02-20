package com.happylearning.curve;

import java.util.ArrayList;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
	public ArrayList<CurveRecord> personalCurve;
	private Curve curve;
	private Context context = this;

	public ArrayList<CurveRecord> getCurve() {
		return this.personalCurve;
	}

	public void setCurve(ArrayList<CurveRecord> personalCurve) {
		this.personalCurve = personalCurve;
	}

	public void changeCurve(int index, double gamemodel1, double gamemodel2,
			double gamemodel3, double learnmodel, double meetingtime,
			double correcttime, double wrongtime) {
		if (this.personalCurve.get(index) != null) {
			this.personalCurve.get(index).gamemodel1 = gamemodel1;
			this.personalCurve.get(index).gamemodel2 = gamemodel2;
			this.personalCurve.get(index).gamemodel3 = gamemodel3;
			this.personalCurve.get(index).learnmodel = learnmodel;
			this.personalCurve.get(index).meetingtime = this.personalCurve
					.get(index).meetingtime + meetingtime;
			this.personalCurve.get(index).correcttime = this.personalCurve
					.get(index).correcttime + correcttime;
			this.personalCurve.get(index).wrongtime = this.personalCurve
					.get(index).wrongtime + wrongtime;
			// System.out.println("程序共享变量的内容："+this.personalCurve.get(index).gamemodel1+this.personalCurve.get(index).meetingtime);
		}
	}

	public void changegroupCurve(int firstindex, int lastindex,
			double gamemodel1, double gamemodel2, double gamemodel3,
			double learnmodel, double meetingtime, double correcttime,
			double wrongtime) {
		for (int index = firstindex; index < lastindex; index++) {
			if (index < this.personalCurve.size()
					&& this.personalCurve.get(index) != null) {
				this.personalCurve.get(index).gamemodel1 = gamemodel1;
				this.personalCurve.get(index).gamemodel2 = gamemodel2;
				this.personalCurve.get(index).gamemodel3 = gamemodel3;
				this.personalCurve.get(index).learnmodel = learnmodel;
				this.personalCurve.get(index).meetingtime = this.personalCurve
						.get(index).meetingtime + meetingtime;
				this.personalCurve.get(index).correcttime = this.personalCurve
						.get(index).correcttime + correcttime;
				this.personalCurve.get(index).wrongtime = this.personalCurve
						.get(index).wrongtime + wrongtime;
				// System.out.println("程序共享变量的内容："+this.personalCurve.get(index).gamemodel3+this.personalCurve.get(index).meetingtime);
			}
		}
	}

	public void changePersonalCurve() {
		curve = new Curve(context);
		for (int index = 0; index < this.personalCurve.size(); index++) {
			if (this.personalCurve.get(index) != null) {
				curve.updatePartCurve(this.personalCurve.get(index).countname,
						this.personalCurve.get(index).englishvocabulary,
						this.personalCurve.get(index).meetingtime,
						this.personalCurve.get(index).correcttime,
						this.personalCurve.get(index).wrongtime);
			}
		}
		// System.out.println("退出程序");
	}

}
