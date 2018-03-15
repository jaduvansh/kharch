(function(){
'use strict';

angular.module('kharchApp').controller('LoginController',
    ['LoginService', '$scope', '$state','UserService',  function( loginService, $scope, $state, userService) {
    	
    	$scope.user = {};
    	$scope.login = function(){
    		loginService.getUser($scope.user).then(function(userDetails){
    		   userService.setUser(userDetails);
    		   $scope.user = {};
    		   $state.go('dashboard');
    		}, function(error){
    			alert(error);
    			$scope.error = error;
    		});
    	};
    	
    	$scope.cancel = function(){
    		$state.go('home');
    	}
    	
}])
})();