
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>		
	<acme:input-money code="inventor.toolkit.form.label.totalPrice" path="totalPriceEUR"/>		
	<acme:input-money code="" path="totalPriceUSD"/>	
	<acme:input-money code="" path="totalPriceGBP"/>
	<acme:input-textarea code="inventor.toolkit.form.label.description" path="description"/>>
	<acme:input-textarea code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-url code="inventor.toolkit.form.label.link" path="link"/>	
	
	<acme:button code="inventor.toolkit.form.button.items" action="/inventor/toolkit-item/list?toolkitId=${id}"/>

</acme:form>