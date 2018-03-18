package com.kharchmonitor.translator;

import org.springframework.stereotype.Service;

import com.kharchmonitor.persistence.entity.ExpenditureType;
import com.kharchmonitor.view.ExpenditureAddView;

@Service
public class ExpenditureTranslator {

	public ExpenditureType toView(ExpenditureAddView expenditureAddView) {
		
		ExpenditureType expenditureType = new ExpenditureType();
		expenditureType.setAmount(expenditureAddView.getAmount());
		expenditureType.setComment(expenditureAddView.getComment());
		expenditureType.setType(expenditureAddView.getExpenditureType());
		return expenditureType;
	}

}
