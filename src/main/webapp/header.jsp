
<div id="header">
	<div class="container">
		<div id="welcomeLine" class="row">
			<div class="span6">
				Welcome! <strong>${sessionScope.user} </strong><a
					${sessionScope.nohidden} href="LogoutController">Log out</a>
			</div>

		</div>
		<!-- Navbar ================================================== -->
		<div id="logoArea" class="navbar">
			<a id="smallScreen" data-target="#topMenu" data-toggle="collapse"
				class="btn btn-navbar"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a>
			<div class="navbar-inner">
				<a class="brand" href="ListAllProduct"><img
					src="themes/images/logo.png" alt="Bootsshop" /></a>
				<form class="form-inline navbar-search" method="post"
					action="SearchController2">
					<input id="srchFld" name="s" class="srchTxt" type="text" />
					<button type="submit" id="submitButton" class="btn btn-primary">Go</button>
				</form>
				<ul id="topMenu" class="nav pull-right">
					<li class=""><a href="cart.jsp">Cart</a></li>
					<li ${sessionScope.hidden } class=""><a href="contact.html">Register</a></li>
					<li ${sessionScope.hidden } class=""><a href="login.jsp"
						style="padding-right: 0"><span
							class="btn btn-large btn-success">Login</span></a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
