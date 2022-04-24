<%@page language="java" import="java.util.HashMap"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<link rel="stylesheet" href="css/acme.css"/>

<acme:form readonly="true">
	<b class="center"><acme:message code="administrator.dashboard.patronage"/></b>
	
	<div class="dashboard-row">
	   <div class="dashboard-column">
	      <acme:message code="administrator.dashboard.average"/>
	      <canvas id="average-patronages-canvas"></canvas>
	   </div>
	   <div class="dashboard-column">
	      <acme:message code="administrator.dashboard.deviation"/>
	      <canvas id="deviation-patronages-canvas"></canvas>
	   </div>
	</div>
   <br>
   <div class="dashboard-row">
	   <div class="dashboard-column">
	      <acme:message code="administrator.dashboard.max"/>
	      <canvas id="maximum-patronages-canvas"></canvas>
	   </div>
	   <div class="dashboard-column">
	      <acme:message code="administrator.dashboard.min"/>
	      <canvas id="minimum-patronages-canvas"></canvas>
	   </div>
  </div>

<div class="dashboard">
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.patronage"/></b>
		<li>
			<acme:message code="administrator.dashboard.proposed"/>: <acme:print value="${dashboard.getPatronagesProposed()}"/>
		</li>
		<li>
			<acme:message code="administrator.dashboard.accepted"/>: <acme:print value="${dashboard.getPatronagesAccepted()}"/>
		</li>
		<li>
			<acme:message code="administrator.dashboard.denied"/>: <acme:print value="${dashboard.getPatronagesDenied()}"/>
		</li>
	</ul>
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.average"/></b>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesAverageAcceptedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesAverageProposedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesAverageProposedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesAverageAcceptedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesAverageAcceptedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesAverageAcceptedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesAverageDeniedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesAverageDeniedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesAverageDeniedGBP}"/> &#163;
		</li>
	</ul>
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.deviation"/></b>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationProposedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationProposedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationProposedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationAcceptedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationAcceptedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationAcceptedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationDeniedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationDeniedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationDeniedGBP}"/> &#163;
		</li>
	</ul>
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.max"/></b>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMaxProposedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMaxProposedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMaxProposedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMaxAcceptedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMaxAcceptedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMaxAcceptedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMaxDeniedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMaxDeniedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMaxDeniedGBP}"/> &#163;
		</li>
	</ul>
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.min"/></b>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMinProposedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMinProposedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMinProposedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMinAcceptedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMinAcceptedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMinAcceptedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMinDeniedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMinDeniedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesMinDeniedGBP}"/> &#163;
		</li>
	</ul>
</div>
<br/>
<b class="center"><acme:message code="administrator.dashboard.tools"/></b>
<div class="dashboard-row">
	   <div class="dashboard-column">
	      <acme:message code="administrator.dashboard.average"/>
	      <canvas id="average-tools-canvas"></canvas>
	   </div>
	   <div class="dashboard-column">
	      <acme:message code="administrator.dashboard.deviation"/>
	      <canvas id="deviation-tools-canvas"></canvas>
	   </div>
	</div>
   <br>
   <div class="dashboard-row">
	   <div class="dashboard-column">
	      <acme:message code="administrator.dashboard.max"/>
	      <canvas id="maximum-tools-canvas"></canvas>
	   </div>
	   <div class="dashboard-column">
	      <acme:message code="administrator.dashboard.min"/>
	      <canvas id="minimum-tools-canvas"></canvas>
	   </div>
  </div>

<div class="dashboard">
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.average"/></b>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${toolsAverageEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${toolsAverageUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${toolsAverageGBP}"/> &#163;
		</li>

	</ul>
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.deviation"/></b>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${toolsDeviationEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${toolsDeviationUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${toolsDeviationGBP}"/> &#163;
		</li>

	</ul>
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.max"/></b>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${toolsMaxEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${toolsMaxUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${toolsMaxGBP}"/> &#163;
		</li>

	</ul>
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.min"/></b>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${toolsMinEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${toolsMinUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${toolsMinGBP}"/> &#163;
		</li>

	</ul>
