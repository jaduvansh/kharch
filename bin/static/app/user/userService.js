(function(){
'use strict';
angular.module('kharchApp').factory('UserService',['$http','$q','urls',userService]);

    function userService($http, $q, urls) {
    	
    	var user={};
    	
    	function setUser(userInfo){
    		user = userInfo;
    	}
    	
    	function getUser(){
    		return user;
    	}
    	
    	 function create(user) {
             console.log(user);
             var deferred = $q.defer();
             $http.post(urls.SIGNUP, user)
                 .then(
                     function (response) {
                     	console.log(response);
                     	user = response.data;
                        deferred.resolve(response.data);
                     },
                     function (errResponse) {
                         console.error(errResponse);
                         deferred.reject('Error while creating user account');
                     }
                 );
             return deferred.promise;
         }
    	
    	return {
    		setUser: setUser,
    		getUser: getUser,
    		create: create
    	};
    }
 })();   