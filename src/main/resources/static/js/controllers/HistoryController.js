angular.module('app.controllers')
    .controller('HistoryController', function ($scope, Restangular) {
        Restangular.one('access-history').get().then(function(response){
            $scope.redirectHistory = response
        })
    });