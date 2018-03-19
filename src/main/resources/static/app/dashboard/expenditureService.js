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
    	
    	var processByDate = function(objPerDate){
    		var result = {};
	    	result.date = objPerDate.date;
			result.expType = {};
			for(var i=0; i< objPerDate.expenditureTypes.length; i++){
				var expTypeObj = objPerDate.expenditureTypes[i];
				result.expType[expTypeObj.type] = expTypeObj;
			}
			return result;
    	}
    	
    	var searchAllExpenditureByUserName = function(userName){
    		userName = 'Jadu';
    		var deffered = $q.defer();
    		$http.get(urls.SEARCH_EXP+userName).then(function(response){
    			var result = postProcess(response.data);
    			deffered.resolve(result);	
    		}, function(error){
    			defffered.reject('error while fetching search');
    		});
    		return deffered.promise;
    	}
    	
    	return {
    		searchAllExpenditureByUserName : searchAllExpenditureByUserName
    		
    	};
    }
 })();   