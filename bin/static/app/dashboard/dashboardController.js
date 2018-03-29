(function(){
'use strict';

angular.module('kharchApp').controller('DashboardController',
    ['DashboardService', '$scope','UserService','$state',  
    	function( dashboardService, $scope, userService, $state) {
    	
	    	$scope.user = userService.getUser();
	    	
	    	(function(){
	    		if(!$scope.user){
		    		$state.go('login');
		    	} else {
		    		dashboardService.gridData($scope.user.userName).then(function(grid){
			    		 $scope.grid = grid;
			    	}, function(error){
			    		alert("error loading data");
			    	});
		    	}
	    	})();
	    	
	    	$scope.logout = function(){
	    		userService.clearUser();
	    		//$state.go('home');
	    	}
	    	
    	}
    ])
})();