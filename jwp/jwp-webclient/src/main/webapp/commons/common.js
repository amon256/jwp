if($.fn.bootstrapTable.defaults){
	$.extend($.fn.bootstrapTable.defaults,{
		cache: false,
		pagination: true,
		sidePagination: "server",
		search: true,
		showColumns: true,
        showPaginationSwitch: true,
        showRefresh: true,
        showToggle: true,
        queryParams: function(param){
        	if(param.search){
        		param['keyword'] = param.search;
        		delete param.search;
        	}
        	if(param.order){
        		param['order_'] = param.order;
        		delete param.order;
        	}
        	if(param.sort){
        		param['sort_'] = param.sort;
        		delete param.sort;
        	}
        	if(param.limit){
        		param['currentPage'] = Math.ceil(param.offset / param.limit) + 1;
        		param['pageSize'] = param.limit;
        		delete param.limit;
        		delete param.offset;
        	}
        	return param;
        },
        dataField: 'datas',
        totalField: 'recordCount'
	});
}
if($.fn.bootstrapTable.columnDefaults){
	$.extend($.fn.bootstrapTable.columnDefaults,{
		halign: "center"
	});
}