<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-select readonly="true" code="patron.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED' }"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED' }"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED' }"/>
	</acme:input-select>
	<jstl:choose>
		<jstl:when test="${command != 'create' }">
			<acme:input-textbox	readonly="true" placeholder="KGB-007-A" code="patron.patronage.form.label.code" path="code"/>
		</jstl:when>
		<jstl:when test="${command == 'create' }">
			<acme:input-textbox placeholder="KGB-007-A" code="patron.patronage.form.label.code" path="code"/>
		</jstl:when>
	</jstl:choose>
			
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
	<acme:input-moment readonly="true" code="patron.patronage.form.label.createdAt" path="createdAt"/>
	<acme:input-moment code="patron.patronage.form.label.startedAt" path="startedAt"/>
	<acme:input-moment code="patron.patronage.form.label.finishedAt" path="finishedAt"/>
	
	<h2><acme:message code="patron.patronage.section.inventor"/></h2>
	<jstl:if test="${acme:anyOf(command,'create, update, show')}">
			<acme:input-select code="patron.patronage.form.label.inventor" path="inventor.username">
				<jstl:forEach items="${allInventors}" var="inventorObj">
					<acme:input-option code="${inventorObj}"
						value="${inventorObj}"
						selected="${ inventor.userAccount.username == inventorObj }" />
				</jstl:forEach>
			</acme:input-select>
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'show, delete')}">
		<acme:input-textbox readonly="true" code="patron.patronage.form.label.inventor.company" path="company"/>
		<acme:input-textarea readonly="true" code="patron.patronage.form.label.inventor.statement" path="statement"/>
		<acme:input-url readonly="true" code="patron.patronage.form.label.inventor.info" path="inventorLink"/>
	</jstl:if>
	
	
	<jstl:if test="${acme:anyOf(command, 'show, update, delete, publish') && published == false }">
		<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
		<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
		<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
	</jstl:if>
	
</acme:form>