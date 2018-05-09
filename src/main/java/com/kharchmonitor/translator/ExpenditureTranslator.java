package com.kharchmonitor.translator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kharchmonitor.persistence.entity.Expenditure;
import com.kharchmonitor.persistence.entity.ExpenditureType;
import com.kharchmonitor.view.ExpenditureSearchView;

@Service
public class ExpenditureTranslator {
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private final SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
	
	public ExpenditureSearchView toView(Expenditure entity) {
		
		ExpenditureSearchView view = new ExpenditureSearchView();
		view.setGroupName(entity.getGroupName());
		view.setExpenditureTypes(entity.getExpenditureTypes());
		view.setDate(dateFormat.format(entity.getDate()));
		formatExpenditureCreatedDate(view.getExpenditureTypes());
		return view;
		
	}
	
    private void formatExpenditureCreatedDate(List<ExpenditureType> expenditureTypes) {
    	expenditureTypes.forEach(type->{
    		if(null!=type.getCreatedDate()) {
    		   type.setDate(dateFormat2.format(type.getCreatedDate()));
    		}
    	});
	}

	public List<ExpenditureSearchView> toView(List<Expenditure> entities) {
		
		List<ExpenditureSearchView> views = new ArrayList<>();
		for (Expenditure entity : entities) {
			views.add(toView(entity));
		}
		return views;
		
	}

}
