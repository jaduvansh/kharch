var app = angular.module('kharchApp',['ui.router']);

app.constant('urls', {
    BASE: '/',
    LOGIN : '/user/login/',
    SIGNUP : '/user/',
    SEARCH_EXP : '/expenditure/',
    LOOKUP_EXPENDITURE_TYPE : '/expenditureType/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

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
            });
        $urlRouterProvider.otherwise('/');
    }]);

