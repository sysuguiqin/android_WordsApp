package com.happylearning.user;

public class PersonRecord {

	public int _id;
	public String countname;
	public String personname;
	public String email;
	public int ability;
	public String password;

	public PersonRecord() {
	}

	public PersonRecord(String countname, String personname, String email,
			int ability, String password) {
		this.countname = countname;
		this.personname = personname;
		this.email = email;
		this.ability = ability;
		this.password = password;
	}

	public void changePersonRecord(String countname, String personname,
			String email, int ability, String password) {
		this.countname = countname;
		this.personname = personname;
		this.email = email;
		this.ability = ability;
		this.password = password;
	}
}
