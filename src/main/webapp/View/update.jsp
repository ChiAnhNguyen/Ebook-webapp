<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Update Product</h1>


    <c:forEach items="${productList}" var="productMap">
        <c:set var="product" value="${productMap['product']}" />
        <c:set var="imageData" value="${productMap['imageData']}" />
        <c:set var="category" value="${productMap['category']}" />
       <form id="updateProductForm" action="updateproduct" method="post" enctype="multipart/form-data">
	        <label for="productName">Product Name:</label><br>
	        <input type="text" id="productName" name="productName" value="${product.productName}"><br>
	
	        <label for="categoryName">Category Name:</label><br>
	        <input type="text" id="categoryName" name="categoryName" value="${category.categoryName}"><br>
	
	        <label for="price">Price:</label><br>
	        <input type="text" id="price" name="price" value="${product.price}"><br>
	
	        <label for="author">Author:</label><br>
	        <input type="text" id="author" name="author" value="${product.author}"><br>
	
	        <label for="publisher">Publisher:</label><br>
	        <input type="text" id="publisher" name="publisher" value="${product.publisher}"><br>
	
	        <label for="descript">Description:</label><br>
	        <input type="text" id="descript" name="descript" value="${product.descript}"><br>
	         <label for="image">Image:</label><br>
	        <input type = "file" id="imageData" name="imageData"><br>
	        <input type="hidden" name="productIdToUpdateForm" value="${product.productID}">
	        <!-- Hiển thị hình ảnh -->
	        
	        <input type="submit" value="Update">
        </form>
    </c:forEach>
     
    
</body>
</html>