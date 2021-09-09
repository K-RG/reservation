package egovframework.com.reservation.custom.helper;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cms.board.service.BoardArticleFileService;

public class BoardArticleFileCustomHelper {
	private static final String BOARD_ARTICLE_FILE = "boardArticleFileService";
	
	public static BoardArticleFileService getBoardArticleService(ServletContext ctx) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
		
		return (BoardArticleFileService) wac.getBean(BOARD_ARTICLE_FILE);
	}
}
