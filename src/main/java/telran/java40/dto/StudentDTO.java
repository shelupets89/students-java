package telran.java40.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class StudentDTO {
	Integer id;
	String name;
	Map<String, Integer> scores;
}
