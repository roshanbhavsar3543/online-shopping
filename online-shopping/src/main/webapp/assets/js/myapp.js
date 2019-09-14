$(function(){	
	switch(menu){
		
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		
		break;	
	default :
		if(menu=='Home'){
			$('#home').addClass('active');
			break;
		}
	
			$('#listProducts').addClass('active');
			$('#a_'+menu).addClass('active');
		break;
	}
	
	//to tackle CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if(typeof  token!== 'undefined' && typeof  header!== 'undefined' ){
		if(token.length>0 && header.length>0){
			//set the token header in ajax
			$(document).ajaxSend(function(e,xhr,options){
				xhr.setRequestHeader(header,token);
			});
		}		
	}
	
	//Code For data Table Product Active
	var $table = $('#productListTable');
	
	// execute the below code only where we have this table
	if($table.length) {
		//console.log('Inside the table!');
		var jsonURL='';
		if(window.categoryId==''){
			jsonURL= window.contextRoot+'/json/data/all/products';
		}else{
			jsonURL= window.contextRoot+'/json/data/category/'+window.categoryId+'/products';
		}
			
		$table.DataTable({
			lengthMenu:[[3,5,10,-1],['3 Records','5 Records','10 Records','All Records']],
			pageLength:5,
			ajax:{
				url:jsonURL,
				dataSrc:''
			},
			columns:[
				{
					data:'code',
					mRender:function(data,type,row){						
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
					}
				},
				{
					data:'name'					
				},
				{
					data:'brand'					
				},
				{
					data:'unitPrice',
					mRender:function(data,type,row){
						return '&#8377; '+data;
					}
				},
				{
					data:'quantity',
					mRender:function(data,type,row){
						if(data<1){
							return '<span style="color:red">Out of Stock</span>';
						}
						return data;
					}
				}	,
				{

		        	  data: 'id',
		        	  bSortable: false,
		        	  mRender: function(data, type, row) {
		        		  var str = '';
		        		  str += '<a href="'+window.contextRoot+ '/show/'+data+'/product" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-eye-open"></span> </a> &#160;';
		        		  
		        		 if(row.quantity<1){
		        			 str += '<a href="javaScript:void(0)" class="btn btn-success disabled"> <strike> <span class="glyphicon glyphicon-shopping-cart"></span> </strike> </a>';
		        		 }else{
		        			 str += '<a href="'+window.contextRoot+ '/cart/add/'+data+'/product" class="btn btn-success btn-sm"> <span class="glyphicon glyphicon-shopping-cart"> </span> </a>';
		        		 } 		   
		        		 return str;
					}
				}			
			]
			
		});
	}
	
	//dismissing the alert after 3 seconds
	var $alert = $('.alert');
	if($alert.length){
		setTimeout(function(){
			$alert.fadeOut('slow');
		},3000);
	}
	
	//Start -> Switch To activate and Deactivate Product 
	
	
	
	//End -> Switch To activate and Deactivate Product 
	
	// Start -> data  table for admin
	var $adminProdutsTable = $('#adminProductsTable');//id of the table
	
	// execute the below code only where we have this table
	if($adminProdutsTable.length) {
		//console.log('Inside the table!');
		var jsonURL=window.contextRoot+'/json/data/admin/all/products';
					
		$adminProdutsTable.DataTable({
			lengthMenu:[[10,30,50,-1],['10 Records','30 Records','50 Records','All Records']],
			pageLength:30,
			ajax:{
				url:jsonURL,
				dataSrc:''
			},//Sequence of column depends on table sequence
			columns:[
				{
					data:'id'
				},	
				
				{
					data:'code',
					bSortable:false,
					mRender:function(data,type,row){						
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg"/>';
					}
				},
				{
					data:'name'					
				},
				{
					data:'brand'					
				},
								{
					data:'quantity',
					mRender:function(data,type,row){
						if(data<1){
							return '<span style="color:red">Out of Stock</span>';
						}
						return data;
					}
				}	,
				{
					data:'unitPrice',
					mRender:function(data,type,row){
						return '&#8377; '+data;
					}
				},
				{
	        	  data: 'active',
	        	  bSortable:false,
	        	  mRender:function(data,type,row){
	        		  var str='';
	        		  
	        		  str+='<label class="switch">';
	        		  if(data){
	        			  str+='<input type="checkbox" checked="checked" value="'+row.id+'"/>';
	        		  }else{
	        			  str+='<input type="checkbox" value="'+row.id+'"/>';
	        		  }	        		  
	        		  str+='<div class="slider round"></div></label>';	        		  
						return str;
					}		        	
				},
				{
					data:'id',
					bSortable:false,
					mRender:function(data,type,row){
						var str='';
						str+='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
						str+='<span class="glyphicon glyphicon-pencil"></span></a>';						
						return str;
					}
				}
			],
			 //We are writing below code because the pop up are not working
			// before fetching data the we are applying the toggle switch code So to avoid this use below code
			initComplete:function(){
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on('change', function() {
					var checkbox =$(this);
					var changedvalue = checkbox.prop('checked');
					var value = checkbox.prop('value'); 
					var messgae;
					if(changedvalue){
						messgae='Activate';
					}else{
						messgae='Deactivate';
					}
			        if (confirm('Are You Sure You Want To '+messgae+' Product?')) {     
			        	console.log('You will Perform Operations on '+value);
			        	var activationUrl = window.contextRoot + '/manage/product/' + value + '/activation';
			        	$.post(activationUrl,function(data){
			        		alert(data);
			        	});
			        }else{			        	
			        	checkbox.prop('checked',!changedvalue);
			        }
				});
			}
		});
	}
	// End -> data  table for admin
	
	//Start Client Side Validation  category Form Validation
	var $categoryForm = $('#categoryForm');
	if($categoryForm.length){
		$categoryForm.validate({
			rules:{
				/*field name*/
				name :{
					required: true,
					minlength:2
				},
				
				description :{
					required: true
				}
			},
			messages:{
				name :{
					required: 'Please Add Category Name !',
					minlength:'Category Name Should not be less than 2 characters.'
				},
				description :{
					required: 'Please Add Description of Category'
				}				
			},
			errorElement:'em',
			errorPlacement:function(error,element){
				error.addClass('help-block');
				error.insertAfter(element);
			}			
		});		
	}
	//End Client Side Validation  category Form Validation
	
	
	//Start Client Side Validation  Login Form 
	var $loginForm = $('#loginForm');
	if($loginForm.length){
		$loginForm.validate({
			rules:{
				/*field name*/
				username :{
					required: true,
					email:true
				},
				
				password :{
					required: true
				}
			},
			messages:{
				username :{
					required: 'Please Enter the Username !',
					email:'Please Enter Valid Email Address'
				},
				password :{
					required: 'Please Enter the Password'
				}				
			},
			errorElement:'em',
			errorPlacement:function(error,element){
				error.addClass('help-block');
				error.insertAfter(element);
			}			
		});		
	}
	//End Client Side Validation  Login Form 
	
});

