<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<s:message code="message.button.restore" var="msg_restore"/>
<div class="custom-error">
    <c:out value="${error_admin_message}"/>
</div>
    <div class="scroll-users">
        <table>
            <thead>
            <tr>
                <td>
                    <s:message code="message.label.user-nick"/>
                </td>
                <td>
                    <s:message code="message.label.user-email"/>
                </td>
                <td>
                    <s:message code="message.label.user-role"/>
                </td>
                <td>
                </td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${user_list}">
                <tr>
                    <td>
                        <c:out value="${entry.nick}"/>
                    </td>
                    <td>
                        <c:out value="${entry.email}"/>
                    </td>
                    <td>
                        <c:out value="${entry.role}"/>
                    </td>
                    <td>
                        <form>
                            <input type="hidden" name="user_id" value="${entry.id}">
                            <c:forEach var="var_button" items="${entry.buttons}">
                                <button formaction="${var_button.buttonLink}"><c:out value="${var_button.buttonCaption}"/></button>
                            </c:forEach>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <br>
    <s:message code="message.label.bin-users"/>

        <div class="scroll-users">
            <table>
                <thead>
                <tr>
                    <td>
                        <s:message code="message.label.user-nick"/>
                    </td>
                    <td>
                        <s:message code="message.label.user-email"/>
                    </td>
                    <td>
                        <s:message code="message.label.user-role"/>
                    </td>
                    <td>
                    </td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="entry" items="${user_list_bin}">
                    <input type="hidden" name="list_user_bin" value="${entry.id}"/>
                    <tr>
                        <td>
                            <c:out value="${entry.nick}"/>
                        </td>
                        <td>
                            <c:out value="${entry.email}"/>
                        </td>
                        <td>
                            <c:out value="${entry.role}"/>
                        </td>
                        <td>
                            <form>
                            <input type="hidden" name="user_id" value="${entry.id}">
                            <c:forEach var="var_button" items="${entry.buttons}">
                                <button formaction="${var_button.buttonLink}"><c:out value="${var_button.buttonCaption}"/></button>
                            </c:forEach>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    <form action="${pageContext.request.contextPath}/admin/user-bin-clear.html">
        <s:message code="message.button.clear-bin" var="msg_clear"/>
        <input type="submit" value="${msg_clear}">
    </form>