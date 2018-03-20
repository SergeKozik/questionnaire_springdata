<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <tiles:insertAttribute name="include"/>
    <title>
        <s:message code="message.application.title"/>
    </title>
</head>
<body>
    <div class="wrapper">
        <tiles:insertAttribute name="header"/>
        <tiles:insertAttribute name="main-menu"/>
        <div class="main">
            <tiles:insertAttribute name="side-bar"/>
            <div class="box content">
                <tiles:insertAttribute name="main-content"/>
            </div>
        </div>
        <tiles:insertAttribute name="footer"/>
    </div>
</body>
</html>
