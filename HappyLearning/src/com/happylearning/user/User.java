package com.happylearning.user;

import java.util.ArrayList;

import com.happylearning.R;
import com.happylearning.curve.Curve;
import com.happylearning.curve.CurveRecord;
import com.happylearning.curve.MyApp;
import com.happylearning.main.Choose;
import com.happylearning.main.Logon;
import com.happylearning.study.OperateVocabularyDB;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class User extends Activity {

	private String countname;
	private String count;
	private String name;
	private String email;
	private int ability = 5;
	private String password1;
	private Button registerBtn;
	private Button updateBtn;
	private Button returnBtn;
	private Button exitBtn;
	private EditText countET;
	private EditText nameET;
	private EditText emailET;
	private EditText pass1ET;
	private Spinner abilitysp;
	private Curve curve;
	private OperatePersonDB personDB;
	private PersonRecord person;
	private ArrayAdapter<String> uAdapter;
	private OperateVocabularyDB vocabularyDB;

	private static final String[] m_ability = { "Сѧ����", "��ѧ�м�", "���и߼�", "��ѧ�ļ�",
			"�в����", "��ѧ����", "רҵ�˼�" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user);
		Bundle bundle = this.getIntent().getExtras();
		countname = bundle.getString("countName");
		registerBtn = (Button) findViewById(R.id.pregisterbtn);
		updateBtn = (Button) findViewById(R.id.pupdatebtn);
		returnBtn = (Button) findViewById(R.id.preturnbtn);
		exitBtn = (Button) findViewById(R.id.pexitbtn);
		countET = (EditText) findViewById(R.id.countet);
		nameET = (EditText) findViewById(R.id.nameet);
		emailET = (EditText) findViewById(R.id.emailet);
		pass1ET = (EditText) findViewById(R.id.passwordet);
		abilitysp = (Spinner) findViewById(R.id.spinner1);
		uAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, m_ability);
		uAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		abilitysp.setAdapter(uAdapter);

		vocabularyDB = new OperateVocabularyDB(this);

		personDB = new OperatePersonDB(this);
		final Context context = this;
		person = personDB.querySomeone(countname);
		if (person != null) {
			countET.setText(person.countname);
			nameET.setText(person.personname);
			emailET.setText(person.email);
			pass1ET.setText(person.password);
			if (person.ability == 1) {
				abilitysp.setSelection(0, true);
			}
			if (person.ability == 2) {
				abilitysp.setSelection(1, true);
			}
			if (person.ability == 3) {
				abilitysp.setSelection(2, true);
			}
			if (person.ability == 4) {
				abilitysp.setSelection(3, true);
			}
			if (person.ability == 5) {
				abilitysp.setSelection(4, true);
			}
			if (person.ability == 6) {
				abilitysp.setSelection(5, true);
			}
			if (person.ability == 8) {
				abilitysp.setSelection(6, true);
			}
		} else {
			countET.setText("");
			nameET.setText("");
			emailET.setText("");
			pass1ET.setText("");
			abilitysp.setSelection(4, true);
		}

		abilitysp.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (m_ability[arg2].contains("Сѧ����")) {
					ability = 1;
				}
				if (m_ability[arg2].contains("��ѧ�м�")) {
					ability = 2;
				}
				if (m_ability[arg2].contains("���и߼�")) {
					ability = 3;
				}
				if (m_ability[arg2].contains("��ѧ�ļ�")) {
					ability = 4;
				}
				if (m_ability[arg2].contains("��ѧ����")) {
					ability = 6;
				}
				if (m_ability[arg2].contains("רҵ�˼�")) {
					ability = 8;
				}
				if (m_ability[arg2].contains("�в����")) {
					ability = 5;
				}
				arg0.setVisibility(View.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		updateBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				count = countET.getText().toString();
				name = nameET.getText().toString();
				email = emailET.getText().toString();
				password1 = pass1ET.getText().toString();

				if (count.equals("") || password1.equals("") || name.equals("")
						|| email.equals("")) {
					Toast toast = Toast.makeText(context, "����д�������ύ��",
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				} else {
					PersonRecord contact = new PersonRecord(count, name, email,
							ability, password1);
					personDB.updateSomeone(contact);
					Toast toast = Toast.makeText(context, "��ϲ�㣬���³ɹ���",
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				}

			}
		});
		exitBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("countName", countname);
				intent.putExtras(bundle);
				intent.setClass(User.this, Logon.class);
				finish();
				startActivity(intent);
			}
		});

		returnBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("countName", countname);
				intent.putExtras(bundle);
				intent.setClass(User.this, Choose.class);
				finish();
				startActivity(intent);

			}
		});

		registerBtn.setOnClickListener(new OnClickListener() {
			private PersonRecord lperson;
			private Handler handler;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				count = countET.getText().toString();
				name = nameET.getText().toString();
				email = emailET.getText().toString();
				password1 = pass1ET.getText().toString();

				if (count.equals("") || password1.equals("") || name.equals("")
						|| email.equals("")) {
					Toast toast = Toast.makeText(context, "����д�������ύ��",
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				} else {
					lperson = personDB.querySomeone(count);
					if (lperson != null) {
						Toast toast = Toast.makeText(context, "�˺��Ѿ�����ע���ˣ�",
								Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						toast.show();
					} else {
						PersonRecord contact = new PersonRecord(count, name,
								email, ability, password1);
						countname = count;
						personDB.add(contact);
						Handler handler = new Handler();
						handler.post(r);
						if (count.contentEquals("administer")) {
							Toast toast = Toast.makeText(context, "��ʼ���ʿ�",
									Toast.LENGTH_LONG);
							toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
							toast.show();
							vocabularyDB.addTogether();
						}
					}
				}
			}

			Runnable r = new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					curve = new Curve(context);
					curve.initCurve(countname, ability);
					Toast toast = Toast.makeText(context, "��ϲ�㣬ע��ɹ���",
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				}
			};
		});

	}
}