<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div class="box sidebar">
<c:url value="/rest/quests" var="rest_url"/>
<form name="quest_menu_form" id="quest_menu_form" method="post" enctype="application/x-www-form-urlencoded">
    <div class="quest-menu">
        <h3><s:message code="message.label.lang-title" /></h3>
        <ul>
            <c:forEach var="entry" items="${languages}">
                <li>
                    <input type="checkbox" name="quest_lang" id="${entry}" checked value="${entry}" onchange="quest_list_service('${rest_url}');"/>
                    <label for="${entry}">${entry}</label>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="quest-menu">
        <h3><s:message code="message.label.quest-type" /></h3>
        <ul>
            <c:forEach var="entry" items="${types}">
                <li>
                    <input type="checkbox" name="quest_type" id="${entry.nameEn}" checked value="${entry.nameEn}" onchange="quest_list_service('${rest_url}');"/>
                    <label for="${entry.nameEn}">${entry.name18n}</label>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="quest-menu">
        <h3><s:message code="message.label.quest-category" /></h3>
        <ul>
            <c:forEach var="entry" items="${categories}">
                <li>
                    <input type="checkbox" name="quest_categ" id="${entry}" checked value="${entry}" onchange="quest_list_service('${rest_url}');"/>
                    <label for="${entry}">${entry}</label>
                </li>
            </c:forEach>
        </ul>
    </div>
</form>
</div>