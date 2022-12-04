<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/shared/header.jsp" %>


<div class="container">
	<div>
		<p>
		    <button type="button" class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/bbs/insertBoard'"><i class="fa fa-plus"></i> 게시물 등록</button>
		</p>
	</div>

	<div id="wrap" align="center">
		<h4>고객 센터 게시판</h4>
		<form name="bbsForm" class="form-horizontal" role="form">
		
	        <div class="form-group">
	            <label class="control-label col-sm-3" for="title">제목</label>
	            <div class="col-sm-3" >
					<input type="text" class="form-control" id="title" name="title">
	            </div>
	            <label class="control-label col-sm-3" for="writer">작성자</label>
	            <div class="col-sm-3">
	                <input type="text" class="form-control" id="writer" name="writer" >
	            </div>
	        </div>
		    <div>
				<input type="button" id="btnSearch" class="btn btn-primary" value="조회"/>
			</div>
		</form>

		<table id="datatable"
			class="table table-striped table-bordered dt-responsive nowrap"
			style="width: 100%">
			<thead>
				<tr>
					<th>no</th>
					<th>title</th>
					<th>writer</th>
					<th>hit</th>
					<th>regdate</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="qnaBbs" items="${qnaBbsList}">
					<tr>
						<td align=left>${qnaBbs.no }</td>
						<td align=left><a href="${pageContext.request.contextPath}/board/viewBoard?no=${qnaBbs.no}">${qnaBbs.title}</a></td>
						<td align=left>${qnaBbs.writer }</td>
						<td align=left><fmt:formatNumber value="${qnaBbs.hit }" pattern="#,###" /></td>
						<td align=left>${qnaBbs.regdate }</td>
					</tr>
				</c:forEach>
			</tbody>

			<tfoot>
			</tfoot>
		</table>
	</div>
</div>

<!-- Modal Dialog Div -->
<div id="myModal" class="modal fade" data-backdrop="false" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog modal-size">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" id="closbtn" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h4 class="modal-title" id="myModalLabel">Modal</h4>
              </div>
              <div class="modal-body" id="myModalContent" ></div>
              <div class="modal-footer">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">close</button>
              </div>
          </div>
      </div>
