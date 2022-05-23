<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.toolkit.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="any.toolkit.form.label.title" path="title"/>
	<jstl:if test="${command == 'show'}">
		<acme:input-money code="any.toolkit.form.label.totalPrice" path="totalPrice" readonly="true"/>
	</jstl:if>
	<acme:input-textbox code="any.toolkit.form.label.description" path="description"/>
	<jstl:if test="${command == 'show'}">
		<acme:input-textbox code="any.toolkit.form.label.inventor" path="inventor.fullName" readonly="true"/>
	</jstl:if>
	<acme:input-textbox code="any.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-url code="any.toolkit.form.label.link" path="link"/>
	<acme:input-textbox code="any.toolkit.form.label.published" path="published" readonly="true"/>
	
	<jstl:if test="${command=='show'}">
		<acme:button code="any.toolkit.form.label.items" action="/inventor/quantity/list?toolkitId=${id}"/>
	</jstl:if>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete') && published == false}">
			<acme:submit code="inventor.toolkit.form.button.update" action="/inventor/toolkit/update"/>
			<acme:submit code="inventor.toolkit.form.button.delete" action="/inventor/toolkit/delete"/>
			<jstl:if test="${canPublish}">
				<acme:submit code="inventor.toolkit.form.button.publish" action="/inventor/toolkit/publish"/>
			</jstl:if>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.toolkit.form.button.create" action="/inventor/toolkit/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>
