 <link rel="stylesheet" href="${ ui.resourceLink("patientportal", "styles/sharePostStyles.css") }" type="text/css">
 <script type="text/javascript" src="/openmrs/ms/uiframework/resource/patientportal/scripts/bootstrap.min.js"></script>
<%
  ui.includeCss("patientportal", "sharePostStyles.css")
    ui.includeJavascript("patientportal", "jquery.autosize.js")
%>

<div class="journal-share">
	    			 <div class="journal-share-header">
                                <p> <i class="icon-share-alt"></i> share with 
                                <select >
  <option value="all connections" selected>all connections</option>
  <option value="personal connections">personal connections</option>
  <option value="medical connections">medical connections</option>
</select></p>
                            </div>
                            <div class="journal-share-content">
                        
                                       <textarea class='share-text-area animated' placeholder="How are you feeling today?"></textarea>
                            </div>
                            <div class="journal-share-footer">
                                <p> Feeling
                                <i class=" icon-camera"></i>
                                 <a class="post-button">post</a>
                                <a>clear</a>
                                
                                
                               
                               </p>
                            </div>
	    		</div>