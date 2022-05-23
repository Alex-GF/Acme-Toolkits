<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="patron.patronage.form.label.status" path="status" readonly="true"/>
	<acme:input-textbox readonly="true" code="patron.patronage.form.label.code" path="code" />
	<acme:input-textbox code="patron.patronage.form.label.legalStuff" path="legalStuff" />
	
	<jstl:choose>
		<jstl:when test="${showDefaultCurrency}">
			<acme:input-money code="patron.patronage.form.label.budget" path="defaultCurrency"/>
			<acme:input-money code="patron.patronage.form.label.budget" path="budget" readonly="true"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
		</jstl:otherwise>
	</jstl:choose>
	
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
	<jstl:if test="${command == 'show'}">
		<acme:button code="user-account.form.label.inventor" action="/any/user-account/show?id=${inventor.userAccount.id}"/>
	</jstl:if>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete') && published == false}">
			<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
		</jstl:when>		
	</jstl:choose>

</acme:form>