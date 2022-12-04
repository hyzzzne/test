package com.javalab.javaconfig.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javalab.javaconfig.domain.QnaBbs;
import com.javalab.javaconfig.service.IQnaBbsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/bbs")
@RequiredArgsConstructor
public class BbsController
{
	//@Autowired
	// [생성자주입] @RequiredArgsConstructor + final
	private final IQnaBbsService service;
	
	// 처음 화면이 뜰때 게시물 목록 조회
	@RequestMapping(value = "/getQnaBbsList", method = RequestMethod.GET)
	public String getQnaBbsList(@ModelAttribute QnaBbs qnaBbs, Model model) throws Exception
	{
		List<QnaBbs> qnaBbsList = service.getQnaBbsList(qnaBbs);
		model.addAttribute("qnaBbsList", qnaBbsList);
		return "/bbs/board_list";
	}

	
	// 게시물 한개의 내용을 보여주는 메소드(핸들러)
	@GetMapping("/getBbsByNo")
	public String getBbsByNo(@RequestParam("no") int no, Model model){
		log.info("getBbsByNo 메소드 no : " + no);
		QnaBbs board = service.getBbsByNo(no);
		model.addAttribute("board", board);
		return "/bbs/board_view";	// bbsView.jsp
	}	
	
	/*
	 * [Ajax 요청 처리]
	 *  - 조회 화면은 조회후 이동하지 않기 때문에  Ajax를 통해서 Rest방식으로 처리해줌.
	 *  - @RequestBody QnaBbs : 화면의 조회 조건(메시지 body)을 QnaBbs 객체로 받음(바인딩)
	 *  - @ResponseBody : 처리 결과를 화면의 바디에 바로 출력할 수 있도록 조회 결과 자체를 리턴
	 *    화면쪽에서는 그 결과를 받아서 게시물 목록 부분만 변경함.
	 */
	@ResponseBody
	@RequestMapping(value = "/getQnaBbsListSearch", method = RequestMethod.POST)
	public List<QnaBbs> getQnaBbsSearch(@RequestBody QnaBbs qnaBbs, Model model) throws Exception
	{
		log.info("title : " + qnaBbs.getTitle());
		List<QnaBbs> qnaBbsList = service.getQnaBbsList(qnaBbs);
		model.addAttribute("qnaBbsList", qnaBbsList);
		return qnaBbsList;
	}	
	
	// 게시물 작성 폼을 띄워주는 메소드(Get방식)
	@GetMapping("/insertBbsForm")
	public String insertBbsForm(Model model){
		log.info("insertBbsForm 메소드");
		return "/bbs/insertBbsForm";	// insertBbsForm.jsp
	}

	// 작성된 게시물을 저장하는 메소드(Post방식)
	@PostMapping("/insertBbs")
	public String insertBbs(QnaBbs vo, Model model) throws IOException{
		log.info("insertBoard post 메소드");

		// 게시물 등록(저장)
		service.insertBbs(vo);
		
		// 저장후 목록 출력 컨트롤러 호출
		return "redirect:/bbs/getQnaBbsList"; 
	}
	
	// 수정폼을 보여주는 메소드(핸들러)
	@GetMapping("/updateBbsForm")
	public String updateBbsForm(@RequestParam("no") int no,
									Model model){
		// 수정폼에 보여줄 게시물 조회
		QnaBbs bbs = service.getBbsByNo(no);
		model.addAttribute("bbs", bbs);
		return "/bbs/updateBbsForm";	// updateBbsForm.jsp
	}
	
	// 수정한 내용을 데이터베이스에 반영하는 메소드(핸들러)
	@PostMapping("/updateBbs")
	public String updateBbs(@ModelAttribute("bbs") QnaBbs qnaBbs,
							RedirectAttributes rttr){
		log.info("updateBbs post 메소드 vo : " + qnaBbs.toString());
		service.updateBbs(qnaBbs); // 수정
		
		/*
		 * [업데이트 처리후]
		 *  - 업데이트후 redirect하기 때문에 페이징 처리를 위해서는 페이징 관련 정보를
		 *    url에 달아서 보내야 함.
		 *  1. rttr.addAttribute는 GET 방식이며 페이지를 새로고침 한다 해도 값이 유지된다.
		 *   - rttr.addAttribute("pageNum", cri.getPageNum());
		 *   - rttr.addAttribute("amount", cri.getAmount());
		 *   - rttr.addAttribute("searchText", cri.getSearchText());
		 *  2. rttr.addFlashAttribute는 POST 방식이며 이름처럼 일회성 데이터라 새로고침 하면 값이 사라진다.
		 *   - 처리후 이동해간 페이지에서 간단한 팝업을 띄울 경우 1회성으로 사용 가능함.
		 */
		
		return "redirect:/bbs/getQnaBbsList"; // 업데이트후 사용자로 하여금 게시물 조회 요청을 하도록 리다이렉트 함.
	}
	

}
