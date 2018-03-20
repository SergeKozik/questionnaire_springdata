<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<div class="custom-error">
    <c:out value="${error_question_message}"/>
</div>
<spring:form cssClass="question-create-form" modelAttribute="form_question" action="${pageContext.request.contextPath}/author/next-question.html">
    <spring:errors path="formulation" cssClass="custom-error"/>
    <label for="question_title"><s:message code="message.label.question-сreate" /></label>
    <spring:textarea rows="2" cols="30" path="formulation" id="question_title"></spring:textarea>
    <div class="hint"><s:message code="message.hint.question-title"/></div>
    <br>
    <div class="answer-multiple">
        <table id="table_answers">
            <thead>
            <tr>
                <td><s:message code="message.label.answers-title"/></td>
                <td><s:message code="message.label.answers-mark" /></td>
                <td></td>
            </tr>
            </thead>
        </table>
        <s:message code="message.button.answer-del" var="msg_del"/>
        <s:message code="message.label.answer-yes" var="msg_yes"/>
        <s:message code="message.label.answer-no" var="msg_no"/>
        <s:message code="message.label.answers-text-hint" var="msg_text"/>
        <s:message code="message.label.answers-text-ask" var="msg_text_placeholder"/>
        <input type="button" class="button-answer add" id="answer_custom" onclick="add_answer_custom_mark('table_answers','${msg_del}')"/>
        <label for="answer_custom"><s:message code="message.button.answer-custom-add" /></label>
        <input type="button" class="button-answer add" id="answer_yesno" onclick="add_answer_yesno_mark('table_answers','${msg_del}','${msg_yes}','${msg_no}')"/>
        <label for="answer_yesno"><s:message code="message.button.answer-yesno-add" /></label>
        <input type="button" class="button-answer add" id="answer_user" onclick="add_answer_user('table_answers','${msg_text_placeholder}','${msg_text}','${msg_del}')"/>
        <label for="answer_user"><s:message code="message.button.answer-user-add" /></label>
    </div>
    <s:message code="message.button.next-question" var="msg_tonext"/>
    <input type="submit" class="button-quest" value="${msg_tonext}"/>
    <input class="finish-button-check" type="checkbox" name="typeLink" value="toFinish" id="finishLink" onclick="this.form.submit();"/><label for="finishLink"><s:message code="message.button.question-finish" /></label>
</spring:form>
<spring:form modelAttribute="form_question" action="${pageContext.request.contextPath}/author/next-question.html">
    <s:message code="message.button.rewrite-question" var="msg_cancel"/>
    <input type="hidden" name="typeLink" value="toNew"/>
    <input type="submit" class="button-quest" value="${msg_cancel}"/>
</spring:form>