(function(){
	'use strict';
	angular.module('kharchApp').factory('DashboardService',['ExpenditureService', '$q', 'LookupTypeService', dashboardService]);
	
		function dashboardService(expenditureService, $q, lookupTypeService) {
			
			var arrangeRowBasedOnHeaderOrder = function(header, row){
				var result = {};
				result.date = row.date;
				result.expTypes = [];
				for(var i=0; i< header.length; i++){
					var expTypeObj = row.expType[header[i]];
					if(expTypeObj){
						result.expTypes.push(expTypeObj);
					} else {
						result.expTypes.push({});
					}
					
				}
				return result;
			};
			
			var arrangeBodyBasedOnHeaderOrder = function(header, searchResult){
				var rows = [];
				for(var i=0; i< searchResult.length; i++){
					rows.push(arrangeRowBasedOnHeaderOrder(header, searchResult[i]));
				}
				return rows;
			};
			
			var populateFooterBasedOnHeader = function(header, searchResult){
				var footer = [];
				for(var j=0; j< header.length; j++){
					var footerObj = {};
					footerObj.type = header[j];
					footerObj.amount = calculateFooterTotal(header[j], searchResult);
					footer.push(footerObj);
				}
				return footer;
			};
			
			var calculateFooterTotal = function(headerType,searchResult){
				var amount =  0;
				for(var i=0; i< searchResult.length; i++){
					amount += searchResult[i].expType[headerType] ? searchResult[i].expType[headerType].amount : 0;
				}
				return amount;
			}

			var gridData = function(username){
				var grid={};
				var deffered = $q.defer();
				$q.all([lookupTypeService.getExpenditureTypes(),
						expenditureService.searchAllExpenditureByUserName(username)]).then(function(response){
							grid.header = response[0].data;
							console.log(grid.header);
							grid.body = arrangeBodyBasedOnHeaderOrder(grid.header, response[1]);
							console.log(grid.body);
							grid.footer = populateFooterBasedOnHeader(grid.header, response[1]);
							console.log(grid.footer);
							deffered.resolve(grid);
					  },function(error){
						  deffered.reject(error);
					  });
				return deffered.promise;
			}
			
			return {
				gridData : gridData
				
			};
		}
	 })();   