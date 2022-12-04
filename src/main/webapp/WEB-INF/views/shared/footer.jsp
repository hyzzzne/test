<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="clear"></div>

	<!-- [Bootstrap JS] -->
	<!--  
	<script type="text/javascript" src="https://unpkg.com/jquery@3.3.1/dist/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.js"></script>
	-->


	<!--  
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
	-->

	
	<!-- [Non Bootstrap Js] -->
	<!--    	
	<script src="https://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
	<script src="https://cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="https://cdn.datatables.net/plug-ins/f2c75b7247b/integration/jqueryui/dataTables.jqueryui.js" type="text/javascript"></script>
	-->

	<footer>
		<hr>
		<div id="copy" style="text-align:center;">
			All contents Copyright 2020 Magicoh Inc. all rights reserved<br />
			Contact mail : magicoh@naver.com Tel: +82 64 123 4315 Fax +82 64 123
			4321<br /> Web server : <%=application.getServerInfo() %> <br />
			Servlet Specification :	<%=application.getMajorVersion()%>.<%= application.getMinorVersion() %>
			Jsp version : <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %>
		</div>
	</footer>

</body>
</html>