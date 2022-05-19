<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="inventor.item.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.item.form.label.code" path="code"/>		
	<acme:input-textbox code="inventor.item.form.label.technology" path="technology"/>	
	<acme:input-textbox code="inventor.item.form.label.description" path="description"/>
	<acme:input-money code="inventor.item.form.label.retailPrice" path="retailPrice"/>
	<acme:input-url code="inventor.item.form.label.link" path="link"/>	
	<acme:input-select code="inventor.item.form.label.type" path="type">
		<acme:input-option code="TOOL" value="TOOL" selected="${type == 'TOOL'}"/>
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${type == 'COMPONENT'}"/>
	</acme:input-select>
	<acme:input-select code="inventor.item.form.label.published" path="isPublished">
		<acme:input-option code="inventor.item.form.label.false" value="false" selected="${isPublished == false}"/>
		<acme:input-option code="inventor.item.form.label.true" value="true" selected="${isPublished == true }"/>
	</acme:input-select>
	
	
	<acme:submit code="inventor.item.form.button.update" action="/inventor/item/update"/>
	<acme:submit code="inventor.item.form.button.delete" action="/inventor/item/delete"/>
	<acme:submit code="inventor.item.form.button.publish" action="/inventor/item/publish"/>
	<acme:submit code="inventor.item.form.button.create" action="/inventor/item/create"/>

</acme:form>