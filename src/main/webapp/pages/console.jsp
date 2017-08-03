<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Консоль</title>
    <c:url value="/static/j_spring_security_check" var="loginUrl" />
    <c:url value="/console/user/add" var="ADD_USER" />
    <c:url value="/console/user/update" var="UPDATE_USER" />
    <c:url value="/console/user/delete/" var="DELETE_USER" />
    <c:url value="/console/user/{id}" var="GET_USER" />
    <c:url value="/console/user/all" var="GET_ALL_USERS" />

    <c:url value="/console/role/add" var="ADD_ROLE" />
    <c:url value="/console/role/update" var="UPDATE_ROLE" />
    <c:url value="/console/role/delete/" var="DELETE_ROLE" />
    <c:url value="/console/role/{id}" var="GET_ROLE" />
    <c:url value="/console/role/all" var="GET_ALL_ROLES" />

    <c:url value="/console/authority/set" var="SET_ROLE" />
    <c:url value="/console/authority/unset" var="UNSET_ROLE" />
</head>
<body>
<div>
    <h1>Users</h1>
    <c:forEach var = "user" items="${users}">
        <p><span>id: "${user.getId()}" | login: "${user.getLogin()}" | roles: "${user.getRolesList().toString()}"</span><a href="${DELETE_USER}${user.getId()}">удалить</a></p>
    </c:forEach>

    <h1>Roles</h1>
    <c:forEach var = "role" items="${roles}">
        <p><span>id: "${role.getId()}" | name: "${role.getRole()}" | login: "${role.getUsers().toString()}" </span><a href="${DELETE_ROLE}${role.getId()}">удалить</a></p>
    </c:forEach>

    <%--<h1>Authorities</h1>--%>
    <%--<c:forEach var = "auth" items="${authorities}">--%>
        <%--<p>id: ${auth.getId()} | name: ${auth.getRole().getRole()} | login: ${auth.getUser().getName()} </p>--%>
    <%--</c:forEach>--%>
</div>
<div style="width: 300px;">
    <form action="${UPDATE_USER}" method="post">
        <h2 >Добавить или обновить пользователя</h2>
        <input type="text" name=id placeholder="user id" required = false>
        <input type="text" name="name" placeholder="user name" required = true>
        <input type="text" name="login" placeholder="user login" required = true>
        <input type="text" name="password" placeholder="user password" required = true>
        <button type="submit">Отправить</button>
    </form>
</div>
<div style="width: 300px;">
    <form action="${UPDATE_ROLE}" method="post">
        <h2 >Добавить или обновить роль</h2>
        <input type="text" name=id placeholder="role id" required = false>
        <input type="text" name="name" placeholder="role name" required = true>
        <button type="submit">Отправить</button>
    </form>
</div>
<div>
<div style="width: 300px; display: inline-block">
    <form action="${SET_ROLE}" method="post">
        <h2 >Добавить authority</h2>
        <input type="text" name="user" placeholder="user login" required = true >
        <input type="text" name="role" placeholder="role id" required = true>
        <button type="submit">Добавить</button>
    </form>
</div>
<div style="width: 300px; display: inline-block">
    <form action="${UNSET_ROLE}" method="post">
        <h2 >Удалить authority</h2>
        <input type="text" name="user" placeholder="user login" required = true>
        <input type="text" name="role" placeholder="role id" required = true>
        <button type="submit">Удалить</button>
    </form>
</div>
</div>
</body>
</html>
