<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="inventor.patronageReport.form.label.creationMoment" path="creationMoment" readonly="true"/>	
	<acme:input-textbox code="inventor.patronageReport.form.label.memorandum" path="memorandum" />
	<acme:input-textbox code="inventor.patronageReport.form.label.automaticSequenceNumber" path="automaticSequenceNumber" />
	<acme:input-url code="inventor.patronageReport.form.label.link" path="link" />
	
	<acme:button code="inventor.patronageReport.form.label.patronage" action="/inventor/patronage/show?id=${patronage.id}"/>
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="inventor.patronageReport.form.label.confirmation" path="confirmation"/>
		<acme:submit code="inventor.patronageReport.form.button.create" action="/inventor/patronageReport/create"/>
	</jstl:if>
</acme:form>
