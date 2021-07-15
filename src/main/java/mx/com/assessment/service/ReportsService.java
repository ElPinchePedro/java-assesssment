package mx.com.assessment.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.assessment.dto.WeeklyReportDto;
import mx.com.assessment.repository.ReportsDao;

@Service
public class ReportsService {

	@Autowired
	private ReportsDao reportsDao;

	public List<WeeklyReportDto> getReport() {

		List<WeeklyReportDto> periods = this.getPeriods();

		for (WeeklyReportDto period : periods) {
			period.setQuantity(this.reportsDao.salesQuantity(period.getStartDay(), period.getEndDay()));
		}
		return periods;
	}

	public List<WeeklyReportDto> getPeriods() {

		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int thisYear = cal.get(Calendar.YEAR);
		int thisMonth = cal.get(Calendar.MONTH);
		int week = 0;
		int lastMonday = 0;
		int firstMonday = 0;
		List<WeeklyReportDto> periods = null;
		WeeklyReportDto period = new WeeklyReportDto();

		switch (dayOfWeek) {

		case 1:
			lastMonday = dayOfWeek;

		case 2:
			lastMonday = dayOfWeek - 1;

		case 3:
			lastMonday = dayOfWeek - 2;

		case 4:
			lastMonday = dayOfWeek - 3;

		case 5:
			lastMonday = dayOfWeek - 4;

		case 6:
			lastMonday = dayOfWeek - 5;

		case 7:
			lastMonday = dayOfWeek - 6;

		}

		while (lastMonday > 0) {
			if (lastMonday - 7 > 0)
				firstMonday = lastMonday;
		}

		for (int i = firstMonday; i <= dayOfMonth; i = firstMonday + 7) {
			if (firstMonday - dayOfMonth < 0) {
				week++;
				period.setWeekNum(week);
				period.setStartDay(LocalDate.of(thisYear, thisMonth, firstMonday).toString());
				period.setEndDay(LocalDate.of(thisYear, thisMonth, firstMonday + 6).toString());
				periods.add(period);
			} else {
				week++;
				period.setWeekNum(week);
				period.setStartDay(LocalDate.of(thisYear, thisMonth, firstMonday).toString());
				period.setEndDay(LocalDate.of(thisYear, thisMonth, dayOfMonth).toString());
				periods.add(period);
			}
		}

		return periods;
	}

}
