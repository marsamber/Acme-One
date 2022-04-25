<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Patron,acme.roles.Inventor"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.user-account.list-all-user-accounts" action="/any/user-account/list-all-user-accounts"/>
			<acme:menu-suboption code="master.menu.any.toolkit.list-all-toolkits" action="/any/toolkit/list-all-toolkits"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.any" access="hasRole('Any')">
			<acme:menu-suboption code="master.menu.any.list-chirps" action="/any/chirp/list"/>	
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-pedparbas" action="https://www.keybr.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-marsamber" action="https://open.spotify.com/"/>		
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-alvvazort" action="http://www.twitter.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-alvmirpoz" action="https://www.wordreference.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-gonmarmar5" action="https://www.chess.com/"/>	
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-marcasbon" action="https://www.wikipedia.org/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.any" access="hasRole('Any')">
			<acme:menu-suboption code="master.menu.any.list-chirps" action="/any/chirp/list"/>	
			<acme:menu-suboption code="master.menu.any.inventor.list-all-inventors" action="/any/inventor/list-all-inventors"/>
			<acme:menu-suboption code="master.menu.any.patron.list-all-patrons" action="/any/patron/list-all-patrons"/>
			<acme:menu-suboption code="master.menu.any.item.list-all-tools" action="/any/item/list-all-tools"/>
			<acme:menu-suboption code="master.menu.any.item.list-all-components" action="/any/item/list-all-components"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.any.list-chirps" action="/any/chirp/list"/>	
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.any.toolkit.list-all-toolkits" action="/any/toolkit/list-all-toolkits"/>
			<acme:menu-suboption code="master.menu.anonymous.user-account.list-all-user-accounts" action="/any/user-account/list-all-user-accounts"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard"/>
			<acme:menu-suboption code="master.menu.administrator.systemConfiguration" action="/administrator/system-configuration/show"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>
			
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.any.list-chirps" action="/any/chirp/list"/>	
			<acme:menu-suboption code="master.menu.patron.dashboard" action="/authenticated/patron/dashboard"/>
			<acme:menu-suboption code="master.menu.anonymous.user-account.list-all-user-accounts" action="/any/user-account/list-all-user-accounts"/>
			<acme:menu-suboption code="master.menu.patron.patronages" action="/patron/patronage/list"/>
			<acme:menu-suboption code="master.menu.any.toolkit.list-all-toolkits" action="/any/toolkit/list-all-toolkits"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
			<acme:menu-suboption code="master.menu.any.list-chirps" action="/any/chirp/list"/>	
			<acme:menu-suboption code="master.menu.inventor.favourite-link" action="http://www.wikipedia.com/"/>
			<acme:menu-separator/>
				<acme:menu-suboption code="master.menu.anonymous.user-account.list-all-user-accounts" action="/any/user-account/list-all-user-accounts"/>
				<acme:menu-suboption code="master.menu.any.toolkit.list-all-toolkits" action="/any/toolkit/list-all-toolkits"/>
				<acme:menu-suboption code="master.menu.inventor.toolkit.list-my-toolkits" action="/inventor/toolkit/list-mine"/>
				<acme:menu-suboption code="master.menu.inventor.item.list-all-mine-components" action="/inventor/item/list-all-mine-components"/>		
				<acme:menu-suboption code="master.menu.inventor.item.list-all-mine-tools" action="/inventor/item/list-all-mine-tools"/>
				<acme:menu-suboption code="master.menu.inventor.patronage.list-all-my-patronages" action="/inventor/patronage/list"/>
				<acme:menu-suboption code="master.menu.inventor.patronageReport.list-mine" action="/inventor/patronage-report/list-mine"/>
		</acme:menu-option>
		
	</acme:menu-left>



	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.announcements" action="/authenticated/announcement/list-recent"/>
			<acme:menu-suboption code="master.menu.user-account.systemConfiguration" action="/authenticated/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update" access="hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.become-inventor" action="/authenticated/inventor/create" access="!hasRole('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.inventor" action="/authenticated/inventor/update" access="hasRole('Inventor')"/>
		</acme:menu-option>
		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

