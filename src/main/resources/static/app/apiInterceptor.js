app.factory('APIInterceptor',['$rootScope', function($rootScope) {
    var service = this;
    var protectedUrls = ['dashboard.html','addExpenditure.html','lookupType.html','addLookupType.html'];
    var getUserFromLocalStorage = function(){
		var user = localStorage.getItem("log");
		return !user ? undefined : JSON.parse(user);
	}
    var contains = function(urls, configUrl){
    	for(var i=0; i<urls.length; i++){
    		if(configUrl.indexOf(urls[i])>-1){
    			return true;
    		}
    	}
    	return false;
    }
    service.request = function(config) {   
    	console.log(config.url);
    	var user = getUserFromLocalStorage();  	
    	if(contains(protectedUrls, config.url) && !user){
    		console.log('login');
    		$rootScope.$broadcast('unauthorized');
    	}
        return config;
    };
 
    return service;
}]);