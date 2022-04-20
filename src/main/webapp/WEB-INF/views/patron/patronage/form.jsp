<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="patron.patronage.form.label.status" path="status" />	
	<acme:input-textbox code="patron.patronage.form.label.code" path="code" />
	<acme:input-textbox code="patron.patronage.form.label.legalStuff" path="legalStuff" />
	<acme:input-money code="patron.patronage.form.label.budget" path="budget" />
	<acme:input-textbox code="patron.patronage.form.label.creationMoment" path="creationMoment" />
	<acme:input-textbox code="patron.patronage.form.label.startDate" path="startDate" />
	<acme:input-textbox code="patron.patronage.form.label.finishDate" path="finishDate" />
	<acme:input-textbox code="patron.patronage.form.label.link" path="link" />
	
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="patron.patronage.form.label.confirmation" path="confirmation"/>
		<acme:submit code="patron.patronage.form.button.create" action="#"/>
	</jstl:if>

	<acme:button code="user-account.form.label.inventor" action="/any/user-account/show?id=${inventor.userAccount.id}"/>
</acme:form>