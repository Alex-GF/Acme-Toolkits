<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="any.item.form.label.code" path="code"/>	
	<acme:input-textbox code="any.item.form.label.name" path="name"/>
	<acme:input-textbox code="any.item.form.label.technology" path="technology"/>
	<acme:input-money code="any.item.form.label.retailPrice" path="retailPrice"/>
	<acme:input-textbox code="any.item.form.label.description" path="description"/>
	<acme:input-url code="any.item.form.label.link" path="link"/>
	
	<!--<jstl:if test="${!readonly}">
		<acme:input-checkbox code="any.item..form.label.confirmation" path="confirmation"/>
		<acme:submit code="any.item..form.button.create" action="#"/>
	</jstl:if>-->
</acme:form>
