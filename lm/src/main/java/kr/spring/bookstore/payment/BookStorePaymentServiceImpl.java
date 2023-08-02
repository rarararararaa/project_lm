package kr.spring.bookstore.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.bookstore.payment.dao.BookStorePaymentCartMapper;
import kr.spring.bookstore.payment.vo.BookStorePaymentCartVO;
import kr.spring.bookstore.product.vo.ProductVO;

@Service
@Transactional
public class BookStorePaymentServiceImpl implements BookStorePaymentService{
	
	@Autowired
	BookStorePaymentCartMapper bookStorePaymentCartMapper;
	
	@Override
	public void insertCart(BookStorePaymentCartVO cartVO) {
		bookStorePaymentCartMapper.insertCart(cartVO);
	}

	@Override
	public List<BookStorePaymentCartVO> selectCartList(int mem_num) {
		return bookStorePaymentCartMapper.selectCartList(mem_num);
	}

	@Override
	public ProductVO selectDetailBook(int store_product_num) {
		return bookStorePaymentCartMapper.selectDetailBook(store_product_num);
	}

}
