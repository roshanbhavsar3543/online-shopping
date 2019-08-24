<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<!-- breadcrumb -->
	<div class="row">
		<div class="col-xs-12">
			<ol class="breadcrumb">
				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/show/all/products">Products</a></li>
				<li class="active">${product.name}</li>
			</ol>		
		</div>
	</div>
	<div class="row">
		<!-- Product Image -->
		<div class="col-xs-12 col-sm-4">
			<div class="thumbnail">
				<img class="img img-responsive" src="${images}/${product.code}.jpg">
			</div>	
		</div>
		<!-- Product Description -->
		<div class="col-xs-12 col-sm-8">
			<h3>${product.name}</h3>
			<hr/>
			<p>${product.description}</p>
			<h4>Price : <strong>&#8377; ${product.unitPrice} /-</strong></h4>
			<hr/>		
			
			<!--Product Quantity  -->
			<c:choose>
				<c:when test="${product.quantity < 1 }">
					<h6><span style="color:red">Out of Stock</span></h6>
				</c:when>
				<c:otherwise>
					<h6>Quantity Available : ${product.quantity}</h6>
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${product.quantity < 1 }">
					<a href="javaScript:void(0)" class="btn btn-success disabled"><strike>
					<span class="glyphicon glyphicon-shopping-cart"></span>Add To Cart</strike></a>
				</c:when>
				<c:otherwise>
					<a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success">
					<span class="glyphicon glyphicon-shopping-cart"></span>Add To Cart</a>
				</c:otherwise>
			</c:choose>
			
			<a href="${contextRoot}/show/all/products" class="btn btn-primary">
					Back</a>
		</div>
	</div>

</div>