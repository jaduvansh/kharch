(function(){
'use strict';

angular.module('kharchApp').controller('DashboardController',
    ['ExpenditureService', '$scope','UserService',  function( expenditureService, $scope, userService) {
    	
    	$scope.user = userService.getUser();
    	expenditureService.searchAllExpenditureByUserName($scope.user.userName).then(function(data){
    		 $scope.searchData = data;
    	}, function(error){
    		alert(error);
    	});
    	
}])
})();