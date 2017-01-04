<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<%@ include file="/WEB-INF/views/common/head.jsp"%>

<body class=" ">
    <div>
        
        <%@ include file="/WEB-INF/views/common/top.jsp"%>
        
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