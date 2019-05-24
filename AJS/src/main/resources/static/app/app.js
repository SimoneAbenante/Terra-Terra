'use strict';

var tt = angular.module('terra&terra', ['ngRoute', 'ui.grid', 'ui.grid.selection', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav', 'ui.grid.resizeColumns']);

tt.config(function ($routeProvider) {
	$routeProvider
		.when("/waiter", {
			templateUrl: "views/waiter.html",
			controller: "waiterCtrl",
			controllerAs: "waiterController"
		})
		.when("/manager", {
			templateUrl: "views/manager.html",
			controller: "managerCtrl",
			controllerAs: "managerController"
		})
		.when("/kitchen", {
			templateUrl: "views/kitchen.html",
			controller: "kitchenCtrl",
			controllerAs: "kitchenController"
		})
		.otherwise('/');

});
