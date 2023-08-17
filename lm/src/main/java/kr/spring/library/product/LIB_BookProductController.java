package kr.spring.library.product;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.vo.RentVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LIB_BookProductController {
	@Autowired
	private BookProductService bookProductService;
	//도서 API
	@RequestMapping("/lm/bookproduct.do")
	public void insertBook() {
		bookProductService.getData();
		
		log.debug("<<도서 API 등록>> : ");
	}
	//도서 상세 API
	@RequestMapping("/lm/bookproduct_detail.do")
	public void getDetail() {
		bookProductService.updateLIB_P_description();
		log.debug("<<도서 API 등록>> : ");
	}

	//도서 상세
	@GetMapping("/library/lib_book/bookDetail.do")
	public String cartForm(@RequestParam String callNumber, Model model, HttpSession session) {
		BookProductVO book = bookProductService.selectDetailLIB_P(callNumber);
		log.debug("<<도서 상세>> : "+book);
		//대출 도서 리스트
		List<BookProductVO> list = bookProductService.selectListLIB_P(book.getLib_product_isbn());
		//도서분류
		for(BookProductVO vo : list) {
			RentVO rent = bookProductService.selectDate(vo.getCallNumber());
			if(rent != null) {
				vo.setReturn_reg_deadline(rent.getReturn_reg_deadline());
			}
		}
		String class_name = getClassName(book.getLib_product_class_no());
		
		model.addAttribute("book", book);
		model.addAttribute("className", class_name);
		model.addAttribute("list", list);
		
		log.debug("<<도서 상세>> : "+list);
		
		return "bookDetail";
	}
	//도서 분류
	public String getClassName(int class_no) {
		String class_name = "";
		if(class_no == 0 ) class_name ="총류";
		if(class_no == 1 ) class_name ="철학";
		if(class_no == 2 ) class_name ="종교";
		if(class_no == 3 ) class_name ="사회과학";
		if(class_no == 4 ) class_name ="자연과학";
		if(class_no == 5 ) class_name ="기술과학";
		if(class_no == 6 ) class_name ="예술";
		if(class_no == 7 ) class_name ="언어";
		if(class_no == 8 ) class_name ="문학";
		if(class_no == 9 ) class_name ="역사";
		return class_name;
	}
	
}
