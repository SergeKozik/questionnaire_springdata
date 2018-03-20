<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="headmenu-wrapper">
<security:authorize access="isAuthenticated()">
        <s:message code="message.user.greeting"/>
        <c:out value="${userProfile.username}"/>
        <ul class="headmenu">
            <li><a href="${pageContext.request.contextPath}/logout.html"><s:message code="message.menu.user-logout"/></a></li>
        </ul>
</security:authorize>
<security:authorize access="not isAuthenticated()">
        <a href="${pageContext.request.contextPath}/login.html"><s:message code="message.button.user-login"/></a>
        <a href="${pageContext.request.contextPath}/register.html"><s:message code="message.button.user-register"/></a>
</security:authorize>
</div>

