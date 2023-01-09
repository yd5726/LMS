package com.and.middle;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lms_member.MemberVO;

@RestController
public class LMSController {
	@Autowired @Qualifier("bteam") private SqlSession session;
	
	// Spring을 연 곳이 서버 _ 202호 컴퓨터 _ 192.168.0.122로 안드로이드 ip 설정
	// http://192.168.0.122/smart/login.mj?id=user3&pw=000aA
	@RequestMapping(value = "/login.mj", produces ="text/html;charset=UTF-8")
	public String login1(String id, String pw) {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		
		MemberVO member = session.selectOne("lms.login", map);
		if(member != null) {
			System.out.println("==로그인 성공==");
			System.out.println("id:" + member.getId());
			System.out.println("type:" + member.getType());
			System.out.println("name:" + member.getMember_name());
			System.out.println("email:" + member.getEmail());
		}else {
			System.out.println("==로그인 실패==");
		}		
		return new Gson().toJson(member);
	}
	
	@RequestMapping(value = "/join.mj", produces ="text/html;charset=utf-8")
	public String join(String member, HttpServletRequest request) {
		MemberVO vo = new Gson().fromJson(member, MemberVO.class);
		int result = session.insert("lms.join",vo);
		if(vo != null) {
			System.out.println("==회원가입 성공==");
			System.out.println("id:" + vo.getId());
			System.out.println("type:" + vo.getType());
			System.out.println("name:" + vo.getMember_name());
			System.out.println("email:" + vo.getEmail());
		}else {
			System.out.println("==회원가입 실패==");
		}	
		return new Gson().toJson(result + "");
	}
	
}
