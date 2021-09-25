package net.thumbtack.school.hospital.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDtoResponse {

    private String description;

    public ErrorDtoResponse(Exception e) {
        this.description = e.getMessage();
    }
}