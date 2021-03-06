(function(){
'use strict';
angular.module('kharchApp').factory('ExpenditureService',['$http', '$q', 'urls', expenditureService]);

    function expenditureService($http, $q, urls) {
    	
    	var postProcess = function(data){
    		var result = [];
    		for(var i=0; i< data.length; i++){
    			result.push(processByDate(data[i]));
    		}
    		console.log(result);
    		return result;
    	};
    	
    	var addExpenditure = function(expTypeObj,result){
    		var expenditure = {};
			expenditure.amount = expTypeObj.amount;
			expenditure.comment = expTypeObj.comment;
			expenditure.expDetails = [expTypeObj];
			result.expType[expTypeObj.type] = expenditure;
    	};
    	
    	var updateExpenditure = function(expTypeObj,result){
    		var expenditure = result.expType[expTypeObj.type];
    		expenditure.comment = getComment(expenditure.comment)+ "\n" + getComment(expTypeObj.comment);
			expenditure.amount += expTypeObj.amount;
			expenditure.expDetails.push(expTypeObj);
    	};
    	
    	var getComment = function(comment){
    		return comment || "";
    	}
    	
    	var processByDate = function(objPerDate){
    		var result = {};
	    	result.date = objPerDate.date;
			result.expType = {};
			for(var i=0; i< objPerDate.expenditureTypes.length; i++){
				var expTypeObj = objPerDate.expenditureTypes[i];
				if(!result.expType[expTypeObj.type]){
					addExpenditure(expTypeObj,result);
				}else{
					updateExpenditure(expTypeObj,result);
				}
			}
			return result;
    	}
    	    	
    	var searchAllExpenditureByUserName = function(groupName, month, year){
    		var deffered = $q.defer();
    		$http.get(urls.EXPENDITURE+groupName+"/"+month+"-"+year).then(function(response){
    			var result = postProcess(response.data);
    			deffered.resolve(result);	
    		}, function(error){
    			defffered.reject('error while fetching search');
    		});
    		return deffered.promise;
    	}
    	
    	var add = function(expense){
    		return $http.post(urls.EXPENDITURE, expense);
    	}
    	
    	return {
    		searchAllExpenditureByUserName : searchAllExpenditureByUserName,
    		add : add
    		
    	};
    }
 })();   