//一级类目新增初始化
function initSaveFirstCategory(firstCategory, secondCategory) {
	$('#' + firstCategory).combobox({
	    url: host + '/category/findByPid?parentId=' + 0,
	    method:'get',
		valueField:'id',
		textField:'categoryName',
		panelHeight:'auto',
		label: '一级分类:',
		labelPosition: 'left',
		required:false,
		labelWidth:'70px',
		onLoadSuccess: function() {
			$('#' + secondCategory).combobox('disable');
		},
		onSelect: function(param) {
			$('#' + secondCategory).combobox('reload', host + '/category/findByPid?parentId=' + param.id);
			$('#' + secondCategory).combobox('enable');
		}
	});
}			
//二级类目新增初始化
function initSaveSecondCategory(secondCategory) { 
	$('#' + secondCategory).combobox({
	    url: '',
	    method:'get',
		valueField:'id',
		textField:'categoryName',
		panelHeight:'auto',
		label: '二级分类:',
		labelPosition: 'left',
		required:false,
		labelWidth:'70px'
	});
}

//一级类目编辑初始化
function initEditFirstCategory(firstCategory, secondCategory, data) {
	$('#' + firstCategory).combobox({
	    url: host + '/category/findByPid?parentId=' + 0,
	    method:'get',
		valueField:'id',
		textField:'categoryName',
		panelHeight:'auto',
		label: '一级分类:',
		labelPosition: 'left',
		required:false,
		labelWidth:'70px',
		onLoadSuccess: function() {
			//设置一级分类选中的值
			$('#' + firstCategory).combobox('select', data.category.parentId);
			//设置二级分类选中的值
			$('#' + secondCategory).combobox('select', data.category.id);
			$('#' + secondCategory).combobox('disable');
		},
		onSelect: function(param) {
			$('#' + secondCategory).combobox('reload', host + '/category/findByPid?parentId=' + param.id);
			$('#' + secondCategory).combobox('enable');
		}
	});
}

//二级类目编辑初始化
function initEditSecondCategory(secondCategory, data) {
	$('#' + secondCategory).combobox({
	    url: '',
	    method:'get',
		valueField:'id',
		textField:'categoryName',
		panelHeight:'auto',
		label: '二级分类:',
		labelPosition: 'left',
		required:false,
		labelWidth:'70px',
		onLoadSuccess: function() {
	        var data= $('#' + secondCategory).combobox("getData");
                if (data.length > 0) {
                    $('#' + secondCategory).combobox('select', data[0].id);
                }
		}
	});
}