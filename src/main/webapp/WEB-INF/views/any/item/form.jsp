<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="any.item.form.label.name" path="name"/>
	<acme:input-textbox code="any.item.form.label.code" path="code"/>		
	<acme:input-textbox code="any.item.form.label.technology" path="technology"/>	
	<acme:input-textbox code="any.item.form.label.description" path="description"/>
	<acme:input-select code="any.item.form.label.type" path="type">
		<acme:input-option code="TOOL" value="TOOL" selected="${type == 'TOOL'}"/>
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${type == 'COMPONENT'}"/>
	</acme:input-select>
	<acme:input-money code="any.item.form.label.retailPrice" path="retailPrice"/>
	<acme:input-money code="any.item.form.label.retailPriceEUR" path="retailPriceEUR"/>
	<acme:input-money code="any.item.form.label.retailPriceUSD" path="retailPriceUSD"/>
	<acme:input-money code="any.item.form.label.retailPriceGBP" path="retailPriceGBP"/>
	<acme:input-url code="any.item.form.label.link" path="link"/>	
	
</acme:form>
