(function(){
'use strict';
angular.module('kharchApp').factory('UserService',['$http','$q','urls',userService]);

    function userService($http, $q, urls) {
    	
    	var user = undefined;
    	
    	var getUserFromLocalStorage = function(){
    		var user = localStorage.getItem("log");
    		return !user ? undefined : JSON.parse(user);
    	}
    	
    	function setUser(userInfo){
    		var groups =[];
    		for(var i=0; i< userInfo.groups.length; i++){
    			groups.push(userInfo.groups[i].groupName);
    		}
    		userInfo.groups = groups;
    		userInfo.activeGroup = groups[0];
    		localStorage.setItem('log', JSON.stringify(userInfo));
    		user = userInfo;
    	}
    	
    	function getUser(){
    		if(!user){
    			user = getUserFromLocalStorage();
    		}
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
    	 
    	 function clearUser(){
    		 localStorage.removeItem("log");
    	 }
    	
    	return {
    		setUser: setUser,
    		getUser: getUser,
    		clearUser: clearUser,
    		create: create
    	};
    }
 })();   