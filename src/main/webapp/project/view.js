'use strict';

angular.module('ponyhub.view_project', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view_project/:host/:organization/:name/:version', {
            templateUrl: 'project/view.html',
            controller: 'ViewProjectCtrl'
        });
    }])
    .controller('ViewProjectCtrl', ['$scope', '$log', '$routeParams', function ($scope, $log, $routeParams) {
        $scope.host = $routeParams.host;
        $scope.organization = $routeParams.organization;
        $scope.name = $routeParams.name;
        $scope.version = $routeParams.version;
        $scope.fetchProject($scope.host + "/" + $scope.organization + "/" + $scope.name + "/" + $scope.version,
            function (response, success) {
                if (success) {
                    $scope.projectData = response;
                    $log.info("Fetched project.", $scope.projectData);
                    $scope.rendered = marked($scope.projectData._source.readMe);

                }
            });
        $scope.renderReadme = function() {
            console.log("Render:", $scope.rendered);
            return $scope.rendered;
        }
    }]);
