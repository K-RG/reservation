package egovframework.com.reservation.common.utils;

/**
 * 기본 파일 경로 리턴
 * @author user
 *
 */
public class FilePath {
	
	public static String setFilePath(String defaultPath, String slash, int pathNum) {
		// 파일경로
		String path = "";
		
		/* pathNum 에 따라 저장될 위치 변경
         * 1 : 게시글
         * 2 : 상품
         * 3 : 배너팝업
         * 4 : 사용자
         * 5 : 상품 아이템
         */
        switch(pathNum){
        	case 1 : path = defaultPath + "article" + slash; break;
        	case 2 : path = defaultPath + "product" + slash; break;
        	case 3 : path = defaultPath + "bannerpopup" + slash; break;
        	case 4 : path = defaultPath + "user" + slash; break;
        	case 5 : path = defaultPath + "product" + slash + "item" + slash; break;
        }
        
        return path;
	}
}
