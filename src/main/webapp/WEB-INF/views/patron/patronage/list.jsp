<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronage.list.label.status" path="status" width=""/>
	<acme:list-column code="patron.patronage.list.label.code" path="code" width=""/>
	<acme:list-column code="patron.patronage.list.label.link" path="link" width=""/>
</acme:list>
<acme:button code="patron.patronage.list.button.create" action="/patron/patronage/create"/>