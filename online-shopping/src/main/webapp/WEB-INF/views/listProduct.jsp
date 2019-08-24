</br></br>
<div class="container">
	<div class="row">
		<!-- To Disply SideBar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>
		<!-- <!-- To Disply Product -->
		<div class="col-md-9">
			<!-- Added Breadcrum Component -->
			<div class="row">
			
				<div class="col-lg-12">
					<c:if test="${userClickAllProducts == true}">
						<script> window.categoryId='';</script>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">All Products</li>
						</ol>
					</c:if>					
					<c:if test="${userClickCategoryProduct == true}">
						<script> window.categoryId='${category.id}';</script>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">Category</li>
							<li class="breadcrumb-item active">${category.name}</li>
						</ol>
					</c:if>
				</div>
			</div>
		
			<div class="row">
				<div  class="col-xs-12">
				  <div class="container-fluid"> 
					   <div class="table-responsive">						
						<table id="productListTable" class="table table-striped table-borderd" style="width:100%">
							<thead>
								<tr>	
									<th></th>								
									<th>NAME</th>
									<th>BRAND</th>
									<th>PRICE</th>
									<th>Quant Avail</th>
									<th></th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th></th>									
									<th>NAME</th>
									<th>BRAND</th>
									<th>PRICE</th>
									<th>Quant Avail</th>
									<th></th>
								</tr>
							</tfoot>
						</table>
						</div>
					</div>
				</div>
			</div>
		 </div> 

	</div>

</div>