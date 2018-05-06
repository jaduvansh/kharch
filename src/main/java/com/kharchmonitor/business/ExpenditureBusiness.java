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
	private Sort orderByDate = new Sort(Direction.ASC,"date");

	@Autowired
	ExpenditureRepository expenditureRepository;

	@Autowired
	ExpenditureTypeTranslator expenditureTypeTranslator;

	public List<Expenditure> getAllExpenditure(String userName) {
		return expenditureRepository.findByUserNameOrderByDate(userName);
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
		Expenditure existingExpenditure = expenditureRepository.findByUserNameAndDate(expenditureView.getUserName(),
				dateFormat.parse(expenditureView.getDate() + " 10:00:00"));

		if (isExpenditureExistOnDate(existingExpenditure)) {
			return existingExpenditure;
		}
		return createExpenditure(expenditureView);
	}

	private boolean isExpenditureExistOnDate(Expenditure expenditure) {
		return null != expenditure;
	}

	private Expenditure createExpenditure(ExpenditureView expenditureView) throws ParseException {
		Expenditure expenditure = new Expenditure();
		expenditure.setExpenditureTypes(new ArrayList<ExpenditureType>());
	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(expenditureView.getDate() + " 10:00:00"));

		expenditure.setDate(calendar.getTime());
		expenditure.setUserName(expenditureView.getUserName());
		return expenditure;
	}

	public List<Expenditure> getAllExpenditureByMonthYear(String userName, String monthYear) {
		String[] arr = monthYear.split("-");
		return expenditureRepository.findByUsernameMonthAndYear(userName, Integer.parseInt(arr[1]),
				Integer.parseInt(arr[0]),orderByDate);
	}

	public List<Expenditure> findByUsernameDateRange(String userName, String monthYear) {
		List<Expenditure> findByUsernameDateRange = null;
		try {
			Date fromDate = dateFormat.parse("01-" + monthYear + " 00:00:00");
			Date toDate = getToDate(fromDate);
			
			findByUsernameDateRange = expenditureRepository.findByUsernameDateRange(userName, fromDate, toDate, orderByDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return findByUsernameDateRange;
	}

	private Date getToDate(Date fromDate) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(fromDate);
		c1.add(Calendar.MONTH, 1);
		return new Date(c1.getTimeInMillis());
	}
}
