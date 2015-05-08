
<head>
<title>OpenMRS Patient Portal</title>
<link rel="shortcut icon" type="image/ico"
	href="/openmrs/images/openmrs-favicon.ico">
<link rel="icon" type="image/png\"
	href="/openmrs/images/openmrs-favicon.png">
<script type="text/javascript"
	src="${ ui.resourceLink("patientportal", "/scripts/jquery-1.11.1.min.js") }"></script>
<script type="text/javascript"
	src="${ ui.resourceLink("uicommons", "/scripts/jquery-ui-1.9.2.custom.min.js")}"></script>
<script type="text/javascript"
	src="${ ui.resourceLink("uicommons", "/scripts/underscore-min.js")}"></script>
<script type="text/javascript"
	src="${ ui.resourceLink("uicommons", "/scripts/knockout-2.1.0.js")}"></script>
<script type="text/javascript"
	src="${ ui.resourceLink("uicommons", "/scripts/emr.js")}"></script>
<script type="text/javascript"
	src="${ ui.resourceLink("uicommons", "/scripts/jquery.toastmessage.js")}"></script>
<script type="text/javascript"
	src="${ ui.resourceLink("uicommons", "/scripts/jquery.simplemodal.1.4.4.min.js")}"></script>
<script type="text/javascript"
	src="${ ui.resourceLink("patientportal", "/scripts/bootstrap.min.js") }"></script>
<link rel="stylesheet"
	href="${ ui.resourceLink("uicommons", "/styles/styleguide/jquery-ui-1.9.2.custom.min.css")}"
	type="text/css">
<link rel="stylesheet"
	href="${ ui.resourceLink("uicommons", "/styles/styleguide/jquery.toastmessage.css")}"
	type="text/css">
<link rel="stylesheet"
	href="${ ui.resourceLink("patientportal", "styles/bootstrap.min.css") }"
	type="text/css">
<style type="text/css">
span.glyphicon-calendar,span.glyphicon-envelope {
	font-size: 1.4em;
}

body {
	background: #eee;
}

.bgcontent {
	background: #fff;
}

.well {
	padding-bottom: 0px;
	padding-top : 5px;
}

.form-group {
	margin-bottom: 7px;
}

.form-horizontal {
	margin-bottom: 0px;
}

.btn-info {
	padding: 3px 15px;
}

.journal {
	margin-top: 20px;
}

hr {
	width: 95%;
	height: 5px;
	background: #eee;
}
</style>
</head>

<div class="navbar navbar-default container">
	<button type="button" class="navbar-toggle" data-toggle="collapse"
		data-target=".navbar-responsive-collapse">
		<span class="icon-bar"></span> <span class="icon-bar"></span> <span
			class="icon-bar"></span>
	</button>
	<div class="navbar-collapse collapse navbar-responsive-collapse">
		<ul class="nav navbar-nav">
			<li id="patientPortalNavHome"><a href="patientPortalHome.page">Home</a></li>
			<li id="patientPortalNavProfile"><a
				href="patientPortalProfile.page">Profile</a></li>
			<li id="patientPortalNavConnections"><a href="#">Connections</a></li>
			<li id="patientPortalNavCommunity"><a href="communities.page">Community</a></li>
			<li id="patientPortalNavSideEffects"><a href="sideEffects.page">Side
					Effects</a></li>
			<li id="patientPortalNavSymptoms"><a href="#">Symptom
					Management</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-calendar"></span></a></li>
			<li><a href="#"><span class="glyphicon glyphicon-envelope"></span></a></li>
			<li><a href="#">Logout <span
					class="glyphicon glyphicon-log-out"></span></a></li>

		</ul>
	</div>
</div>