(function(){
'use strict';
angular.module('kharchApp').factory('LookupTypeService',['$http', 'urls', lookupTypeService]);

    function lookupTypeService($http, urls) {

        function getExpenditureTypes(user) {
            return $http.get(urls.LOOKUP_EXPENDITURE_TYPE);
        }
        
        return {
        	getExpenditureTypes: getExpenditureTypes
        };
   }
 })();   