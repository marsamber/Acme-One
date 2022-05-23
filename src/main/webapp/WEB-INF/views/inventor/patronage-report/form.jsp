<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	
	<jstl:if test="${readonly}">
		<acme:input-textbox code="inventor.patronageReport.form.label.seqNumber" path="seqNumber"/>
		<acme:input-moment code="inventor.patronageReport.form.label.creationMoment" path="creationMoment"/>
	</jstl:if>
			
	<acme:input-textarea code="inventor.patronageReport.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="inventor.patronageReport.form.label.link" path="link"/>
	
	<jstl:if test="${!readonly}">
		
		<acme:input-select code="inventor.patronageReport.form.label.patronage" path="codigoPatronage">
			<jstl:forEach var="code" items="${availablePatronages}">
				<acme:input-option code="${code}" value="${code}"/>
			</jstl:forEach>
		</acme:input-select>
		
		<acme:input-checkbox code="inventor.patronageReport.form.button.confirmation" path="confirmation"/>
		
		<acme:submit code="inventor.patronageReport.form.button.create" action="/inventor/patronage-report/create"/>
	</jstl:if>
	
</acme:form>
