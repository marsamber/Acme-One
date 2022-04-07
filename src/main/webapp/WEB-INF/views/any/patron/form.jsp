<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="anonymous.patron.form.label.userAccount.identity.name" path="userAccount.identity.name"/>
	<acme:input-textbox code="anonymous.patron.form.label.userAccount.identity.surname" path="userAccount.identity.surname"/>
	<acme:input-textbox code="anonymous.patron.form.label.userAccount.identity.email" path="userAccount.identity.email"/>
	<acme:input-textbox code="anonymous.patron.form.label.company" path="company"/>
	<acme:input-textbox code="anonymous.patron.form.label.statement" path="statement"/>	
	<acme:input-textbox code="anonymous.patron.form.label.userAccount.username" path="userAccount.username"/>
	<acme:input-textbox code="anonymous.patron.form.label.moreInfo" path="moreInfo"/>
		
</acme:form>
