package com.happylearning.study;

public class VocabularyRecord {

	public int _id;
	public String englishvocabulary;
	public String vocabularysoundmark;
	public String chineseexplain;
	public String englishexplain;
	public String englishsentence;
	public String representpicture;
	public double vocabularylength;
	public double frequencyrank;
	public String englishgroup;

	public VocabularyRecord() {
	}

	// ≥ı ºªØ
	public VocabularyRecord(String englishvocabulary,
			String vocabularysoundmark, String chineseexplain,
			String englishexplain, String englishsentence,
			String representpicture, double vocabularylength,
			double frequencyrank, String group) {
		this.englishvocabulary = englishvocabulary;
		this.vocabularysoundmark = vocabularysoundmark;
		this.chineseexplain = chineseexplain;
		this.englishexplain = englishexplain;
		this.englishsentence = englishsentence;
		this.representpicture = representpicture;
		this.vocabularylength = vocabularylength;
		this.frequencyrank = frequencyrank;
		this.englishgroup = group;
	}

	public void changeVocabularyRecord(String englishvocabulary,
			String vocabularysoundmark, String chineseexplain,
			String englishexplain, String englishsentence,
			String representpicture, double vocabularylength,
			double frequencyrank, String group) {
		this.englishvocabulary = englishvocabulary;
		this.vocabularysoundmark = vocabularysoundmark;
		this.chineseexplain = chineseexplain;
		this.englishexplain = englishexplain;
		this.englishsentence = englishsentence;
		this.representpicture = representpicture;
		this.vocabularylength = vocabularylength;
		this.frequencyrank = frequencyrank;
		this.englishgroup = group;
	}
}
