<%@page language="java" import="java.util.HashMap"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<link rel="stylesheet" href="css/acme.css" />

<acme:form readonly="true">

	<div class="dashboard-row">
		<div class="dashboard-column">
			<acme:message code="administrator.dashboard.average" />
			<canvas id="average-canvas"></canvas>
		</div>
		<div class="dashboard-column">
			<acme:message code="administrator.dashboard.deviation" />
			<canvas id="deviation-canvas"></canvas>
		</div>
	</div>
	<br>
	<div class="dashboard-row">
		<div class="dashboard-column">
			<acme:message code="administrator.dashboard.max" />
			<canvas id="maximum-canvas"></canvas>
		</div>
		<div class="dashboard-column">
			<acme:message code="administrator.dashboard.min" />
			<canvas id="minimum-canvas"></canvas>
		</div>
	</div>

	<div class="dashboard">
		<ul class="statistics">
			<li style="list-style: none;"><strong><acme:message
						code="patron.dashboard.patronage" /></strong></li>
			<li><acme:message code="patron.dashboard.proposed" />: <acme:print
					value="${dashboard.getPatronagesProposed()}" /></li>
			<li><acme:message code="patron.dashboard.accepted" />: <acme:print
					value="${dashboard.getPatronagesAccepted()}" /></li>
			<li><acme:message code="patron.dashboard.denied" />: <acme:print
					value="${dashboard.getPatronagesDenied()}" /></li>
		</ul>
		<ul class="statistics">
			<li style="list-style: none;"><strong><acme:message
						code="patron.dashboard.average" /></strong></li>
			<li><fmt:formatNumber type="number" maxFractionDigits="2"
					value="${averageProposedEUR}" /> &euro; / <fmt:formatNumber
					type="number" maxFractionDigits="2" value="${averageProposedUSD}" />
				$ / <fmt:formatNumber type="number" maxFractionDigits="2"
					value="${averageProposedGBP}" /> &#163;</li>
			<li><fmt:formatNumber type="number" maxFractionDigits="2"
					value="${averageAcceptedEUR}" /> &euro; / <fmt:formatNumber
					type="number" maxFractionDigits="2" value="${averageAcceptedUSD}" />
				$ / <fmt:formatNumber type="number" maxFractionDigits="2"
					value="${averageAcceptedGBP}" /> &#163;</li>
			<li><fmt:formatNumber type="number" maxFractionDigits="2"
					value="${averageDeniedEUR}" /> &euro; / <fmt:formatNumber
					type="number" maxFractionDigits="2" value="${averageDeniedUSD}" />
				$ / <fmt:formatNumber type="number" maxFractionDigits="2"
					value="${averageDeniedGBP}" /> &#163;</li>
		</ul>
		<ul class="statistics">
			<li style="list-style: none;"><strong><acme:message
						code="patron.dashboard.deviation" /></strong></li>
			<li><fmt:formatNumber type="number" maxFractionDigits="2"
					value="${deviationProposedEUR}" /> &euro; / <fmt:formatNumber
					type="number" maxFractionDigits="2" value="${deviationProposedUSD}" />
				$ / <fmt:formatNumber type="number" maxFractionDigits="2"
					value="${deviationProposedGBP}" /> &#163;</li>
			<li><fmt:formatNumber type="number" maxFractionDigits="2"
					value="${deviationAcceptedEUR}" /> &euro; / <fmt:formatNumber
					type="number" maxFractionDigits="2" value="${deviationAcceptedUSD}" />
				$ / <fmt:formatNumber type="number" maxFractionDigits="2"
					value="${deviationAcceptedGBP}" /> &#163;</li>
			<li><fmt:formatNumber type="number" maxFractionDigits="2"
					value="${deviationDeniedEUR}" /> &euro; / <fmt:formatNumber
					type="number" maxFractionDigits="2" value="${deviationDeniedUSD}" />
				$ / <fmt:formatNumber type="number" maxFractionDigits="2"
					value="${deviationDeniedGBP}" /> &#163;</li>
		</ul>
		<ul class="statistics">
			<li style="list-style: none;"><strong><acme:message
						code="patron.dashboard.max" /></strong></li>
			<li><fmt:formatNumber type="number" maxFractionDigits="2"
					value="${maxProposedEUR}" /> &euro; / <fmt:formatNumber
					type="number" maxFractionDigits="2" value="${maxProposedUSD}" /> $
				/ <fmt:formatNumber type="number" maxFractionDigits="2"
					value="${maxProposedGBP}" /> &#163;</li>
			<li><fmt:formatNumber type="number" maxFractionDigits="2"
					value="${maxAcceptedEUR}" /> &euro; / <fmt:formatNumber
					type="number" maxFractionDigits="2" value="${maxAcceptedUSD}" /> $
				/ <fmt:formatNumber type="number" maxFractionDigits="2"
					value="${maxAcceptedGBP}" /> &#163;</li>
			<li><fmt:formatNumber type="number" maxFractionDigits="2"
					value="${maxDeniedEUR}" /> &euro; / <fmt:formatNumber
					type="number" maxFractionDigits="2" value="${maxDeniedUSD}" /> $ /
				<fmt:formatNumber type="number" maxFractionDigits="2"
					value="${maxDeniedGBP}" /> &#163;</li>
		</ul>
		<ul class="statistics">
			<li style="list-style: none;"><strong><acme:message
						code="patron.dashboard.min" /></strong></li>
			<li><fmt:formatNumber type="number" maxFractionDigits="2"
					value="${minProposedEUR}" /> &euro; / <fmt:formatNumber
					type="number" maxFractionDigits="2" value="${minProposedUSD}" /> $
				/ <fmt:formatNumber type="number" maxFractionDigits="2"
					value="${minProposedGBP}" /> &#163;</li>
			<li><fmt:formatNumber type="number" maxFractionDigits="2"
					value="${minAcceptedEUR}" /> &euro; / <fmt:formatNumber
					type="number" maxFractionDigits="2" value="${minAcceptedUSD}" /> $
				/ <fmt:formatNumber type="number" maxFractionDigits="2"
					value="${minAcceptedGBP}" /> &#163;</li>
			<li><fmt:formatNumber type="number" maxFractionDigits="2"
					value="${minDeniedEUR}" /> &euro; / <fmt:formatNumber
					type="number" maxFractionDigits="2" value="${minDeniedUSD}" /> $ /
				<fmt:formatNumber type="number" maxFractionDigits="2"
					value="${minDeniedGBP}" /> &#163;</li>
		</ul>
	</div>
