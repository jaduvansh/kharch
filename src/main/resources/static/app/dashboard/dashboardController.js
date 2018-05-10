(function(){
'use strict';

angular.module('kharchApp').controller('DashboardController',
    ['DashboardService', '$scope','UserService','$state','$timeout',  
    	function( dashboardService, $scope, userService, $state, $timeout) {
    	
	    	$scope.user = userService.getUser();
	    	$scope.date = {};
	    	
    		if($scope.user){
    			var d = new Date();
        		var month = d.getMonth() + 1;
        		var year = d.getFullYear();
        		$scope.date.value = d;
    			dashboardService.gridData($scope.user.activeGroup, month, year).then(function(grid){
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
    		
    		$scope.searchAllExpenditureByUserNameMonthAndYear = function(){
    			var date = $scope.date.value;
    			var month = date.getMonth()+1;
    			var year = date.getFullYear();
    			dashboardService.gridData($scope.user.activeGroup, month, year).then(function(grid){
		    		 $scope.grid = grid;
		    		 $scope.spentAmount = calculateSpentAmount(grid.footer);
		    	}, function(error){
		    		alert("error loading data");
		    	});
    		}
	    	
	    	$scope.logout = function(){
	    		userService.clearUser();
	    	}
	    	
	    	$scope.exportToExcel = function(tableId){ // ex: '#my-table'
	            var exportHref = dashboardService.tableToExcel(tableId,'april2018');
	            $timeout(function() {
	            	location.href=exportHref;
	            },100); // trigger download
	        }
	    	
    	}
    ])
})();