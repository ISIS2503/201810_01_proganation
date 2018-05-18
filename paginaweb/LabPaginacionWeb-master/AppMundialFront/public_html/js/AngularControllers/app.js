/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    var aplicacionMundial = angular.module('aplicacionMundial', []);



    aplicacionMundial.directive('toolbar', function () {
        return{
            restrict: 'E',
            templateUrl: 'toolbar.html',
            controller: function () {
                this.tab = 0;
                this.selectTab = function (setTab) {
                    this.tab = setTab;
                };
                this.isSelected = function (tabParam) {
                    return this.tab === tabParam;
                };
            },
            controllerAs: 'toolbar'
        };
    });




    aplicacionMundial.directive('competitorInfo', function () {
        return{
            restrict: 'E',
            templateUrl: 'competitorInfo.html',
            controller: ['$http', '$scope', function ($http, $scope) {
                    var self = this;
                    self.competitors = [];
                    self.fallos = [];
                    $scope.api = function () {
                        var callApi = $http.get('http://172.24.42.60:8080/Alertas/UnidadResidencial/100').success(function (data) {
                            self.competitors = data;
                        });
                    };
                    $scope.api();
                    $scope.fallos = function() {
                        var darFallos = $http.get('http://172.24.42.60:8080/UnidadResidencial/100/Inmueble/1/Dispositivos').success(function (data) {
                            self.fallos = data;
                        });
                    };
                    $scope.fallos();
                }],
            controllerAs: 'CompetitorsListCtrl'
        };
    });
aplicacionMundial.controller('MyCtrl', ['$scope', '$http',
        function MyCtrl($scope, $http) {
            // asynchronous call
                var callApi = $http.get('http://172.24.42.60:8080/Alertas/UnidadResidencial/100').success(function (data) {
                    
                    $scope.competitors = data;                   
                });
                var darFallos = $http.get('http://172.24.42.60:8080/UnidadResidencial/100/Inmueble/1/Dispositivos').success(function (data) {
                            $scope.fallos = data;
                        });
                darFallos.then(function (){
                    $scope.totalFallos = $scope.fallos;
                    console.log($scope.totalFallos);
                });
                callApi.then(function () {
                    $scope.totalR = $scope.competitors;
                    console.log($scope.totalR);
                });
        }
    ]);
    aplicacionMundial.directive('datatableSetup', ['$timeout',
        function ($timeout) {
            return {
                restrict: 'A',
                link: function (scope, element, attrs) {
 
                    $timeout(function () {
                        element.dataTable();
                    });
                }
            }
        }
    ]);



})();

