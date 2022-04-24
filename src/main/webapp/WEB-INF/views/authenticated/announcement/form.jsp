<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="authenticated.announcement.form.label.creationMoment" path="creationMoment"/>	
	<acme:input-textbox code="authenticated.announcement.form.label.title" path="title"/>
	<acme:input-textarea code="authenticated.announcement.form.label.body" path="body"/>
	<acme:input-textbox code="authenticated.announcement.form.label.criticalFlag" path="criticalFlag"/>
	<acme:input-url code="authenticated.announcement.form.label.link" path="link"/>
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="authenticated.announcement.form.label.confirmation" path="confirmation"/>
		<acme:submit code="authenticated.announcement.form.button.create" action="#"/>
	</jstl:if>
</acme:form>
