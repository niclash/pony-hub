'use strict';

angular.module('ponyhub.search', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/search', {
            templateUrl: 'search/search.html',
            controller: 'SearchCtrl'
        });
    }])
    .controller('SearchCtrl', ['$scope', function ($scope) {
        $scope.searchFreeText = function () {
            let searchData = window.document.getElementById("searchTerm").value;
            let query = "free:" + searchData;
            $scope.search(query, function(resp, success){
                if( success )
                {
                    $scope.searchResult = resp;
                }
                else
                {
                    $scope.searchResult = {
                        name: "error occured"
                    }
                }
            });
        };

    }]);
