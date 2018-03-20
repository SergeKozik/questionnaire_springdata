<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<div class="custom-error">
    <c:out value="${error_quest_message}"/>
</div>
<spring:form cssClass="quest-create-form" modelAttribute="quest_new_title" method="post" action="${pageContext.request.contextPath}/author/quest-title-create.html">
    <div class="custom-error">
        <spring:errors path="*"/>
    </div>
    <div>
        <c:url value="/rest/quest/categories" var="rest_category"/>
        <label for="language"><s:message code="message.label.quest-lang-create"/></label>
        <spring:select name="quest_lang" id="language" path="language" items="${languages}" onchange="quest_category_service('${rest_category}','quest_categ')">
        </spring:select>
    </div>
    <div class="quest-category-choice">
        <label for="quest_categ"><s:message code="message.label.quest-categ-сreate"/></label>
        <spring:select id="quest_categ" path="category" items="${categories}">
        </spring:select>
        <spring:checkbox path="ownCategory" id="ownCategory"/><label for="ownCategory"><s:message code="message.label.categ-сreate"/></label>
        <spring:input path="ownCategoryName"/>
        <div class="hint"><s:message code="message.hint.quest-category"/></div>
    </div>
    <div>
        <label for="quest_title"><s:message code="message.label.quest-title"/></label>
        <spring:textarea rows="2" cols="30" path="title" id="quest_title"></spring:textarea>
        <div class="hint"><s:message code="message.hint.quest-title" /></div>
    </div>
    <div>
        <spring:textarea rows="5" cols="80" path="description"/>
        <div class="hint"><s:message code="message.hint.quest-description"/></div>
    </div>
    <div>
        <c:forEach var="entry" items="${type_beans}">
            <spring:radiobutton path="type" value="${entry.nameEn}" id="${entry.nameEn}"/><label for="${entry.nameEn}">${entry.description}</label>
            <br>
        </c:forEach>
    </div>
    <s:message code="message.button.create-questions" var="msg_toquest"/>
    <s:message code="message.button.cancel" var="msg_cancel"/>
    <input type="submit" class="button-quest" value="${msg_toquest}">
    <input type="submit" class="button-quest" value="${msg_cancel}" formaction="${pageContext.request.contextPath}/main.html">
</spring:form>


