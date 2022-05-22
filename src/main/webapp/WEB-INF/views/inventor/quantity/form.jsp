<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

 
<acme:form>
	<jstl:choose>
	
		<jstl:when test="${command == 'create' }">
			<acme:input-select  code="inventor.quantity.form.label.item" path="itemId">
				<jstl:forEach items="${itemList}" var="items">
					<acme:input-option code="${items.name}" value="${items.id}"/>
				</jstl:forEach>
			</acme:input-select>
		</jstl:when>
		<jstl:otherwise>
			<acme:input-textbox  code="inventor.quantity.form.label.item" path="item.name" readonly="true"/>
		</jstl:otherwise>
	
	</jstl:choose>
	<acme:input-textbox  code="inventor.quantity.form.label.toolkit" path="toolkit.title" readonly="true"/>
	<acme:input-textbox code="inventor.quantity.form.label.inventor" path="toolkit.inventor.fullName" readonly="true"/>
	<acme:input-textbox code="inventor.quantity.form.label.quantity" path="amount" readonly="${isPublished}"/>

	<jstl:choose>
		
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.quantity.form.button.create" action="/inventor/quantity/create"/>
		</jstl:when>
		<jstl:when test="${command == 'show'}">
			<acme:submit code="inventor.quantity.form.button.update" action="/inventor/quantity/update"/>
			<acme:submit code="inventor.quantity.form.button.delete" action="/inventor/quantity/delete"/>
		</jstl:when>
	
	</jstl:choose>

</acme:form>
