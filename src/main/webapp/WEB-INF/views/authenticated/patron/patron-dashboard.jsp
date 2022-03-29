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
			<acme:print value="${dashboard.getPatronagesAverage().get(Pair.of(Status.PROPOSED,\"EUR\"))}"/>
		</li>
		<li>
			s<acme:print value="${dashboard.getPatronagesAverage().get(Pair.of(Status.ACCEPTED,\"EUR\"))}"/>
		</li>
		<li>
			s<acme:print value="${dashboard.getPatronagesAverage().get(Pair.of(Status.DENIED,\"EUR\"))}"/>
		</li>
	</ul>
	<ul class="stadistics">
		<b>Desviación</b>
		<li>
			88
		</li>
		<li>
			88
		</li>
		<li>
			88
		</li>
	</ul>
	<ul class="stadistics">
		<b>Máximo</b>
		<li>
			88
		</li>
		<li>
			88
		</li>
		<li>
			88
		</li>
	</ul>
	<ul class="stadistics">
		<b>Mínimo</b>
		<li>
			88
		</li>
		<li>
			88
		</li>
		<li>
			88
		</li>
	</ul>
</div>