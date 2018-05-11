(function(){
'use strict';

angular.module('kharchApp').controller('ExpenditureController',
    ['ExpenditureService', '$scope','UserService','LookupTypeService','$timeout', '$state', 
    	function( expenditureService, $scope, userService, lookupTypeService, $timeout,$state) {
    	
	    	var user = userService.getUser();
	    	$scope.expenditure = {};
	    	
	    	lookupTypeService.getExpenditureTypes().then(function(response){
	    		$scope.expenditureTypes = response.data;
	    		var date = new Date();
	    		$scope.expenditure.date=date;
	    	},function(error){
	    		console.log(error);
	    		alert("error loading lookup type");
	    	});
	    	
	    	$scope.reset = function(){
	    		$scope.expenditure = {};
	    	}
	    	
	    	$scope.close = function(){
	    		$state.go('dashboard');
	    	}
	    	
	    	$scope.add = function(){
	    		$scope.expenditure.groupName = user.activeGroup;
	    		$scope.expenditure.userName = user.userName;
	    		expenditureService.add($scope.expenditure).then(function(){
		    		 $scope.successMessage = "Expenditure has been added sucefully";
		    		 $scope.reset();
		    		 var date = new Date();
			    	 $scope.expenditure.date=date;
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
