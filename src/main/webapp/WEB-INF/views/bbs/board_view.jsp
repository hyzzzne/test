<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/shared/header.jsp" flush="false"/>

	<div class="container">
		<div class="row">
			<!-- <form action="modify" method="post">  -->
				<!-- <input type="hidden" name="bId" value="${content_view.bId}">  -->
				<table class="table table-bordered table-hover table-striped" style="text-align: center; border: 1px solid #dddddd">
						<thead>
							<tr>
								<th colspan="3" style="background-color: #eeeeee; text-align: center;">게시물 보기</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="width: 20%">제목</td>
								<td colspan="2" style="text-align: left;">${board.title} </td>
							</tr>
							<tr>
								<td>내용</td>
								<td colspan="2" style="text-align: left;">${board.content}</td>
							</tr>
							<tr>
								<td>작성자</td>
								<td colspan="2" style="text-align: left;">${board.writer}</td>
							</tr>
							<tr>
								<td>작성일</td>
								<td colspan="2" style="text-align: left;">${board.regdate }</td>
							</tr>
						</tbody>
				</table>
			<!--  </form> -->				
				<a href="${pageContext.request.contextPath}/board/qna_list" class="btn btn-primary">목록</a>
		</div>
	</div>
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<%@ include file="/WEB-INF/views/shared/footer.jsp" %>
