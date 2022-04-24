<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.announcement.list.label.title" path="title"/>
	<acme:list-column code="authenticated.announcement.list.label.creationMoment" path="creationMoment"/>
	<acme:list-column code="authenticated.announcement.list.label.criticalFlag" path="criticalFlag"/>
</acme:list> 

