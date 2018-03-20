<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="custom-error">
    <c:out value="${error_message}"/>
    <br>
    <c:out value="${exception_object.message}"/>
    <br>
    <c:forEach items="${exception_object.stackTrace}" var="ste">
        <c:out value="${ste}"/>
    </c:forEach>
</div>


