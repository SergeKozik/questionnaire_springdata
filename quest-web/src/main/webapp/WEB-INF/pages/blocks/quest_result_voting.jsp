<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<s:message code="message.label.report.quest-completed"/>
<br>
<s:message code="message.label.quest-title"/>
<c:out value="${quest_title}"/>
<br>
<table>
    <c:forEach var="entry" items="${voteResultBeans}">
        <tr>
            <td><c:out value="${entry.formulation}"</td>
            <td><c:out value="${entry.stringValue}"</td>
        </tr>
    </c:forEach>
</table>