angular.module('app.controllers')
    .controller('RedirectHistoryController', function ($scope, Restangular) {
        $scope.pageNumber = 0
        $scope.size = 50

        $scope.refreshPage = function () {
            Restangular.one('redirect-history').customGET('', {page: $scope.pageNumber, size: $scope.size}).then(function (response) {
                $scope.redirectHistory = response
            })
        }

        $scope.refreshPage()

        $scope.prevPage = function () {
            if ($scope.redirectHistory.firstPage)
                return;
            $scope.pageNumber = $scope.pageNumber - 1
            $scope.refreshPage()
        }

        $scope.nextPage = function () {
            if ($scope.redirectHistory.lastPage)
                return
            $scope.pageNumber = $scope.pageNumber + 1
            $scope.refreshPage()
        }

    });