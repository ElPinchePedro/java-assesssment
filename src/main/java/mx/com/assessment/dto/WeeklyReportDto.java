package mx.com.assessment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeeklyReportDto {
	
	private Integer weekNum;
	private String startDay;
	private String endDay;
	private Integer quantity;

}
