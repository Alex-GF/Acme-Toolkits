<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="patron.patronageReport.form.label.creationMoment" path="creationMoment" />	
	<acme:input-textbox code="patron.patronageReport.form.label.memorandum" path="memorandum" />
	<acme:input-textbox code="patron.patronageReport.form.label.automaticSequenceNumber" path="automaticSequenceNumber" />
	<acme:input-url code="patron.patronageReport.form.label.link" path="link" />
	
	<acme:button code="patron.patronageReport.form.label.patronage" action="/patron/patronage/show?id=${patronage.id}"/>
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="patron.patronageReport.form.label.confirmation" path="confirmation"/>
		<acme:submit code="patron.patronageReport.form.button.create" action="#"/>
	</jstl:if>
</acme:form>
