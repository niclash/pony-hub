angular.module('ponyhubapp', ['ngRoute', 'ngMessages', 'ponyhub.search', 'ponyhub.add_project', 'ponyhub.view_project'])
    .config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');
        $routeProvider.otherwise({redirectTo: '/search'});
    }])
    .controller('RootCtrl', ['$scope', '$rootScope', '$http', '$log', '$route', '$location', function ($scope, $rootScope, $http, $log, $route, $location) {
        $scope.updates = [];
        $scope.organizationCount = 0;
        $scope.repositoryCount = 0;
        $scope.versionCount = 0;
        $scope.refresh = function () {
            $http.get('/ov/')
                .then(function (resp) {
                    $log.info("Overview:", resp);
                    let data = resp.data;
                    $scope.updates = data.recentUpdates;
                    $scope.organizationCount = data.orgCount;
                    $scope.repositoryCount = data.repositoryCount;
                    $scope.versionCount = data.versionCount;
                    $log.info("recentUpdates:", $scope.updates);
                })
        };
        $scope.search = function (searchData, callback) {
            $log.info("Searching", searchData);
            $http.post('/s/', searchData)
                .then(function response(resp) {
                        $log.info("search", resp);
                        callback(resp.data, true);
                    },
                    function (response) {
                        $log.error("Error received from server.", response);
                        callback(resp, false);
                    }
                )
        };
        $scope.registerProject = function (locator, version) {
            $log.info("Registering project", locator, version);
            $http.post('/p/', "locator=" + encodeURIComponent(locator) + "&version=" + encodeURIComponent(version),
                {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
                .then(function response(resp) {
                        $log.info("project", resp);
                    },
                    function (response) {
                        $log.error("Error received from server.", response);
                    })
                .catch(function (response) {
                    // TODO: Figure out how to signal things to users
                    $log.error("Error occurred for " + search, response.status, response.data);
                })
                .finally(function () {
                    $route.reload();
                });
        };

        $scope.fetchProject = function (id, callback) {
            $log.info("Fetching " + id);
            $http.get('/p/' + id)
                .then(function (resp) {
                        callback(resp.data, true);
                    },
                    function (resp) {
                        $log.error("Error occurred getting project " + id, resp.status, resp.data);
                        callback(resp, false);
                    })
                .finally(function () {

                });
        };
        $scope.refresh();
    }])
;

