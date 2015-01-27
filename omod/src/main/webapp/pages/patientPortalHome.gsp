
${ ui.includeFragment("patientportal", "patientPortalNav") }
<script type="text/javascript">
 \$(document).ready(function(){
     \$('#patientPortalNavHome').addClass('active');  
});
</script>
<body>
	<div class="container bgcontent">
		${ ui.includeFragment("patientportal", "createJournal") }

		<hrs> ${ ui.includeFragment("patientportal", "viewJournal") }
		
	</div>
</body>