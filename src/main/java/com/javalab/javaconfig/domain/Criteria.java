package com.javalab.javaconfig.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 한 페이지에 보여줄 페이지에 대한 정보와 검색에 관련된 파라미터 보관
 *
 */
@Getter
@Setter
@ToString
public class Criteria {

	private int pageNum;
	private int amount;
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1,10);	//기본적으로 10게 게시물로 한정
		System.out.println("여기는 Criteria()생성자입니다.");
	}
	
	/**
	 * 오버로딩된 생성자
	 * @param pageNum
	 * @param amount
	 */
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	/**
	 * 검색 Type을 갖고 있는 메소드
	 * @return
	 */
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
	
	/**
	 * 여러개의 파라미터들을 연결해서 URL의 형태로 만들어주는 기능
	 * @return
	 */
	public String getListLink() {
		 UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				 .queryParam("pageNum", this.pageNum)
				 .queryParam("amount", this.getAmount())
				 .queryParam("type", this.getType())
				 .queryParam("keyword", this.getKeyword());
		 return builder.toUriString();
	 }
}
