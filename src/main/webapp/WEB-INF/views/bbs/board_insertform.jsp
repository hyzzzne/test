<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/shared/header.jsp" flush="false"/>

<script type="text/javascript" src='<c:url value="/resources/ckeditor/ckeditor.js" />'></script>

	<div class="container">
		<div class="row">
			<form method="post" action="${pageContext.request.contextPath}/board/insertBoard">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<table class="table table-bordered table-hover table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="3" style="background-color: #eeeeee; text-align: center;">고객센터 게시판</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="width: 20%">제목</td>
							<td colspan="2"><input type="text" class="form-control" name="title" maxlength="50" ></td>
						</tr>
						<tr>
							<td style="width: 20%">내용</td>
							<td colspan="2">
								<textarea name="content" cols="80" rows="10">
								</textarea>
								<script>CKEDITOR.replace('content');</script>
							</td>
						</tr>
						<tr>
							<td style="width: 20%">작성자</td>
							<td colspan="2"><input type="text" class="form-control" name="writer" value="writer" maxlength="50" ></td>
						</tr>						
					</tbody>
				</table>
				<div>
					<input type="submit" class="btn btn-primary pull-rigth" value="저장">
					<a href="category_list" class="btn btn-info">목록</a>
				</div>
			</form>
		</div>
	</div>
	
<%@ include file="/WEB-INF/views/shared/footer.jsp" %>
