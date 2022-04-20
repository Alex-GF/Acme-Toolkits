<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="any.toolkit.form.label.code" path="code"/>	
	<acme:input-textbox code="any.toolkit.form.label.title" path="title"/>
	<acme:input-money code="any.toolkit.form.label.totalPrice" path="totalPrice"/>
	<acme:input-textbox code="any.toolkit.form.label.description" path="description"/>
	<acme:input-textbox code="any.toolkit.form.label.inventor" path="inventor.fullName"/>
	<acme:input-textbox code="any.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-url code="any.toolkit.form.label.link" path="link"/>
	
	<acme:button code="any.toolkit.form.label.items" action="/inventor/quantity/list?toolkitId=${id}"/>
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="any.toolkit.form.label.confirmation" path="confirmation"/>
		<acme:submit code="any.toolkit.form.button.create" action="#"/>
	</jstl:if>
</acme:form>
