<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head><meta charset="utf-8"/><title>Categories</title>
    <style>table{border-collapse:collapse;width:100%}th,td{border:1px solid #ddd;padding:6px}</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<h2>Create Category</h2>
<label>Name</label><input id="cC_name"/><br/>
<label>Images</label><input id="cC_images"/><br/>
<label>Product IDs</label><input id="cC_productIds" placeholder="vd: 1,2"/><br/>
<label>User IDs</label><input id="cC_userIds" placeholder="vd: 1,2"/><br/>
<button type="button" onclick="createCategory()">Create</button>
<div id="cC_msg"></div>

<h2>Update Category</h2>
<label>ID</label><input id="cU_id" type="number"/><br/>
<label>Name</label><input id="cU_name"/><br/>
<label>Images</label><input id="cU_images"/><br/>
<label>Product IDs</label><input id="cU_productIds" placeholder="vd: 1,2"/><br/>
<label>User IDs</label><input id="cU_userIds" placeholder="vd: 1,2"/><br/>
<button type="button" onclick="updateCategory()">Update</button>
<div id="cU_msg"></div>

<h2>Delete Category</h2>
<label>ID</label><input id="cD_id" type="number"/>
<button type="button" onclick="deleteCategory()">Delete</button>
<div id="cD_msg"></div>

<h2>Categories list</h2>
<button type="button" onclick="loadCategories()">Load</button>
<table id="tblCategories"><thead><tr><th>ID</th><th>Name</th><th>Images</th></tr></thead><tbody></tbody></table>

<script src="${pageContext.request.contextPath}/js/categories.js"></script>
</body>
</html>