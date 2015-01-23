<%
    ui.decorateWith("uicommons", "html5")
 ui.includeJavascript("patientportal", "jquery-1.11.1.js")
 ui.includeCss("patientportal", "patientPortalStyles.css")
 ui.includeCss("patientportal", "patientPortalProfileStyles.css")
%>
<script  type="text/javascript">
var jq = jQuery;
 \$(document).ready(function(){
    \$('.animated').autosize();  
     \$('#titleBarProfile').addClass('active')  
});
</script>

 <body data-spy="scroll" data-target="#menu">
   ${ ui.includeFragment("patientportal", "patientPortalHeader") }
		   
		     <div class="patient-header new-patient-header">
		    <div class="demographics">
        <h1 class="name">
            <span>John Rambo</span>
             <br>
            <span class="gender-ages">

  <span class="info-detail">Gender</span>
   <span class="info-info">&emsp;Male</span> <br>
  <span class="info-detail">Age</span>
    <span class="info-info">&emsp;22 year(s) </span> <br>
	<span class="info-detail">Address</span>
    <span class="info-info">&emsp;410 West 10th Street, Indianapolis, IN 46202 </span><br>
	<span class="info-detail">Phone</span>
    <span class="info-info">&emsp;9999999999</span> <br>
	<span class="info-detail">Email</span>
    <span class="info-info">&emsp;john.rambo@gmail.com</span> <br>
	<span class="info-detail">PCP</span>
    <span class="info-info">&emsp;Perry Mason</span>
                </span>
            </span>
        </h1>
    </div>		       
</div>
    <div class="container">
	    <div class="dashboard clear">
	    	<div class="container">
	    		
${ ui.includeFragment("patientportal", "sharePost") }

	    		
	    		<hr class="divisionbar" />
	    		
	    ${ ui.includeFragment("patientportal", "viewPost") }		
	    		
</div>

</body>