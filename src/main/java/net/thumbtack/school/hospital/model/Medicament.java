package net.thumbtack.school.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicament implements Assignment {

    private String name;
    private int dailyUse;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean use() {
        // Заглушка временная, реализация приема медикамента
        return true;
    }
}