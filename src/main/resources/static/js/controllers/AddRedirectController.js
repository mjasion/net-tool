angular.module('app.controllers')
    .controller('AddRedirectController', function ($scope, $http, Restangular) {
        $scope.saveRedirect = function () {
            console.log($scope.redirect)
            Restangular.one('redirect').put($scope.redirect).then(function (result) {
                console.log(result)
                $scope.redirect = {}
            })
        }
    });