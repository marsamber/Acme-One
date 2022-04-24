<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.toolkit.list.label.title" path="title" width="50%"/>
	<acme:list-column code="any.toolkit.list.label.code" path="code" width="50%"/>	
</acme:list>
<br/>
<form action="/acme-toolkits-22.3/any/toolkit/list-by-item">
  <acme:input-textbox code="any.toolkit.form.label.item" path="item"/>
  <input type="submit" value="Submit">
</form>