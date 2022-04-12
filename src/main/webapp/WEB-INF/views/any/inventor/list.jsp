<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.inventor.list.label.userAccount.identity.name" path="userAccount.identity.name" width="33%"/>
	<acme:list-column code="anonymous.inventor.list.label.userAccount.identity.surname" path="userAccount.identity.surname" width="33%"/>
	<acme:list-column code="anonymous.inventor.list.label.userAccount.identity.email" path="userAccount.identity.email" width="33%"/>	
</acme:list>
