package AllTrojan;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Trojan;

public class SelectTrojan extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    public void doPost (HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException{

	String server   = "localhost"; //MySQL���`��
	String db       = "trojan";  //
	String user     = "root";      //��`���`��
	String pass     = "";  //�ѥ���`��
	String url      = "jdbc:mysql://" + server + "/" + db + "?useUnicode=true&characterEncoding=gbk";
	
	Connection con  = null;
	String sql	= "";
	request.setCharacterEncoding("utf-8");//����ֵ����
	response.setContentType("text/html; charset=utf-8");
	PrintWriter out = response.getWriter();

	try{
		//�ɥ饤�ФΥ�`��
		Class.forName("org.gjt.mm.mysql.Driver");
		
		//MySQL���`�ФؤνӾA
		con = DriverManager.getConnection(url,user,pass);
		
		//Statement���֥������Ȥ�����
		Statement stmt = con.createStatement();
		
		HttpSession session=request.getSession();

			sql = "select * from share_trojan";

			ResultSet rs = stmt.executeQuery(sql);
			
			//System.out.println(sql);
			if(!rs.next()){
				session.setAttribute("empty", "0");
			}else {
				
			ArrayList TrojanSql=new ArrayList();
			
			String sql2	= "";
			sql2 = "select * from share_trojan";

			ResultSet rs2 = stmt.executeQuery(sql2);
			
			while(rs2.next()){
				Trojan text=new Trojan();	
				text.setName(rs2.getString("Name"));
				text.setType(rs2.getString("Type"));
				text.setServer(rs2.getString("Server"));
				text.setPort(rs2.getString("Port"));
				text.setPassword(rs2.getString("Password"));
				text.setSni(rs2.getString("Sni"));
				TrojanSql.add(text);
				session.setAttribute("empty", "1");
				session.setAttribute("TrojanSql", TrojanSql);
				}
			}
			session.setAttribute("del", "0");
			
			rs.close();
			con.close();
			stmt.close();
			
			request.getRequestDispatcher("showtrojan.jsp").forward(request, response);

	}catch(SQLException e) {
		out.close();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
		    //�ж�
		    con.close();
		}catch(Exception e){

		}
	}
    }
	private void While(boolean next) {
		// TODO �Զ����ɵķ������
	  
}
}
