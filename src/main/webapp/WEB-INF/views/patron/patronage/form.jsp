<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-select code="patron.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox readonly="true" code="patron.patronage.form.label.code" path="code" />
	<acme:input-textbox code="patron.patronage.form.label.legalStuff" path="legalStuff" />
	<acme:input-money code="patron.patronage.form.label.budget" path="budget" />
	<acme:input-moment code="patron.patronage.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:input-moment code="patron.patronage.form.label.startDate" path="startDate" />
	<acme:input-moment code="patron.patronage.form.label.finishDate" path="finishDate" />
	<acme:input-textbox code="patron.patronage.form.label.link" path="link" />
	<acme:input-textbox readonly="true" code="patron.patronage.form.label.published" path="published" />
	<jstl:if test="${ command == 'create' }">
		<acme:input-select code="patron.patronage.form.label.inventor" path="inventor">
			<jstl:forEach items="${ inventorsList }" var="inventors">
				<acme:input-option code="${ inventors.userAccount.identity.surname}, ${ inventors.userAccount.identity.name}" value="${ inventors.id }"/>
			</jstl:forEach>
		</acme:input-select>
	</jstl:if>
	<jstl:choose>
		<jstl:when test="${command == 'show' && published == true}">
			<acme:button code="user-account.form.label.inventor" action="/any/user-account/show?id=${inventor.userAccount.id}"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete') && published == false}">
			<acme:button code="user-account.form.label.inventor" action="/any/user-account/show?id=${inventor.userAccount.id}"/>
			<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
		</jstl:when>		
	</jstl:choose>

</acme:form>