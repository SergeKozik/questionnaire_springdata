<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<spring:form class="roles-form" modelAttribute="roles_form" action="${pageContext.request.contextPath}/admin/role-edit.html">
    <spring:errors path="*" cssClass="custom-error"/>
    <spring:radiobutton path="flagCreate" value="edit_existed" id="role_existed_id"/>
    <label for="role_existed_id"><s:message code="message.label.role-choose-existed"/></label>
    <div class="roles-select-div">
        <spring:select path="roleName" items="${role_names}" onchange="permission_service('${pageContext.request.contextPath}/rest/admin/authorities',get_selected_value('roleName'),'authorities-list-id')">
        </spring:select>
    </div>
    <br>
    <spring:radiobutton path="flagCreate" value="create_own" onchange="permission_service('${pageContext.request.contextPath}/rest/admin/authorities','all','authorities-list-id')" id="role_new_id"/>
    <label for="role_new_id"><s:message code="message.label.role-choose-new"/></label>
    <div class="roles-create-div">
        <spring:input path="ownRoleName" type="text" />
    </div>
    <br>
    <s:message code="message.label.role-authorities"/>
    <br>
    <div id="authorities-list-id">
        <spring:checkboxes path="permissionNames" items="${perm_names}" cssClass="permission-check"/>
    </div>
    <s:message code="message.button.save-changes" var="msg_save"/>
    <input type="submit" value="${msg_save}">
</spring:form>
<s:message code="message.button.delete" var="msg_delete"/>
<form class="roles-form" action="${pageContext.request.contextPath}/admin/role-delete.html">
    <select name="roleName">
        <c:forEach items="${role_names.values()}" var="item">
            <option value="${item}"><c:out value="${item}"/></option>
        </c:forEach>
    </select>
    <input type="submit" class="button-start" value="${msg_delete}">
</form>
