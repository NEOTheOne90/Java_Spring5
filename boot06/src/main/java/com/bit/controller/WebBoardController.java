package com.bit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Sort;

import com.bit.domain.WebBoard;
import com.bit.persistence.WebBoardRepository;
import com.bit.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/boards/")
public class WebBoardController {
	
	@Autowired
	private WebBoardRepository repo;

	@GetMapping("/list")//list.html
	public void list(PageVO vo, Model model) {
		Pageable page = vo.makePageable(0, "bno");
		Page<WebBoard> result = 
				repo.findAll(repo.makePredicate(null, null), page);
		
		log.info(""+page);
		log.info(""+result);
		log.info(""+result.getTotalPages());
		model.addAttribute("result", result);
	}
	
	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo") 
		WebBoard vo) {
		log.info("registerGET....");
		vo.setTitle("샘플 게시물 제목입니다....");
		vo.setContent("샘플 게시물 내용입니다....");
		vo.setWriter("user00");
	}
}










