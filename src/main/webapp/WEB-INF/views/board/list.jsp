<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
</head>
<body>
<div class="col-md-6">
<table class="table">
    <thead>
    <tr>
        <th>제목</th>
        <th>내용</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>최종수정일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${list.content}">
        <tr>
            <td><a href="/board/read?id=${list.id}">${list.title}</a></td>
            <td>${list.content}</td>
            <td>${list.user.email}</td>
            <td>${list.startDate}</td>
            <td>${list.updateDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <a class="btn btn-default " href="/board/write">작성</a>
    <div class="text-center">
        <ul class="pagination">
            <c:forEach var="page" begin="0" end="${list.totalPages-1}">
                <li><a href="/board/list?page=${page}">${page+1}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
