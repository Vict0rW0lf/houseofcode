 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/houseofcode/resources/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
	<title>${product.title}- House of Code</title>
</head>
<body class="product">

	<header>
		<div class="wrapper">
			<div class="title">
				<h2>House of Code</h2>
			</div>
			<div class="shopping-cart">
				<a href="/houseofcode/cart" class="btn btn-info"> Shopping Cart (${shoppingCart.quantity})</a>
			</div>
		</div>
	</header>

  	<nav>
    	<ul>
    		<li><a href="/houseofcode">Home</a></li>
      		<li><a href="/houseofcode/products">Books</a></li>
      		<li><a href="/houseofcode/products/form">Add new book</a></li>
    	</ul>
  	</nav>
  	
  	<div class="pageBody">
	    <div id="${product.id}" itemscope>
	
		<h1 class="product-title" itemprop="name">${product.title}</h1>
		<p itemprop="description" class="book-description">${product.description}</p>
	
		<form action='<c:url value="/cart/add" />' method="post" class="container">
			<ul>
				<input type="hidden" name="productId" value="${product.id}" />
				<c:forEach items="${product.prices}" var="price">
					<li class="buy-option">
						<input type="radio" name="typePrice" id="type" value="${price.type}"checked="checked" /> 
						<label>
							${price.type}
						</label> 
						<p>${price.value}</p>
					</li>
				</c:forEach>
			</ul>
			<button type="submit" class="submit-image icon-basket-alt"
				alt="Buy now" title="Buy now ${product.title}">Buy now</button>
		</form>
	
	    <h2 class="section-title">Book info:</h2>
	    <p>Pages: <span>${product.pages}</span></p>
	    <p>Release: <fmt:formatDate pattern="MM/dd/yyyy" value="${product.releaseDate.time}"/></p>
	    <p>Found an error? <a href='/submission-error' target='_blank'>Submit an error</a></p>
  	</div>

</body>
</html>