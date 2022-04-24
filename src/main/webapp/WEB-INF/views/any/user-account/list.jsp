<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.inventor.list.label.userAccount.identity.name" path="identity.name" width="33%"/>
	<acme:list-column code="anonymous.inventor.list.label.userAccount.identity.surname" path="identity.surname" width="33%"/>
	<acme:list-column code="anonymous.inventor.list.label.userAccount.identity.email" path="identity.email" width="33%"/>	
	<acme:list-column code="any.item.list.label.type" path="roles" width="70%"/>	
</acme:list>