</div>
<br/>
<b class="center"><acme:message code="administrator.dashboard.components"/></b>
<div class="dashboard-row">
	   <div class="dashboard-column">
	      <acme:message code="administrator.dashboard.average"/>
	      <canvas id="average-components-canvas"></canvas>
	   </div>
	   <div class="dashboard-column">
	      <acme:message code="administrator.dashboard.deviation"/>
	      <canvas id="deviation-components-canvas"></canvas>
	   </div>
	</div>
   <br>
   <div class="dashboard-row">
	   <div class="dashboard-column">
	      <acme:message code="administrator.dashboard.max"/>
	      <canvas id="maximum-components-canvas"></canvas>
	   </div>
	   <div class="dashboard-column">
	      <acme:message code="administrator.dashboard.min"/>
	      <canvas id="minimum-components-canvas"></canvas>
	   </div>
  </div>
<div class="dashboard">
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.technologies"/></b>
		<jstl:forEach items="${technologies}" var="technology">
			<li>
				<acme:print value="${technology}"/>
			</li>
		</jstl:forEach>
	</ul>
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.average"/></b>
		<jstl:forEach items="${componentsAverage}" var="average">
			<li>
				<fmt:formatNumber type="number" maxFractionDigits="2" value="${average[0]}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${average[1]}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${average[2]}"/> &#163;
			</li>
		</jstl:forEach>

	</ul>
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.deviation"/></b>
		<jstl:forEach items="${componentsDeviation}" var="deviation">
			<li>
				<fmt:formatNumber type="number" maxFractionDigits="2" value="${deviation[0]}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${deviation[1]}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${deviation[2]}"/> &#163;
			</li>
		</jstl:forEach>
	</ul>
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.max"/></b>
		<jstl:forEach items="${componentsMax}" var="max">
			<li>
				<fmt:formatNumber type="number" maxFractionDigits="2" value="${max[0]}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${max[1]}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${max[2]}"/> &#163;
			</li>
		</jstl:forEach>
	</ul>
	<ul class="stadistics">
		<b><acme:message code="administrator.dashboard.min"/></b>
		<jstl:forEach items="${componentsMax}" var="min">
			<li>
				<fmt:formatNumber type="number" maxFractionDigits="2" value="${min[0]}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${min[1]}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${min[2]}"/> &#163;
			</li>
		</jstl:forEach>
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
		   
		   var canvas = document.getElementById("average-patronages-canvas");
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
		   
		   var canvas = document.getElementById("deviation-patronages-canvas");
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
		   
		   var canvas = document.getElementById("maximum-patronages-canvas");
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
		   
		   var canvas = document.getElementById("minimum-patronages-canvas");
		   var context = canvas.getContext("2d");
		   new Chart(context, {
		   	type : "pie",
		   	data : minimumValues,
		   });
	}
</script>
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
	
	displayToolsAverage();
	displayToolsDeviation();
	displayToolsMaximum();
	displayToolsMinimum();
	
	function displayToolsAverage(){
		var averageTools = {
		   	   	<jstl:forEach items="${dashboard.getRetailPriceToolsAverage()}" var="item" varStatus="loop">
		   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
		   	   	</jstl:forEach>
		   	   };
		   
		   const averageValues = {
		    labels: Object.keys(averageTools),
		    datasets: [{
		      data: Object.values(averageTools),
		      backgroundColor: backgroundColor
		    }]
		   };
		   
		   var canvas = document.getElementById("average-tools-canvas");
		   var context = canvas.getContext("2d");
		   new Chart(context, {
		   	type : "pie",
		   	data : averageValues,
		   });
	}
	
	function displayToolsDeviation(){
		var deviationTools = {
		   	   	<jstl:forEach items="${dashboard.getRetailPriceToolsDeviation()}" var="item" varStatus="loop">
		   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
		   	   	</jstl:forEach>
		   	   };
		   
		   const deviationValues = {
		    labels: Object.keys(deviationTools),
		    datasets: [{
		      data: Object.values(deviationTools),
		      backgroundColor: backgroundColor
		    }]
		   };
		   
		   var canvas = document.getElementById("deviation-tools-canvas");
		   var context = canvas.getContext("2d");
		   new Chart(context, {
		   	type : "pie",
		   	data : deviationValues,
		   });
	}
	
	function displayToolsMaximum(){
		var maximumTools = {
		   	   	<jstl:forEach items="${dashboard.getRetailPriceToolsMaximum()}" var="item" varStatus="loop">
		   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
		   	   	</jstl:forEach>
		   	   };
		   
		   const maximumValues = {
		    labels: Object.keys(maximumTools),
		    datasets: [{
		      data: Object.values(maximumTools),
		      backgroundColor: backgroundColor
		    }]
		   };
		   
		   var canvas = document.getElementById("maximum-tools-canvas");
		   var context = canvas.getContext("2d");
		   new Chart(context, {
		   	type : "pie",
		   	data : maximumValues,
		   });
	}
	
	function displayToolsMinimum(){
		var minimumTools = {
		   	   	<jstl:forEach items="${dashboard.getRetailPriceToolsMinimum()}" var="item" varStatus="loop">
		   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
		   	   	</jstl:forEach>
		   	   };
		   
		   const minimumValues = {
		    labels: Object.keys(minimumTools),
		    datasets: [{
		      data: Object.values(minimumTools),
		      backgroundColor: backgroundColor
		    }]
		   };
		   
		   var canvas = document.getElementById("minimum-tools-canvas");
		   var context = canvas.getContext("2d");
		   new Chart(context, {
		   	type : "pie",
		   	data : minimumValues,
		   });
	}
