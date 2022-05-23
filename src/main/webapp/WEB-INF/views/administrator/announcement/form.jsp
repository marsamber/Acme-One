<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="administrator.announcement.form.label.title" path="title"/>		
	<acme:input-textarea code="administrator.announcement.form.label.body" path="body"/>
	<acme:input-select code="administrator.announcement.form.label.criticalFlag" path="criticalFlag">
		<acme:input-option code="administrator.announcement.form.label.true" value="true" selected="${criticalFlag == true}"/>
		<acme:input-option code="administrator.announcement.form.label.false" value="false" selected="${criticalFlag == false}"/>
	</acme:input-select>
	<acme:input-url code="administrator.announcement.form.label.link" path="link"/>
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="administrator.announcement.form.label.confirmation" path="confirmation"/>
		<acme:submit code="administrator.announcement.form.label.create" action="/administrator/announcement/create"/>
	</jstl:if>
	
</acme:form>
