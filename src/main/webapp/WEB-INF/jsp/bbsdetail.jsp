<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>방명록</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
#contents{
width:100%;
height:600px;
overflow:auto;
}
div{
width:80%;
margin:auto;
}
div p{
text-align:right;
position:fixed;
right:10%;
}
input{
margin:10px;}
#title{
width:60%;
}
.titles{
margin:10px;
font-weight:bold;
fonf-size:large;
}
.authors{
position:relative;
right:5px;
}
</style>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous">
	
</script>
<script>
$(function(){
	$("#num").val(`${bbs.num}`);
	$("#title").val(`${bbs.title}`);
	$("#author").val(`${bbs.author}`);
	$("#contents").val(`${bbs.contents}`);
	$("#hit").val(`${bbs.hit}`+" view")
})
function update(){
	document.getElementById('title').readOnly = false;
	document.getElementById('contents').readOnly = false;
	$("#updbtn").text("수정완료");
	$("#updbtn").attr("onclick","confupd()");
}
function del(){
	if(confirm("정말 이 글을 삭제하시겠습니까?")){
		$.ajax({
			url : '/bbs/delete',
			method : "delete",
			data : {
				'num' : $("#num").val()
			},
			dataType : 'text',
			success : function(res) {
				if(res.trim()=="true"){
					alert("삭제되었습니다");				
					location.href="/bbs/page/1";
				}else
					alert("삭제에 실패하였습니다");
			}
		})
	}
}
function confupd(){
	$.ajax({
		url : '/bbs/upd',
		method : "put",
		data : {
			'num' : $("#num").val(),
			'title' : $("#title").val(),
			'contents' : $("#contents").val()			
		},
		dataType : 'text',
		success : function(res) {
			if(res.trim()=="true"){
				alert("수정되었습니다");
				document.getElementById('title').readOnly = true;
				document.getElementById('contents').readOnly = true;
				$("#updbtn").text("수정");
				$("#updbtn").attr("onclick","update()");
			}else{
				alert("수정을 실패하였습니다");
				document.getElementById('title').readOnly = true;
				document.getElementById('contents').readOnly = true;
				$("#updbtn").text("수정");
				$("#updbtn").attr("onclick","update()");
			}
		}
	})
}
function download(f){
	location.href = "/download/"+f;
}
function toList(){
	location.href = "../page/1";
}
function rewrite(){
	location.href = "/bbs/rewrite/"+`${num}`;
}
</script>
</head>
<body>
<div class="form-group">
<output class="badge badge-dark" id = "num"></output> <span class = "titles"><label for = "title">제목</label><input id = "title" name = "title" readonly></span>
<span class="authors"><label for = "author">작성자: </label><output id = "author"></output></span>
<output class="badge badge-info" id = "hit"></output><br>
<textarea class="form-control" rows="5" id = "contents" name = "contents" readonly></textarea><br>
      <p><a class="btn btn-light" href="javascript:toList()">목록보기</a> 
      <button class="btn btn-dark" onclick="rewrite()">답글 달기</button>  
      <button class="btn btn-dark" onclick="update()">수정</button>      
      <button class="btn btn-secondary" onclick="del()">삭제</button></p>
      
      <label for="files">첨부파일</label> <span> ${fileNone} </span><br> 
      <c:forEach var = "f" items = "${attlist}">
      <span>${f.filename}</span>
      <button class="btn btn-dark" id = "downbtn" onclick="download('${f.filename}')">다운로드</button><br>
      </c:forEach> 
<p><p>
</div>
</body>
</html>