<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="administrator.systemConfiguration.form.label.systemCurrency" path="systemCurrency"/>
	<acme:input-textbox code="administrator.systemConfiguration.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
	<acme:input-textbox code="administrator.systemConfiguration.form.label.strongSpamTerms" path="strongSpamTerms"/>
	<acme:input-textbox code="administrator.systemConfiguration.form.label.strongSpamThreshold" path="strongSpamThreshold"/>
	<acme:input-textbox code="administrator.systemConfiguration.form.label.weakSpamTerms" path="weakSpamTerms"/>
	<acme:input-textbox code="administrator.systemConfiguration.form.label.weakSpamThreshold" path="weakSpamThreshold"/>
	
	<jstl:if test="${acme:anyOf(command, 'show, update')}">
		<acme:submit code="administrator.systemConfiguration.form.label.update" action="/administrator/system-configuration/update"/>
	</jstl:if>
	
</acme:form>
