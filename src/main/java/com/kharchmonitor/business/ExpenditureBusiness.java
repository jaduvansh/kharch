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
	private Sort sort = new Sort(Direction.ASC,"date");

//	private static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";

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


//		convertTimeZone(expenditureView.getDate() + " 00:00:00");
		expenditure.setDate(calendar.getTime());
		expenditure.setUserName(expenditureView.getUserName());
		return expenditure;
	}

	public List<Expenditure> getAllExpenditureByMonthYear(String userName, String monthYear) {
		String[] arr = monthYear.split("-");
		return expenditureRepository.findByUsernameMonthAndYear(userName, Integer.parseInt(arr[1]),
				Integer.parseInt(arr[0]),sort);
	}

	public List<Expenditure> findByUsernameDateRange(String userName, String monthYear) {
		List<Expenditure> findByUsernameDateRange = null;
		try {
			Date fromDate = dateFormat.parse("01-" + monthYear + " 00:00:00");
			Calendar c1 = Calendar.getInstance();
			c1.setTime(fromDate);
			c1.add(Calendar.MONTH, 1);
			Date toDate = new Date(c1.getTimeInMillis());

			
			findByUsernameDateRange = expenditureRepository.findByUsernameDateRange(userName, fromDate, toDate, sort);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return findByUsernameDateRange;
	}

	// public ZonedDateTime convertTimeZone(String dateInString) {
	// LocalDateTime ldt = LocalDateTime.parse(dateInString,
	// DateTimeFormatter.ofPattern(DATE_FORMAT));
	//
	// ZoneId singaporeZoneId = ZoneId.of("Delhi");
	// System.out.println("TimeZone : " + singaporeZoneId);
	//
	// //LocalDateTime + ZoneId = ZonedDateTime
	// ZonedDateTime asiaZonedDateTime = ldt.atZone(singaporeZoneId);
	// System.out.println("Date (Singapore) : " + asiaZonedDateTime);
	//
	// ZoneId newYokZoneId = ZoneId.of("America/New_York");
	// System.out.println("TimeZone : " + newYokZoneId);
	//
	// ZonedDateTime nyDateTime =
	// asiaZonedDateTime.withZoneSameInstant(newYokZoneId);
	// System.out.println("Date (New York) : " + nyDateTime);
	//
	// DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
	// System.out.println("\n---DateTimeFormatter---");
	// System.out.println("Date (Singapore) : " + format.format(asiaZonedDateTime));
	// System.out.println("Date (New York) : " + format.format(nyDateTime));
	// return nyDateTime;
	// }

}
