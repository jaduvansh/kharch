package com.kharchmonitor.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kharchmonitor.persistence.entity.ExpenditureTypeLookup;
import com.kharchmonitor.repository.LookupRepository;

@Service
public class LookupBusiness {
	
	@Autowired
	private LookupRepository lookupRepository;

	public List<ExpenditureTypeLookup> findAll() {
		return lookupRepository.findAll();
	}

	public ExpenditureTypeLookup add(ExpenditureTypeLookup expenditureTypeLookup) {
		if(isTypeAvailable(expenditureTypeLookup)) {
			return null;
		}
		expenditureTypeLookup.setValue(expenditureTypeLookup.getValue());
		return lookupRepository.save(expenditureTypeLookup);
	}

	public void delete(String expenditureTypeLookup) {
		ExpenditureTypeLookup existingLookupType =lookupRepository.findByValue(expenditureTypeLookup);
		lookupRepository.delete(existingLookupType.get_id());
	}

	public void update(String oldValue, String newValue) {
		ExpenditureTypeLookup existingLookupType =lookupRepository.findByValue(oldValue.toUpperCase());
		existingLookupType.setValue(newValue);
		lookupRepository.save(existingLookupType);
	}
	public boolean isTypeAvailable(ExpenditureTypeLookup expenditureTypeLookup) {
		return (null!=lookupRepository.findByValue(expenditureTypeLookup.getValue()));
	}

	public List<String> getLookupTypes() {
		return lookupTypesTranslator(findAll());
	}

	private List<String> lookupTypesTranslator(List<ExpenditureTypeLookup> ExpenditureTypeLookups) {
		List<String> lookupTypes =new ArrayList<String>();
		for(ExpenditureTypeLookup typeLookup:ExpenditureTypeLookups) {
			lookupTypes.add(typeLookup.getValue());
		}
		return lookupTypes;
	}
}
