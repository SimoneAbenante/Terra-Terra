'use strict';

angular.module('terra&terra')
	.controller('managerCtrl', ['$scope', '$http', '$q', function ($scope, $http, $q) {
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
			self.gridOptions.data.push({
				"id": "0",
				"price": 0.00
			});

			console.log(self.gridOptions.data);
		};

		var selectedRow = [];

		self.gridOptions.onRegisterApi = function (gridApi) {
			self.gridApi = gridApi;

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

			/*gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
				console.log('edited row id:' + rowEntity.id + ' Column:' + colDef.name + ' newValue:' + newValue + ' oldValue:' + oldValue);
				//$scope.$apply();
			});*/

			gridApi.rowEdit.on.saveRow($scope, self.saveRow);
		};

		self.saveRow = function (rowEntity) {
			console.log(rowEntity);
			let saveData = {
				id: rowEntity.id,
				name: rowEntity.name,
				price: rowEntity.price
			};

			var promise = $q.defer();

			$http.post("api/dishes/", saveData)
				.then(function success(response) {
					console.log("piatto salvato", response.data);
					promise.resolve();
				}, function error(response) {
					promise.reject();
				});

			self.gridApi.rowEdit.setSavePromise(rowEntity, promise.promise);

		};

		self.delete = function () {
			//	console.log("delete", selectedRow);

			selectedRow.forEach(function (element) {

				var i = self.gridOptions.data.findIndex(function (row) {
					return row.id == element;
				});

				var j = self.gridOptions.data.find(function (row) {
					return row.id == element;
				});;

				$http.delete("api/dishes/" + j.id)
					.then(function success(response) {
						alert("Piatto cancellato");
					}, function error(response) {
						console.log(response.data);
					});

				self.gridOptions.data.splice(i, 1);
			});

			selectedRow = [];
		};

		self.diningTables = [];

		$http.get("api/tables/")
			.then(function success(response) {
				self.diningTables = response.data;
				console.log("tables", self.diningTables);
			});

	}]);
