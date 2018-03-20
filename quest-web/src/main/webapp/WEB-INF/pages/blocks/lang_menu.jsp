<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div class="headmenu-wrapper">
    <s:message code="message.label.lang-title"/>
    <ul class="headmenu">
        <li><a href="${pageContext.request.contextPath}/language.html?lang=ru_RU"><img src="${pageContext.request.contextPath}/resources/img/russia_640.png" width="20" class="lang-image"><s:message code="message.label.lang-title-ru"/></a></li>
        <li><a href="${pageContext.request.contextPath}/language.html?lang=en_GB"><img src="${pageContext.request.contextPath}/resources/img/united_kingdom_640.png" width="20" class="lang-image"><s:message code="message.label.lang-title-en"/></a></li>
    </ul>
</div>