(function(){
'use strict';

angular.module('kharchApp').controller('ExpenditureController',
    ['ExpenditureService', '$scope','UserService',  
    	function( expenditureService, $scope, userService) {
    	
	    	var user = userService.getUser();
	    	$scope.expenditure = {};
	    	
	    	$scope.add = function(){
	    		$scope.expenditure.userName = user.unserName;
	    		expenditureService.add($scope.expenditure).then(function(){
		    		 $scope.success = "Expenditure has been added sucefully";
		    		 setTimeout(function(){
		    			 $scope.success = '';
		    		 }, 2000);
		    	}, function(error){
		    		alert("error while adding expense");
		    	});
	    	}
	    	
    	}
    ])
})();