'use strict';

angular.module('app.controllers', ['restangular'])

var netTool = angular.module('netTool', ['ngRoute', 'app.controllers']);
netTool.config(function ($routeProvider, $locationProvider, RestangularProvider) {
    $locationProvider.html5Mode(false);
    $routeProvider.
        when('/redirect', {
            templateUrl: 'partials/redirects.html',
            controller: 'RedirectsController'
        }).
        when('/redirect/add', {
            templateUrl: 'partials/addRedirect.html',
            controller: 'AddRedirectController'
        }).
        when('/redirect/history', {
            templateUrl: 'partials/redirectHistory.html',
            controller: 'RedirectHistoryController'
        }).
        when('/access/history', {
            templateUrl: 'partials/accessHistory.html',
            controller: 'AccessHistoryController'
        }).
        when('/logout', {
            controller: 'LogoutController'
        }).
        otherwise({
            redirectTo: '/redirects'
        });

    RestangularProvider.setBaseUrl('/admin/')
})

