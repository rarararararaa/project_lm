package kr.spring.bookstore.service.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.bookstore.payment.vo.BookStorePaymentOrderVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.service.vo.AnnounceVO;
import kr.spring.bookstore.service.vo.AnswerVO;
import kr.spring.bookstore.service.vo.AskVO;
import kr.spring.bookstore.service.vo.FaqVO;
import kr.spring.bookstore.service.vo.OrderDetailVO;

@Mapper
public interface ServiceMapper {
	//공지사항
	public List<AnnounceVO> selectAnnounceList(Map<String, Object> map);
	@Select("SELECT * FROM lm_board_announce WHERE board_num=#{board_num}")
	public AnnounceVO selectAnnounce(Integer board_num);
	@Insert("INSERT INTO lm_board_announce (board_num,board_title,board_content,board_reg_date,board_filename) VALUES (board_announce_seq.nextval,#{board_title},#{board_content},SYSDATE,#{board_filename})")
	public void insertAnnounce(AnnounceVO announceVO);
	public int selectRowCount(Map<String,Object> map);
	
	//자주묻는질문
	//작성
	@Insert("INSERT INTO lm_board_faq (faq_num,faq_title,faq_content,faq_reg_date,faq_category) VALUES (lm_board_faq_seq.nextval,#{faq_title},#{faq_content},SYSDATE,#{faq_category})")
	public void insertFaq(FaqVO faqVO);
	@Update("UPDATE lm_board_faq SET faq_title=#{faq_title},faq_content=#{faq_content},faq_category=#{faq_category} WHERE faq_num=#{faq_num}")
	public void updateFaq(FaqVO faqVO);
	@Select("SELECT * FROM lm_board_faq WHERE faq_num=#{faq_num}")
	public FaqVO selectFaq(Integer faq_num);
	//목록불러오기/검색
	public List<FaqVO> selectFaqList(Map<String, Object> map);
	public int selectRowCountFaq(Map<String,Object> map);
	
	//1:1문의 작성
	public void insertAsk(AskVO askVO);
	//목록-회원별
	@Select("SELECT * FROM lm_board_ask WHERE mem_num=#{mem_num} ORDER BY ask_status DESC, ask_reg_date DESC")
	public List<AskVO> selectAskListByMem_num(Integer mem_num);
	//목록-전체
	@Select("SELECT * FROM lm_board_ask ORDER BY ask_status, ask_reg_date DESC")
	public List<AskVO> selectAskList();
	//상세
	@Select("SELECT * FROM lm_board_ask WHERE ask_num=#{ask_num}")
	public AskVO selectAsk(Integer ask_num);
	
	//1:1문의 답변 작성
	public void insertAnswer(AnswerVO answerVO);
	@Update("UPDATE lm_board_ask SET ask_status=1 WHERE ask_num=#{ask_num}")
	public void updateAskStatus(Integer ask_num);
	
	//답변 보기
	@Select("SELECT * FROM lm_board_answer WHERE ask_num=#{ask_num}")
	public AnswerVO selectAnswer(Integer ask_num);
	
	//상품 수정
	@Update("UPDATE store_product_detail SET store_product_description=#{store_product_description}, store_product_pricesales=#{store_product_pricesales}, store_product_stock=#{store_product_stock} WHERE store_product_num=#{store_product_num}")
	public void updateProduct(ProductVO productVO);
	@Update("UPDATE store_product_manage SET store_product_stockstatus=#{store_product_stockstatus} WHERE store_product_num=#{store_product_num}")
	public void updateProductStatus(ProductVO productVO);
	//주문 상세 정보

	@Select("SELECT * FROM store_order_manage")
	public List<BookStorePaymentOrderVO> adminOrderList();
	@Select("SELECT * FROM store_order_detail WHERE order_num=#{order_num}")
	public List<OrderDetailVO> adminOrderDetailList(Integer order_num);
	@Select("SELECT store_product_title FROM store_product_detail WHERE store_product_num IN (SELECT store_product_num FROM store_order_detail WHERE order_num=#{order_num})")
	public List<String> adminOrderProductName(Integer order_num);
	@Select("SELECT * FROM store_order_manage WHERE order_num=#{order_num}")
	public BookStorePaymentOrderVO adminSelectOrder(Integer order_num);
	
	public ProductVO selectProductByNum(int product_num);
	
	@Select("UPDATE store_order_manage SET order_status=#{order_status} WHERE order_num=#{order_num} ")
	public void updateOrderStatus(BookStorePaymentOrderVO orderVO);
	
	public Integer selectOrderTotal(Map<String, Object> map); 
	
	public List<Map<String,Object>> selectOrderTotalOrderBy(Map<String,Object> map);
}