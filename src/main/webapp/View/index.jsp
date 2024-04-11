<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Management</title>
</head>
<body>
    <h1>Add Book</h1>
    <form id="addbookform" action="addbook" method="post" enctype="multipart/form-data" >
      
        <label>Product Name:</label><br/>
        <input type="text" id="productname" name="productname"><br/>
        
        <label >Category Name:</label><br>
        <input type="text" id="categoryname" name="categoryname"><br/>
        
        <label>Price:</label><br/>
        <input type="text" id="price" name="price"><br/>
        
        <label >Author:</label><br>
        <input type="text" id="author" name="author"><br/>
        
        <label >Publisher:</label><br>
        <input type="text" id="publisher" name="publisher"><br/>
        
        <label>Description:</label><br/>
        <input type="text" id="descrip" name="descript"><br/>
        
  		<label>Image:</label><br/>
        <input type="file" id="image" name="image"><br/>
        
        <input type="submit" value="Add Book" >
    </form>

    <h1>Product List</h1>
    <div id="productlist">
        <table border="1">
            <tr>
                <th>Product Name</th>
                <th>Category</th>
                <th>Description</th>
                <th>Price</th>
                <th>Author</th>
                <th>Publisher</th>
                <th>Image</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${productList}" var="productMap">
    <c:set var="product" value="${productMap['product']}" />
    <c:set var="imageData" value="${productMap['imageData']}" />
    <c:set var="category" value="${productMap['category']}" />
    
   
    <tr>
        <td>${product.productName}</td>
        <td>${category.categoryName}</td>
        <td>${product.descript}</td>
        <td>${product.price}</td>
        <td>${product.author}</td>
        <td>${product.publisher}</td>
        <td><img src="data:image/jpeg;base64,${imageData}" alt="Product Image" width="100"></td>
        <!-- Thêm các nút action để chỉnh sửa hoặc xóa sản phẩm -->
        <!-- Thêm nút xóa -->
    <td>
        <form action="deleteproduct" method="post">
            <input type="hidden" name="productId" value="${product.productID}">
            <input type="submit" value="Delete" onclick="confirmDelete()">
        </form>
    </td>
    </tr>
</c:forEach>
        </table>
    </div>
</body>
<script>
    function confirmDelete() {
        return confirm("Are you sure you want to delete this product?");
    }
</script>
</html>