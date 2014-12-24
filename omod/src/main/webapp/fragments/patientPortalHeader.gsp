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
	   
	    <li class="search">
	    <span id="search-wrapper" class="searcher">
        <input type="search" class="patient-portal-search"  placeholder="Search" size="40"/>
        <span class="add-on"><i class="icon-search small"></i></span>
    </span>
	        </li>
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
	         <li class="notifications">
	            <i class="icon-globe small"></i>
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
	 			<a class="active" href="patientPortalHome.page">Home</a>
                <a href="profile.page">Profile</a>
                <a href="connections.page">Connections</a>
               <a href="community.page">Community</a>
        </nav>