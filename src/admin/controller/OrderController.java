package admin.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.bean.Cart;
import shop.bean.EmailAccount;
import shop.entity.Order;
import shop.entity.OrderDetail;
import shop.entity.Product;
import shop.entity.Status;

@Controller
@Transactional
@RequestMapping("/admin/order/")
public class OrderController {
	
	@Autowired
	SessionFactory factory;
	@Autowired
	JavaMailSender mailer;

	
	EmailAccount account;
	
	@RequestMapping("tat-ca-don-hang")
	public String list(ModelMap model) {
		Session ss = factory.getCurrentSession();
		String hql = "FROM Order ORDER BY Id DESC";
		Query query = ss.createQuery(hql);
		List<Order> list = query.list();
		model.addAttribute("lstOrder", list);
		return "admin/list-order";
	}
	
	@RequestMapping(value="detail/{id}" , method = RequestMethod.GET)
	public String update(ModelMap model, @PathVariable("id") Integer id) {
		Order order = getOrder(id);
		model.addAttribute("order", order);
		return "admin/order-detail";
	}
	
	@RequestMapping(value="accept/{id}")
	public String accept(@PathVariable("id") Integer id) {
		Order order = getOrder(id);
		Status stt = getStatus(2);
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		
		String text = "Đơn hàng của bạn đã được xác nhận<br>Tổng số tiền: " + order.getTotalPrice() + " VNĐ <br> Chi tiết đơn hàng<br><br>";
		try {
			order.setStatus(stt);
			ss.update(order);
			t.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			t.rollback();
		}
		finally {
			ss.close();
		}
		try {
			// Tạo mail
			MimeMessage mail = mailer.createMimeMessage();
			System.out.println("1");
			// Sử dụng lớp trợ giúp
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			System.out.println("2");
			//helper.setFrom(account.getUsername(), account.getUsername());
			System.out.println("3");
			helper.setTo(order.getUser().getEmail());
			System.out.println("4");
			//helper.setReplyTo(account.getUsername(), account.getUsername());
			System.out.println("5");
			helper.setSubject("Đã xác nhận đơn hàng #" + order.getId());
			System.out.println("6");
			int i = 0;
			for (OrderDetail o : order.getOrderdetails()) {
				i++;
				text += i + ". " + o.getProduct().getName() + "<br>Số lượng: " + o.getQuantity() + "<br>Tổng cộng: " + o.getUnitPrice() + " VNĐ<br>";
			}
			System.out.println("7");
			helper.setText(text, true);
			// Gửi mail
			mailer.send(mail);

		}
		catch(Exception ex) {
			System.out.println("Không gửi đc mail " + ex.getMessage());
		}
		return "redirect:/admin/order/tat-ca-don-hang.htm";
	}
	
	@RequestMapping(value="cancel/{id}")
	public String cancel(@PathVariable("id") Integer id) {
		Order order = getOrder(id);
		Status stt = getStatus(3);
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		
		Collection<OrderDetail> orderDetail = order.getOrderdetails();
		ArrayList<OrderDetail> d = new ArrayList<OrderDetail>(orderDetail);
		int quantity = 0;
		try {
			order.setStatus(stt);
			ss.update(order);
			t.commit();
			// cap nhat lai so luong san pham
			for (OrderDetail i : d) {
				quantity = i.getProduct().getQuantity() + i.getQuantity();
				updateQuantityProd(i.getProduct().getId(), quantity);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			t.rollback();
		}
		finally {
			ss.close();
		}
		return "redirect:/admin/order/tat-ca-don-hang.htm";
	}
	
	@RequestMapping(value="don-hang-da-xac-nhan", method = RequestMethod.GET)
	public String orderAccept(ModelMap model) {
		List<Order> lst = listOrder(2);
		model.addAttribute("lstOrder", lst);
		return "admin/list-order-accept";
	}
	
	@RequestMapping(value="don-hang-dang-xac-nhan", method = RequestMethod.GET)
	public String orderWait(ModelMap model) {
		List<Order> lst = listOrder(1);
		model.addAttribute("lstOrder", lst);
		return "admin/list-order-wait";
	}
	
	@RequestMapping(value="don-hang-da-huy", method = RequestMethod.GET)
	public String orderCancel(ModelMap model) {
		List<Order> lst = listOrder(3);
		model.addAttribute("lstOrder", lst);
		return "admin/list-order-cancel";
	}
	
	@RequestMapping(value="don-hang-da-giao", method = RequestMethod.GET)
	public String orderDelivered(ModelMap model) {
		List<Order> lst = listOrder(4);
		model.addAttribute("lstOrder", lst);
		return "admin/list-order-delivered";
	}
	
	/* các hàm xử lý */
	
	public Order getOrder(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Order WHERE Id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Order order = (Order) query.uniqueResult();
		session.clear();
		return order;
	}
	
	public Status getStatus(int stt) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Status WHERE Id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", stt);
		Status status = (Status) query.uniqueResult();
		session.clear();
		return status;
	}
	
	public List<Order> listOrder(int type) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Order WHERE status.Id = :status ORDER BY Id DESC";
		Query query = session.createQuery(hql);
		query.setParameter("status", type);
		List<Order> lst = query.list();
		session.clear();
		return lst;
	}
	
	public void updateQuantityProd(int id, int quantity) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE Product SET Quantity = :quantity WHERE Id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("quantity", quantity);
		query.setParameter("id", id);
		
		query.executeUpdate();
		
	}
}
