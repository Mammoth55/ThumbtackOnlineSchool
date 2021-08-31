package net.thumbtack.school.hospital.model;

public class Medicament implements Assignment {

    private String name;
    private int dailyUse;

    public Medicament() {
    }

    public Medicament(String name, int dailyUse) {
        this.name = name;
        this.dailyUse = dailyUse;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDailyUse() {
        return dailyUse;
    }

    public void setDailyUse(int dailyUse) {
        this.dailyUse = dailyUse;
    }

    @Override
    public boolean use() {

        return true;
    }
}