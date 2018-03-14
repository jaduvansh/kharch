(function(){
'use strict';
angular.module('kharchApp').factory('UserService',userService);

    function userService() {
    	
    	var user={};
    	
    	function setUser(userInfo){
    		user = userInfo;
    	}
    	
    	function getUser(){
    		return user;
    	}
    	
    	return {
    		setUser: setUser,
    		getUser: getUser
    	};
    }
 })();   