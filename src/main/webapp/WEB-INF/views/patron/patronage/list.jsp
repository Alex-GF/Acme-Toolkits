<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronage.list.label.status" path="status" width="20%"/>	
	<acme:list-column code="patron.patronage.list.label.code" path="code" width="20%"/>
	<acme:list-column code="patron.patronage.list.label.legalStuff" path="legalStuff" width="15%"/>
	<acme:list-column code="patron.patronage.list.label.budget" path="budget" width="15%"/>
	<acme:list-column code="patron.patronage.list.label.creationMoment" path="creationMoment" width="15%"/>
	<acme:list-column code="patron.patronage.list.label.startDate" path="startDate" width="15%"/>
	<acme:list-column code="patron.patronage.list.label.finishDate" path="finishDate" width="15%"/>
	<acme:list-column code="patron.patronage.list.label.link" path="link" width="15%"/>
</acme:list>