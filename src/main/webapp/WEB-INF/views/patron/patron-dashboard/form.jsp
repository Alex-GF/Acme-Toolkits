<%-- - form.jsp - - Copyright (C) 2012-2022 Rafael Corchuelo. - - In keeping with the traditional purpose of furthering
education and research, it is - the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular - purposes. The copyright
owner does not offer any warranties or representations, nor do - they accept any liabilities with respect to them.
--%>

<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags" %>

<span id="patron.dashboard.form.title">
	<acme:message code="patron.dashboard.form.title" />
</span>
<span id="patron.dashboard.form.label.number-of-patronages-by-status">
	<acme:message code="patron.dashboard.form.label.number-of-patronages-by-status" />
</span>
<span id="patron.dashboard.form.label.average-number-of-budgets-by-currency-and-status">
	<acme:message code="patron.dashboard.form.label.average-number-of-budgets-by-currency-and-status" />
</span>
<span id="patron.dashboard.form.label.deviation-of-budgets-by-currency-and-status">
	<acme:message code="patron.dashboard.form.label.deviation-of-budgets-by-currency-and-status" />
</span>
<span id="patron.dashboard.form.label.min-budget-by-currency-and-status">
	<acme:message code="patron.dashboard.form.label.min-budget-by-currency-and-status" />
</span>
<span id="patron.dashboard.form.label.max-budget-by-currency-and-status">
	<acme:message code="patron.dashboard.form.label.max-budget-by-currency-and-status" />
</span>

<script type="text/javascript">
	$(document).ready(function () {
		createDashboard("${items}", "${methods}");
	});
</script>