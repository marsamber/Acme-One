<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<jstl:choose>
		<jstl:when test="${command != 'create'}">
			<acme:input-textbox code="inventor.toolkit-item.form.label.name"
				path="item.name" readonly="true" />
			<acme:input-textbox code="inventor.toolkit-item.form.label.code"
				path="item.code" readonly="true" placeholder="XXX-000-X" />
			<acme:input-textbox
				code="inventor.toolkit-item.form.label.technology"
				path="item.technology" readonly="true" />
			<acme:input-textbox
				code="inventor.toolkit-item.form.label.description"
				path="item.description" readonly="true" />
			<acme:input-select code="inventor.toolkit-item.form.label.type"
				readonly="true" path="item.type" >
				<acme:input-option code="TOOL" value="TOOL"
					selected="${status == 'TOOL'}" />
				<acme:input-option code="COMPONENT" value="COMPONENT"
					selected="${status == 'COMPONENT'}" />
			</acme:input-select>
			<acme:input-money code="inventor.toolkit-item.form.label.retailPrice"
				path="item.retailPrice" readonly="true" />
			<acme:input-url code="inventor.toolkit-item.form.label.link"
				path="item.link" readonly="true" />
		</jstl:when>
		<jstl:when test="${command == 'create' }">
		<acme:input-textbox code="inventor.toolkit-item.form.label.code"
				path="item.code" placeholder="XXX-000-X" />
			<!--<acme:input-select code="inventor.toolkit-item.form.label.items"
				path="item">
				<acme:input-option code="TOOL" value="TOOL"
					selected="${status == 'TOOL'}" />
				<acme:input-option code="COMPONENT" value="COMPONENT"
					selected="${status == 'COMPONENT'}" />
			</acme:input-select>-->
		</jstl:when>
	</jstl:choose>
	<acme:input-url code="inventor.toolkit-item.form.label.units"
		path="units" />

	<jstl:choose>
		<jstl:when
			test="${acme:anyOf(command, 'show, update, delete') && draftMode == true}">
			<acme:submit code="inventor.toolkit-item.form.button.update"
				action="/inventor/toolkit-item/update" />
			<acme:submit code="inventor.toolkit-item.form.button.delete"
				action="/inventor/toolkit-item/delete" />
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.toolkit-item.form.button.create"
				action="/inventor/toolkit-item/create?toolkitId=${toolkitId}" />
		</jstl:when>
	</jstl:choose>

</acme:form>
