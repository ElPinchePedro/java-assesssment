package mx.com.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.assessment.commons.AppUrlConstants;
import mx.com.assessment.dto.WeeklyReportDto;
import mx.com.assessment.service.ReportsService;

@RestController
@RequestMapping(AppUrlConstants.BASE_NAME)
public class ReportsController {
	
	@Autowired
	private ReportsService reportsService;
	
	@GetMapping(AppUrlConstants.WEEKLY_REPORT)
	public @ResponseBody List<WeeklyReportDto> getReport() {
		return this.reportsService.getReport();
	}

}
