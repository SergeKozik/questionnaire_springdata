<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
    <form action="${pageContext.request.contextPath}/user/user-result.html">
        <table>
            <thead>
            <tr>
                <td>
                    <s:message code="message.label.user-result-title"/>
                </td>
                <td>
                    <s:message code="message.label.user"/>
                </td>
                <td>
                    <s:message code="message.label.user-result-date" />
                </td>
                <td>

                </td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${users_results}">
                <tr>
                    <td>
                        <c:out value="${entry.questTitle}"/>
                    </td>
                    <td>
                        <c:out value="${entry.userName}"/>
                    </td>
                    <td>
                        <c:out value="${entry.date}"/>
                    </td>
                    <td>
                        <input class="finish-button-check" type="checkbox" name="result_id" value="${entry.id}" id="check${entry.id}" onclick="this.form.submit();"/>
                        <label for="check${entry.id}"><s:message code="message.label.user-result-show"/></label>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
