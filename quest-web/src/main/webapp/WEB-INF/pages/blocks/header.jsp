<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="header">
    <s:message code="message.application.head-greeting"/>
    <tiles:insertAttribute name="login-menu"/>
    <tiles:insertAttribute name="lang-menu"/>
</div>

