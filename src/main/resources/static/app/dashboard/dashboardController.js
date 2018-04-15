(function(){
'use strict';

angular.module('kharchApp').controller('DashboardController',
    ['DashboardService', '$scope','UserService','$state',  
    	function( dashboardService, $scope, userService, $state) {
    	
	    	$scope.user = userService.getUser();
	    	
    		if($scope.user){
    			dashboardService.gridData($scope.user.userName).then(function(grid){
		    		 $scope.grid = grid;
		    		 $scope.spentAmount = calculateSpentAmount(grid.footer);
		    	}, function(error){
		    		alert("error loading data");
		    	});
    		}
	    	
    		var calculateSpentAmount = function(footer){
    			var spentAmount = 0;
    			for(var i=0; i<footer.length ; i++){
    				spentAmount += footer[i].amount;
    			}
    			console.log("spentAmount " +spentAmount);
    			return spentAmount;
    		};
	    	
	    	$scope.logout = function(){
	    		userService.clearUser();
	    	}
	    	
    	}
    ])
})();