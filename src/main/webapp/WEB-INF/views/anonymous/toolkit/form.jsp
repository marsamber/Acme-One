<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="anonymous.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="anonymous.toolkit.form.label.code" path="code"/>		
	<acme:input-money code="anonymous.toolkit.form.label.totalPrice" path="totalPrice"/>		
	<acme:input-textarea code="anonymous.toolkit.form.label.description" path="description"/>>
	<acme:input-textarea code="anonymous.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-url code="anonymous.toolkit.form.label.link" path="link"/>	
	
	<acme:button code="anonymous.toolkit.form.button.items" action="/anonymous/item/list?toolkitId=${id}"/>
</acme:form>
