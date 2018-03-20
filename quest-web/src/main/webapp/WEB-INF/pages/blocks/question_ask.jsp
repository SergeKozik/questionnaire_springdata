<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div class="custom-error">
    <c:out value="${error_question_message}"/>
</div>
<div>
    <form action="${pageContext.request.contextPath}/user/quest-next-question.html">
        <input type="hidden" name="question_id" value="${question_id}">
        <input type="hidden" name="quest_id" value="${quest_id}">
        <textarea rows="2" cols="50" disabled><c:out value="${question_formulation}"/></textarea>
        <br>
        <c:forEach var="answer" items="${question_variants}">
            <input type="radio" name="current_answer" value="${answer.id}" id="answ${answer.id}"/><label for="answ${answer.id}"><c:out value="${answer.formulation}"/></label>
            <br>
        </c:forEach>
        <c:forEach var="answer_text" items="${question_variants_user}">
            <input type="radio" name="current_answer_text" value="${answer_text.id}" id="answtxt${answer_text.id}"/><label for="answtxt${answer_text.id}"><c:out value="${answer_text.formulation}"/></label>
            <br>
            <textarea rows="3" cols="30" name="user_answer"></textarea>        <br>
        </c:forEach>
        <s:message code="message.button.question-next" var="msg_next"/>
        <s:message code="message.button.question-finish" var="msg_finish"/>
        <s:message code="message.button.question-skip" var="msg_skip"/>
        <input class="finish-button-check" type="checkbox" name="typeLink" value="toFinish" id="finishLink" onclick="this.form.submit();"/><label for="finishLink"><c:out value="${msg_finish}"/></label>
        <input type="submit" class="button-quest" formaction="${pageContext.request.contextPath}/user/quest-skip-question.html" value="${msg_skip}">
        <input type="submit" class="button-quest" value="${msg_next}">
    </form>
</div>
