package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.board.service.BoardService;

public class BoardCustomHelper {
	
	private static final String BOARD = "boardService";
	
	public static BoardService getBoardService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (BoardService) wac.getBean(BOARD);
	}
}
