<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<%@ include file="/WEB-INF/views/common/head.jsp"%>

<body class=" ">
    <div>
        <div class="news-ticker bg-orange" style="display:none;">
            <div class="container">
                <ul id="news-ticker-content" class="list-unstyled mbn">
                    <li><a href='http://madmin.swlabs.co/' , target='_blank'>This is HTML app version of this template. To see Angular app version, please click here</a>
                    </li>
                </ul><a id="news-ticker-close" href="javascript:;"><i class="fa fa-times"></i></a>
            </div>
        </div>
       
        <!--BEGIN TOPBAR-->
        <%@ include file="/WEB-INF/views/common/top_bar.jsp"%>
        <!--END TOPBAR-->
        
        <div id="wrapper">
            <!--BEGIN SIDEBAR MENU-->
            <%@ include file="/WEB-INF/views/common/left_menu.jsp"%>
            <!--END SIDEBAR MENU-->
            
            <!--BEGIN CHAT FORM-->
            <%@ include file="/WEB-INF/views/common/chart_form.jsp"%>
            <!--END CHAT FORM-->
            
            <!--BEGIN PAGE WRAPPER-->
            <div id="page-wrapper">
                <!--BEGIN TITLE & BREADCRUMB PAGE-->
                 <%@ include file="/WEB-INF/views/common/menu_bar.jsp"%>
                <!--END TITLE & BREADCRUMB PAGE-->
                
                <!--BEGIN CONTENT-->
                <div class="page-content">
                  	<%@ include file="/WEB-INF/views/common/context.jsp"%>
    			</div>
    			<!--END CONTENT-->
    		</div>
		    <!--BEGIN FOOTER-->
		    <%@ include file="/WEB-INF/views/common/footer.jsp"%>
		    <!--END FOOTER-->
		    
    		<!--END PAGE WRAPPER-->
    	</div>
    </div>
    <%@ include file="/WEB-INF/views/common/footlib.jsp"%>

</body>

</html>