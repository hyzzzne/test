package com.javalab.javaconfig.domain;

import lombok.Getter;
import lombok.ToString;

/**
 * 페이징 및 한 페이지에 대한 정보 그리고 검색 관련 파라미터 정보 보관
 * [페이징 관련 정보]
 *  - 시작페이지, 종료페이지, 이전/이후 플래그, 전체 게시물갯수
 * [한 페이지 관련 정보]
 *  - 현재 페이지, 한 화면에 보여줄 게시물 수량
 * [검색 관련 정보]
 *  - 검색 type, 검색어  
 * @author magic
 *
 */
@Getter
@ToString
public class PageDto {

	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int total;
	private Criteria cri;
	
	/**
	 * 현재 페이지와 전체 게시물 갯수를 전달 받아서 페이징 관련 정보 계산
	 *  - 종료페이지 : 요청 페이지 * 10(예를들어 1페이지 요청하면 종료페이지는 10)
	 *  - 시작페이지 : 종료페이지 - 9(예를들면 1페이지 요청했으면 10-9 = 1)
	 *  - 실제종료페이지 : 1페이지 요청했는데 실제 게시글수가 90개라면 이건 종료페이지가 10이 아니고 9가 되어야 함.
	 *  - 이전표시(<) : 1페이지보다 크면 무조건 < 표시가 있어야 함.
	 *  - 다음표시(>) : 기본적으로 계산된 종료페이지 보다 실제 종료 페이지가 크면 다음 페이지 블록이 있기 때문에 > 표시해야.
	 * @param total
	 */
	public PageDto(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0)) * 10;
		this.startPage = endPage - 9;
		int realEndPage = (int)(Math.ceil((total*1.0)/cri.getAmount()));
		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		this.prev = this.startPage > 1;	//starPage > 1 면 true
		this.next = this.endPage < realEndPage;	//현재 페이지 블럭의 endPage가 realEndPage보다 작을때는 안보여줌.(false)
	}
	
}
