<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<s:message code="message.label.error.login-js-validation" var="msg_error"/>
<spring:form class="login-form" name="form_login" modelAttribute="login_user" action="${pageContext.request.contextPath}/login-user.html" method="post">
    <div id="login_error" class="custom-error">
        <c:out value="${error_login_message}"/>
        <c:out value="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}"/>
    </div>
    <table>
        <tr>
            <td><s:message code="message.label.login-email"/> </td>
            <td>
                <spring:input path="email" value=""/>
            </td>
        </tr>
        <tr>
            <td><s:message code="message.label.login-password"/></td>
            <td>
                <spring:password path="password" value=""/>
            </td>
        </tr>
    </table>
    <s:message code="message.button.user-login" var="msg_login"/>
    <input class="button" type="submit" value="${msg_login}">
</spring:form>
</div>
