<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="inventor.patronage.form.label.status" path="status"/>	
	<acme:input-textbox code="inventor.patronage.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-money code="inventor.patronage.form.label.budget" path="budget" />
	<acme:input-textbox code="inventor.patronage.form.label.creationMoment" path="creationMoment" />
	<acme:input-textbox code="inventor.patronage.form.label.startDate" path="startDate" />
	<acme:input-textbox code="inventor.patronage.form.label.finishDate" path="finishDate"/>
	<acme:input-url code="inventor.patronage.form.label.link" path="link" />
	
	<acme:button code="user-account.form.label.patron" action="/any/user-account/show?id=${patron.userAccount.id}"/>
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="inventor.patronage.form.label.confirmation" path="confirmation"/>
		<acme:submit code="inventor.patronage.form.button.create" action="#"/>
	</jstl:if>
</acme:form>
