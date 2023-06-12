package AllTrojan;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateTrojan extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    public void doPost (HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException{

	String server   = "localhost"; //MySQLサーバ
	String db       = "trojan";  //
	String user     = "root";      //ユーザー名
	String pass     = "";  //パスワード
	String url      = "jdbc:mysql://" + server + "/" + db + "?useUnicode=true&characterEncoding=gbk";
	
	Connection con  = null;
	String sql	= "";
	//request.setCharacterEncoding("utf-8");//防传值乱码
	response.setContentType("text/html; charset=utf-8");
	PrintWriter out = response.getWriter();

	try{
		//ドライバのロード
		Class.forName("org.gjt.mm.mysql.Driver");
		
		//MySQLサーバへの接続
		con = DriverManager.getConnection(url,user,pass);
		
		//Statementオブジェクトの生成
		Statement stmt = con.createStatement();
		
		HttpSession session=request.getSession();

		String AllTrojans=new String( request.getParameter("AllTrojan").getBytes("iso-8859-1"),"UTF-8");
		
        //System.out.println(AllTrojans);

        String pattern0 = "(\"name\":\")(.*?)(\",)"; //读取次数用,每类查询只能循环在当前页面试用一次
        String pattern1 = "(\"name\":\")(.*?)(\",)";
        String pattern2 = "(\"type\":\")(.*?)(\",)";
        String pattern3 = "(\"server\":\")(.*?)(\",)";
        String pattern4 = "(\"port\":)(.*?)(,\")";
        String pattern5 = "(\"password\":\")(.*?)(\",\"sni\")";
        String pattern6 = "(\"sni\":\")(.*?)(\")";
        
        Pattern r0 = Pattern.compile(pattern0);
        Pattern r1 = Pattern.compile(pattern1);
        Pattern r2 = Pattern.compile(pattern2);
        Pattern r3 = Pattern.compile(pattern3);
        Pattern r4 = Pattern.compile(pattern4);
        Pattern r5 = Pattern.compile(pattern5);
        Pattern r6 = Pattern.compile(pattern6);

        java.util.regex.Matcher m0 = r0.matcher(AllTrojans);
        java.util.regex.Matcher m1 = r1.matcher(AllTrojans);
        java.util.regex.Matcher m2 = r2.matcher(AllTrojans);
        java.util.regex.Matcher m3 = r3.matcher(AllTrojans);
        java.util.regex.Matcher m4 = r4.matcher(AllTrojans);
        java.util.regex.Matcher m5 = r5.matcher(AllTrojans);
        java.util.regex.Matcher m6 = r6.matcher(AllTrojans);
        
        int count=0;

        while (m0.find()) {
        	count=count+1;
        }
        int counts=count;
        
        /*
        while (m1.find()) {
        	count=count+1;
        	//System.out.println("Found value1: " + m1.group(2));
        }
        int counts=count;
        
        while (m2.find()) {
        	//System.out.println("Found value2: " + m2.group(2));
        }
        while (m3.find()) {
        	//System.out.println("Found value3: " + m3.group(2));
        }
        while (m4.find()) {
        	//System.out.println("Found value4: " + m4.group(2));
        }
        while (m5.find()) {
        	//System.out.println("Found value5: " + m5.group(2));
        }
        while (m6.find()) {
        	//System.out.println("Found value6: " + m6.group(2));
        }
        */
        
		String empty=(String)session.getAttribute("empty"); //防止页面刷新重复提交值
		if(empty.equals("1")){
			request.getRequestDispatcher("SelectTrojan").forward(request, response);
		}else{
		int back =counts;
		if (back==0){
			session.setAttribute("backs", "0");
			request.getRequestDispatcher("select.jsp").forward(request, response);
		} 
			else {
				for(int i=0; i<counts; i++){
					if(i==0){
						sql = "INSERT INTO share_trojan (Name,Type,Server,Port,Password,Sni) VALUES ";
					}for (int j=0; j<counts;j++){
						if(i<counts-1){
							
			        	while (m1.find()){
							sql = sql + "('"+m1.group(2)+"',";    
							break;
			        	}
			        	while (m2.find()){
							sql = sql + "'"+m2.group(2)+"',";  
							break;
			        	}
			        	while (m3.find()){
							sql = sql + "'"+m3.group(2)+"',";   
							break;
			        	}
			        	while (m4.find()){
							sql = sql + "'"+m4.group(2)+"',";  
							break;
			        	}
			        	while (m5.find()){
							sql = sql + "'"+m5.group(2)+"',";  
							break;
			        	}
			        	while (m6.find()){
				        	if(j==counts-1){
								sql = sql + "'"+m6.group(2)+"');";  
								break;
			        		}else if(j<counts-1){
								sql = sql + "'"+m6.group(2)+"'),";  
								break;
				        	}
			        	}
					}
					sql = sql;
				}
			}
		stmt.executeUpdate(sql);
		con.close();
		stmt.close();

		request.getRequestDispatcher("SelectTrojan").forward(request, response);
		
			}
		}

	}catch(SQLException e) {
		out.close();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
		    //切断
		    con.close();
		}catch(Exception e){

		}
	}
    }
	private void While(boolean next) {
		// TODO 自动生成的方法存根
	  
	}

}