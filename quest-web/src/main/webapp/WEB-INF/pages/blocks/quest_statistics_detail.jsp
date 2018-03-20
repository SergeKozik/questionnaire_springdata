<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
    <s:message code="message.label.result-user"/>
    <br>
    <c:out value="${users_result.userName}"/>
    <br>
    <s:message code="message.label.result-date"/>
    <br>
    <c:out value="${users_result.date}"/>
    <br>
    <br>
    <c:forEach var="question" items="${users_result.questions}">
        <s:message code="message.label.question-title" />
        <br>
        <c:out value="${question.formulation}"/>
        <br>
        <s:message code="message.label.answers-given"/>
        <br>
        <c:forEach var="answer" items="${question.textAnswers}">
            <c:out value="${answer.formulation}"/>
            <br>
        </c:forEach>
        <br>
    </c:forEach>
