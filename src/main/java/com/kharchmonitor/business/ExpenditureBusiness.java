package com.kharchmonitor.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kharchmonitor.persistence.entity.Expenditure;
import com.kharchmonitor.persistence.entity.ExpenditureType;
import com.kharchmonitor.repository.ExpenditureRepository;
import com.kharchmonitor.translator.ExpenditureTypeTranslator;
import com.kharchmonitor.view.ExpenditureView;

@Service
public class ExpenditureBusiness {

	private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	private final DateFormat changeFormat = new SimpleDateFormat("dd-MM-yyyy");
	private Sort orderByDate = new Sort(Direction.ASC, "date");

	@Autowired
	ExpenditureRepository expenditureRepository;

	@Autowired
	ExpenditureTypeTranslator expenditureTypeTranslator;

	public List<Expenditure> getAllExpenditure(String groupName) {
		return expenditureRepository.findByGroupNameOrderByDate(groupName);
	}

	public void addExpenditure(ExpenditureView expenditureView) {

		try {
			ExpenditureType expenditureType = expenditureTypeTranslator.toEntity(expenditureView);

			Expenditure expenditure = getExpenditure(expenditureView);
			expenditure.getExpenditureTypes().add(expenditureType);

			expenditureRepository.save(expenditure);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Expenditure getExpenditure(ExpenditureView expenditureView) throws ParseException {
		Expenditure existingExpenditure = expenditureRepository.findByGroupNameAndDate(expenditureView.getGroupName(),
				dateFormat.parse(changeDateFormat(expenditureView.getDate()) + " 10:00:00"));

		if (isExpenditureExistOnDate(existingExpenditure)) {
			return existingExpenditure;
		}
		return createExpenditure(expenditureView);
	}

	private String changeDateFormat(Date date) {
		return changeFormat.format(date);
	}

	private boolean isExpenditureExistOnDate(Expenditure expenditure) {
		return null != expenditure;
	}

	private Expenditure createExpenditure(ExpenditureView expenditureView) throws ParseException {
		Expenditure expenditure = new Expenditure();
		expenditure.setExpenditureTypes(new ArrayList<ExpenditureType>());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(changeDateFormat(expenditureView.getDate()) + " 10:00:00"));

		expenditure.setDate(calendar.getTime());
		expenditure.setGroupName(expenditureView.getGroupName());
		return expenditure;
	}

	public List<Expenditure> getAllExpenditureByMonthYear(String groupName, String monthYear) {
		String[] arr = monthYear.split("-");
		return expenditureRepository.findByGroupnameMonthAndYear(groupName, Integer.parseInt(arr[1]),
				Integer.parseInt(arr[0]), orderByDate);
	}

	public List<Expenditure> findByGroupNameDateRange(String groupName, String monthYear) {
		List<Expenditure> findByUsernameDateRange = null;
		try {
			Date fromDate = dateFormat.parse("01-" + monthYear + " 00:00:00");
			Date toDate = getToDate(fromDate);

			findByUsernameDateRange = expenditureRepository.findByGroupnameDateRange(groupName, fromDate, toDate,
					orderByDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return findByUsernameDateRange;
	}

	private Date getToDate(Date fromDate) {
		Calendar date = Calendar.getInstance();
		date.setTime(fromDate);
		date.add(Calendar.MONTH, 1);
		return new Date(date.getTimeInMillis());
	}
}
