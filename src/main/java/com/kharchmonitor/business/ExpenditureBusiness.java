package com.kharchmonitor.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kharchmonitor.persistence.entity.Expenditure;
import com.kharchmonitor.persistence.entity.ExpenditureType;
import com.kharchmonitor.repository.ExpenditureRepository;
import com.kharchmonitor.translator.ExpenditureTranslator;
import com.kharchmonitor.view.ExpenditureAddView;

@Service
public class ExpenditureBusiness {
	
	@Autowired
	ExpenditureRepository expenditureRepository;
	
	@Autowired
	ExpenditureTranslator expenditureTranslator;
	
	public List<Expenditure> getAllExpenditure(String userName) {
		return expenditureRepository.findByUserName(userName);
	}

	public void addExpenditure(ExpenditureAddView expenditureAddView){
		
		ExpenditureType expenditureType = expenditureTranslator.toView(expenditureAddView);
		Expenditure existingexpenditure =expenditureRepository.
				findByUserNameAndDate(expenditureAddView.getUserName(), expenditureAddView.getDate());
		
//		TODO delete before deployment
//		expenditureRepository.delete("5aae8d03c66a020fc88b496f");
		
		if(null==existingexpenditure) {
			existingexpenditure=new Expenditure();
			existingexpenditure.setExpenditureTypes(new ArrayList<ExpenditureType>());
			existingexpenditure.setDate(expenditureAddView.getDate());
//			existingexpenditure.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(expenditureAddView.getDate()));
			existingexpenditure.setUserName(expenditureAddView.getUserName());
		}
		existingexpenditure.getExpenditureTypes().add(expenditureType);
		expenditureRepository.save(existingexpenditure);
	}

}		
