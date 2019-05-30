'use strict';

angular.module('terra&terra')
	.controller('kitchenCtrl', ['$http', '$q', function ($http, $q) {
		var self = this;

		self.gridOptions = {
			enableRowSelection: true,
			enableRowHeaderSelection: true,
			multiSelect: true,
			enableSelectAll: true,
			columnDefs: [
				{
					name: 'id_diningTable',
					displayName: 'Tavolo'
				},
				{
					name: 'dishName',
					displayName: 'Piatto'
				},
				{
					name: 'actions',
					displayName: 'Azioni',
					cellTemplate: '<button ng-if="row.entity.status==2" ng-click="grid.appScope.kitchenController.setStatus(row.entity)" type="button" class="btn btn-outline-dark btn-sm">Pronto</button>'+
									  '<button ng-if="row.entity.status==1" ng-click="grid.appScope.kitchenController.setStatus(row.entity)" type="button" class="btn btn-outline-dark btn-sm">Prendi in carica</button>'+
									  '<button ng-if="row.entity.status==3" disabled ng-click="grid.appScope.kitchenController.setStatus(row.entity)" type="button" class="btn btn-outline-dark btn-sm">Da servire</button>',
					cellClass: 'ui-grid-center-ar',
				}
			]
		};

		self.jobs = [];
		self.data = [];
		self.dishes = [];
		self.statuses = [];

		$http.get("api/dishes/")
			.then(function success(response) {
				self.dishes=response.data;
			}, function error(response) {

			});		

		$http.get("api/statuses/")
			.then(function success(response) {
				self.statuses=response.data;
			});

		$http.get("api/jobs/")
			.then(function success(response) {
				self.jobs=response.data;

				self.jobs.forEach(function (job, index) {
					self.data[index] = {};
					self.data[index].id = job.id;
					self.data[index].id_diningTable = job.id_diningTable;
		
					self.data[index].dishName = self.dishes.find(function (dish) {
						return dish.id == job.id_dish;
					}).name;
		
					self.data[index].status = job.id_status;
				});
			});

		self.gridOptions.data = self.data;

		//console.log(self.gridOptions.data);

		self.setStatus = function(rowEntity) {
			var dataElement = self.gridOptions.data.find(function (element) {
				return element.id==rowEntity.id;
			});

			if (rowEntity.status==1) {
				dataElement.status=2;
			} else if (rowEntity.status==2) {
				dataElement.status=3;
			}
		}

	}]);
