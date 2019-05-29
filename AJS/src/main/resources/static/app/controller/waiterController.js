'use strict';

angular.module('terra&terra')
.controller('waiterCtrl', ['$http', function($http) {
	var self=this;

	self.gridOptions= {
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
		],
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
		]
	};

/*$http.get("dishes")
.then(function(response) {
//			console.log(response.data);
var data=response.data;

data.forEach(element => {
element.quantity=0;
});

self.gridOptions.data=data;
}, function(error) {

});*/

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

	if (self.diningTablesModel === undefined) {
		alert("Nessun tavolo selezionato!");
	} else if (list.length==0) {
		//self.error=true;
		alert("Nessun piatto selezionato!");
	}

	console.log(self.diningTablesModel);
};

}]);
