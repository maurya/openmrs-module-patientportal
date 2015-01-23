 <%
  ui.includeCss("patientportal", "patientPortalHeaderStyles.css")
%>
 <header style="background: #333;">
	    <div class="openmrs-logo">
         <a href="#">
            <img style="height:45px;width:45px;" src="/openmrs/images/openmrs_logo_white.gif"/>
        </a>

	    </div>
	    
	    <ul class="user-options">
	        <li class="identifier">
	            <i class="icon-user small"></i>
	            admin
	        </li>
	         <li class="followup">
	            <i class="icon-calendar small"></i>
	        </li>
	         <li class="messages">
	            <i class="icon-envelope-alt small"></i>
	        </li>
	        <li class="logout">
	            <a href="/openmrs/logout">
	                Logout
	                <i class="icon-signout small"></i>
	            </a>
	        </li>
	    </ul>

	</header>
	 <nav >
	 			<a id="titleBarHome"  href="patientPortalHome.page">Home</a>
                <a id="titleBarProfile" href="patientPortalProfile.page">Profile</a>
                <a id="titleBarConnections" href="connections.page">Connections</a>
               <a id="titleBarCommunity" href="community.page">Community</a>
               <a id="titleBarSideEffects" href="sideeffects.page">Side Effects</a>
               <a id="titleBarSymptomManagement" href="burdetteportal.page">Symptom Management</a>
        </nav>