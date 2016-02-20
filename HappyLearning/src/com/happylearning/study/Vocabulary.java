package com.happylearning.study;

import com.happylearning.R;
import com.happylearning.main.Choose;
import com.happylearning.main.Logon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Vocabulary extends Activity {

	private String englishvocabulary;
	private String vocabularysoundmark;
	private String chineseexplain;
	private String englishexplain;
	private String englishsentence;
	private String representpicture = "picture";
	private String vocabularylengthstr;
	private String frequencyrankstr;
	private String countname;
	private String group;
	private double vocabularylength;
	private double frequencyrank;
	private double time;
	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private EditText editText4;
	private EditText editText5;
	private EditText editText6;
	private EditText editText7;
	private EditText editText8;
	private Button addBtn;
	private Button deleteBtn;
	private Button updateBtn;
	private Button queryBtn;
	private Button exitBtn;
	private Button returnBtn;

	private VocabularyRecord vocabulary;
	private OperateVocabularyDB vocabularyDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vocabulary);
		Bundle bundle = this.getIntent().getExtras();
		countname = bundle.getString("countName");
		bundle.putDouble("time", time);

		editText1 = (EditText) findViewById(R.id.vet1);
		editText2 = (EditText) findViewById(R.id.vet2);
		editText3 = (EditText) findViewById(R.id.vet3);
		editText4 = (EditText) findViewById(R.id.vet4);
		editText5 = (EditText) findViewById(R.id.vet5);
		editText6 = (EditText) findViewById(R.id.vet6);
		editText7 = (EditText) findViewById(R.id.vet7);
		editText8 = (EditText) findViewById(R.id.vet8);
		addBtn = (Button) findViewById(R.id.vaddBtn);
		deleteBtn = (Button) findViewById(R.id.vdeleteBtn);
		updateBtn = (Button) findViewById(R.id.vupdateBtn);
		queryBtn = (Button) findViewById(R.id.vqueryBtn);
		exitBtn = (Button) findViewById(R.id.vexitBtn);
		returnBtn = (Button) findViewById(R.id.vreturnBtn);
		vocabularyDB = new OperateVocabularyDB(this);
		final Context context = this;
		if (countname.contains("administer")) {
			addBtn.setEnabled(true);
			deleteBtn.setEnabled(true);
			updateBtn.setEnabled(true);
			queryBtn.setEnabled(true);
			exitBtn.setEnabled(true);
			returnBtn.setEnabled(true);
		} else {
			addBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			updateBtn.setEnabled(false);
			queryBtn.setEnabled(false);
			exitBtn.setEnabled(false);
			returnBtn.setEnabled(false);
		}

		queryBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				englishvocabulary = editText1.getText().toString();
				vocabularysoundmark = editText2.getText().toString();
				chineseexplain = editText3.getText().toString();
				englishexplain = editText4.getText().toString();
				englishsentence = editText5.getText().toString();
				representpicture = "picture";
				group = editText6.getText().toString();
				vocabularylengthstr = editText7.getText().toString();
				frequencyrankstr = editText8.getText().toString();

				if ("".equals(englishvocabulary)) {
					Toast toast = Toast.makeText(context, "所要查询的单词不能为空！",
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				} else {
					vocabulary = vocabularyDB.querySomeone(englishvocabulary);
					if (vocabulary == null) {
						Toast toast = Toast.makeText(context,
								"所要查询的单词在词库中不存在！", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						toast.show();
					} else {

						Toast toast = Toast.makeText(context, "单词查询成功！",
								Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						toast.show();
						editText1.setText(vocabulary.englishvocabulary);
						editText2.setText(vocabulary.vocabularysoundmark);
						editText3.setText(vocabulary.chineseexplain);
						editText4.setText(vocabulary.englishexplain);
						editText5.setText(vocabulary.englishsentence);
						editText6.setText(vocabulary.englishgroup);
						editText7.setText(Double
								.toString(vocabulary.vocabularylength));
						editText8.setText(Double
								.toString(vocabulary.frequencyrank));

					}
				}
			}
		});
		addBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				englishvocabulary = editText1.getText().toString();
				vocabularysoundmark = editText2.getText().toString();
				chineseexplain = editText3.getText().toString();
				englishexplain = editText4.getText().toString();
				englishsentence = editText5.getText().toString();
				representpicture = "picture";
				group = editText6.getText().toString();
				vocabularylengthstr = editText7.getText().toString();
				frequencyrankstr = editText8.getText().toString();
				// 增加新单词的前提是词库中原先并没有存在相同的单词
				if (englishvocabulary.equals("")
						|| vocabularysoundmark.equals("")
						|| chineseexplain.equals("")
						|| englishexplain.equals("")
						|| englishsentence.equals("")
						|| representpicture.equals("")
						|| vocabularylengthstr.equals("")
						|| frequencyrankstr.equals("") || group.equals("")) {
					Toast toast = Toast.makeText(context, "请填写完整才提交！",
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				} else {
					vocabulary = vocabularyDB.querySomeone(englishvocabulary);
					if (vocabulary != null) {
						Toast toast = Toast.makeText(context, "单词已经存在词库中了！",
								Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						toast.show();
					} else {
						try {
							vocabularylength = Double
									.parseDouble(vocabularylengthstr);
							frequencyrank = Double
									.parseDouble(frequencyrankstr);
							vocabulary = new VocabularyRecord(
									englishvocabulary, vocabularysoundmark,
									chineseexplain, englishexplain,
									englishsentence, representpicture,
									vocabularylength, frequencyrank, group);
							vocabularyDB.add(vocabulary);
							Toast toast = Toast.makeText(context, "新增单词成功！",
									Toast.LENGTH_LONG);
							toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
							toast.show();
						} catch (Exception e) {
							Toast toast = Toast.makeText(context,
									"请检查数据类型是否符合要求！", Toast.LENGTH_LONG);
							toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
							toast.show();
						}

					}

				}
			}
		});
		deleteBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				englishvocabulary = editText1.getText().toString();
				if (englishvocabulary.equals("")) {
					Toast toast = Toast.makeText(context, "所要删除的单词不能为空！",
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				} else {
					vocabulary = vocabularyDB.querySomeone(englishvocabulary);
					if (vocabulary == null) {
						Toast toast = Toast.makeText(context,
								"所要删除的单词在词库中不存在，不需要删除！", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						toast.show();
					} else {
						if (vocabularyDB.delete(englishvocabulary)) {
							Toast toast = Toast.makeText(context, "单词删除成功！",
									Toast.LENGTH_LONG);
							toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
							toast.show();
							editText1.setText("");
							editText2.setText("");
							editText3.setText("");
							editText4.setText("");
							editText5.setText("");
							editText6.setText("");
							editText7.setText("");
							editText8.setText("");
						} else {
							Toast toast = Toast.makeText(context, "单词删除失败！",
									Toast.LENGTH_LONG);
							toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
							toast.show();
						}
					}
				}
			}
		});

		updateBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				englishvocabulary = editText1.getText().toString();
				vocabularysoundmark = editText2.getText().toString();
				chineseexplain = editText3.getText().toString();
				englishexplain = editText4.getText().toString();
				englishsentence = editText5.getText().toString();
				representpicture = "picture";
				group = editText6.getText().toString();
				vocabularylengthstr = editText7.getText().toString();
				frequencyrankstr = editText8.getText().toString();

				if ("".equals(englishvocabulary)) {
					Toast toast = Toast.makeText(context, "所要更新的单词不能为空！",
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				} else {
					vocabulary = vocabularyDB.querySomeone(englishvocabulary);
					if (vocabulary == null) {
						Toast toast = Toast.makeText(context,
								"所要更新的单词在词库中不存在，请选择增加！", Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						toast.show();
					} else {
						if (englishvocabulary.equals("")
								|| vocabularysoundmark.equals("")
								|| chineseexplain.equals("")
								|| englishexplain.equals("")
								|| englishsentence.equals("")
								|| representpicture.equals("")
								|| vocabularylengthstr.equals("")
								|| frequencyrankstr.equals("")
								|| group.equals("")) {
							Toast toast = Toast.makeText(context,
									"要更新的单词有部分元素为空，请检查！", Toast.LENGTH_LONG);
							toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
							toast.show();
						} else {
							try {
								vocabularylength = Double
										.parseDouble(vocabularylengthstr);
								frequencyrank = Double
										.parseDouble(frequencyrankstr);
								vocabulary = new VocabularyRecord(
										englishvocabulary, vocabularysoundmark,
										chineseexplain, englishexplain,
										englishsentence, representpicture,
										vocabularylength, frequencyrank, group);
								vocabularyDB.update(vocabulary);
								Toast toast = Toast.makeText(context, "更新成功！",
										Toast.LENGTH_LONG);
								toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
								toast.show();
							} catch (Exception e) {
								Toast toast = Toast.makeText(context,
										"请检查数据类型是否符合要求！", Toast.LENGTH_LONG);
								toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
								toast.show();
							}
						}
					}
				}
			}
		});
		returnBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("countName", countname);
				bundle.putDouble("time", time);
				intent.putExtras(bundle);
				intent.setClass(Vocabulary.this, Choose.class);
				finish();
				startActivity(intent);
			}

		});
		exitBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Vocabulary.this, Logon.class);
				finish();
				startActivity(intent);
			}

		});

	}
}
