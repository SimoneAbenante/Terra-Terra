'use strict';

var tt=angular.module('terra&terra', ['ngRoute', 'ui.grid', 'ui.grid.selection', 'ui.grid.edit']);

tt.config(function($routeProvider) {
		$routeProvider
			.when("/waiter", {
				templateUrl : "/views/waiter.html",
				controller : "waiterController",
				controllerAs: "waiterController",
			})
			.when("/manager", {
				templateUrl: "/views/manager.html",
				controller: "managerCtrl",
				controllerAs: "managerController"
			})
			.when("/kitchen", {
				templateUrl: "/views/kitchen.html",
				controller: "kitchenCtrl",
				controllerAs: "kitchenController"
			})
			.otherwise('/');
		
});
