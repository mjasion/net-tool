angular.module('app.controllers')
    .controller('WatchedPagesController', function ($scope, Restangular) {
        $scope.refreshPage = function () {
            Restangular.all('watchedPages').getList().then(function (response) {
                $scope.watchedPages = response
            })
        }
        $scope.refreshPage()

        $scope.remove = function (watchedPage) {
            if (confirm('Delete this page?\n\nURL: ' + watchedPage.url  + '\nCreated: ' + watchedPage.created)) {
                Restangular.one('watchedPage').remove({url: watchedPage.url}).then(function (response) {
                    $scope.refreshPage()
                })
            }
        }

    });