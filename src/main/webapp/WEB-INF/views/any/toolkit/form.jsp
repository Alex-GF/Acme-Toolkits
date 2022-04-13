<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="any.toolkit.form.label.code" path="code"/>	
	<acme:input-textbox code="any.toolkit.form.label.name" path="name"/>
	<acme:input-textbox code="any.toolkit.form.label.technology" path="technology"/>
	<acme:input-textbox code="any.toolkit.form.label.retailPrice" path="retailPrice"/>
	<acme:input-textbox code="any.toolkit.form.label.description" path="description"/>
	<acme:input-url code="any.toolkit.form.label.link" path="link"/>
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="any.toolkit.form.label.confirmation" path="confirmation"/>
		<acme:submit code="any.toolkit.form.button.create" action="#"/>
	</jstl:if>
</acme:form>
