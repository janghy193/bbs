<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>방명록 작성</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
#contents {
	width: 100%;
	height: 600px;
	overflow: auto;
}

div {
	width: 80%;
	margin: auto;
}

div p {
	text-align: right;
}

input {
	margin: 10px;
}

#title {
	width: 60%;
}

.titles {
	margin: 10px;
	font-weight: bold;
	fonf-size: large;
}

.authors {
	position: relative;
	right: 5px;
}
#pnum{
width:2em;
}
</style>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous">
	
</script>
<script>
$(function(){
	if(`${pnum}`!=""){
		$('#pnum').val(${pnum});
		$('#pnum_label').val(`${pnum}`+"번 글의 답글");
	}
})
	function write() {
		if ($('#title').val() == "" || $('#author').val() == "") {
			alert("제목과 작성자명을 입력하세요");
		} else {
			// 폼데이터로 파일 업로드
			let form = $('#form-id')[0];

			// FormData 객체 생성
			let formData = new FormData(form);

			$.ajax({
				url : '/bbs/save',
				method : "post",
				enctype : 'multipart/form-data',
				processData : false,
				contentType : false,
				data : formData,
				dataType : 'text',
				success : function(res) {
					if (res.trim() == 'true') {
						alert("글 게재 성공");
						location.href = "/bbs/page/1";
					} else {
						alert("글 게재 오류 발생");
					}
				}
			})
		}
	}
	function back() {
		location.href = "/bbs/page/1";
	}
</script>
</head>
<body>
	<div>
		<form method="POST" enctype="multipart/form-data" id="form-id">
			<output id="pnum_label"></output>
			<input id="pnum" name="pnum" type="hidden" value="0"></input>
			<span class="titles"><label for="title">제목</label>
			<input id="title" name="title"></span> <span class="authors">
			<label for="author">작성자</label><input id="author" name="author"></span>
			
			<br>
			<textarea class="form-control" id="contents" name="contents"></textarea><br> 
			<label for="files">첨부파일</label> 
			<input type="file" id="files" name="files" multiple="multiple"><br>
		</form>
		<p>
			<a class="btn btn-light" href="/bbs/page/1">목록보기</a>
			<a class="btn btn-dark" href="javascript:write()">작성</a>
			<button class="btn btn-secondary" onclick="back()">취소</button>
		</p>
	</div>
</body>
</html>