package kr.spring.bookstore.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.bookstore.payment.dao.BookStorePaymentOrderMapper;
import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;

@Service
public class BookStorePaymentOrderServiceImpl implements BookStorePaymentOrderService{
	@Autowired
	BookStorePaymentOrderMapper bookStorePaymentOrderMapper;

	@Override
	public void insertOrder(BookStorePaymentOrderVO bookStorePaymentOrderVO, List<BookStorePaymentCartVO> cartInfo) {
		int order_num = bookStorePaymentOrderMapper.selectOrder_num();
		bookStorePaymentOrderVO.setOrder_num(order_num);
		bookStorePaymentOrderMapper.insertOrder(bookStorePaymentOrderVO);
		
		for(BookStorePaymentCartVO vo : cartInfo) {
			vo.setOrder_num(order_num);
			bookStorePaymentOrderMapper.insertDetailOrder(vo);
		}
	} 
	
	

}
