<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="row">
		<c:if test="${not empty message}">
			<div class="col-xs-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>
		</c:if>
		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Product Management</h4>
				</div>
				<div class="panel-body">
				
					<!-- FORM TO ADD PRODUCT -->
					<!-- modelAttribute name should be same as that of  in ManagementController->showManagedProducts() -->
					<sf:form class="form-horizontal" modelAttribute="product" 
								action="${contextRoot}/manage/products" method="POST" 
								enctype="multipart/form-data">  <!-- enctype is added for multipart file upload -->
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Enter Product Name: </label>
							<div class="col-md-8">
								<!--if we use modelAttribute then repalce name attribute of input to path-->
								<sf:input type="text"  path="name" id="name" placeholder="Product Name" class="form-control"/>
								<!-- <em class="help-block">Please Enter Product Name!</em> -->
								<sf:errors cssClass="help-block" path="name" element="em"></sf:errors>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="brand">Enter Brand Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand" placeholder="Brand Name" class="form-control"/>
								<sf:errors cssClass="help-block" path="brand" element="em"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Product Description: </label>
							<div class="col-md-8">
								<sf:textarea rows="4" placeholder="Write a description for the product!" class="form-control" path="description"/>
								<sf:errors cssClass="help-block" path="description" element="em"></sf:errors>
							</div>
						</div>
						<div class="form-group">
       						<label class="control-label col-md-4" for="unitPrice">Enter Unit Price: </label>
      						<div class="col-md-8">
        						<sf:input type="number" path="unitPrice" id="unitPrice" placeholder="Unit Price In &#8377;" class="form-control"/>
        						<sf:errors cssClass="help-block" path="unitPrice" element="em"/>
					        </div>
					    </div>
					    <div class="form-group">
       						<label class="control-label col-md-4" for="quantity">Quantity Available: </label>
      						<div class="col-md-8">
        						<sf:input type="number" path="quantity" id="quantity" placeholder="Quantity Available" class="form-control"/>
       						</div>
     					</div>  
     					<!-- FILE (image for product) -->
     					<div class="form-group">
       						<label class="control-label col-md-4" for="file">Upload File: </label>
      						<div class="col-md-8">
        						<sf:input type="file" path="file" id="file" class="form-control"/>
        						<sf:errors cssClass="help-block" path="file" element="em"/>
       						</div>
     					</div>   
     					 					
     					<div class="form-group">
       						<label class="control-label col-md-4" for="categoryId">Select Category: </label>
      						<div class="col-md-8">
       							 <sf:select class="form-control" id="categoryId" path="categoryId" 
							         items="${categories}"
							         itemLabel="name"
							         itemValue="id"
							        />  
							       <c:if test="${product.id == 0}">
							        	<div class="text-right">
								        	<br/>
								        	<button type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning btn-xs">Add Category</button>
							        	</div>
							        </c:if>    
       						</div>
      					</div>	
      										
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" id="submit" value="Submit" class="btn btn-primary"/>
								<sf:hidden path="id"/>
								<sf:hidden path="code"/>
								<sf:hidden path="supplierId"/>
								<sf:hidden path="purchases"/>
								<sf:hidden path="active"/>
								<sf:hidden path="views"/>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
	  <div class="col-xs-12">
	   <h3>Available Products</h3>
	   <hr/>
	  </div>  
	  <div class="col-xs-12">
	  	<div class="container-fluid">  		
	  		<div class="table-responsive">
			     <!-- Products table for Admin -->
			    <table id="adminProductsTable" class="table table-striped table-bordered">
			     <thead>
			      <tr>
			       <th>Id</th>
			       <th>&#160;</th>
			       <th>Name</th>
			       <th>Brand</th>
			       <th>Quantity</th>
			       <th>Unit Price</th>
			       <th>Active</th>
			       <th>Edit</th>        
			      </tr>
			     </thead>
			     <!-- <tbody>
			     Code will be added by data table
			     </tbody> -->
			     <tfoot>
				     <tr>
				     <th>Id</th>
				     <th>&#160;</th>
				     <th>Name</th>
				     <th>Brand</th>
				     <th>Quantity</th>
				     <th>Unit Price</th>
				     <th>Active</th>
				     <th>Edit</th>        
				    </tr>
			     </tfoot>    
			    </table>   		
	  		</div>  	
	  	</div>  
 	 </div>
 	</div>
	
	<!-- Below Div is only visible in case add category -> please see Id of below div -->
	<!-- Modal -->
	<div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
 	  <div class="modal-dialog" role="document">
 		<div class="modal-content">
 			<!-- Modal Header -->
 			<div class="modal-header">
 			<h4 class="modal-title"> Add New Category</h4>
 				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button> 				 				
 			</div>
 			<div class="modal-body">
 				<!-- Category Form -->
 				<sf:form id="categoryForm" class="form-horizontal" modelAttribute="category"
 				action="${contextRoot}/manage/category" method="POST" >
 					<div class="form-group">
 						<label for="category_name" class="control-label col-md-4">Category Name:</label>
 						<div class="col-md-8">
 						<!-- Path should be equal to category class field name -->
 							<sf:input path="name" id="category_name" type="text" class="form-control"/>
 						</div>
 					</div>
 					<div class="form-group">
 						<label for="category_description" class="control-label col-md-4">Category Description:</label>
 						<div class="col-md-8">
 						<!-- Path should be equal to category class field name -->
 							<sf:textarea cols="" rows="5" path="description" id="category_description" type="text" class="form-control"/>
 						</div>
 					</div>
 					<div class="form-group">
 						<div class="col-md-offset-4 col-md-8">
 							<input type="submit" value="Add Category" class="btn btn-primary"/>
 						</div>
 					</div>
 				</sf:form> 				
 			</div>
		 </div> 		
		</div>		 
	</div> 
</div>
