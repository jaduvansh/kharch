(function(){
'use strict';
angular.module('kharchApp').factory('DashboardService',['ExpenditureService', '$q', 'LookupTypeService', dashboardService]);

    function dashboardService(expenditureService, $q, lookupTypeService) {
    	
    	var arrangeRowBasedOnHeaderOrder(header, row){
    		var result = {};
	    	result.date = row.date;
			result.expType = {};
			for(var i=0; i< row.expenditureTypes.length; i++){
				var expTypeObj = row.expenditureTypes[i];
				result.expType[expTypeObj.type] = expTypeObj;
			}
			return result;
    	};
    	
    	var arrangeBodyBasedOnHeaderOrder = function(header, searchResult){
    		var rows = [];
    		for(var i=0; i< searchResult.length; i++){
    			rows.push(arrangeRowBasedOnHeaderOrder(header, searchResult[i]));
    		}
    		console.log(body);
    		return rows;
    	};
    	
    	var gridData = function(username){
    		var grid={};
    		var deffered = $q.defer();
    		$q.all([lookupTypeService.getExpenditureTypes(),
    				expenditureService.searchAllExpenditureByUserName(username)]).then(function(response){
    					grid.header = response[0].data;
    					console.log(grid.header);
    				    console.log(response[1]);
    				    grid.body = arrangeBodyBasedOnHeaderOrder(grid.header, response[1]);
    				    deffered.resolve(grid);
    			  },function(error){
    				  deffered.reject(error);
    			  });
    		return deffered.promise;
    	}
    	
    	return {
    		gridData : gridData
    		
    	};
    }
 })();   