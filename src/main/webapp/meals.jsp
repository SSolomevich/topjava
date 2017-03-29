<%--
  Created by IntelliJ IDEA.
  User: 15
  Date: 26.03.2017
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <title>Meals list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>

<jsp:useBean id="meals" class="java.util.ArrayList" scope="request"/>

<%--<c:set var="date" value="<%=new java.util.Date()%>" />--%>
<table border="1">
    <thead>
    <tr>
        <th width="100">ID</th>
        <th width="180">DateTime</th>
        <th width="180">Description</th>
        <th width="120">Calories</th>
    </tr>
    </thead>
    <c:forEach   items="${meals}" var="m">
        <tr >
            <td><c:out value="${m.id}"/></td>

            <%--<td><c:out value="${m.dateTime}"/></td>--%>
            <c:set var="cleanedDateTime" value="${fn:replace(m.dateTime, 'T', ' ')}" />
            <fmt:parseDate value="${ cleanedDateTime }" pattern="yyyy-MM-dd HH:mm" var="parsedDateTime" type="both" />
            <td align="left"><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" /></td>

            <td><c:out value="${m.description}"/></td>
            <td><c:out value="${m.calories}"/></td>
            <td><a href="meals?action=edit&mealId=<c:out value="${m.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${m.id}"/>">Delete</a></td>


        </tr>
    </c:forEach>
</table>
</body>
</html>
