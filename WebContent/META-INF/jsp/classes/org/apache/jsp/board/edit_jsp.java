/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.52
 * Generated at: 2021-10-18 15:16:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.vo.BoardVO;

public final class edit_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar", Long.valueOf(1633590731731L));
    _jspx_dependants.put("jar:file:/D:/JspWork/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/bb/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/c.tld", Long.valueOf(1425946270000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.vo.BoardVO");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

    BoardVO vo = (BoardVO)request.getAttribute("vo");
	int seq = 0;
	if (request.getParameter("seq") != null) {
	seq = Integer.parseInt(request.getParameter("seq"));
	}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>edit.jsp</title>\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("<script\r\n");
      out.write("	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n");
      out.write("<!-- 링크 -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"../css/style.css\">\r\n");
      out.write("<style>\r\n");
      out.write("table, td, th {\r\n");
      out.write("	border: solid 1px gray;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("table {\r\n");
      out.write("	border-spacing: 3px;\r\n");
      out.write("	border-collapse: separate;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("table, tr, td {\r\n");
      out.write("	/* border-radius: 3px; */\r\n");
      out.write("	/*  padding:3px;  */\r\n");
      out.write("	\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("table {\r\n");
      out.write("	width: 600px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script>\r\n");
      out.write("   $(document).ready(function (){	  \r\n");
      out.write("   });\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("	<div align=\"center\">\r\n");
      out.write("		<h2>수정하기</h2>\r\n");
      out.write("		<!-- [중요사항] action 속성 X -->\r\n");
      out.write("		<form method=\"post\" action=\"Update\">\r\n");
      out.write("		<input type=\"hidden\" value=\"");
      out.print(seq);
      out.write("\" name=\"seq\"/>\r\n");
      out.write("			<table>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td colspan=\"2\" align=\"center\"><b>글을 수정합니다</b></td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td align=\"center\">이름</td>\r\n");
      out.write("					<td><input type=\"text\" readonly name=\"writer\" size=\"15\"\r\n");
      out.write("						value=\"");
      out.print( vo.getWriter() );
      out.write("\"></td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td align=\"center\">Email</td>\r\n");
      out.write("					<td><input type=\"email\" name=\"email\" size=\"50\"\r\n");
      out.write("						value=\"");
      out.print( vo.getEmail() );
      out.write("\"></td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td align=\"center\">제목</td>\r\n");
      out.write("					<td><input type=\"text\" name=\"title\" size=\"50\"\r\n");
      out.write("						value=\"");
      out.print( vo.getTitle() );
      out.write("\"></td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td align=\"center\">내용</td>\r\n");
      out.write("					<td><textarea name=\"content\" cols=\"50\" rows=\"10\">");
      out.print( vo.getContent() );
      out.write("</textarea>\r\n");
      out.write("					</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td align=\"center\">HTML</td>\r\n");
      out.write("					<td>\r\n");
      out.write("					<input type=\"radio\" name=\"tag\" value=\"1\">적용 \r\n");
      out.write("					<input type=\"radio\" name=\"tag\" value=\"0\">비적용 \r\n");
      out.write("					<script>\r\n");
      out.write("		        // JS  에서 EL 사용하는 방법    \"EL 사용가능\"\r\n");
      out.write("		        // :radio      ==  input type=radio\r\n");
      out.write("		        $(\":radio[value=");
      out.print( vo.getTag() );
      out.write("]\").attr(\"checked\", \"checked\");\r\n");
      out.write("		      </script>\r\n");
      out.write("		      </td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td align=\"center\">비밀번호</td>\r\n");
      out.write("					<td><input type=\"password\" name=\"pwd\" size=\"15\"></td>\r\n");
      out.write("				</tr>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td colspan=\"2\" align=\"center\">\r\n");
      out.write("					<input type=\"submit\" value=\"작성 완료\">&nbsp;&nbsp; \r\n");
      out.write("					<input type=\"button\" onClick=\"javascript:history.back();\" value=\"이전으로\">\r\n");
      out.write("					</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("			</table>\r\n");
      out.write("		</form>\r\n");
      out.write("	</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
