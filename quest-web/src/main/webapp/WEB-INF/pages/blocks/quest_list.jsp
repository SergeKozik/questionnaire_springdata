<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="quest-list" id="quest-list" >
    <c:forEach var="entry" items="${quests}">
        <div class="quest-card">
            <div><c:out value="${entry.title}"/></div>
            <table>
                <tr>
                    <td><c:out value="${entry.languageName}"/></td>
                    <td><c:out value="${entry.language}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${entry.categoryName}"/></td>
                    <td><c:out value="${entry.category}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${entry.typeResultName}"/></td>
                    <td><c:out value="${entry.typeResultNative}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${entry.authorName}"/></td>
                    <td><c:out value="${entry.author}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${entry.dateName}"/></td>
                    <td><c:out value="${entry.creationDate}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${entry.numQuestionsName}"/></td>
                    <td><c:out value="${entry.numQuestions}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${entry.passedName}"/></td>
                    <td><c:out value="${entry.passed}"/></td>
                </tr>
            </table>
            <form method="post">
                <input type="hidden" name="quest_id" value="${entry.id}"/>
            <c:forEach var="action" items="${entry.buttons}">
                <input class="button-start" type="submit" formaction="${action.buttonLink}" value="${action.buttonCaption}"/>
            </c:forEach>
            </form>
        </div>
    </c:forEach>
</div>

