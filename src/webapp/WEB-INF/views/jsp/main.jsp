<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
  <c:choose>
    <c:when test="${user.getUsername() ne null}">
      ${user.getUsername()}
    </c:when>
    <c:otherwise>
      <form:form action="/log_in" modelAttribute="user">
        <form:input path="username"/>
        <form:input path="password"/>
        <input type="submit"/>
      </form:form>
    </c:otherwise>
  </c:choose>
</body>
</html>
