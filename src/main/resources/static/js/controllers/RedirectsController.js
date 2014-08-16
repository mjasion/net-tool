angular.module('app.controllers')
    .controller('RedirectsController', function ($scope, Restangular) {
        $scope.refreshPage = function () {
            Restangular.all('redirects').getList().then(function (response) {
                $scope.redirects = response
            })
        }
        $scope.refreshPage()

        $scope.remove = function (redirect) {
            if (confirm('Delete this redirect?\n\nAccess URL: ' + redirect.accessUrl + '\nRedirect URL: ' + redirect.redirectUrl + '\nCreated: ' + redirect.created)) {
                Restangular.one('redirect').remove({accessUrl: redirect.accessUrl}).then(function (response) {
                    $scope.refreshPage()
                })
            }
        }
    });