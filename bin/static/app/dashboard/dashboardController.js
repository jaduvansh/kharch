(function(){
'use strict';

angular.module('kharchApp').controller('DashboardController',
    ['DashboardService', '$scope','UserService',  
    	function( dashboardService, $scope, userService) {
    	
	    	$scope.user = userService.getUser();
	    	
	    	dashboardService.gridData($scope.user.userName).then(function(grid){
	    		 $scope.grid = grid;
	    	}, function(error){
	    		alert("error loading data");
	    	});
	    	
    	}
    ])
})();