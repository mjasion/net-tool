angular.module('app.controllers')
    .controller('AccessHistoryController', function ($scope, Restangular) {
        $scope.page = 0
        Restangular.one('access-history').customGET('', {page: $scope.page}).then(function (response) {
            $scope.accessHistory = response
        })
    });