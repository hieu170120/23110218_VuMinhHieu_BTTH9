<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head><meta charset="utf-8"/><title>Users</title>
    <style>table{border-collapse:collapse;width:100%}th,td{border:1px solid #ddd;padding:6px}</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<h2>Create User</h2>
<label>Fullname</label><input id="uC_fullname"/><br/>
<label>Email</label><input id="uC_email"/><br/>
<label>Password</label><input id="uC_password" type="password"/><br/>
<label>Phone</label><input id="uC_phone"/><br/>
<label>Category IDs</label><input id="uC_catIds" placeholder="vd: 1,2"/><br/>
<button type="button" onclick="createUser()">Create</button>
<div id="uC_msg"></div>

<h2>Update User</h2>
<label>ID</label><input id="uU_id" type="number"/><br/>
<label>Fullname</label><input id="uU_fullname"/><br/>
<label>Email</label><input id="uU_email"/><br/>
<label>Password</label><input id="uU_password" type="password"/><br/>
<label>Phone</label><input id="uU_phone"/><br/>
<label>Category IDs</label><input id="uU_catIds" placeholder="vd: 1,2"/><br/>
<button type="button" onclick="updateUser()">Update</button>
<div id="uU_msg"></div>

<h2>Delete User</h2>
<label>ID</label><input id="uD_id" type="number"/>
<button type="button" onclick="deleteUser()">Delete</button>
<div id="uD_msg"></div>

<h2>Users list</h2>
<button type="button" onclick="loadUsers()">Load</button>
<table id="tblUsers"><thead><tr><th>ID</th><th>Fullname</th><th>Email</th><th>Phone</th></tr></thead><tbody></tbody></table>

<script src="${pageContext.request.contextPath}/js/users.js"></script>
</body>
</html>