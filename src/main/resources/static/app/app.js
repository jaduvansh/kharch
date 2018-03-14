var app = angular.module('kharchApp',['ui.router']);

app.constant('urls', {
    BASE: '/',
    LOGIN : '/user/login/'
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
            }).state('dashboard', {
                url: '/dashboard',
                templateUrl: 'app/dashboard/view/dashboard.html',
                controller:'DashboardController'
            });
        $urlRouterProvider.otherwise('/');
    }]);

