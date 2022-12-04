package com.javalab.javaconfig.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.javalab.javaconfig.domain.BoardVo;
import com.javalab.javaconfig.domain.Criteria;

/**
 * 매퍼 인터페이스
 * 추상 메소드 위에 SQL을 얹은 형태
 * 스프링이 자동으로 본 인터페이스를 바탕으로 SQL을 처리하는 클래스를 만들고 
 * 그 객체를 주입해준다. 
 * @author magic
 *
 */
public interface BoardMapper {

	//목록 조회
	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVo> getList();
	//조회(페이징)
	public List<BoardVo> getListWithPaging(Criteria cri);
	//저장
	public void insert(BoardVo board);	//단순 저장
	//저장
	public int insertSelectKey(BoardVo board);	//저장 하기 전에 PK값을 알아내서 그 값으로 저장
	//1건 조회
	public BoardVo read(Long bno);
	//삭제
	public int delete(Long bno);
	//수정
	public int update(BoardVo board);
	//전체게시물수
	public int getTotalCount(Criteria cri);
}
