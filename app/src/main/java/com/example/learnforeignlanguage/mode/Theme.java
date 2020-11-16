package com.example.learnforeignlanguage.mode;

public class Theme {
    private int idTheme;
    private int idCustom;
    private String theme;

    public Theme() {
    }

    public Theme(int idTheme, int idCustom, String theme) {
        this.idTheme = idTheme;
        this.idCustom = idCustom;
        this.theme = theme;
    }

    public int getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(int idTheme) {
        this.idTheme = idTheme;
    }

    public int getIdCustom() {
        return idCustom;
    }

    public void setIdCustom(int idCustom) {
        this.idCustom = idCustom;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
