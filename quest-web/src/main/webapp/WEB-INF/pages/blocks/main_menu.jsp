<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="main-menu">
    <li><a href="${pageContext.request.contextPath}/main.html"><s:message code="message.menu.tomain"/></a></li>
    <li><a href="${pageContext.request.contextPath}/quests.html"><s:message code="message.menu.toquest"/></a></li>

        <li class="mainnav-wrapper">
            <a href="#"><s:message code="message.menu.user"/></a>
            <ul class="mainnav-submenu">
                <li>
                    <a href="${pageContext.request.contextPath}/user/quest-own.html"><s:message code="message.menu.toauthor"/></a>
                </li>
            </ul>
        </li>


        <li class="mainnav-wrapper">
            <a href="#"><s:message code="message.menu.author"/></a>
            <ul class="mainnav-submenu">
                <li>
                    <a href="${pageContext.request.contextPath}/author/quest-create.html"><s:message code="message.menu.toauthor-create-quest"/></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/author/quest-own.html"><s:message code="message.menu.toauthor"/></a>
                </li>
            </ul>
        </li>


        <li class="mainnav-wrapper">
            <a href="#"><s:message code="message.menu.admin"/></a>
            <ul class="mainnav-submenu">
                <li>
                    <a href="${pageContext.request.contextPath}/admin/users.html"><s:message code="message.menu.admin-users"/></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/roles.html"><s:message code="message.menu.admin-roles"/></a>
                </li>
            </ul>
        </li>

</nav>
