'use strict';

angular.module('terra&terra')
	.controller('managerCtrl', ['$scope', '$http', function ($scope, $http) {
		var self = this;

		
		self.dataList = [
			{
				id: "01",
				plate: "Pasta",
				price: 10.00,
			},
			{
				id: "02",
				plate: "Secondi",
				price: 15.00,
			},
			{
				id: "03",
				plate: "Dolci",
				price: 5.00,
			}
		];

		self.gridOptions = {
			enableRowSelection: true,
			enableRowHeaderSelection: true,
			multiSelect: true,
			enableSelectAll: true,
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
				}
			],
			data: self.dataList
		};

		self.add = function () {
			var n = self.gridOptions.data.length + 1;
			self.gridOptions.data.push({
				"id": "0" + n,
				"plate:": "",
				"price": 0.00
			});

			console.log(self.gridOptions.data);
		};

		self.delete = function () {
			self.gridOptions.selection.clearSelectedRows();
		};

		self.gridOptions.onRegisterApi = function (gridApi) {
			$scope.gridApi = gridApi;

			gridApi.selection.on.rowSelectionChangedBatch($scope, function (rows) {
				console.log(rows);
			});
		};

		console.log("gestione");
	}]);