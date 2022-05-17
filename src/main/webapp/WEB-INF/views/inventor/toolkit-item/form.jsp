<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form">

	<acme:input-textbox code="inventor.toolkit-item.form.label.name"
		path="item.name" />
	<acme:input-textbox code="inventor.toolkit-item.form.label.code"
		path="item.code" />
	<acme:input-textbox code="inventor.toolkit-item.form.label.technology"
		path="item.technology" />
	<acme:input-textbox code="inventor.toolkit-item.form.label.description"
		path="item.description" />
	<acme:input-select code="inventor.toolkit-item.form.label.type"
		path="item.type">
		<acme:input-option code="TOOL" value="TOOL"
			selected="${status == 'TOOL'}" />
		<acme:input-option code="COMPONENT" value="COMPONENT"
			selected="${status == 'COMPONENT'}" />
	</acme:input-select>
	<acme:input-money code="inventor.toolkit-item.form.label.retailPrice"
		path="item.retailPrice" />
	<acme:input-url code="inventor.toolkit-item.form.label.link"
		path="item.link" />
	<acme:input-url code="inventor.toolkit-item.form.label.units"
		path="units" />

	<jstl:choose>
		<jstl:when
			test="${acme:anyOf(command, 'show, update, delete') && draftMode == true}">
			<acme:submit code="inventor.toolkit-item.form.update"
				action="/inventor/toolkit-item/update" />
			<acme:submit code="inventor.toolkit-item.form.delete"
				action="/inventor/toolkit-item/delete" />
		</jstl:when>
		<jstl:when test="${command == 'create' }">
			<acme:submit code="inventor.toolkit-item.form.button.create"
				action="/inventor/toolkit-item/create?masterId=${masterId}" />
		</jstl:when>
	</jstl:choose>

</acme:form>
