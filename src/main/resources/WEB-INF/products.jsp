<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/><title>Home</title>
    <style>
        body{font-family:system-ui,Arial;margin:20px}
        table{border-collapse:collapse;width:100%;margin-top:8px}
        th,td{border:1px solid #ddd;padding:6px}
        h2{margin-top:20px}
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<h1>Products</h1>

<h2>Create Product</h2>
<label>Title</label><input id="pC_title"/><br/>
<label>Quantity</label><input id="pC_quantity" type="number"/><br/>
<label>Description</label><input id="pC_desc"/><br/>
<label>Price</label><input id="pC_price" type="number" step="0.01"/><br/>
<label>Owner ID</label><input id="pC_ownerId" type="number"/><br/>
<label>Category IDs</label><input id="pC_categoryIds" placeholder="vd: 1,2"/><br/>
<button type="button" onclick="createProduct()">Create</button>
<div id="pC_msg"></div>

<h2>Update Product</h2>
<label>ID</label><input id="pU_id" type="number"/><br/>
<label>Title</label><input id="pU_title"/><br/>
<label>Quantity</label><input id="pU_quantity" type="number"/><br/>
<label>Description</label><input id="pU_desc"/><br/>
<label>Price</label><input id="pU_price" type="number" step="0.01"/><br/>
<label>Owner ID</label><input id="pU_ownerId" type="number"/><br/>
<label>Category IDs</label><input id="pU_categoryIds" placeholder="vd: 1,2"/><br/>
<button type="button" onclick="updateProduct()">Update</button>
<div id="pU_msg"></div>

<h2>Delete Product</h2>
<label>ID</label><input id="pD_id" type="number"/>
<button type="button" onclick="deleteProduct()">Delete</button>
<div id="pD_msg"></div>

<h2>All Products</h2>
<button type="button" onclick="loadAllProducts()">Load</button>
<table id="tblAll"><thead><tr><th>ID</th><th>Title</th><th>Price</th><th>Qty</th><th>OwnerId</th></tr></thead><tbody></tbody></table>

<h2>Price tăng dần</h2>
<button type="button" onclick="loadProductsAsc()">Load</button>
<table id="tblAsc"><thead><tr><th>ID</th><th>Title</th><th>Price</th><th>Qty</th><th>OwnerId</th></tr></thead><tbody></tbody></table>

<h2>Lọc theo Category</h2>
<input id="catId" type="number" value="1"/>
<button type="button" onclick="loadByCategory()">Load</button>
<table id="tblCat"><thead><tr><th>ID</th><th>Title</th><th>Price</th></tr></thead><tbody></tbody></table>

<script src="${pageContext.request.contextPath}/js/products.js"></script>
</body>
</html>