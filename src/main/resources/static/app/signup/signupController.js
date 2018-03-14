(function(){
'use strict';

angular.module('kharchApp').controller('SignupController',
    ['$scope', '$state','UserService',  function( $scope, $state, userService) {
    	
    	$scope.user = {};
    	$scope.create = function(){
    		console.log($scope.user);
    		userService.create($scope.user).then(function(){
     		   $scope.user = {};
     		   $state.go('dashboard');
     		}, function(error){
     			alert('somthing went wrong');
     			$scope.error = error;
     		});
    	};
    	
    	$scope.cancel = function(){
    		$state.go('home');
    	}
    	
    	$scope.clear = function(){
    		$scope.user = {};
    	}
    	
}])
})();