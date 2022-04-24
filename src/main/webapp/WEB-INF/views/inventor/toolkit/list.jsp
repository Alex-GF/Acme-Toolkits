<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.quantity.list.label.code" path="code" width="50%"/>	
	<acme:list-column code="inventor.quantity.list.label.title" path="title" width="50%"/>
</acme:list>