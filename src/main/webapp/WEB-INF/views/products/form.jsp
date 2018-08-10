<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/houseofcode/resources/main.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
	<title>Programming Books</title>
</head>
<body>

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
	   	<form:form action="/houseofcode/products" method="POST" commandName="product" enctype="multipart/form-data">
	
		    <div>
		        <label>Title</label>
		        <form:input path="title" />
		        <form:errors path="title"/>
		    </div>
		    <div>
		        <label>Description</label>
		        <form:textarea path="description" rows="10" cols="20"></form:textarea>
		       	<form:errors path="description"/>
		    </div>
		    <div>
		        <label>Pages</label>
		        <form:input path="pages" />
				<form:errors path="pages"/>
		    </div>
		    <div>
		    	<label>Release date</label>
		    	<form:input path="releaseDate"/>
		    	<form:errors path="releaseDate"/>
		    </div>
		    <c:forEach items="${types}" var="typePrice" varStatus="status">
		    	<div>
		    		<label>${typePrice}</label>
		        	<form:input path="prices[${status.index}].value"/>
		        	<form:hidden path="prices[${status.index}].type" value="${typePrice}"/>
		        </div>
		    </c:forEach>
		    <div>
		    	<label>Summary</label>
		    	<input name="summary" type="file">
		    </div>
		    <button type="submit" class="btn btn-default">Register</button>
		</form:form>
  	</div>
  
</body>
</html>