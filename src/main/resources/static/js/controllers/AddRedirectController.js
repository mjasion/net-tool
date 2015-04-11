angular.module('app.controllers')
    .controller('AddRedirectController', function ($scope, $http, Restangular) {
        $scope.saveRedirect = function () {
            Restangular.one('redirect').put($scope.redirect).then(function (result) {
                $scope.redirect = {}
            })
        }
    });