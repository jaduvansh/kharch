(function(){
'use strict';

angular.module('kharchApp').controller('LookupTypeController',
    [ '$scope','LookupTypeService','$timeout', '$state', 
    	function( $scope, lookupTypeService, $timeout,$state) {

    	lookupTypeService.getExpenditureTypes().then(function(response){
    		$scope.expenditureTypes = response.data;
    	},function(error){
    		console.log(error);
    		alert("error loading lookup type");
    	});
    	
    	$scope.close = function(){
    		$state.go('dashboard');
    	}
    	
    	$scope.edit = function(){
    	}
    	
    	$scope.add = function(){
    		$state.go('addLookupType');
    	}
    }
])
})();