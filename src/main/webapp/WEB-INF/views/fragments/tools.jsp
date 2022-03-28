<%--
- welcome.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="jumbotron">
	
	<acme:message code="master.tools.title"/>
	<acme:list>
			<acme:list-column code="master.tools.col1" path="name" width="20%"/>
			<acme:list-column code="master.tools.col2" path="code" width="20%"/>
			<acme:list-column code="master.tools.col3" path="type" width="20%"/>
			<acme:list-column code="master.tools.col4" path="description" width="20%"/>
			<acme:list-column code="master.tools.col5" path="retailPrice" width="20%"/>
			
	</acme:list>
	
	
</div>
