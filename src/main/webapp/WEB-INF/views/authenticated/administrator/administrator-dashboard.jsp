<%@page language="java" import="java.util.HashMap"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<link rel="stylesheet" href="css/acme.css"/>

<b class="center"><acme:message code="administrator.dashboard.patronage"/></b>
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
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationProposedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationProposedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationProposedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationAcceptedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationAcceptedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationAcceptedGBP}"/> &#163;
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
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationProposedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationProposedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationProposedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationAcceptedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${patronagesDeviationAcceptedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationAcceptedGBP}"/> &#163;
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