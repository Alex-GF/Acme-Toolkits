<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list >

	<acme:list-column code="inventor.quantity.list.label.code" path="item.code"/>
	<acme:list-column code="inventor.quantity.list.label.name" path="item.name"/>
	<acme:list-column code="inventor.quantity.list.label.technology" path="item.technology"/>
	<acme:list-column code="inventor.quantity.list.label.retailPrice" path="item.retailPrice"/>
	<acme:list-column code="inventor.quantity.list.label.type" path="item.type"/>
	<acme:list-column code="inventor.quantity.list.label.quantity" path="amount"/>

</acme:list>

<jstl:if test="${!isPublished}">
	<acme:button code="inventor.quantity.list.button.create" action="/inventor/quantity/create?toolkitId=${masterId}"/>
</jstl:if>