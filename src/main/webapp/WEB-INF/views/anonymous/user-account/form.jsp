<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="anonymous.user-account.form.label.username" path="username"/>
	<acme:input-textbox code="anonymous.user-account.form.label.identity" path="identity"/>		
		
</acme:form>