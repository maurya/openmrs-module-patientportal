<%
    ui.decorateWith("uicommons", "html5")
 ui.includeJavascript("patientportal", "jquery-1.11.1.js")
 ui.includeCss("patientportal", "patientPortalStyles.css")
%>

<script  type="text/javascript">
var jq = jQuery;
 \$(document).ready(function(){
    \$('.animated').autosize();    
});
</script>

  <body data-spy="scroll" data-target="#menu">
   ${ ui.includeFragment("patientportal", "patientPortalHeader") }
		   
    <div class="container">
	    <div class="dashboard clear">
	    	<div class="container">
	    		
${ ui.includeFragment("patientportal", "sharePost") }

	    		
	    		<hr class="divisionbar" />
	    		
	    ${ ui.includeFragment("patientportal", "viewPost") }		
	    		
</div>

</body>
