'use strict';

angular.module('terra&terra')
	.controller('kitchenCtrl', ['$http', function ($http) {
		var self = this;
		self.action='Prendi in carica';

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

		self.jobs = [
			{
				"id": 8,
				"id_bill": 6,
				"id_diningTable": 3,
				"id_dish": 1,
				"id_status": 3
			},
			{
				"id": 9,
				"id_bill": 7,
				"id_diningTable": 1,
				"id_dish": 2,
				"id_status": 3
			},
			{
				"id": 10,
				"id_bill": 8,
				"id_diningTable": 1,
				"id_dish": 3,
				"id_status": 1
			}
		];

		self.dishes = [
			{
				"id": 1,
				"name": "penne al sugo",
				"price": 10
			},
			{
				"id": 2,
				"name": "lasagna",
				"price": 12
			},
			{
				"id": 3,
				"name": "filetto di manzo",
				"price": 10
			},
			{
				"id": 5,
				"name": "Gnocchi",
				"price": 5
			}
		];

		self.statuses = [
		  {
		    "status": "In Lavorazione",
		    "id": 2
		  },
		  {
		    "status": "Non Pronto",
		    "id": 1
		  },
		  {
		    "status": "Pronto",
		    "id": 3
		  }
		];

		self.data = [];

		self.jobs.forEach(function (job, index) {
			self.data[index] = {};
			self.data[index].id = job.id;
			self.data[index].id_diningTable = job.id_diningTable;

			self.data[index].dishName = self.dishes.find(function (dish) {
				return dish.id == job.id_dish;
			}).name;

			self.data[index].status = job.id_status;
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
