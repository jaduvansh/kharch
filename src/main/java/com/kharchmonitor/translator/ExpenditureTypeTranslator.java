package com.kharchmonitor.translator;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.kharchmonitor.persistence.entity.ExpenditureType;
import com.kharchmonitor.view.ExpenditureView;

@Service
public class ExpenditureTypeTranslator {

	public ExpenditureType toEntity(ExpenditureView expenditureAddView) {
		
		ExpenditureType expenditureType = new ExpenditureType();
		expenditureType.setAmount(expenditureAddView.getAmount());
		expenditureType.setComment(expenditureAddView.getComment());
		expenditureType.setType(expenditureAddView.getExpenditureType());
		expenditureType.setUserName(expenditureAddView.getUserName());
		expenditureType.setCreatedDate(new Date());
		return expenditureType;
	}

}
