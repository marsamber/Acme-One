<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-select code="inventor.patronage.form.label.status"
		path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED"
			selected="${status == 'PROPOSED' }" />
		<acme:input-option code="ACCEPTED" value="ACCEPTED"
			selected="${status == 'ACCEPTED' }" />
		<acme:input-option code="DENIED" value="DENIED"
			selected="${status == 'DENIED' }" />
	</acme:input-select>

	<acme:input-textbox readonly="true" code="inventor.patronage.form.label.code"
		path="code" />
	<acme:input-textarea readonly="true" code="inventor.patronage.form.label.legalStuff"
		path="legalStuff" />
	<acme:input-money code="inventor.patronage.form.label.budget" path="budget"/>
	<jstl:choose>
		<jstl:when test="${!(command == 'create')}">
			<acme:input-money readonly="true" code="inventor.patronage.form.label.budgetEUR" path="budgetEUR"/>
			<acme:input-money readonly="true" code="inventor.patronage.form.label.budgetUSD" path="budgetUSD"/>
			<acme:input-money readonly="true" code="inventor.patronage.form.label.budgetGBP" path="budgetGBP"/>
		</jstl:when>
	</jstl:choose>
	<acme:input-url readonly="true" code="inventor.patronage.form.label.link" path="link" />
	<acme:input-moment readonly="true" code="inventor.patronage.form.label.createdAt"
		path="createdAt" />
	<acme:input-moment readonly="true" code="inventor.patronage.form.label.startedAt"
		path="startedAt" />
	<acme:input-moment readonly="true" code="inventor.patronage.form.label.finishedAt"
		path="finishedAt" />
	
	<h2>
		<acme:message code="inventor.patronage.section.patron" />
	</h2>
	<acme:input-textbox readonly="true"
		code="inventor.patronage.form.label.patron.username" path="username" />
	<acme:input-textbox readonly="true" code="inventor.patronage.form.label.patron.company"
		path="company" />
	<acme:input-textarea readonly="true"
		code="inventor.patronage.form.label.patron.statement" path="statement" />
	<acme:input-url readonly="true" 
		code="inventor.patronage.form.label.patron.info" path="patronLink" />
	
	<jstl:if test="${command == 'show' && status == 'PROPOSED' }">
		<acme:submit code="inventor.patronage.form.update" action="/inventor/patronage/update"/>
	</jstl:if>
	
</acme:form>
