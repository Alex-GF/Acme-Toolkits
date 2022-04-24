<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.item.list.label.name" path="name" width="23%"/>	
	<acme:list-column code="inventor.item.list.label.code" path="code" width="23%"/>
	<acme:list-column code="inventor.item.list.label.technology" path="technology" width="13%"/>
	<acme:list-column code="inventor.item.list.label.retail-price" path="retailPrice" width="13%"/>
	<acme:list-column code="inventor.item.list.label.type" path="type" width="13%"/>
</acme:list>