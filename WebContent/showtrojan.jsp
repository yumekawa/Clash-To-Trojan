<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=gbk" 
pageEncoding="gbk" 
import="java.util.ArrayList,Bean.Trojan"%>
<html>
<head>
<title>ShowTrojan</title>
<link rel="shortcut icon" href="#" />
</head>
<script>
function doSubmit(servlet_name){
	frm.action=servlet_name;
	frm.method="post";
	frm.submit();
}
</script>
<body body bgcolor="black" text="white">
<form name=frm  method="get">
<table border=1>
		<%
		String empty =null;
		String del=(String)session.getAttribute("del");
		empty=(String)session.getAttribute("empty");
		if(del.equals("1")){
			out.println("当前无任何节点");
			%>
			<BUTTON onclick="doSubmit('select.jsp')"><div>返回</div></BUTTON>
			<%
		}else{
			if(empty.equals("0")){
				out.println("当前无任何节点");
				%>
				<BUTTON onclick="doSubmit('select.jsp')"><div>返回</div></BUTTON>
				<%
			}else {
		ArrayList TrojanSqls= (ArrayList)session.getAttribute("TrojanSql");
		for(int i=0; i<TrojanSqls.size(); i++){
			Trojan All = (Trojan)TrojanSqls.get(i);
			while (i%2==0){out.println("<tr style='background-color:blue;'>"); break;};
			while (i%2!=0){out.println("<tr style='background-color:green;'>"); break;};
			//out.println("<td>");
			int c = i+1;
			//out.println("节点"+c+"");
			//out.println("</td>");
			out.println("<td>");
			out.println("trojan://"+All.getPassword()+"@"+All.getServer()+":"+All.getPort()+"?sni="+All.getSni()+"#"+All.getName());
			out.println("</td>");
			out.println("</tr>");			
			}
		%>
		<BUTTON onclick="doSubmit('select.jsp')"><div>返回</div></BUTTON>
		<BUTTON onclick="doSubmit('DelTrojan')"><div>删除所有节点</div></BUTTON>
		<%
			}

		}
		%>
</table>
</form>
</body>
</html>