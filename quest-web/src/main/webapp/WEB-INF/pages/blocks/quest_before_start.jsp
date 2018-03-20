<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div>
    <c:out value="${quest_title}"/>
</div>
<div>
    <c:out value="${quest_description}"/>
</div>
<s:message code="message.button.quest-start" var="msg_start"/>
<s:message code="message.button.cancel" var="msg_cancel"/>
<form action="${pageContext.request.contextPath}/user/quest-run.html">
    <input type="submit" value="${msg_start}">
    <input type="submit" formaction="${pageContext.request.contextPath}/quests.html" value="${msg_cancel}">
</form>
