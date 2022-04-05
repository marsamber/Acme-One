<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.user-account.list.label.username" path="name" width="50%"/>
	<acme:list-column code="anonymous.user-account.list.label.identity" path="code" width="50%"/>
</acme:list>
