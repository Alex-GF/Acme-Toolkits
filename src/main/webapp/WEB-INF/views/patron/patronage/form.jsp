<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:form-column code="patron.patronage.form.label.status" path="status" />	
	<acme:form-column code="patron.patronage.form.label.code" path="code" />
	<acme:form-column code="patron.patronage.form.label.legalStuff" path="legalStuff" />
	<acme:form-column code="patron.patronage.form.label.budget" path="budget" />
	<acme:form-column code="patron.patronage.form.label.creationMoment" path="creationMoment" />
	<acme:form-column code="patron.patronage.form.label.startDate" path="startDate" />
	<acme:form-column code="patron.patronage.form.label.finishDate" path="finishDate" />
	<acme:form-column code="patron.patronage.form.label.link" path="link" />
	
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="patron.patronage.form.label.confirmation" path="confirmation"/>
		<acme:submit code="patron.patronage.form.button.create" action="#"/>
	</jstl:if>

	<acme:button code="patron.patronage.form.label.confirmation" action="/any/user-account/show?id=${inventor.userAccount.id}"/>
</acme:form>