<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<div class="custom-error" id = "divErrorId">
    <c:out value="${error_register_message}"/>
    <c:out value="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}"/>
</div>
<s:message code="message.label.error.fields-empty" var="msg_js"/>
<s:message code="message.label.mark-necessary" var="msg_asterisk"/>
<spring:form class="login-form" name="form_register" modelAttribute="register_user" action="${pageContext.request.contextPath}/register-user.html" method="post" onsubmit="return true;">
    <table>
        <tr>
            <td><s:message code="message.label.register-nick"/> </td>
            <td>
                <s:message code="regexp.login" var="login_reg"/>
                <spring:input name="user_name" path="login" type="text" value="" pattern="${login_reg}"/>
                <div class="hint"><s:message code="message.hint.register-nick"/></div>
            </td>
            <td>
                <c:out value="${msg_asterisk}"/>
                <spring:errors path="login" cssClass="custom-error"/>
            </td>
        </tr>
        <tr>
            <td><s:message code="message.label.register-password"/></td>
            <td>
                <s:message code="regexp.password" var="login_pass"/>
                <spring:input name="passw" path="password" type="password" value="" pattern="${login_pass}"/>
                <div class="hint"><s:message code="message.hint.register-password"/> </div>
            </td>
            <td>
                <c:out value="${msg_asterisk}"/>
                <spring:errors path="password" cssClass="custom-error"/>
            </td>
        </tr>
        <tr>
            <td><s:message code="message.label.register-password-double"/></td>
            <td>
                <spring:input name="passw_double" path="passwordConfirm" type="password" value="" pattern="${login_pass}"/>
            </td>
            <td>
                <c:out value="${msg_asterisk}"/>
                <spring:errors path="passwordConfirm" cssClass="custom-error"/>
            </td>
        </tr>
        <tr>
            <td><s:message code="message.label.register-email"/></td>
            <td>
                <s:message code="regexp.email" var="login_email"/>
                <spring:input name="email" path="email" type="email" value="" pattern="${login_email}"/>
            </td>
            <td>
                <c:out value="${msg_asterisk}"/>
                <spring:errors path="email" cssClass="custom-error"/>
            </td>
        </tr>
        <tr>
            <td>
                <s:message code="message.label.user-role"/>
            </td>
            <td>
                <spring:select name="select_role" path="roleName" items="${role_names}">
                </spring:select>
            </td>
            <td>
                <c:out value="${msg_asterisk}"/>
                <spring:errors path="roleName" cssClass="custom-error"/>
            </td>
        </tr>
    </table>
    <s:message code="message.button.user-register" var="msg_login"/>
    <input class="button" type="submit" value="${msg_login}">
</spring:form>
