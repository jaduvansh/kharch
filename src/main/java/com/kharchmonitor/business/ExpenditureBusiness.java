package com.kharchmonitor.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kharchmonitor.persistence.entity.Expenditure;
import com.kharchmonitor.persistence.entity.ExpenditureType;
import com.kharchmonitor.repository.ExpenditureRepository;
import com.kharchmonitor.translator.ExpenditureTypeTranslator;
import com.kharchmonitor.view.ExpenditureView;

@Service
public class ExpenditureBusiness {

	private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

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
		Expenditure existingExpenditure = expenditureRepository.findByUserNameAndDate(
				expenditureView.getUserName(), dateFormat.parse(expenditureView.getDate()+" 10:00:00"));

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
		expenditure.setDate(dateFormat.parse(expenditureView.getDate() +" 10:00:00"));
		expenditure.setUserName(expenditureView.getUserName());
		return expenditure;
	}

	public List<Expenditure> getAllExpenditureByMonth(String userName, String month, String year) {
		return expenditureRepository.findByUsernameMonthAndYearOrderByDate(userName, Integer.parseInt(year), Integer.parseInt(month));
	}

}
