<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-select code="patron.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED' }"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED' }"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED' }"/>
	</acme:input-select>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>		
	<acme:input-textarea code="patron.patronage.form.label.legalStuff" path="legalStuff"/>	
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<jstl:choose>
		<jstl:when test="${!(command == 'create')}">
			<acme:input-money readonly="true" code="patron.patronage.form.label.budgetEUR" path="budgetEUR"/>
			<acme:input-money readonly="true" code="patron.patronage.form.label.budgetUSD" path="budgetUSD"/>
			<acme:input-money readonly="true" code="patron.patronage.form.label.budgetGBP" path="budgetGBP"/>
		</jstl:when>
	</jstl:choose>
	<acme:input-url code="patron.patronage.form.label.link" path="link"/>	
	<acme:input-moment code="patron.patronage.form.label.createdAt" path="createdAt"/>
	<acme:input-moment code="patron.patronage.form.label.startedAt" path="startedAt"/>
	<acme:input-moment code="patron.patronage.form.label.finishedAt" path="finishedAt"/>
	
	<h2><acme:message code="patron.patronage.section.inventor"/></h2>
	<acme:input-textbox code="patron.patronage.form.label.inventor.username" path="username"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor.company" path="company"/>
	<acme:input-textarea code="patron.patronage.form.label.inventor.statement" path="statement"/>
	<acme:input-url code="patron.patronage.form.label.inventor.info" path="patronLink"/>
	
</acme:form>