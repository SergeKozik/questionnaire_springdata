<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<s:message code="message.label.report.quest-completed"/>
<br>
<s:message code="message.label.quest-title"/>
<c:out value="${quest_title}"/>
<br>
<table>
    <tr>
        <td><s:message code="message.label.quest-questions"/></td>
        <td><c:out value="${quest_num_questions}"/></td>
    </tr>
</table>
