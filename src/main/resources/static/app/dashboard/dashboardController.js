(function(){
'use strict';

angular.module('kharchApp').controller('DashboardController',
    ['DashboardService', '$scope','UserService',  function( dashboardService, $scope, userService) {
    	
    	$scope.user = userService.getUser();
    	
}])
})();