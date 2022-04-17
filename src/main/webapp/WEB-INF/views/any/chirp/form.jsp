<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="any.chirp.form.label.creationMoment" path="creationMoment"/>
	<acme:input-textbox code="any.chirp.form.label.title" path="title"/>
	<acme:input-textbox code="any.chirp.form.label.author" path="author"/>
	<acme:input-textbox code="any.chirp.form.label.body" path="body"/>
	<acme:input-textbox code="any.chirp.form.label.email" path="email"/>		
</acme:form>
