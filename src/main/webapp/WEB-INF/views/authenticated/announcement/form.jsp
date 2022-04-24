<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-moment code="authenticated.announcement.form.label.creationMoment" path="creationMoment"/>
	<acme:input-textbox code="authenticated.announcement.form.label.title" path="title"/>		
	<acme:input-textarea code="authenticated.announcement.form.label.body" path="body"/>
	<acme:input-select code="authenticated.announcement.form.label.criticalFlag" path="criticalFlag">
		<acme:input-option code="authenticated.announcement.form.label.true" value="true" selected="${criticalFlag == true}"/>
		<acme:input-option code="authenticated.announcement.form.label.false" value="false" selected="${criticalFlag == false}"/>
	</acme:input-select>
	<acme:input-url code="authenticated.announcement.form.label.link" path="link"/>
	
</acme:form>
