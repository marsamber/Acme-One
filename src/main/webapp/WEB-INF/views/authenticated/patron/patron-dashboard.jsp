<%@page language="java" import="java.util.HashMap"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<link rel="stylesheet" href="css/acme.css"/>

<div class="dashboard">
	<ul class="stadistics">
		<b>Patrocinios</b>
		<li>
			Propuestos: <acme:print value="${dashboard.getPatronagesProposed()}"/>
		</li>
		<li>
			Aceptados: <acme:print value="${dashboard.getPatronagesAccepted()}"/>
		</li>
		<li>
			Denegados: <acme:print value="${dashboard.getPatronagesDenied()}"/>
		</li>
	</ul>
	<ul class="stadistics">
		<b>Presupuesto medio</b>
		<li>
			<acme:print value="${averageProposedEUR} € / ${averageProposedUSD} $ / ${averageProposedGBP} £"/>
		</li>
		<li>
			<acme:print value="${averageAcceptedEUR} € / ${averageAcceptedUSD} $ / ${averageAcceptedGBP} £"/>
		</li>
		<li>
			<acme:print value="${averageDeniedEUR} € / ${averageDeniedUSD} $ / ${averageDeniedGBP} £"/>
		</li>
	</ul>
	<ul class="stadistics">
		<b>Desviación</b>
		<li>
			<acme:print value="${deviationProposedEUR} € / ${deviationProposedUSD} $ / ${deviationProposedGBP} £"/>
		</li>
		<li>
			<acme:print value="${deviationAcceptedEUR} € / ${deviationAcceptedUSD} $ / ${deviationAcceptedGBP} £"/>
		</li>
		<li>
			<acme:print value="${deviationDeniedEUR} € / ${deviationDeniedUSD} $ / ${deviationDeniedGBP} £"/>
		</li>
	</ul>
	<ul class="stadistics">
		<b>Máximo</b>
		<li>
			<acme:print value="${maxProposedEUR} € / ${maxProposedUSD} $ / ${maxProposedGBP} £"/>
		</li>
		<li>
			<acme:print value="${maxAcceptedEUR} € / ${maxAcceptedUSD} $ / ${maxAcceptedGBP} £"/>
		</li>
		<li>
			<acme:print value="${maxDeniedEUR} € / ${maxDeniedUSD} $ / ${maxDeniedGBP} £"/>
	</ul>
	<ul class="stadistics">
		<b>Mínimo</b>
		<li>
			<acme:print value="${minProposedEUR} € / ${minProposedUSD} $ / ${minProposedGBP} £"/>
		</li>
		<li>
			<acme:print value="${minAcceptedEUR} € / ${minAcceptedUSD} $ / ${minAcceptedGBP} £"/>
		</li>
		<li>
			<acme:print value="${minDeniedEUR} € / ${minDeniedUSD} $ / ${minDeniedGBP} £"/>
	</ul>
</div>