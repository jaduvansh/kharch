(function() {
	'use strict';
	angular.module('kharchApp')
			.factory(
					'DashboardService',
					[ 'ExpenditureService', '$q', 'LookupTypeService','$window',
							dashboardService ]);

	function dashboardService(expenditureService, $q, lookupTypeService, $window) {

		var arrangeRowBasedOnHeaderOrder = function(header, row) {
			var result = {};
			result.date = row.date;
			result.expTypes = [];
			for (var i = 0; i < header.length; i++) {
				var expTypeObj = row.expType[header[i]];
				if (expTypeObj) {
					result.expTypes.push(expTypeObj);
				} else {
					result.expTypes.push({});
				}

			}
			return result;
		};

		var searchAllExpenditureByUserNameMonthAndYear = function(userName, month, year){
			expenditureService.searchAllExpenditureByUserNameMonthAndYear(userName, month, year);
		};
		
		var arrangeBodyBasedOnHeaderOrder = function(header, searchResult) {
			var rows = [];
			for (var i = 0; i < searchResult.length; i++) {
				rows
						.push(arrangeRowBasedOnHeaderOrder(header,
								searchResult[i]));
			}
			return rows;
		};

		var populateFooterBasedOnHeader = function(header, searchResult) {
			var footer = [];
			for (var j = 0; j < header.length; j++) {
				var footerObj = {};
				footerObj.type = header[j];
				footerObj.amount = calculateFooterTotal(header[j], searchResult);
				footer.push(footerObj);
			}
			return footer;
		};

		var calculateFooterTotal = function(headerType, searchResult) {
			var amount = 0;
			for (var i = 0; i < searchResult.length; i++) {
				amount += searchResult[i].expType[headerType] ? searchResult[i].expType[headerType].amount
						: 0;
			}
			return amount;
		}

		var gridData = function(groupName, month, year) {
			var grid = {};
			var deffered = $q.defer();
			$q
					.all(
							
							[
									lookupTypeService.getExpenditureTypes(),
									expenditureService
											.searchAllExpenditureByUserName(groupName, month, year) ])
					.then(
							function(response) {
								grid.header = response[0].data;
								console.log(grid.header);
								grid.body = arrangeBodyBasedOnHeaderOrder(
										grid.header, response[1]);
								console.log(grid.body);
								grid.footer = populateFooterBasedOnHeader(
										grid.header, response[1]);
								console.log(grid.footer);
								deffered.resolve(grid);
							}, function(error) {
								deffered.reject(error);
							});
			return deffered.promise;
		}

		var tableToExcel = function(tableId, worksheetName) {
			console.log("export");
			var uri = 'data:application/vnd.ms-excel;base64,', 
			template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>', 
			base64 = function(s) {
				return $window.btoa(unescape(encodeURIComponent(s)));
			}, format = function(s, c) {
				return s.replace(/{(\w+)}/g, function(m, p) {
					return c[p];
				})
			};
			
		     var table=$(tableId),
             ctx={worksheet:worksheetName,table:table.html()},
             href=uri+base64(format(template,ctx));
             return href;
		}
		return {
			gridData : gridData,
			tableToExcel : tableToExcel,
			searchAllExpenditureByUserNameMonthAndYear : searchAllExpenditureByUserNameMonthAndYear

		};
	}
})();