'use strict';

angular.module('app.controllers', [])

var netTool = angular.module('netTool', ['ngRoute', 'app.controllers']);
netTool.config(function ($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(false);
    $routeProvider.
        when('/redirects', {
            templateUrl: 'partials/redirects.html',
            controller: 'RedirectsController'
        }).
        when('/redirects/add', {
            templateUrl: 'partials/addRedirect.html',
            controller: 'AddRedirectController'
        }).
        when('/history', {
            templateUrl: 'partials/history.html',
            controller: 'HistoryController'
        }).
        when('/logout', {
            controller: 'LogoutController'
        }).
        otherwise({
            redirectTo: '/redirects'
        });
})

