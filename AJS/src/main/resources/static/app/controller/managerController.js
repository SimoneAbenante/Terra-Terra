'use strict';

angular.module('terra&terra')
	.controller('managerCtrl', ['$scope', '$http', function ($scope, $http) {
		var self = this;

		$http.get("dishes")
		.then(function(response) {
			console.log(response.data);
			var data=response.data;

			data.forEach(element => {
				element.quantity=0;
			});

			self.gridOptions.data=data;
		}, function(error) {
			
		});

		self.gridOptions = {
			enableRowSelection: true,
			enableRowHeaderSelection: true,
			multiSelect: true,
			enableSelectAll: true,
			columnDefs: [
				{
					name: 'name',
					displayName: 'Piatto'
				},
				{
					name: 'price',
					displayName: 'Prezzo',
					cellFilter: 'currency: "€ "'
				}
			],
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

		var selectedRow = [];

		self.gridOptions.onRegisterApi = function (gridApi) {
			$scope.gridApi = gridApi;

			gridApi.selection.on.rowSelectionChanged($scope, function (row) {
				if (row.isSelected)
					selectedRow.push(row.entity.id);

				else {
					var i = selectedRow.indexOf(row.entity.id);
					selectedRow.splice(i, 1);
				}

				console.log(selectedRow);
			});

			gridApi.selection.on.rowSelectionChangedBatch($scope, function (rows) {
				if (rows[0].isSelected) {
					rows.forEach(function (element) {
						selectedRow.push(element.entity.id);
					});
				}

				else {
					selectedRow = [];
				}
			});

		};

		self.delete = function () {
			console.log("delete", selectedRow);

			selectedRow.forEach(function (element) {

				var i = self.gridOptions.data.findIndex(function (row) {
					return row.id == element;
				});

				self.gridOptions.data.splice(i, 1);
			});

			selectedRow = [];
		};
	}]);