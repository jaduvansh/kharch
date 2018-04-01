(function(){
'use strict';

angular.module('kharchApp').controller('DashboardController',
    ['DashboardService', '$scope','UserService','$state',  
    	function( dashboardService, $scope, userService, $state) {
    	
	    	$scope.user = userService.getUser();
	    	
    		if($scope.user){
    			dashboardService.gridData($scope.user.userName).then(function(grid){
		    		 $scope.grid = grid;
		    	}, function(error){
		    		alert("error loading data");
		    	});
    		}
	    	
	    	
	    	$scope.logout = function(){
	    		userService.clearUser();
	    	}
	    	
    	}
    ])
})();