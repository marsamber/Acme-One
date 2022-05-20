<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="authenticated.inventor.form.label.company"
		path="company" />
	<acme:input-textbox code="authenticated.inventor.form.label.statement"
		path="statement" />
	<acme:input-textbox code="authenticated.inventor.form.label.moreInfo"
		path="moreInfo" />

	<acme:submit test="${command == 'create'}"
		code="authenticated.inventor.form.button.create"
		action="/authenticated/inventor/create" />
</acme:form>
