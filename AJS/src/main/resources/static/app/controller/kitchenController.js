'use strict';

angular.module('terra&terra')
	.controller('kitchenCtrl', ['$http', 'Pubnub', '$scope', function ($http, Pubnub, $scope) {
		var self = this;

		/*$scope.subscribe = function () {
			Pubnub.subscribe({
				channel: 'awesomeChannel',
				triggerEvents: ['message']
			})
		};*/

		// Subscribe to a channel
		Pubnub.addListener({
			status: function (statusEvent) {
				if (statusEvent.category === "PNUnknownCategory") {
					var newState = {
						new: 'error'
					};
					Pubnub.setState(
						{
							state: newState
						},
						function (status) {
							console.log(statusEvent.errorData.message)
						}
					);
				}
			},
			message: function (message) {
				console.log(message.message);
			}
		})

		Pubnub.subscribe({
			channels: ['awesomeChannel'],
			triggerEvents: ['message']
		});

		$scope.$on(Pubnub.getMessageEventNameFor('awesomeChannel'), function (ngEvent, envelope) {
			$scope.$apply(function () {
				console.log("envelope", envelope.message);
				self.gridOptions.data.push(envelope.message);
			});
	  });

		$scope.msg={};

		self.addJob = function(msg) {
			$scope.msg = msg;
			console.log($scope.msg);
		}

		self.data = [];

		self.gridOptions = {
			data: 'data',
			enableRowSelection: true,
			enableRowHeaderSelection: true,
			multiSelect: true,
			enableSelectAll: true,
			columnDefs: [
				{
					name: 'diningTable',
					displayName: 'Tavolo'
				},
				{
					name: 'dishName',
					displayName: 'Piatto'
				},
				{
					name: 'actions',
					displayName: 'Azioni',
					cellTemplate: '<button ng-if="row.entity.status==2" ng-click="grid.appScope.kitchenController.setStatus(row.entity)" type="button" class="btn btn-outline-dark btn-sm">Pronto</button>' +
						'<button ng-if="row.entity.status==1" ng-click="grid.appScope.kitchenController.setStatus(row.entity)" type="button" class="btn btn-outline-dark btn-sm">Prendi in carico</button>' +
						'<button ng-if="row.entity.status==3" disabled ng-click="grid.appScope.kitchenController.setStatus(row.entity)" type="button" class="btn btn-outline-dark btn-sm">Da servire</button>',
					cellClass: 'ui-grid-center-ar',
				}
			]
		};

		self.jobs = [];
		
		self.dishes = [];
		self.statuses = [];

		$http.get("api/statuses/")
			.then(function success(response) {
				self.statuses = response.data;
			});

		$http.get("api/jobs/")
			.then(function success(response) {
				self.jobs = response.data;
				createData();
			});

		self.gridOptions.data = self.data;

		console.log(self.gridOptions.data);

		var createData = function () {
			$http.get("api/dishes/")
				.then(function success(response) {
					self.dishes = response.data;

					self.jobs.forEach(function (job, index) {
						self.data[index] = {};

						self.data[index].job = job.id;
						self.data[index].bill = job.idBill;
						self.data[index].diningTable = job.idDiningTable;
						self.data[index].dish = job.idDish;
						self.data[index].dishName = self.dishes.find(function (dish) {
							return dish.id == job.idDish;
						}).name;

						self.data[index].status = job.idStatus;
					});
				}, function error(response) {

				});
		};

		self.setStatus = function (rowEntity) {

			if (rowEntity.status == 1) {
				rowEntity.status = 2;
			} else if (rowEntity.status == 2) {
				rowEntity.status = 3;
			}

			let jobToUpdate = {
				id: rowEntity.job,
				idBill: rowEntity.bill,
				idDiningTable: rowEntity.diningTable,
				idDish: rowEntity.dish,
				idStatus: rowEntity.status
			};

			console.log("jobToUpdate", jobToUpdate);

			$http.post("api/jobs/", jobToUpdate)
				.then(function success(response) {
					console.log("response data", response.data);

				}, function error(response) {

				});
		}

	}]);
