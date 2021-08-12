'use strict';

angular.module('ponyhub.search', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/search', {
            templateUrl: 'search/search.html',
            controller: 'SearchCtrl'
        });
    }])
    .controller('SearchCtrl', ['$scope', function ($scope) {
        $scope.isGreater = (a, b) => {

            const [majorA, minorA, patchA] = String(a).split('.').map(v => Number.parseInt(v));
            const [majorB, minorB, patchB] = String(b).split('.').map(v => Number.parseInt(v));

            if (majorA !== majorB) {
                return majorA > majorB;
            }

            if (minorA !== minorB) {
                return minorA > minorB;
            }

            return patchA > patchB;

        };
        $scope.searchFreeText = function () {
            let searchData = window.document.getElementById("searchTerm").value;
            let query = "free:" + searchData;
            $scope.search(query, function(resp, success){
                if( success )
                {
                    var result = []
                    for( let i=0; i < resp.length; i++ )
                    {
                        let repo = resp[i];
                        var found = false;
                        for( let j=0; j < result.length; j++ )
                        {
                            let old = result[j];
                            if( old.host === repo.host && old.organization === repo.organization && old.name === repo.name )
                            {
                                found = true;
                                if( $scope.isGreater(repo.version, old.version))
                                {
                                    result[j] = repo;
                                }
                            }
                        }
                        if( !found )
                        {
                            result.push(repo);
                        }
                    }
                    $scope.searchResult = result;
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
