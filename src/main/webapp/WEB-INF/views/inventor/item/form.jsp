<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form  >

	<acme:input-textbox placeholder="Item X" code="inventor.item.form.label.name" path="name"/>
	<acme:input-textbox placeholder="XXX-000-X" code="inventor.item.form.label.code" path="code"/>		
	<acme:input-textbox placeholder="Technology" code="inventor.item.form.label.technology" path="technology"/>	
	<acme:input-textarea placeholder="Example of a description" code="inventor.item.form.label.description" path="description"/>
	<acme:input-money placeholder="10 EUR" code="inventor.item.form.label.retailPrice" path="retailPrice"/>
	<jstl:choose>
		<jstl:when test="${!(command == 'create')}">
			<acme:input-money readonly="true" code="inventor.item.form.label.retailPriceEUR" path="retailPriceEUR"/>
			<acme:input-money readonly="true" code="inventor.item.form.label.retailPriceUSD" path="retailPriceUSD"/>
			<acme:input-money readonly="true" code="inventor.item.form.label.retailPriceGBP" path="retailPriceGBP"/>
		</jstl:when>
	</jstl:choose>
	<acme:input-url placeholder="http://ev.us.es" code="inventor.item.form.label.link" path="link"/>	
	<acme:input-select readonly="true" code="inventor.item.form.label.type" path="type">
		<acme:input-option code="TOOL" value="TOOL" selected="${type == 'TOOL'}"/>
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${type == 'COMPONENT'}"/>
	</acme:input-select>
	
	
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="inventor.item.form.button.update" action="/inventor/item/update"/>
			<acme:submit code="inventor.item.form.button.delete" action="/inventor/item/delete"/>
			<acme:submit code="inventor.item.form.button.publish" action="/inventor/item/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.item.form.button.create" action="/inventor/item/create"/>
		</jstl:when>		
	</jstl:choose>

</acme:form>
