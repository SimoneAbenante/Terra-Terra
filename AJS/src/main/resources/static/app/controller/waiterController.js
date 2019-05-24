'use strict';

angular.module('terra&terra')
	.controller('waiterCtrl', ['$http', function($http) {
		var self=this;
		
		self.gridOptions= {
			enableRowSelection: false,
			enableRowHeaderSelection: false,
			enableFiltering: true,
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
					displayName: 'Prezzo',
					enableFiltering: false,
					cellFilter: 'currency: "€ "'
				},
				{
					name: 'quantity',
					displayName: '#',
					enableFiltering: false
				},
				{
					name: 'add',
					displayName: 'Aggiungi',
					cellTemplate: '<i class="far fa-plus-square fa-vh-alignment" ng-click="grid.appScope.waiterController.add(row.entity.id)"></i>',
					cellClass: 'ui-grid-center-ar',
					enableFiltering: false
				},
				{
					name: 'remove',
					displayName: 'Rimuovi',
					cellTemplate: '<i class="far fa-minus-square fa-vh-alignment" ng-click="grid.appScope.waiterController.remove(row.entity.id)" style="cursor: pointer;"></i>',
					cellClass: 'ui-grid-center-ar',
					enableFiltering: false
				}
			]
		};

		$http.get("dishes")
		.then(function(response) {
			console.log(response.data);
		}, function(error) {
			alert(JSON.stringify(error));
		});

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

		self.add=function(id) {
			var i=self.gridOptions.data.find(function(element) {
				return element.id==id;
			});

			i.quantity++;
		};

		self.remove=function(id) {
			var i=self.gridOptions.data.find(function(element) {
				return element.id==id;
			});

			if (i.quantity==0)
				return;

			i.quantity--;
		};

		self.send=function() {
			var list=[];

			self.gridOptions.data.forEach(element => {
				if (element.quantity>0)
					list.push(element.id);
			});

			if (list.length==0)
				//self.error=true;
				alert("Nessun piatto selezionato!");

			console.log("invio ordine", list);
		};

	}]);