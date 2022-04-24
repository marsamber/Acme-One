<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="any.user-account.form.label.identity.name" path="identity.name"/>
	<acme:input-textbox code="any.user-account.form.label.identity.surname" path="identity.surname"/>
	<acme:input-textbox code="any.user-account.form.label.identity.email" path="identity.email"/>
	<acme:input-textbox code="any.user-account.form.label.company" path="company"/>
	<acme:input-textbox code="any.user-account.form.label.statement" path="statement"/>	
	<acme:input-textbox code="any.user-account.form.label.userAccount.username" path="username"/>
	<acme:input-textbox code="any.user-account.form.label.moreInfo" path="moreInfo"/>
		
</acme:form>
