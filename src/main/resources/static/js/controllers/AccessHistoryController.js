angular.module('app.controllers')
    .controller('AccessHistoryController', function ($scope, Restangular) {
        $scope.pageNumber = 0
        $scope.size = 50

        $scope.refreshPage = function () {
            Restangular.one('access-history').customGET('', {page: $scope.pageNumber, size: $scope.size}).then(function (response) {
                $scope.accessHistory = response
            })
        }

        $scope.prevPage = function () {
            if ($scope.accessHistory.firstPage)
                return;
            $scope.pageNumber = $scope.pageNumber - 1
            $scope.refreshPage()
        }

        $scope.nextPage = function () {
            if ($scope.accessHistory.lastPage)
                return
            $scope.pageNumber = $scope.pageNumber + 1
            $scope.refreshPage()
        }

        $scope.$watch('size', function (newSize, old) {
            $scope.size = newSize
            $scope.pageNumber = 0
            $scope.refreshPage()
        })
    });