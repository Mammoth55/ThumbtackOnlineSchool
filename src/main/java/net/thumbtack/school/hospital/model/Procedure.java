package net.thumbtack.school.hospital.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Procedure implements Assignment {

    private String name;
    private List<WeekDays> daysOfUse;

    public Procedure(String name) {
        this.name = name;
        this.daysOfUse = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean use() {
        return true;
    }
}