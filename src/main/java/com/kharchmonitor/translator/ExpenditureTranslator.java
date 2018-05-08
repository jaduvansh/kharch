package com.kharchmonitor.translator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kharchmonitor.persistence.entity.Expenditure;
import com.kharchmonitor.view.ExpenditureSearchView;

@Service
public class ExpenditureTranslator {
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	public ExpenditureSearchView toView(Expenditure entity) {
		
		ExpenditureSearchView view = new ExpenditureSearchView();
		view.setGroupName(entity.getGroupName());
		view.setExpenditureTypes(entity.getExpenditureTypes());
		view.setDate(dateFormat.format(entity.getDate()));
		return view;
		
	}
	
    public List<ExpenditureSearchView> toView(List<Expenditure> entities) {
		
		List<ExpenditureSearchView> views = new ArrayList<>();
		for (Expenditure entity : entities) {
			views.add(toView(entity));
		}
		return views;
		
	}

}
