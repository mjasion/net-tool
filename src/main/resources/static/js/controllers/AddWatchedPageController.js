angular.module('app.controllers')
    .controller('AddWatchedPageController', function ($scope, $http, Restangular) {
        $scope.saveWatchedPage = function () {
            Restangular.one('watchedPage').put($scope.watchedPage).then(function (result) {
                $scope.watchedPage = {}
            })
        }
    });