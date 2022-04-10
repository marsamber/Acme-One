<%@page language="java" import="java.util.HashMap"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<link rel="stylesheet" href="css/acme.css"/>


<div class="dashboard">
	<ul class="stadistics">
		<b><acme:message code="patron.dashboard.patronage"/></b>
		<li>
			<acme:message code="patron.dashboard.proposed"/>: <acme:print value="${dashboard.getPatronagesProposed()}"/>
		</li>
		<li>
			<acme:message code="patron.dashboard.accepted"/>: <acme:print value="${dashboard.getPatronagesAccepted()}"/>
		</li>
		<li>
			<acme:message code="patron.dashboard.denied"/>: <acme:print value="${dashboard.getPatronagesDenied()}"/>
		</li>
	</ul>
	<ul class="stadistics">
		<b><acme:message code="patron.dashboard.average"/></b>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${averageProposedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${averageProposedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${averageProposedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${averageAcceptedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${averageAcceptedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${averageAcceptedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${averageDeniedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${averageDeniedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${averageDeniedGBP}"/> &#163;
		</li>
	</ul>
	<ul class="stadistics">
		<b><acme:message code="patron.dashboard.deviation"/></b>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationProposedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationProposedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationProposedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationAcceptedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationAcceptedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationAcceptedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationDeniedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationDeniedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${deviationDeniedGBP}"/> &#163;
		</li>
	</ul>
	<ul class="stadistics">
		<b><acme:message code="patron.dashboard.max"/></b>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${maxProposedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${maxProposedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${maxProposedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${maxAcceptedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${maxAcceptedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${maxAcceptedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${maxDeniedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${maxDeniedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${maxDeniedGBP}"/> &#163;
		</li>
	</ul>
	<ul class="stadistics">
		<b><acme:message code="patron.dashboard.min"/></b>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${minProposedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${minProposedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${minProposedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${minAcceptedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${minAcceptedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${minAcceptedGBP}"/> &#163;
		</li>
		<li>
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${minDeniedEUR}"/> &euro; / <fmt:formatNumber type="number" maxFractionDigits="2" value="${minDeniedUSD}"/> $ / <fmt:formatNumber type="number" maxFractionDigits="2" value="${minDeniedGBP}"/> &#163;
		</li>
	</ul>
</div>