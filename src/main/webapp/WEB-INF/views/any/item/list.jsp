<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.item.list.label.code" path="code"/>
	<acme:list-column code="authenticated.item.list.label.name" path="name"/>
	<acme:list-column code="authenticated.item.list.label.technology" path="technology"/>
	<acme:list-column code="authenticated.item.list.label.retailPrice" path="retailPrice"/>
</acme:list>