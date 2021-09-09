package egovframework.com.reservation.reservation.service;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
@Transactional
public class CheckService {

	
	public void certifiedPhoneNumber(String phoneNumber, String cerNum) {
		
		String api_key = "NCSBSBAXPIAPHQYD";
		String api_secret = "5HEGXICJKLHHIQB1OHCF1HMNRZBOEFX6";
		
		Message coolsms = new Message(api_key, api_secret);
		
		HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01021057767");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "야놀자 휴대폰 인증  메시지 : 인증번호는" + "["+cerNum+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version
        
        try {
            JSONObject obj = coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
	}
}
