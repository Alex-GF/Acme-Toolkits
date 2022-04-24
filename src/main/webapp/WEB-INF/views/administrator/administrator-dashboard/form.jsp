<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<span id="administrator.dashboard.form.title">
	<acme:message code="administrator.dashboard.form.title" />
</span>
<span id="administrator.dashboard.form.label.number-of-components">
	<acme:message code="administrator.dashboard.form.label.number-of-components" />
</span>
<span id="administrator.dashboard.form.label.average-retail-price-of-components-by-technology-and-currency">
	<acme:message code="administrator.dashboard.form.label.average-retail-price-of-components-by-technology-and-currency" />
</span>
<span id="administrator.dashboard.form.label.deviation-retail-price-of-components-by-technology-and-currency">
	<acme:message code="administrator.dashboard.form.label.deviation-retail-price-of-components-by-technology-and-currency" />
</span>
<span id="administrator.dashboard.form.label.min-retail-price-of-components-by-technology-and-currency">
	<acme:message code="administrator.dashboard.form.label.min-retail-price-of-components-by-technology-and-currency" />
</span>
<span id="administrator.dashboard.form.label.max-retail-price-of-components-by-technology-and-currency">
	<acme:message code="administrator.dashboard.form.label.max-retail-price-of-components-by-technology-and-currency" />
</span>
<span id="administrator.dashboard.form.label.number-of-tools">
	<acme:message code="administrator.dashboard.form.label.number-of-tools" />
</span>
<span id="administrator.dashboard.form.label.deviation-retail-price-of-tools-by-currency">
	<acme:message code="administrator.dashboard.form.label.deviation-retail-price-of-tools-by-currency" />
</span>
<span id="administrator.dashboard.form.label.min-retail-price-of-tools-by-currency">
	<acme:message code="administrator.dashboard.form.label.min-retail-price-of-tools-by-currency" />
</span>
<span id="administrator.dashboard.form.label.max-retail-price-of-tools-by-currency">
	<acme:message code="administrator.dashboard.form.label.max-retail-price-of-tools-by-currency" />
</span>
<span id="administrator.dashboard.form.label.number-of-patronages-by-status">
	<acme:message code="administrator.dashboard.form.label.number-of-patronages-by-status" />
</span>
<span id="administrator.dashboard.form.label.average-budget-of-patronages-by-status">
	<acme:message code="administrator.dashboard.form.label.average-budget-of-patronages-by-status" />
</span>
<span id="administrator.dashboard.form.label.deviation-budget-of-patronages-by-status">
	<acme:message code="administrator.dashboard.form.label.deviation-budget-of-patronages-by-status" />
</span>
<span id="administrator.dashboard.form.label.min-budget-of-patronages-by-status">
	<acme:message code="administrator.dashboard.form.label.min-budget-of-patronages-by-status" />
</span>
<span id="administrator.dashboard.form.label.max-budget-of-patronages-by-status">
	<acme:message code="administrator.dashboard.form.label.max-budget-of-patronages-by-status" />
</span>
<span id="administrator.dashboard.form.label.average-retail-price-of-tools-by-currency">
	<acme:message code="administrator.dashboard.form.label.average-retail-price-of-tools-by-currency" />
</span>

<script type="text/javascript">
	$(document).ready(function () {
		createDashboard("${items}", "${methods}");
	});
</script>