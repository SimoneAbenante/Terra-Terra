'use strict';

angular.module('terra&terra')
	.controller('waiterController', ['$scope', '$http', function($scope, $http) {
		var self=this;
		
		self.gridOptions= {
			enableRowSelection: false,
			enableRowHeaderSelection: false,
			rowHeight: 35,
			columnDefs: [
				/*{
					name: 'id',
					visible: false
				},*/
				{
					name: 'plate',
					displayName: 'Piatto'
				},
				{
					name: 'price',
					displayName: 'Prezzo'
				},
				{
					name: 'quantity',
					displayName: '#'
				},
				{
					name: 'add',
					displayName: 'Aggiungi',
					cellTemplate: '<button class="btn btn-outline-secondary" ng-click="grid.appScope.waiterController.add(row.entity.id)">+</button>'
				},
				{
					name: 'remove',
					displayName: 'Rimuovi',
					cellTemplate: '<button class="btn btn-outline-secondary">-</button>'
				}
			]
		};

		self.gridOptions.data= [
			{
				id: "01",
				plate: "Pasta",
				price: 10.00,
				quantity: 0
			},
			{
				id: "02",
				plate: "Secondi",
				price: 15.00,
				quantity: 0
			},
			{
				id: "03",
				plate: "Dolci",
				price: 5.00,
				quantity: 0
			}
		];
/*
		self.gridOptions.onRegisterApi=function(gridApi) {
			self.gridApi=gridApi;
			gridApi.selection.on.rowSelectionChanged($scope, callbackFn);
		};*/

		 var map=new Map([["01", 0], ["02", 0], ["03", 0]]);

		console.log(map);

		self.add=function(row) {
			var count=map.get(row.toString());
			count++;
			map.set(count);
			console.log(map);
		}
	}]);