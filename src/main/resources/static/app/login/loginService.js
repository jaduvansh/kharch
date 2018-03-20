(function(){
'use strict';
angular.module('kharchApp').factory('LoginService',['$http', '$q', 'urls', loginService]);

    function loginService($http, $q, urls) {

        function getUser(user) {
            var deferred = $q.defer();
            $http.post(urls.LOGIN, user).then(function (response) {
            	if(response.data){
                   deferred.resolve(response.data);
            	} else {
            		deferred.reject("user name or password is incorrect");
            	}
            },
            function (errResponse) {
                deferred.reject('Error while login');
            });
            return deferred.promise;
        }
        
        return {
            getUser: getUser
        };
   }
 })();   