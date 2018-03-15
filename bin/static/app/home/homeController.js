(function(){
'use strict';

angular.module('kharchApp').controller('HomeController',
    ['HomeService', '$scope',  function( homeService, $scope) {
    	
    	$scope.user = {
    			userName : 'Dashboard'
    	}
    	
}])
})();