</div>

	<!-- <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script> -->
	<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
	<script src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
	<script src="//cdn.datatables.net/plug-ins/1.10.12/sorting/datetime-moment.js"></script>


	<script>
		var table ;
		$(document).ready(function(){
			//datatables setting
			table =  $('#datatable').removeAttr('width').DataTable({
					select: true,
					mark: true, // Highlight search terms
					aLengthMenu: [
						// Show entries incrementally
						[15, 30, 50, -1],
						[15, 30, 50, "All"]
					],
					dom: 'Bfrtip',
			        buttons: [
			        	  { extend: 'pdf', text: '<i class="fa fa-pencil" aria-hidden="true"> PDF</i>' },
		                  { extend: 'csv', text: '<i class="fas fa-file-csv fa-1x">CSV</i>' },
		                  { extend: 'excel', text: '<i class="fas fa-file-excel" aria-hidden="true">EXCEL</i>' }
			        ],
					"scrollY": "700px",			//default scrool with
		        	"scrollX": true,
		        	"scrollCollapse": true,
			    	"processing": true,
			    	"lengthChange": false,
			    	"searching": true,
			    	"order": [[ 0, "desc" ]],	//default order column index and sort direction
			    	"columnDefs": [
			    		{  
		                    "targets": [0],  
		                    "visible": true,  
		                    "searchable": true,
		                    "orderable": true
		                },  
		                {  
		                    "targets": [1],  
		                    "visible": true,  
		                    "searchable": true,
		                    "orderable": true
		                },  
		                {  
		                    "targets": [2],  
		                    "visible": true,  
		                    "searchable": true,
		                    "orderable": true
		                },  
		                {  
		                    "targets": [3],  
		                    "className": 'dt-body-right',	//align right
		                    "visible": true,  
		                    "searchable": true,
		                    "orderable": true
		                },
		                {  
		                    "targets": [4],  
		                    "visible": true,  
		                    "searchable": true,
		                    "orderable": true
		                }
					], 
			        fixedColumns: true,
			        //footer sum
			 });	//end of datatable

			//calendar jQuery-ui 없으면 datatable 날짜가 정수로 나옴
		 	$('.date-picker').datepicker({ 
				dateFormat: 'yyyy-mm-dd', 
				changeMonth: 'true', 
				changeYear: 'true',
		        dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
		        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
		        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				showButtonPanel: true, 
		        currentText: '오늘 날짜', 
		        closeText: '닫기',
			}); 
			
			//search 
            $('#btnSearch').on('click', function () {
            	event.preventDefault(); 	
                Fn_Search_Board();
            });
		});	//end of ready()
		
		function Fn_Search_Board() {
			var url = "${pageContext.request.contextPath}/bbs/getQnaBbsListSearch";
			var data = $("form[name=bbsForm]").serializeObject();
			console.log(data);
			$.ajax({
				type : "POST",
				url : url,
				data : JSON.stringify(data), 
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					console.log(data);
					if ($.fn.dataTable.isDataTable('#datatable')) {
             		    $('#datatable').DataTable().clear().destroy();               
            		}
					$('#datatable').DataTable({
				    	"data": data,
						select: true,
						dom: 'Bfrtip',
				        buttons: [
				        	  { extend: 'pdf', text: '<i class="fa fa-pencil" aria-hidden="true"> PDF</i>' },
			                  { extend: 'csv', text: '<i class="fas fa-file-csv fa-1x">CSV</i>' },
			                  { extend: 'excel', text: '<i class="fas fa-file-excel" aria-hidden="true">EXCEL</i>' }
				        ],
						"scrollY": "700px",			//default scrool with
			        	"scrollX": true,
			        	"scrollCollapse": true,
				    	"processing": true,
				    	"lengthChange": false,
				    	"searching": true,
				    	"order": [[ 0, "desc" ]],	//default order column index and sort direction			        	
				    	"columnDefs": [
				    		{  
			                    "targets": [0],  
			                    "visible": true,  
			                    "searchable": true,
			                    "orderable": true
			                },  
			                {  
			                    "targets": [1],  
			                    "visible": true,  
			                    "searchable": true,
			                    "orderable": true
			                },  
			                {  
			                    "targets": [2],  
			                    "visible": true,  
			                    "searchable": true,
			                    "orderable": true
			                },  
			                {  
			                    "targets": [3],  
			                    "className": 'dt-body-right',	//align right
			                    "visible": true,  
			                    "searchable": true,
			                    "orderable": true
			                },
			                {  
			                    "targets": [4],  
			                    "visible": true,  
			                    "searchable": true,
			                    "orderable": true
			                }
						], 
						fixedColumns: true,
				    	"columns": [
			                    { "data": "no", "name": "no", "width": "100px"},
			                    { 
			                    	"data": "title", "name": "title", "width": "100px",
			                    	"render": function (data, type, full, meta) {
			                    		return $('<a>').attr('href', '/board/viewBoard?no=' + full.no).text(data).wrap('<td></td>').parent().html();
			                    	}			
			                    },
			                    { "data": "writer", "name": "writer", "width": "100px" },
			                    { 
			                      "data": "hit", "name": "hit", "width": "100px", 
			                      "render" : $.fn.dataTable.render.number( ',', '.', 1 )
			                    },
			                    /* { "data": "regdate", "name": "regdate", "width": "100px"} */
			                    { data: 'regdate', render: $.fn.dataTable.moment('yyyy-mm-dd HH:mm:ss') }
			                    
			          ]
				    });
				},
				failure : function(data) {
					alert(data);
				},
				error : function(xhr, status) {
					alert(xhr.responseText);
				}
			});
		}	//end of Fn_Search_Board		
		
	</script>	
	
<%@ include file="/WEB-INF/views/shared/footer.jsp" %>	
