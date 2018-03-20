<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div class="custom-error">
    <c:out value="${error_quest_message}"/>
</div>
<form action="${pageContext.request.contextPath}/author/quest-save.html">
    <table>
        <tr>
            <td><s:message code="message.label.quest-title"/></td>
            <td><c:out value="${quest_title}"/></td>
        </tr>
        <tr>
            <td><s:message code="message.label.quest-language"/></td>
            <td><c:out value="${quest_language}"/></td>
        </tr>
        <tr>
            <td><s:message code="message.label.quest-category"/></td>
            <td><c:out value="${quest_category}"/></td>
        </tr>
        <tr>
            <td><s:message code="message.label.quest-type"/></td>
            <td><c:out value="${quest_type}"/></td>
        </tr>
        <tr>
            <td><s:message code="message.label.quest-description"/></td>
            <td><textarea rows="10" cols="40" disabled><c:out value="${quest_description}"/></textarea></td>
        </tr>
        <tr>
            <td><s:message code="message.label.quest-questions"/></td>
            <td><c:out value="${quest_questions}"/></td>
        </tr>
        <tr>
            <td><s:message code="message.label.quest-author"/></td>
            <td><c:out value="${quest_author}"/></td>
        </tr>
    </table>
    <s:message code="message.button.quest-create" var="msg_create"/>
    <input type="submit" value="${msg_create}"/>
</form>
<form action="${pageContext.request.contextPath}/author/quest-save-cancel.html">
    <s:message code="message.button.quest-create-cancel" var="msg_cancel"/>
    <input type="submit" value="${msg_cancel}"/>
</form>
