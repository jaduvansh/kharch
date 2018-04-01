(function(){
'use strict';

angular.module('kharchApp').controller('LoginController',
    ['LoginService', '$scope', '$state','UserService','$rootScope',  function( loginService, $scope, $state, userService, $rootScope) {
    	
    	$scope.user = {};
    	$scope.login = function(){
    		loginService.getUser($scope.user).then(function(userDetails){
    		   userService.setUser(userDetails);
    		   $scope.user = {};
    		   $state.go('dashboard');
    		}, function(error){
    			$scope.errorMessage = error;
    		});
    	};
    	
    	$scope.cancel = function(){
    		$state.go('home');
    	}
    	
}])
})();