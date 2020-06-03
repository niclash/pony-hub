'use strict';

angular.module('ponyhub.add_project', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/add_project', {
            templateUrl: 'project/add.html',
            controller: 'AddProjectCtrl'
        });
    }])
    .controller('AddProjectCtrl', ['$scope', '$log', function ($scope, $log) {
        $scope.addProject = function() {
            let locator = document.getElementById("projectLocator").value;
            console.log(locator);
            if( locator.length > 0 )
            {
                let version = "master";
                let pos = locator.indexOf("#");
                if(pos > 0 )
                {
                    version = locator.substring(pos+1);
                    locator = locator.substrin(0,pos);
                }
                $scope.registerProject(locator, version);
            }
        }
    }]);
