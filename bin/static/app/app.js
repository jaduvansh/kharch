var app = angular.module('kharchApp',['ui.router']);

app.constant('urls', {
    BASE: '/',
    LOGIN : '/user/login/',
    SIGNUP : '/user/',
    EXPENDITURE : '/expenditure/',
    LOOKUP_EXPENDITURE_TYPE : '/expenditureType/'
});

app.config(['$stateProvider', '$urlRouterProvider','$httpProvider',
    function($stateProvider, $urlRouterProvider, $httpProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'app/home/view/home.html',
                controller:'HomeController'
            }).state('login', {
                url: '/login',
                templateUrl: 'app/login/view/login.html',
                controller:'LoginController'
            }).state('signup', {
                url: '/signup',
                templateUrl: 'app/signup/view/signup.html',
                controller:'SignupController'
            }).state('dashboard', {
                url: '/dashboard',
                templateUrl: 'app/dashboard/view/dashboard.html',
                controller:'DashboardController'
            }).state('add-expenditure', {
                url: '/add-expenditure',
                templateUrl: 'app/expenditure/view/addExpenditure.html',
                controller:'ExpenditureController'
            }).state('lookupType', {
                url: '/lookupType',
                templateUrl: 'app/lookupType/view/lookupType.html',
                controller:'LookupTypeController'
            }).state('addLookupType', {
                url: '/addLookupType',
                templateUrl: 'app/lookupType/view/addLookupType.html',
                controller:'LookupTypeController'
            });
        $urlRouterProvider.otherwise('/');
        $httpProvider.interceptors.push('APIInterceptor');
    }]);

