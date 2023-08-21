package kr.spring.bookstore.payment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.bookstore.event.dao.BsEventMapper;
import kr.spring.bookstore.payment.dao.BookStorePaymentCartMapper;
import kr.spring.bookstore.payment.dao.BookStorePaymentOrderMapper;
import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.lm.point.dao.PointMapper;
import kr.spring.lm.point.vo.PointVO;
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
	@Autowired 
	BsEventMapper bsEventMapper; //포인트 적립 관련
	@Autowired
	PointMapper pointMapper;
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
				bookStorePaymentOrderMapper.updateUsed(0,vo.getUsed_product_num());
				continue;
			}
			int quantity = vo.getCart_quantity()*-1;
			bookStorePaymentOrderMapper.updateProduct(vo.getStore_product_num(),quantity);
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
	@Override
	public void updateQuantity(List<BookStorePaymentOrderVO> order) {
		log.debug("<<결제 취소 재고 변경>> : "+order);
		for(BookStorePaymentOrderVO vo : order) {
			if(vo.getUsed_product_num() != 0) {
				bookStorePaymentOrderMapper.updateUsed(1, vo.getUsed_product_num());
				continue;
			}
			int quantity = vo.getCart_quantity();
			bookStorePaymentOrderMapper.updateProduct(vo.getStore_product_num(),quantity);
		}
	}
	

	@Override
	public void updatePoint(Map<String, Object> map,int addPoint) {
		//포인트 차감 & 적립
		PointVO vo = new PointVO();
		vo.setMem_num((Integer)map.get("mem_num"));
		String order = String.valueOf(map.get("order_num"));
		vo.setPoint_reason(order);
		vo.setPoint_value((Integer)map.get("addPoint"));
		vo.setPoint_status(3);
		bsEventMapper.updateMemberPoint(map);
		//포인트 내역 등록
		pointMapper.insertOrderPoint(vo);
		log.debug("<<포인트 등록>> : "+vo);
		//적립 예정 포인트
		/*map.put("addPoint", addPoint);
		vo.setPoint_value((Integer)map.get("addPoint"));
		vo.setPoint_status(0);
		bsEventMapper.updateMemberPoint(map);
		pointMapper.insertOrderPoint(vo);*/
		//포인트 로그 등록
	}

	//주문 취소 포인트 조회
	@Override
	public void selectPoint(String point_reason) {
		//포인트 조회
		List<PointVO> point = pointMapper.selectPoint(point_reason);
		log.debug("<<주문 취소-포인트 조회>> : "+point);
		Map<String, Object> map = new HashMap<String, Object>();
		for(PointVO vo : point) {
			map.put("mem_num", vo.getMem_num());
			map.put("addPoint", (vo.getPoint_value()*-1));
			//내 포인트 변경
			bsEventMapper.updateMemberPoint(map);
		}
		//포인트 테이블 status 변경
		pointMapper.updatePointStatus(point_reason);
	}


}