</acme:form>

<script type="text/javascript">
	var backgroundColor= [
  	  'rgb(44, 243, 125)',
      'rgb(239, 49, 221)',
      'rgb(237, 239, 49)',
      'rgb(44, 192, 182)',
      'rgb(59, 162, 22)',
      'rgb(22, 100, 252)',
      'rgb(202, 22, 203)',
      'rgb(239, 82, 49)',
      'rgb(82, 239, 49)'
    ];
	
	displayPatronagesAverage();
	displayPatronagesDeviation();
	displayPatronagesMaximum();
	displayPatronagesMinimum();
	
	function displayPatronagesAverage(){
		var averagePatronages = {
		   	   	<jstl:forEach items="${dashboard.getPatronagesAverage()}" var="item" varStatus="loop">
		   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
		   	   	</jstl:forEach>
		   	   };
		   
		   const averageValues = {
		    labels: Object.keys(averagePatronages),
		    datasets: [{
		      data: Object.values(averagePatronages),
		      backgroundColor: backgroundColor
		    }]
		   };
		   
		   var canvas = document.getElementById("average-canvas");
		   var context = canvas.getContext("2d");
		   new Chart(context, {
		   	type : "pie",
		   	data : averageValues,
		   });
	}
	
	function displayPatronagesDeviation(){
		var deviationPatronages = {
		   	   	<jstl:forEach items="${dashboard.getPatronagesDeviation()}" var="item" varStatus="loop">
		   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
		   	   	</jstl:forEach>
		   	   };
		   
		   const deviationValues = {
		    labels: Object.keys(deviationPatronages),
		    datasets: [{
		      data: Object.values(deviationPatronages),
		      backgroundColor: backgroundColor
		    }]
		   };
		   
		   var canvas = document.getElementById("deviation-canvas");
		   var context = canvas.getContext("2d");
		   new Chart(context, {
		   	type : "pie",
		   	data : deviationValues,
		   });
	}
	
	function displayPatronagesMaximum(){
		var maximumPatronages = {
		   	   	<jstl:forEach items="${dashboard.getPatronagesMaximum()}" var="item" varStatus="loop">
		   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
		   	   	</jstl:forEach>
		   	   };
		   
		   const maximumValues = {
		    labels: Object.keys(maximumPatronages),
		    datasets: [{
		      data: Object.values(maximumPatronages),
		      backgroundColor: backgroundColor
		    }]
		   };
		   
		   var canvas = document.getElementById("maximum-canvas");
		   var context = canvas.getContext("2d");
		   new Chart(context, {
		   	type : "pie",
		   	data : maximumValues,
		   });
	}
	
	function displayPatronagesMinimum(){
		var minimumPatronages = {
		   	   	<jstl:forEach items="${dashboard.getPatronagesMinimum()}" var="item" varStatus="loop">
		   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
		   	   	</jstl:forEach>
		   	   };
		   
		   const minimumValues = {
		    labels: Object.keys(minimumPatronages),
		    datasets: [{
		      data: Object.values(minimumPatronages),
		      backgroundColor: backgroundColor
		    }]
		   };
		   
		   var canvas = document.getElementById("minimum-canvas");
		   var context = canvas.getContext("2d");
		   new Chart(context, {
		   	type : "pie",
		   	data : minimumValues,
		   });
	}
</script>