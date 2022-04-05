<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.toolkit.list.label.title" path="title" width="10%"/>
	<acme:list-column code="anonymous.toolkit.list.label.code" path="code" width="33%"/>
	<acme:list-column code="anonymous.toolkit.list.label.description" path="description" width="56%"/>	
</acme:list>
<br/>
<form action="/acme-toolkits-22.3/anonymous/toolkit/list-by-item">
  <acme:input-textbox code="anonymous.toolkit.form.label.item" path="item"/>
  <input type="submit" value="Submit">
</form>