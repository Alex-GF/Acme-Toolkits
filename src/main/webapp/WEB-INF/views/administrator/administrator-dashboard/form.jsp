<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title"/>
</h2>



<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-of-components"/>
		</th>
		<td>
			<acme:print value="${numberOfComponents}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-retail-price-of-components-by-technology-and-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="averageRetailPriceOfComponentsByTechnologyAndCurrency"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-retail-price-of-components-by-technology-and-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="deviationRetailPriceOfComponentsByTechnologyAndCurrency"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.min-retail-price-of-components-by-technology-and-currency"/>
		</th>
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="minRetailPriceOfComponentsByTechnologyAndCurrency"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.max-retail-price-of-components-by-technology-and-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="maxRetailPriceOfComponentsByTechnologyAndCurrency"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-of-tools"/>
		</th>
		<td>
			<acme:print value="${numberOfTools}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-retail-price-of-tool-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="averageRetailPriceOfToolsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-retail-price-of-tool-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="deviationRetailPriceOfToolsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.min-retail-price-of-tool-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="minRetailPriceOfToolsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.max-retail-price-of-tool-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="maxRetailPriceOfToolsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number-of-patronages-by-status"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="numberOfPatronagesByStatus"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-budget-of-patronages-by-status"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="averageBudgetOfPatronagesByStatus"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-budget-of-patronages-by-status"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="deviationBudgetOfPatronagesByStatus"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.min-budget-of-patronages-by-status"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="minBudgetOfPatronagesByStatus"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.max-budget-of-patronages-by-status"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="maxBudgetOfPatronagesByStatus"></canvas>
			</div>
		</td>
	</tr>
	
	
</table>

