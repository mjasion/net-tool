angular.module('app.controllers')
    .controller('RedirectHistoryController', function ($scope, Restangular) {
        $scope.page = 0
        Restangular.one('redirect-history').customGET('', {page: $scope.page}).then(function (response) {
            $scope.redirectHistory = response
        })
    });