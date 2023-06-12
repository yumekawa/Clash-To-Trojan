<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%request.setCharacterEncoding("utf-8");//防乱码 %>
<html>
<head>
<title>select</title>
<link rel="shortcut icon" href="#" />
</head>
<script>
function doSubmit(servlet_name){
	frm.action=servlet_name;
	frm.method="post";
	frm.submit();
}
</script>
<form name=frm  method="get">
<body body bgcolor="black" text="white" >
<%
	session.setAttribute("empty", "0");
	String backs="";
	String back=(String)session.getAttribute("backs");
		if(back==null||back.equals("1")){
			%>
			<textarea name=AllTrojan rows=20 cols=50 >请输入</textarea></br>
			<BUTTON onclick="doSubmit('UpdateTrojan')"><div>转换yaml文件内容</div></BUTTON>
			<BUTTON onclick="doSubmit('SelectTrojan')"><div>查询当前节点</div></BUTTON>
			<input type="hidden" name="del" value="0">
			<%
		}else if(back.equals("0")){
			out.println("请保证至少有一条正确节点");
			session.setAttribute("backs", "1");
			%>
			<BUTTON onclick="doSubmit('select.jsp')"><div>返回</div></BUTTON>
			<%
		}
%>
</form>
</body>
</html>