'use strict';

angular.module('terra&terra')
	.controller('waiterCtrl', ['$http', 'Pubnub', '$scope','$rootScope', function ($http, Pubnub, $scope, $rootScope) {
		var self = this;

		/*self.pub = function() {
			Pubnub.publish(
				{
					 channel: 'awesomeChannel',
					 message: '{"size": 3}'
				}, 
				function(status, response){
					 console.log(response);
				}
		  );
		};

		$rootScope.$on(Pubnub.getMessageEventNameFor('awesomeChannel'), function (ngEvent, envelope) {
			$scope.$apply(function () {
				// add message to the messages list
				$scope.chatMessages.unshift(envelope.message);
			});
		});

		$rootScope.$on(Pubnub.getEventNameFor('publish', 'callback'), function (ngEvent, status, response) {
			$scope.$apply(function () {
				if (status.error) {
					$scope.statusSentSuccessfully = false;
				} else {
					$scope.statusSentSuccessfully = true;
				}
			})
		});*/

		self.gridOptions = {
			enableRowSelection: false,
			enableRowHeaderSelection: false,
			enableFiltering: true,
			columnDefs: [
				{
					name: 'name',
					displayName: 'Piatto',
					minWidth: 150,
					width: 200
				},
				{
					name: 'price',
					displayName: 'Prezzo',
					enableFiltering: false,
					cellFilter: 'currency: "€ "',
					minWidth: 20,
					width: '20%'
				},
				{
					name: 'quantity',
					displayName: '#',
					enableFiltering: false,
				},
				{
					name: 'add',
					displayName: '',
					cellTemplate: '<i class="far fa-plus-square fa-vh-alignment" ng-click="grid.appScope.waiterController.add(row.entity.id)"></i>',
					cellClass: 'ui-grid-center-ar',
					enableFiltering: false
				},
				{
					name: 'remove',
					displayName: '',
					cellTemplate: '<i class="far fa-minus-square fa-vh-alignment" ng-click="grid.appScope.waiterController.remove(row.entity.id)" style="cursor: pointer;"></i>',
					cellClass: 'ui-grid-center-ar',
					enableFiltering: false
				}
			]
		};

		var dishes = [];

		$http.get("api/dishes/")
			.then(function (response) {
				//			console.log(response.data);
				let data = response.data;
				dishes = response.data;

				data.forEach(element => {
					element.quantity = 0;
				});

				self.gridOptions.data = data;
			}, function (error) {

			});

		self.diningTables = [];

		$http.get("api/tables/")
			.then(function success(response) {
				self.diningTables = response.data;
			});

		self.add = function (id) {
			var i = self.gridOptions.data.find(function (element) {
				return element.id == id;
			});

			i.quantity++;
		};

		self.remove = function (id) {
			var i = self.gridOptions.data.find(function (element) {
				return element.id == id;
			});

			if (i.quantity == 0)
				return;

			i.quantity--;
		};

		self.send = function () {
			var list = [];

			self.gridOptions.data.forEach(element => {
				if (element.quantity > 0)
					list.push(element);
			});

			if (self.diningTablesModel === undefined) {
				alert("Nessun tavolo selezionato!");
			} else if (list.length == 0) {
				//self.error=true;
				alert("Nessun piatto selezionato!");
			} else {
				console.log(list);

				$http.post("api/jobs/params", { params: { idBill: userID } })
					.then(function success(response) {

					});
			}
		};

		self.gridOptionsDishes = {
			enableRowSelection: false,
			enableRowHeaderSelection: false,
			columnDefs: [
				{
					name: 'name',
					displayName: 'Piatto',
					minWidth: 150,
					width: 200
				},
				{
					name: 'table',
					displayName: 'Tavolo'
				},
				{
					name: 'action',
					displayName: 'Servito',
					cellTemplate: '<div class="form-check"><input class="form-check-input"' +
						' type="checkbox" id="servito"></div>',
					//cellClass: 'ui-grid-center-ar'
				}
			]
		};

		self.change = function (rowEntity) {

			console.log(rowEntity);

			if (rowEntity.served) {
				/*let result=confirm("Il piatto risulta già servito. Impostarlo come 'Non servito'?");

				if (result)
					rowEntity.served=false;
				else
					rowEntity.served=true;*/
				rowEntity.served = false;
				alert("Piatto impostato a non servito");
			} else {
				rowEntity.served = true;
				alert("Piatto impostato a servito");
			}

		}

		self.jobs = [];
		self.statuses = [];
		var data = [];

		$http.get("api/jobs/")
			.then(function success(response) {
				self.jobs = response.data;

				self.jobs.forEach(function (job, index) {
					let rowEntity = {};

					rowEntity.index = index;

					rowEntity.name = dishes.find(function (dish) {
						return job.idDish == dish.id;
					}).name;

					rowEntity.table = job.idDiningTable;
					rowEntity.served = true;

					data.push(rowEntity);
				});
			});

		$http.get("api/statuses/")
			.then(function success(response) {
				self.statuses = response.data;
			});

		self.gridOptionsDishes.data = data;
		self.dishModel = data;

	}]);
