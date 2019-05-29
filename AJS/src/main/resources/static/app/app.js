'use strict';

var tt = angular.module('terra&terra', ['ngRoute', 'ui.grid', 'ui.grid.selection', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav', 'ui.grid.resizeColumns']);

tt.config(function ($routeProvider) {
	$routeProvider
		.when("/waiter", {
			templateUrl: "views/waiter/dish.html",
			controller: "waiterCtrl",
			controllerAs: "waiterController"
		})
		.when("/waiter/jobs", {
			templateUrl: "views/waiter/job.html",
			controller: "waiterCtrl",
			controllerAs: "waiterController"
		})
		.when("/waiter/reservations", {
			templateUrl: "views/waiter/reservation.html",
			controller: "waiterCtrl",
			controllerAs: "waiterController"
		})
		.when("/manager/reservations", {
			templateUrl: "views/manager/reservation.html",
			controller: "managerCtrl",
			controllerAs: "managerController"
		})
		.when("/manager/menu", {
			templateUrl: "views/manager/menu.html",
			controller: "managerCtrl",
			controllerAs: "managerController"
		})
		.when("/kitchen/jobs", {
			templateUrl: "views/kitchen/job.html",
			controller: "kitchenCtrl",
			controllerAs: "kitchenController"
		})
});
