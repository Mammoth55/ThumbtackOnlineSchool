package net.thumbtack.school.hospital.dtoresponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDto {

    private String description;

    public ErrorDto(Exception e) {
        this.description = e.getMessage();
    }
}