<script type="text/javascript">
$(document).ready(function() {
	//LABELS
	let ACCEPTED = "ACCEPTED";
	let DENIED = "DENIED";
	let PROPOSED = "PROPOSED";
	let EUR = "EUR";
	let GBP = "GBP";
	let USD = "USD";
	let EURSampleTechnology01 = "EUR <-> ST01";
	let EURSampleTechnology02 = "EUR <-> ST02";
	let EURSampleTechnology03 = "EUR <-> ST03";
	let EURSampleTechnology04 = "EUR <-> ST04";
	let EURSampleTechnology05 = "EUR <-> ST05";
	let EURSampleTechnology06 = "EUR <-> ST06";
	let EURSampleTechnology07 = "EUR <-> ST07";
	let GBPSampleTechnology01 = "GBP <-> ST01";
	let GBPSampleTechnology02 = "GBP <-> ST02";
	let GBPSampleTechnology03 = "GBP <-> ST03";
	let GBPSampleTechnology04 = "GBP <-> ST04";
	let GBPSampleTechnology05 = "GBP <-> ST05";
	let	GBPSampleTechnology06 = "GBP <-> ST06";
	let GBPSampleTechnology07 = "GBP <-> ST07";
	let USDSampleTechnology01 = "USD <-> ST01";
	let USDSampleTechnology02 = "USD <-> ST02";
	let USDSampleTechnology03 = "USD <-> ST03";
	let USDSampleTechnology04 = "USD <-> ST04";
	let USDSampleTechnology05 = "USD <-> ST05";
	let USDSampleTechnology06 = "USD <-> ST06";
	let USDSampleTechnology07 = "USD <-> ST07";
			
	function createChart(labelList, dataList, id){
		var barColors = ["#2F4F4F", "#008080","#3CB371","#11cf43","#004d14", "#769146", "#a5ad97", "#b1f046", "#53574c"];
		var data = {
			labels : labelList,
			datasets : [
				{
					backgroundColor: barColors,
					data : dataList
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
		var canvas, context;
		canvas = document.getElementById(id);
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
		   
	}
	
	
	
	//averageRetailPriceOfComponentsByTechnologyAndCurrency
	let labels1 = [
		<jstl:forEach items="${averageRetailPriceOfComponentsByTechnologyAndCurrency}" var="averageRetailPriceOfComponentsByTechnologyAndCurrency">
			<acme:print value="${averageRetailPriceOfComponentsByTechnologyAndCurrency['key'].getFirst()}${averageRetailPriceOfComponentsByTechnologyAndCurrency['key'].getSecond()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data1 = [
		<jstl:forEach items="${averageRetailPriceOfComponentsByTechnologyAndCurrency}" var="averageRetailPriceOfComponentsByTechnologyAndCurrency">
			<acme:print value="${averageRetailPriceOfComponentsByTechnologyAndCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels1, data1, "averageRetailPriceOfComponentsByTechnologyAndCurrency");
	
	//deviationRetailPriceOfComponentsByTechnologyAndCurrency
	let labels2 = [
		<jstl:forEach items="${deviationRetailPriceOfComponentsByTechnologyAndCurrency}" var="deviationRetailPriceOfComponentsByTechnologyAndCurrency">
			<acme:print value="${deviationRetailPriceOfComponentsByTechnologyAndCurrency['key'].getFirst()}${deviationRetailPriceOfComponentsByTechnologyAndCurrency['key'].getSecond()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data2 = [
		<jstl:forEach items="${deviationRetailPriceOfComponentsByTechnologyAndCurrency}" var="deviationRetailPriceOfComponentsByTechnologyAndCurrency">
			<acme:print value="${deviationRetailPriceOfComponentsByTechnologyAndCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels2, data2, "deviationRetailPriceOfComponentsByTechnologyAndCurrency");
	
	//minRetailPriceOfComponentsByTechnologyAndCurrency
	let labels3 = [
		<jstl:forEach items="${minRetailPriceOfComponentsByTechnologyAndCurrency}" var="minRetailPriceOfComponentsByTechnologyAndCurrency">
			<acme:print value="${minRetailPriceOfComponentsByTechnologyAndCurrency['key'].getFirst()}${minRetailPriceOfComponentsByTechnologyAndCurrency['key'].getSecond()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data3 = [
		<jstl:forEach items="${minRetailPriceOfComponentsByTechnologyAndCurrency}" var="minRetailPriceOfComponentsByTechnologyAndCurrency">
			<acme:print value="${minRetailPriceOfComponentsByTechnologyAndCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels3, data3, "minRetailPriceOfComponentsByTechnologyAndCurrency");
	
	//maxRetailPriceOfComponentsByTechnologyAndCurrency
	let labels4 = [
		<jstl:forEach items="${maxRetailPriceOfComponentsByTechnologyAndCurrency}" var="maxRetailPriceOfComponentsByTechnologyAndCurrency">
			<acme:print value="${maxRetailPriceOfComponentsByTechnologyAndCurrency['key'].getFirst()}${maxRetailPriceOfComponentsByTechnologyAndCurrency['key'].getSecond()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data4 = [
		<jstl:forEach items="${maxRetailPriceOfComponentsByTechnologyAndCurrency}" var="maxRetailPriceOfComponentsByTechnologyAndCurrency">
			<acme:print value="${maxRetailPriceOfComponentsByTechnologyAndCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels4, data4, "maxRetailPriceOfComponentsByTechnologyAndCurrency");

	//averageRetailPriceOfToolsByCurrency
	let labels5 = [
		<jstl:forEach items="${averageRetailPriceOfToolsByCurrency}" var="averageRetailPriceOfToolsByCurrency">
			<acme:print value="${averageRetailPriceOfToolsByCurrency['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data5 = [
		<jstl:forEach items="${averageRetailPriceOfToolsByCurrency}" var="averageRetailPriceOfToolsByCurrency">
			<acme:print value="${averageRetailPriceOfToolsByCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels5, data5, "averageRetailPriceOfToolsByCurrency");
	
	//deviationRetailPriceOfToolsByCurrency
	let labels6 = [
		<jstl:forEach items="${deviationRetailPriceOfToolsByCurrency}" var="deviationRetailPriceOfToolsByCurrency">
			<acme:print value="${deviationRetailPriceOfToolsByCurrency['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data6 = [
		<jstl:forEach items="${deviationRetailPriceOfToolsByCurrency}" var="deviationRetailPriceOfToolsByCurrency">
			<acme:print value="${deviationRetailPriceOfToolsByCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels6, data6, "deviationRetailPriceOfToolsByCurrency");
	
	//minRetailPriceOfToolsByCurrency
	let labels7 = [
		<jstl:forEach items="${minRetailPriceOfToolsByCurrency}" var="minRetailPriceOfToolsByCurrency">
			<acme:print value="${minRetailPriceOfToolsByCurrency['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data7 = [
		<jstl:forEach items="${minRetailPriceOfToolsByCurrency}" var="minRetailPriceOfToolsByCurrency">
			<acme:print value="${minRetailPriceOfToolsByCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels7, data7, "minRetailPriceOfToolsByCurrency");
	
	//averageRetailPriceOfToolsByCurrency
	let labels8 = [
		<jstl:forEach items="${maxRetailPriceOfToolsByCurrency}" var="maxRetailPriceOfToolsByCurrency">
			<acme:print value="${maxRetailPriceOfToolsByCurrency['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data8 = [
		<jstl:forEach items="${maxRetailPriceOfToolsByCurrency}" var="maxRetailPriceOfToolsByCurrency">
			<acme:print value="${maxRetailPriceOfToolsByCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels8, data8, "maxRetailPriceOfToolsByCurrency");
	
	//numberOfPatronagesByStatus
	let labels9 = [
		<jstl:forEach items="${numberOfPatronagesByStatus}" var="numberOfPatronagesByStatus">
			<acme:print value="${numberOfPatronagesByStatus['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data9 = [
		<jstl:forEach items="${numberOfPatronagesByStatus}" var="numberOfPatronagesByStatus">
			<acme:print value="${numberOfPatronagesByStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels9, data9, "numberOfPatronagesByStatus");
	
	//averageBudgetOfPatronagesByStatus
	let labels10 = [
		<jstl:forEach items="${averageBudgetOfPatronagesByStatus}" var="averageBudgetOfPatronagesByStatus">
			<acme:print value="${averageBudgetOfPatronagesByStatus['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data10 = [
		<jstl:forEach items="${averageBudgetOfPatronagesByStatus}" var="averageBudgetOfPatronagesByStatus">
			<acme:print value="${averageBudgetOfPatronagesByStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels10, data10, "averageBudgetOfPatronagesByStatus");
	
	//deviationBudgetOfPatronagesByStatus
	let labels11 = [
		<jstl:forEach items="${deviationBudgetOfPatronagesByStatus}" var="deviationBudgetOfPatronagesByStatus">
			<acme:print value="${deviationBudgetOfPatronagesByStatus['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data11 = [
		<jstl:forEach items="${deviationBudgetOfPatronagesByStatus}" var="deviationBudgetOfPatronagesByStatus">
			<acme:print value="${deviationBudgetOfPatronagesByStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels11, data11, "deviationBudgetOfPatronagesByStatus");
	
	//minBudgetOfPatronagesByStatus
	let labels12 = [
		<jstl:forEach items="${minBudgetOfPatronagesByStatus}" var="minBudgetOfPatronagesByStatus">
			<acme:print value="${minBudgetOfPatronagesByStatus['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data12 = [
		<jstl:forEach items="${minBudgetOfPatronagesByStatus}" var="minBudgetOfPatronagesByStatus">
			<acme:print value="${minBudgetOfPatronagesByStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels12, data12, "minBudgetOfPatronagesByStatus");
	
	//maxBudgetOfPatronagesByStatus
	let labels13 = [
		<jstl:forEach items="${maxBudgetOfPatronagesByStatus}" var="maxBudgetOfPatronagesByStatus">
			<acme:print value="${maxBudgetOfPatronagesByStatus['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let data13 = [
		<jstl:forEach items="${maxBudgetOfPatronagesByStatus}" var="maxBudgetOfPatronagesByStatus">
			<acme:print value="${maxBudgetOfPatronagesByStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	createChart(labels13, data13, "maxBudgetOfPatronagesByStatus");
	
	
	
	
	});
</script>

<acme:return/>


