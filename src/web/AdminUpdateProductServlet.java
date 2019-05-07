package web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import service.AdminProductListService;
import domain.Product;

public class AdminUpdateProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 1����ȡ����
		Map<String, String[]> properties = request.getParameterMap();
		// 2����װ����
		Product product = new Product();

		try {
			BeanUtils.populate(product, properties);
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ��λ��Product�Ѿ���װ���----���������ݷ�װ���
		// �ֶ����ñ���û������
		// 1����private String pid;
		//request.getParameter("pid");
		// 2����private String pimage;
		product.setPimage("products/1/c_0033.jpg");
		// 3����private String pdate;//�ϼ�����
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String pdate = format.format(new Date());
		product.setPdate(pdate);
		// 4����private int pflag;//��Ʒ�Ƿ����� 0����δ�¼�
		product.setPflag(0);

		// 3���������ݸ�service��
		AdminProductListService service = new AdminProductListService();
		try {
			service.updateProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/adminproductlist");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
