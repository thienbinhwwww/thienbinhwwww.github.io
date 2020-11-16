package com.example.learnforeignlanguage.mode;

public class Vocabulary {
    private int idVocabulary;
    private String vocabulary;
    private String means;
    private String pronounce;

    public Vocabulary() {
    }

    public Vocabulary(int idVocabulary, String vocabulary, String means, String pronounce) {
        this.idVocabulary = idVocabulary;
        this.vocabulary = vocabulary;
        this.means = means;
        this.pronounce = pronounce;
    }

    public int getIdVocabulary() {
        return idVocabulary;
    }

    public void setIdVocabulary(int idVocabulary) {
        this.idVocabulary = idVocabulary;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getMeans() {
        return means;
    }

    public void setMeans(String means) {
        this.means = means;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }
}
