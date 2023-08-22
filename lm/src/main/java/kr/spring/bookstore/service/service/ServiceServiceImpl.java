package kr.spring.bookstore.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.service.dao.ServiceMapper;
import kr.spring.bookstore.service.vo.AnnounceVO;
import kr.spring.bookstore.service.vo.AnswerVO;
import kr.spring.bookstore.service.vo.AskVO;
import kr.spring.bookstore.service.vo.FaqVO;
import kr.spring.bookstore.service.vo.OrderDetailVO;

@Service
@Transactional
public class ServiceServiceImpl implements ServiceService{

	@Autowired
	ServiceMapper serviceMapper;
	
	@Override
	public List<AnnounceVO> selectAnnounceList(Map<String, Object> map) {
		return serviceMapper.selectAnnounceList(map);
	}

	@Override
	public void insertAnnounce(AnnounceVO announceVO) {
		serviceMapper.insertAnnounce(announceVO);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return serviceMapper.selectRowCount(map);
	}

	@Override
	public AnnounceVO selectAnnounce(Integer board_num) {
		return serviceMapper.selectAnnounce(board_num);
	}

	@Override
	public void insertFaq(FaqVO faqVO) {
		serviceMapper.insertFaq(faqVO);
	}

	@Override
	public List<FaqVO> selectFaqList(Map<String, Object> map) {
		return serviceMapper.selectFaqList(map);
	}

	@Override
	public int selectRowCountFaq(Map<String, Object> map) {
		return serviceMapper.selectRowCountFaq(map);
	}

	@Override
	public void updateFaq(FaqVO faqVO) {
		serviceMapper.updateFaq(faqVO);
	}

	@Override
	public void insertAsk(AskVO askVO) {
		serviceMapper.insertAsk(askVO);
	}

	@Override
	public List<AskVO> selectAskListByMem_num(Integer mem_num) {
		return serviceMapper.selectAskListByMem_num(mem_num);
	}

	@Override
	public List<AskVO> selectAskList() {
		return serviceMapper.selectAskList();
	}

	@Override
	public AskVO selectAsk(Integer ask_num) {
		return serviceMapper.selectAsk(ask_num);
	}

	@Override
	public void insertAnswer(AnswerVO answerVO) {
		serviceMapper.updateAskStatus(answerVO.getAsk_num());
		serviceMapper.insertAnswer(answerVO);
	}

	@Override
	public AnswerVO selectAnswer(Integer ask_num) {
		return serviceMapper.selectAnswer(ask_num);
	}

	@Override
	public List<BookStorePaymentOrderVO> adminOrderList() {
		List<BookStorePaymentOrderVO> list = new ArrayList<BookStorePaymentOrderVO>();
		list = serviceMapper.adminOrderList();
		for(BookStorePaymentOrderVO order:list) {
			List<String> name = new ArrayList<String>();
			name = serviceMapper.adminOrderProductName(order.getOrder_num());
			String ordername = name.get(0);
			if(name.size() > 1) {
				ordername += "외 " + (name.size()-1) + "개";
			}
			
			order.setProduct_name(ordername);
		}
		return list;
	}

	@Override
	public void updateProduct(ProductVO productVO) {
		serviceMapper.updateProductStatus(productVO);
		serviceMapper.updateProduct(productVO);
	}

	@Override
	public List<OrderDetailVO> adminOrderDetailList(Integer order_num) {
		return serviceMapper.adminOrderDetailList(order_num);
	}

	@Override
	public BookStorePaymentOrderVO adminSelectOrder(Integer order_num) {
		return serviceMapper.adminSelectOrder(order_num);
	}

	@Override
	public ProductVO selectProductByNum(int product_num) {
		return serviceMapper.selectProductByNum(product_num);
	}

	@Override
	public void updateOrderStatus(BookStorePaymentOrderVO orderVO) {
		serviceMapper.updateOrderStatus(orderVO);
	}

	@Override
	public Integer selectOrderTotal(Map<String, Object> map) {
		return serviceMapper.selectOrderTotal(map);
	}

}
