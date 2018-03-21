(function(){
'use strict';

angular.module('kharchApp').controller('ExpenditureController',
    ['ExpenditureService', '$scope','UserService','LookupTypeService','$timeout',  
    	function( expenditureService, $scope, userService, lookupTypeService, $timeout) {
    	
	    	var user = userService.getUser();
	    	$scope.expenditure = {};
	    	
	    	lookupTypeService.getExpenditureTypes().then(function(response){
	    		$scope.expenditureTypes = response.data;
	    	},function(error){
	    		console.log(error);
	    		alert("error loading lookup type");
	    	});
	    	
	    	$scope.reset = function(){
	    		$scope.expenditure = {};
	    	}
	    	
	    	$scope.add = function(){
	    		$scope.expenditure.userName = user.unserName || "Jadu";
	    		expenditureService.add($scope.expenditure).then(function(){
		    		 $scope.successMessage = "Expenditure has been added sucefully";
		    		 $scope.reset();
		    		 $timeout(function(){
		    			 $scope.successMessage = undefined;
		    		 }, 2000);
		    	}, function(error){
		    		$scope.errorMessage = "error while adding expense";
		    	});
	    	}
	    	
    	}
    ])
})();