</script>
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
	
	displayComponentsAverage();
	displayComponentsDeviation();
	displayComponentsMaximum();
	displayComponentsMinimum();
	
	function displayComponentsAverage(){
		var averageComponents = {
		   	   	<jstl:forEach items="${dashboard.getRetailPriceComponentsAverage()}" var="item" varStatus="loop">
		   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
		   	   	</jstl:forEach>
		   	   };
		   
		   const averageValues = {
		    labels: Object.keys(averageComponents),
		    datasets: [{
		      data: Object.values(averageComponents),
		      backgroundColor: backgroundColor
		    }]
		   };
		   
		   var canvas = document.getElementById("average-components-canvas");
		   var context = canvas.getContext("2d");
		   new Chart(context, {
		   	type : "pie",
		   	data : averageValues,
		   });
	}
	
	function displayComponentsDeviation(){
		var deviationComponents = {
		   	   	<jstl:forEach items="${dashboard.getRetailPriceComponentsDeviation()}" var="item" varStatus="loop">
		   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
		   	   	</jstl:forEach>
		   	   };
		   
		   const deviationValues = {
		    labels: Object.keys(deviationComponents),
		    datasets: [{
		      data: Object.values(deviationComponents),
		      backgroundColor: backgroundColor
		    }]
		   };
		   
		   var canvas = document.getElementById("deviation-components-canvas");
		   var context = canvas.getContext("2d");
		   new Chart(context, {
		   	type : "pie",
		   	data : deviationValues,
		   });
	}
	
	function displayComponentsMaximum(){
		var maximumComponents = {
		   	   	<jstl:forEach items="${dashboard.getRetailPriceComponentsMaximum()}" var="item" varStatus="loop">
		   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
		   	   	</jstl:forEach>
		   	   };
		   
		   const maximumValues = {
		    labels: Object.keys(maximumComponents),
		    datasets: [{
		      data: Object.values(maximumComponents),
		      backgroundColor: backgroundColor
		    }]
		   };
		   
		   var canvas = document.getElementById("maximum-components-canvas");
		   var context = canvas.getContext("2d");
		   new Chart(context, {
		   	type : "pie",
		   	data : maximumValues,
		   });
	}
	
	function displayComponentsMinimum(){
		var minimumComponents = {
		   	   	<jstl:forEach items="${dashboard.getRetailPriceComponentsMinimum()}" var="item" varStatus="loop">
		   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
		   	   	</jstl:forEach>
		   	   };
		   
		   const minimumValues = {
		    labels: Object.keys(minimumComponents),
		    datasets: [{
		      data: Object.values(minimumComponents),
		      backgroundColor: backgroundColor
		    }]
		   };
		   
		   var canvas = document.getElementById("minimum-components-canvas");
		   var context = canvas.getContext("2d");
		   new Chart(context, {
		   	type : "pie",
		   	data : minimumValues,
		   });
	}
</script>