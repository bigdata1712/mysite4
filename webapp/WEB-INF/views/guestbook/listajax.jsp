<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript"src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>



<title>Mysite</title>
</head>
<body>
	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="wrapper">
			<div id="content">
				<div id="guestbook">

					<form id="writeForm" method="post" action="${pageContext.request.contextPath}/main">
						<table>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name" /></td>
								<td>비밀번호</td>
								<td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>
						<input id="btnDel" type="button" VALUE="삭제예제버튼" />
					</form>
					<ul id="listArea">


					</ul>
					
					<!-- <input id="btnNext" type="button" VALUE=" 다음글 5개 가져오기" /> -->

				</div>
				<!-- /guestbook -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
	<!-- /container -->
		

	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label>
					<input type="password" name="modalPassword" id="modalPassword"><br>	
					<input type="text" name="modalPassword" value="" id="modalNo"> <br>	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
</body>




<script type="text/javascript">
var page = 1;

$(document).ready(function() {
	fetchList();
});

 /* 다음글 버튼 클릭시 */
$("#btnNext").on("click", function(){
	page += 1 ;
	console.log(page);
	
	fetchList();
});
 

/* 스크롤 최하단 일때 */
$(window).on("scroll", function(){
	console.log($(window).scrollTop() +"/" + $(document).height()+ "/" +$(window).height());
	
	if($(window).scrollTop()==$(document).height()-$(window).height()){
		page += 1 ;
		fetchList()
	}
});

//저장버튼을 클릭했을때
$("#writeForm").on("submit", function() {
	event.preventDefault();
	console.log("저장");
	guestbookVo ={
    	name: $("input[name='name']").val(),
    	password: $("input[name='password']").val(),
    	content: $("textarea[name='content']").val()
    }
                
	$.ajax({
		url : "${pageContext.request.contextPath }/gb/api/add",		
		type : "post",
		/* contentType : "application/json", */
		data : guestbookVo,
		dataType : "json",
		success : function(guestbooVo) {
			console.log(guestbooVo);
			render(guestbooVo, "up");
			$("input[name='name']").val("");
	    	$("input[name='password']").val("");
	    	$("textarea[name='content']").val("");
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
});


/* 삭제버튼 클릭했을때 --> 팝업창호출*/
$("#listArea").on("click", ".delModal", function(){
	var no = $(this).data("no");  //data() 사용법 알아둘것
	$("#modalNo").val(no);
	$("#del-pop").modal();
});


/* 모달창 삭제버튼 클릭했을때 */
$("#btn_del").on("click", function() {
	
	var no = $("#modalNo").val();
	var password = $("#modalPassword").val();
	
	$.ajax({
		url : "${pageContext.request.contextPath }/gb/api/delete",	
		type : "post",
		contentType : "application/json",
		data : JSON.stringify({no: no, password: password}),
		dataType : "json",
		success : function(guestbookNo) {
			console.log(guestbookNo);
			if(guestbookNo != -1){
				$("#li-" + guestbookNo).remove();	
				$("#del-pop").modal("hide")
				$("#modalPassword").val("");
				$("#modalNo").val("");
			}else{
				$("#del-pop").modal("hide")
			}	
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
});



function fetchList(){
	$.ajax({
		url : "${pageContext.request.contextPath }/gb/api/list",
		type : "post",
		data : {
			page : page
		},

		dataType : "json",
		success : function(guestbookList) {
			console.log(guestbookList);

			for (var i = 0; i < guestbookList.length; i++) {
				render(guestbookList[i], "down");
			}

		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
}

	
function render(guestbookVo, updown) {
	
	var str = "";
	str += "<li id='li-"+guestbookVo.no+"' >";
	str += "	<table>";
	str += "		<tr>";
	str += "			<td>[" + guestbookVo.no + "]</td>";
	str += "		    <td>" + guestbookVo.name + "</td>";
	str += "		    <td>" + guestbookVo.regDate + "</td>";
	str += "		    <td><input class='delModal' type='button' data-name=''  data-no='"+ guestbookVo.no +"' value='삭제'></td>";
	str += "	    </tr>";
	str += "	    <tr>";
	str += "	    	<td colspan=4>" + guestbookVo.content + "</td>";
	str += "	    </tr>";
	str += "	</table>";
	str += "	<br>";
	str += "</li>";
	
	if (updown == "up") {
		$("#listArea").prepend(str);
	} else if (updown == "down") {
		$("#listArea").append(str);
	} else {
		console.log("updown 오류");
	}

}
</script>


</html>

