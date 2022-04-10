<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="authenticated.item.form.label.name" path="name"/>
	<acme:input-textbox code="authenticated.item.form.label.code" path="code"/>		
	<acme:input-textbox code="authenticated.item.form.label.technology" path="technology"/>	
	<acme:input-textbox code="authenticated.item.form.label.description" path="description"/>
	<acme:input-select code="authenticated.item.form.label.type" path="type">
		<acme:input-option code="TOOL" value="TOOL" selected="${type == 'TOOL'}"/>
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${type == 'COMPONENT'}"/>
	</acme:input-select>
	<acme:input-money code="authenticated.item.form.label.retailPrice" path="retailPrice"/>
	<acme:input-url code="authenticated.item.form.label.link" path="link"/>	
	
</acme:form>
