<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<jstl:if test="${command != 'show' }"> <% Boolean readonly = false; %></jstl:if>
<acme:form readonly="${readonly}">

	<acme:input-select readonly="true" code="patron.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED' }"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED' }"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED' }"/>
	</acme:input-select>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>		
	<acme:input-textarea code="patron.patronage.form.label.legalStuff" path="legalStuff"/>	
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-money readonly="true" code="patron.patronage.form.label.budgetEUR" path="budgetEUR"/>
	<acme:input-money readonly="true" code="patron.patronage.form.label.budgetUSD" path="budgetUSD"/>
	<acme:input-money readonly="true" code="patron.patronage.form.label.budgetGBP" path="budgetGBP"/>
	<acme:input-url code="patron.patronage.form.label.link" path="link"/>	
	<acme:input-moment readonly="true" code="patron.patronage.form.label.createdAt" path="createdAt"/>
	<acme:input-moment code="patron.patronage.form.label.startedAt" path="startedAt"/>
	<acme:input-moment code="patron.patronage.form.label.finishedAt" path="finishedAt"/>
	
	<h2><acme:message code="patron.patronage.section.inventor"/></h2>
	<jstl:if test="${acme:anyOf(command,'create')}">
			<acme:input-select code="patron.patronage.form.label.inventor" path="inventor.username">
				<jstl:forEach items="${allInventors}" var="inventor">
					<acme:input-option code="${inventor}"
						value="${inventor}"
						selected="${ status == inventor }" />
				</jstl:forEach>
			</acme:input-select>
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'show, delete, publish')}">
		<acme:input-textbox code="patron.patronage.form.label.inventor.username" path="username"/>
		<acme:input-textbox code="patron.patronage.form.label.inventor.company" path="company"/>
		<acme:input-textarea code="patron.patronage.form.label.inventor.statement" path="statement"/>
		<acme:input-url code="patron.patronage.form.label.inventor.info" path="inventorLink"/>
	</jstl:if>
	
	
	<jstl:if test="${acme:anyOf(command, 'show, update, delete, publish') }">
		<acme:submit code="patron.patronage.form.button.update" action="/inventor/item/update"/>
		<acme:submit code="patron.patronage.form.button.delete" action="/inventor/item/delete"/>
		<acme:submit code="patron.patronage.form.button.publish" action="/inventor/item/publish"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
	<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
	</jstl:if>
	
</acme:form>