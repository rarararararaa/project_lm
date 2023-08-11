package kr.spring.bookstore.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.bookstore.payment.dao.BookStorePaymentCartMapper;
import kr.spring.bookstore.payment.dao.BookStorePaymentOrderMapper;
import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
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
			bookStorePaymentCartMapper.deleteCart(vo.getStore_product_num(), vo.getMem_num());
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
	
	

}
