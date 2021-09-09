package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.board.service.BoardArticleService;

public class BoardArticleCustomHelper {
	private static final String BOARD_ARTICLE = "boardArticleService";
	
	public static BoardArticleService getBoardArticleService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (BoardArticleService) wac.getBean(BOARD_ARTICLE);
	}
}
