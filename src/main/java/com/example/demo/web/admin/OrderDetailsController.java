package com.example.demo.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.common.FlashData;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.BaseService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/orderdetails")
public class OrderDetailsController {
	@Autowired
	BaseService<Order> orderService;

	@Autowired
	BaseService<OrderDetail> orderDetailService;

	/*
	 * 新規作成画面表示
	 */
	@GetMapping(value = "/create/{id}")
	public String form(@PathVariable Integer id, OrderDetail orderDetail, Model model) {
		model.addAttribute("orderdetail", orderDetail);
		model.addAttribute("id", id);
		return "admin/orderdetails/create";
	}

	/*
	 * 新規登録
	 */
	@PostMapping(value = "/create/{id}")
	public String register(@PathVariable Integer id, @Valid OrderDetail orderDetail, BindingResult result, Model model, RedirectAttributes ra) {
		FlashData flash;
		try {
			if (result.hasErrors()) {
				model.addAttribute("orderdetail", orderDetail);
				model.addAttribute("id", id);
				return "admin/orderdetails/create";
			}
			orderDetail.setOrder(orderService.findById(id));
			// 新規登録
			orderDetailService.save(orderDetail);
			flash = new FlashData().success("新規作成しました");
		} catch (Exception e) {
			flash = new FlashData().danger("処理中にエラーが発生しました");
		}
		ra.addFlashAttribute("flash", flash);
		return "redirect:/admin/orders/view/" + id;
	}
}
