'use strict';

angular.module('terra&terra')
	.controller('managerCtrl', ['$scope', '$http', function ($scope, $http) {
		var self = this;

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
			]/*,
	data: [
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
	]*/
		};

		$http.get("api/dishes/")
			.then(function (response) {
				console.log(response.data);
				var data = response.data;

				data.forEach(element => {
					element.quantity = 0;
				});

				self.gridOptions.data = data;
			}, function (error) {
				alert(JSON.stringify(error));
			});

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

			gridApi.rowEdit.on.saveRow($scope, self.saveRow);
		};

		self.saveRow = function (rowEntity) {
			var promise = $http.post("dishes/", rowEntity);

			// TODO da verificare
			promise.resolve();

			$scope.gridApi.rowEdit.setSavePromise(rowEntity, promise.promise);
		};

		self.delete = function () {
			console.log("delete", selectedRow);

			selectedRow.forEach(function (element) {

				var i = self.gridOptions.data.findIndex(function (row) {
					return row.id == element;
				});

				var j = self.gridOptions.data.find(function (row) {
					return row.id == element;
				});;

				$http.delete("dishes/delete", { params: { id: j.id } })
					.then(function (response) {
						alert("Piatto cancellato");
					});

				self.gridOptions.data.splice(i, 1);
			});

			selectedRow = [];
		};

		self.diningTables = [
			{
				"id": 1,
				"size": 5,
				"status": 3
			},
			{
				"id": 2,
				"size": 6,
				"status": 3
			},
			{
				"id": 3,
				"size": 4,
				"status": 1
			},
			{
				"id": 4,
				"size": 10,
				"status": 3
			},
			{
				"id": 5,
				"size": 8,
				"status": 3
			}
		];

	}]);
