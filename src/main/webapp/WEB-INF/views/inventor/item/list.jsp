<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.item.list.label.name" path="name"/>
	<acme:list-column code="inventor.item.list.label.published" path="published"/>
	<acme:list-column code="inventor.item.list.label.code" path="code"/>
	<acme:list-column code="inventor.item.list.label.technology" path="technology"/>
	<acme:list-column code="inventor.item.list.label.description" path="description"/>
	<acme:list-column code="inventor.item.list.label.price" path="price"/>
	<acme:list-column code="inventor.item.list.label.link" path="link"/>
</acme:list>
<acme:button code="inventor.item.form.button.create" action="/inventor/item/create"/>