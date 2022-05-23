<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox readonly="true" code="any.item.form.label.code" path="code"/>	
	<acme:input-textbox code="any.item.form.label.name" path="name"/>
	<acme:input-textbox code="any.item.form.label.technology" path="technology"/>
	<acme:input-money code="any.item.form.label.retailPrice" path="retailPrice"/>
	<acme:input-textarea code="any.item.form.label.description" path="description"/>
	<acme:input-url code="any.item.form.label.link" path="link"/>
	<acme:input-textbox code="any.item.form.label.published" path="published"/>
		
	<acme:input-select code="any.item.form.label.type" path="type">
		<acme:input-option code="TOOL" value="TOOL" selected="${status == 'TOOL'}"/>
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${status == 'COMPONENT'}"/>
	</acme:input-select>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete') && published == true}">
			<acme:button code="any.item.form.label.toolkit" action="/inventor/toolkit/list-mine?itemId=${id}"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete') && published == false}">
			<acme:submit code="inventor.item.form.button.update" action="/inventor/item/update"/>
			<acme:submit code="inventor.item.form.button.delete" action="/inventor/item/delete"/>
			<acme:submit code="inventor.item.form.button.publish" action="/inventor/item/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.item.form.button.create" action="/inventor/item/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>