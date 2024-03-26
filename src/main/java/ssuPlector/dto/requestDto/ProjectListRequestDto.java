package ssuPlector.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectListRequestDto {
    private String searchString;
    private String category;
    private String sortType; // recent, old, high, low
}
