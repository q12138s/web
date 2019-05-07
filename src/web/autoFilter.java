package web;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;
import domain.User;

public class autoFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		//���cookie���û��������� ���е�¼�Ĳ���
		//����cookie_username
		String cookie_username = null;
		//����cookie_password
		String cookie_password = null;
		//���cookie
		Cookie[] cookies = req.getCookies();
		if(cookies!=null){
			for(Cookie cookie : cookies){
				//���������cookie_username��cookie_password
				if("cookie_username".equals(cookie.getName())){
					cookie_username = cookie.getValue();
					//�ָ������û���
					//cookie_username = URLDecoder.decode(cookie_username, "UTF-8");
					System.out.println(cookie_username+"222");
				}
				if("cookie_password".equals(cookie.getName())){
					cookie_password = cookie.getValue();
					System.out.println(cookie_password+"111");
				}
			}
		}
		
		//�ж�username��password�Ƿ���null
		if(cookie_username!=null&&cookie_password!=null){
			//��¼�Ĵ���
			LoginService service=new LoginService();
			User user=null;
			try {
				 user=service.login(cookie_username,cookie_password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(user);
			//����¼���û���user����浽session��
			session.setAttribute("user", user);
		}
		chain.doFilter(req, resp);

		
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	 
}
