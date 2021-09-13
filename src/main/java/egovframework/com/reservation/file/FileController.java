package egovframework.com.reservation.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.reservation.common.utils.FilePath;

@Controller
@PropertySource("classpath:egovframework/properties/file.properties")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Value("${file.path}")
	private String defaultPath;

	@Value("${file.slash}")
	private String slash;
		
	
	@RequestMapping(value="/ckeditor/fileupload.do", method=RequestMethod.POST)
	@ResponseBody
	public void fileupload(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiFile) throws IOException {
		logger.info("---------------- fileupload ----------------");
		
		PrintWriter pw = null;
		OutputStream os = null;
		MultipartFile file = multiFile.getFile("upload");
		
		if(file != null) {
			if(file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) {
				if(file.getContentType().toLowerCase().startsWith("image/")) { 
					
					try {
						String fileName = file.getOriginalFilename();
						byte[] bytes = file.getBytes();
						
						String filename_ext = fileName.substring(fileName.lastIndexOf(".") + 1);
						filename_ext = filename_ext.toLowerCase();
						
						logger.info("fileName : " + fileName + ", extension : " + filename_ext);
						
						String realFilePath = defaultPath + "ckeditor" + slash;
						
						logger.info("default path : " + defaultPath + " , realFilePath : " + realFilePath);
						
						File uploadFile = new File(realFilePath);
						if(!uploadFile.exists()) {
							uploadFile.mkdir();
						}
						
						fileName = UUID.randomUUID().toString() + "." + filename_ext;
						
						os = new FileOutputStream(new File(realFilePath + fileName));
						os.write(bytes);
						os.flush(); // outputStream 에 저장된 데이터를 전송하고 초기화
						
						String callback = request.getParameter("CKEditorFuncNum");
						logger.info("callback funcNum : " + callback);
						
						pw = response.getWriter();
						String fileUrl = "/ckeditor/imgsubmit.do?fileName=" + fileName;
						
						// 업로드시 메시지 출력
						pw.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
						pw.flush();
						
					} catch(IOException e) {
						e.printStackTrace();
					} finally {
						if(os != null) {
							os.close();
						}
						if(pw != null) {
							pw.close();
						}
					}
				}
			}
		}
		
		return;
	}       
	
	@RequestMapping("/ckeditor/imgsubmit.do")
	public void imgsubmit(@RequestParam(value="fileName") String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//서버에 저장된 이미지 경로
		String filePath = defaultPath + "ckeditor" + slash + fileName;
		File imgFile = new File(filePath);
		
		 //사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
		if(imgFile.isFile()) {
			byte[] buf = new byte[1024];
            int readByte = 0;
            int length = 0;
            byte[] imgBuf = null;
            
            FileInputStream fileInputStream = null;
            ByteArrayOutputStream outputStream = null;
            ServletOutputStream out = null;
            
            try{
                fileInputStream = new FileInputStream(imgFile);
                outputStream = new ByteArrayOutputStream();
                out = response.getOutputStream();
                
                while((readByte = fileInputStream.read(buf)) != -1){
                    outputStream.write(buf, 0, readByte);
                }
                
                imgBuf = outputStream.toByteArray();
                length = imgBuf.length;
                out.write(imgBuf, 0, length);
                out.flush();
                
            } catch(IOException e){
                logger.info(e.getMessage());
            } finally {
                outputStream.close();
                fileInputStream.close();
                out.close();
            }
		}
		
	}
}
