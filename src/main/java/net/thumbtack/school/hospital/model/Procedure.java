package net.thumbtack.school.hospital.model;

import java.util.List;

public class Procedure implements Assignment {

    private String name;
    private List<Week> daysOfUse;

    public Procedure() {
    }

    public Procedure(String name, List<Week> daysOfUse) {
        this.name = name;
        this.daysOfUse = daysOfUse;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Week> getDaysOfUse() {
        return daysOfUse;
    }

    public void setDaysOfUse(List<Week> daysOfUse) {
        this.daysOfUse = daysOfUse;
    }

    @Override
    public boolean use() {

        return true;
    }
}