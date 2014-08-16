'use strict'

var directives = angular.module('app.directives', [])

directives.directive('tablePaginator', function () {
    return {
        restrict: 'AE',
        scope: {
            page: '=page',
            pageNum: '=pageNum',
            prevPage: '&',
            nextPage: '&'
        },
        link: function (scope) {
            function getPages() {
                var pages = []
                for (var i = 0; i < scope.page.totalElements / scope.page.size; i++) {
                    pages.push({
                        num: i,
                        isActive: i === scope.page.number
                    })
                }
                return pages

                scope.$watch('page', function (page) {
                    if (page) {
                        scope.paginator = getPages()
                    }
                })
            }
        },
        template: '<ul class="pager">' +
            '<li></li>' +
            '<li ng-class="{disabled : page.firstPage}"><a ng-click="prevPage()">Previous</a></li>' +
            '<li ng-class="{disabled : page.lastPage}"><a ng-click="nextPage()">Next</a></li>' +
            '</ul>'
    }
})

directives.directive('tableInfo', function () {
    return {
        restrict: 'AE',
        scope: {
            page: '=page'
        },
        template: '<p class="text-right">{{page.number + 1}} / {{page.totalPages}}&nbsp;(Total: {{page.totalElements}})</p>'
    }
})

