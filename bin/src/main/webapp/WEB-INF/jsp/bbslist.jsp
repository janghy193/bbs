<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>WEB BBS</title>
<style>
.subj{
width:55%;}
th,td{
text-align:center;
}
td.subj{
text-align:left;
}
.container p{
text-align:right;
}
.curr{
font-weight:bold;
}
.paginations{
display:block;
position:relative;
right:0px;
}
#search{
text-align:center;
}
.notImp{
color:gray;
font-size:small;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
<script src="http://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous">
	
</script>
<script>
function writePage(num){
	location.href="/bbs/write";
}

function search(){
	if($("#searchVal").val()==""){
		location.href="/bbs/page/1"
	}else{
		location.href="/bbs/search/"+$("#searchKey").val()+"/"+$("#searchVal").val()+"/";	
	}
}
</script>
</head>
<body>
<br>

<div class="container">
	<table class="table">
		<tr class="thead-dark"><th></th><th>글번호</th><th class = "subj">제목</th><th>작성자</th><th>작성일</th><th>조회수</th></tr>
		<c:forEach var="b" items="${pageInfo.list}" varStatus="status">
		<c:if test="${b.pnum==0}">
			<tr>
			<td class = "notImp">${status.count}</td><td>${b.num}</td>
			<td class="subj"><a href="/bbs/read/${b.num}">${b.title}</a></td>
			<td>${b.author}</td><td>${b.wdate}</td><td>${b.hit}</td></tr>
		</c:if>
		<c:if test="${b.pnum!=0}">
			<tr>
			<td class = "notImp">${status.count}</td><td class = "notImp">답글</td>
			<td class = "subj"><a href="/bbs/read/${b.num}" id = "tLink">${b.title}</a></td>
			<td>${b.author}</td><td>${b.wdate}</td><td>${b.hit}</td></tr>
		</c:if>
		</c:forEach>
		<c:forEach var="b" items="${list}">
		<tr>
		<td><a href="/bbs/read/${b.num}">${b.num}</a></td>
		<td><a href="/bbs/read/${b.num}" id = "tLink">${b.title}</a></td>
		<td><a href="/bbs/search/author/${b.author}/">{b.author}</a></td><td>${b.wdate}</td><td>${b.hit}</td></tr></a>
		</c:forEach>			
	</table>
	<span class = "paginations">
	<ul class="pagination" >
	<c:forEach var = "p" items="${pageInfo.navigatepageNums}" step="1">
	<c:choose>
		<c:when test="${p==pageInfo.pageNum}">
			<li class="page-item active"><a class="page-link">${p}</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class = "page-link" href="./${p}">${p}</a></li>
		</c:otherwise>
	</c:choose>
	</c:forEach>
	</ul>
	</span>
	<p id="search">
	<select id="searchKey">
		<option value="title">제목</option>
		<option value="contents">내용</option>
		<option value="author">작성자명</option>
		<option value="num">글번호</option>
	</select>
	<input id="searchVal" type = "search">
	<button class="btn btn-dark" onclick="search()" for="searchVal">검색</button></p>
	
	<p><button class="btn btn-dark" onclick="writePage(${pageInfo.pageNum})">글쓰기</button></p>
</div>
</body>
</html>