package kr.spring.bookstore.payment.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.bookstore.payment.dao.BookStorePaymentCartMapper;
import kr.spring.bookstore.payment.dao.BookStorePaymentOrderMapper;
import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.member.dao.MemberMapper;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookStorePaymentOrderServiceImpl implements BookStorePaymentOrderService{
	@Autowired
	BookStorePaymentOrderMapper bookStorePaymentOrderMapper;
	@Autowired
	BookStorePaymentCartMapper	bookStorePaymentCartMapper;
	@Autowired
	MemberService memberService;
	//주문 테이블 저장
	@Override
	public void insertOrder(BookStorePaymentOrderVO bookStorePaymentOrderVO, List<BookStorePaymentCartVO> cartInfo) {
		int order_num = bookStorePaymentOrderMapper.selectOrder_num();
		
		bookStorePaymentOrderVO.setOrder_num(order_num);
		bookStorePaymentOrderMapper.insertOrder(bookStorePaymentOrderVO);
		
		for(BookStorePaymentCartVO vo : cartInfo) {
			log.debug("<<insert-detail>> : "+vo);
			vo.setOrder_num(order_num);
			//도서 상세 등록 후 해당 도서 장바구니에서 삭제
			bookStorePaymentOrderMapper.insertDetailOrder(vo);
			bookStorePaymentCartMapper.deleteCart(vo.getMem_cart_num());
			if(vo.getUsed_product_num() != 0) {
				bookStorePaymentOrderMapper.updateUsed(vo.getUsed_product_num());
			}
			bookStorePaymentOrderMapper.updateProduct(vo.getStore_product_status());
		}
	}
//배송지 관련
	@Override
	public MemberVO selectHome(int home_num) {
		return bookStorePaymentOrderMapper.selectHome(home_num);
	}

	@Override
	public List<MemberVO> selectMemHome(int mem_num) {
		return bookStorePaymentOrderMapper.selectMemHome(mem_num);
	}
//배송지 추가
	@Override
	public void insertHome(MemberVO memberVo) {
		if(memberVo.getHome_default() == 0) {
			MemberVO member = memberService.homeDefault(memberVo.getMem_num());
			log.debug("<<update home>> : "+member);
			bookStorePaymentOrderMapper.updateDefault(member);
		}
		bookStorePaymentOrderMapper.insertHome(memberVo);
	}
	@Override
	public void deleteHome(int home_num) {
		bookStorePaymentOrderMapper.deleteHome(home_num);;
	}
	@Override
	public MemberVO selectDefaultHome(int mem_num) {
		return bookStorePaymentOrderMapper.selectDefaultHome(mem_num);
	}
	@Override
	public void updateHome(MemberVO memberVO) {
		if(memberVO.getHome_default() == 0) {
			MemberVO member = memberService.homeDefault(memberVO.getMem_num());
			bookStorePaymentOrderMapper.updateDefault(member);
		}
		bookStorePaymentOrderMapper.updateHome(memberVO);
	}
	@Override
	public void updateNormal(int home_num, MemberVO mem) {
		bookStorePaymentOrderMapper.updateDefault(mem);
		bookStorePaymentOrderMapper.updateNormal(home_num);
	}
	@Override
	public BookStorePaymentOrderVO selectOrder(int order_num) {
		return bookStorePaymentOrderMapper.selectOrder(order_num);
	}
	@Override
	public List<BookStorePaymentOrderVO> listOrder(int order_num) {
		return bookStorePaymentOrderMapper.listOrder(order_num);
	}
	@Override
	public ProductVO selectProductNum(int product_num) {
		return bookStorePaymentOrderMapper.selectProductNum(product_num);
	}
	//==================주문 취소==========================//
	//imp_uid 값 구하기
	@Override
	public String selectImp_uid(int order_num) {
		return bookStorePaymentOrderMapper.selectImp_uid(order_num);
	}
	@Override
	public void updateCancelInfo(int order_num, String cancel_notice) {
		bookStorePaymentOrderMapper.updateCancelInfo(order_num, cancel_notice);
	} 
	
	

}
