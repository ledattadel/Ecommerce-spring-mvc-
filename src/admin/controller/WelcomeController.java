package admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.entity.User;

@Controller
@Transactional
@RequestMapping("/admin/")
public class WelcomeController {
	User mUser = new User();
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String manage(ModelMap model) {
		model.addAttribute("totalPro", totalItem());
		model.addAttribute("totalOrder", totalOrder());
		model.addAttribute("revenue", revenue());
		model.addAttribute("orderWait", orderWait());
		return "admin/index";
	}
	
	public int totalItem() {
		Session ss = factory.getCurrentSession();
		String hql = "SELECT COUNT(*) FROM Product";
		Query query = ss.createQuery(hql);
		Long count = (Long) query.uniqueResult();
		if(count == null) {
			return 0;
		}
		return count.intValue();
	}
	
	public int totalOrder() {
		Session ss = factory.getCurrentSession();
		String hql = "SELECT COUNT(*) FROM Order";
		Query query = ss.createQuery(hql);
		Long count = (Long) query.uniqueResult();
		if(count == null) {
			return 0;
		}
		return count.intValue();
	}
	
	public double revenue() {
		Session ss = factory.getCurrentSession();
		String hql = "SELECT SUM(TotalPrice) FROM Order WHERE status.Id = 2";
		Query query = ss.createQuery(hql);
		Double revenue = (Double) query.uniqueResult();
		if(revenue == null) {
			return 0;
		}
		return revenue.intValue();
	}
	
	public int orderWait() {
		Session ss = factory.getCurrentSession();
		String hql = "SELECT COUNT(*) FROM Order WHERE status.Id = 1";
		Query query = ss.createQuery(hql);
		Long count = (Long) query.uniqueResult();
		if(count == null) {
			return 0;
		}
		return count.intValue();
	}

	@RequestMapping("add-product")
	public String addProduct() {
		return "admin/add-product";
	}

}
