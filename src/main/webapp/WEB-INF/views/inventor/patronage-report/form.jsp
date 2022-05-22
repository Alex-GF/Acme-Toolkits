<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	
	<jstl:if test="${command == 'create' }">
			<acme:input-select  code="inventor.patronageReport.form.label.patronage" path="patronageId">
				<jstl:forEach items="${patronageList}" var="patronages">
					<acme:input-option code="${patronages.code}" value="${patronages.id}"/>
				</jstl:forEach>
			</acme:input-select>
	</jstl:if>
	
	<acme:input-textbox code="inventor.patronageReport.form.label.creationMoment" path="creationMoment" readonly="true"/>	
	<acme:input-textbox code="inventor.patronageReport.form.label.memorandum" path="memorandum" />
	<acme:input-url code="inventor.patronageReport.form.label.link" path="link" />
	
	<jstl:if test="${command != 'create'}">
		<acme:input-textbox code="inventor.patronageReport.form.label.automaticSequenceNumber" path="automaticSequenceNumber" readonly="true"/>
		<acme:button code="inventor.patronageReport.form.label.patronage" action="/inventor/patronage/show?id=${patronage.id}"/>
	</jstl:if>

	<jstl:if test="${command == 'create'}">
		<acme:input-checkbox code="inventor.patronageReport.form.label.confirmation" path="confirmation"/>
		<acme:submit code="inventor.patronageReport.form.button.create" action="/inventor/patronage-report/create"/>
	</jstl:if>
</acme:form>