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

<h2>Price tăng dần</h2>
<button type="button" onclick="loadProductsAsc()">Load</button>
<table id="tblAsc"><thead><tr><th>ID</th><th>Title</th><th>Price</th><th>Qty</th><th>OwnerId</th></tr></thead><tbody></tbody></table>

<h2>Lọc theo Category</h2>
<input id="catId" type="number" value="1"/>
<button type="button" onclick="loadByCategory()">Load</button>
<table id="tblCat"><thead><tr><th>ID</th><th>Title</th><th>Price</th></tr></thead><tbody></tbody></table>

<script src="${pageContext.request.contextPath}/js/index.js"></script>
</body>
</html>