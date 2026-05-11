<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/img/favicon1.png" type="image/x-icon">
<link href="/css/common.css" rel="stylesheet"/>
<style>
  main {width: 100%; text-align: center;}
  div {font-size: 20px; margin: 10px;}
</style>
</head>
<body>
  <main>
    <h1>Home</h1>
    <a href="/test">Test</a>
    <div><a href="/Menus/WriteForm">새 메뉴추가</a></div>
    <div><a href="/Menus/WriteForm2">새 메뉴추가2</a></div>
    <div><a href="/Menus/List">메뉴목록</a></div>  
    <div>&nbsp;</div>
    <div><a href="/Users/Index">회원정보</a></div>    
    <div>&nbsp;</div>
    <div><a href="/Board/List?menu_id=MENU01">게시글 목록</a></div>
    <div><a href="/Board/WriteForm?menu_id=MENU01">게시글 추가</a></div>
    <div>&nbsp;</div>
    <div>
      <span style="font-weight: bold;">${sessionScope.login.username}</span> 님 환영합니다<br>
      당신의 가입일은 ${sessionScope.login.regdate} 입니다<br><br>
      <a href="/Users/LoginForm">로그인</a><br>
      <a href="/Users/Logout">로그아웃</a>
    </div>
  </main>
</body>
</html>