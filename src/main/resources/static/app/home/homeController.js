(function(){
'use strict';

angular.module('kharchApp').controller('HomeController',
    ['$rootScope', '$scope', '$state', function( $rootScope, $scope, $state) {
    	
     $rootScope.$on('unauthorized', function() {
    	 $state.go('login');
     });
    	
}])
})();