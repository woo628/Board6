package com.green.paging.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.paging.dto.Pagination;
import com.green.paging.dto.PagingResponse;
import com.green.paging.dto.SearchDto;
import com.green.paging.mapper.BoardPagingMapper;

@Controller
@RequestMapping("/BoardPaging")
public class BoardPagingController {
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private BoardPagingMapper boardPagingMapper;
	
	@RequestMapping("/List")
	private ModelAndView list(BoardDto boardDto, int nowpage) {
		
		List<MenuDTO> menuList = menuMapper.getMenuList();
		int totalCount = boardPagingMapper.count(boardDto); // menu_id 
		
		PagingResponse<BoardDto> response = null;
		if (totalCount < 1) { // 현재 Menu_id 로 조회한 자료가 없다면 
			response = new PagingResponse<>(Collections.emptyList(), null);
			                             // 자료가 없는 빈 리스트를 채운다 
		}
		
		// 페이징을 위한 초기설정 (안해도 되긴함 DTO에 만들어놔서)
		SearchDto searchDto = new SearchDto();
		searchDto.setPageNo(nowpage); // 현재 페이지 정보
		searchDto.setNumOfRows(10); // 한페이지에 출력할 정보
		searchDto.setPageSize(10); // paging.jsp에 출력될 페이지 번호 수
		
		// pagination 설정
		Pagination pagination = new Pagination(totalCount, searchDto);
		searchDto.setPagination(pagination);
		
		// 검색조건 추가
		// 추가된 검색 조건
		String menu_id = boardDto.getMenu_id();
		String title = boardDto.getTitle();
		String writer = boardDto.getWriter();
		String content = boardDto.getContent();
		
		int offset = searchDto.getOffset();
		int numOfRows = searchDto.getNumOfRows();
		
		List<BoardDto> list = boardPagingMapper.getBoardPagingList(menu_id, title, writer, content, offset, numOfRows);
		response = new PagingResponse<>(list, pagination);
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/list");
		mv.addObject("menuList", menuList);
		mv.addObject("nowpage", nowpage);
		mv.addObject("menu_id", menu_id); // 현재정보메뉴
		
		mv.addObject("searchDto", searchDto);
		mv.addObject("boardList", list);
		
		return mv;
	}
}
