<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronageReport.list.label.creationMoment" path="creationMoment" width="20%"/>	
	<acme:list-column code="patron.patronageReport.list.label.memorandum" path="memorandum" width="20%"/>
	<acme:list-column code="patron.patronageReport.list.label.automaticSequenceNumber" path="automaticSequenceNumber" width="15%"/>
	<acme:list-column code="patron.patronageReport.list.label.link" path="link" width="15%"/>
</acme:list>