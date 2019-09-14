<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<div class="container">
		<a class="navbar-brand" href="${contextRoot}/home">Online Shopping</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li id="home"><a class="nav-link" href="${contextRoot}/home">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li id="listProducts"><a class="nav-link"
					href="${contextRoot}/show/all/products">View Prodcuts</a></li>
				<li id="about"><a class="nav-link" href="${contextRoot}/about">About</a></li>
				<li id="contact"><a class="nav-link"
					href="${contextRoot}/contact">Contact</a></li>
				<li id="manageProducts"><a class="nav-link"
					href="${contextRoot}/manage/products">Manage Product</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li id="register"><a class="nav-link"
					href="${contextRoot}/register">Sign Up</a></li>
				<li id="login"><a class="nav-link"
					href="${contextRoot}/login">Login</a></li>
			</ul>
		</div>
	</div>
</nav>