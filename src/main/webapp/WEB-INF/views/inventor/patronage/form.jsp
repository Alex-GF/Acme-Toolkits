<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}" >

	<acme:input-select code="inventor.patronage.form.label.status" path="status">
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
	</acme:input-select>
	
	<acme:input-textbox  readonly="true" code="inventor.patronage.form.label.code" path="code"/>
	<acme:input-textbox  readonly="true" code="inventor.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-money   readonly="true" code="inventor.patronage.form.label.budget" path="budget" />
	<acme:input-textbox  readonly="true" code="inventor.patronage.form.label.creationMoment" path="creationMoment" />
	<acme:input-textbox  readonly="true" code="inventor.patronage.form.label.startDate" path="startDate" />
	<acme:input-textbox  readonly="true" code="inventor.patronage.form.label.finishDate" path="finishDate"/>
	<acme:input-url  readonly="true" code="inventor.patronage.form.label.link" path="link" />
	
	<acme:button code="user-account.form.label.patron" action="/any/user-account/show?id=${patron.userAccount.id}"/>
	
	
	<jstl:if test="${status == 'PROPOSED'}">
		<acme:submit code="inventor.patronage.form.button.update" action="/inventor/patronage/update"/>
	</jstl:if>
</acme:form>


