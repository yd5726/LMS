package com.and.middle;

import java.util.HashMap;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lms_member.MemberVO;
import lms_member.TESTMemberVO;

@RestController
public class LMSController {
	//@Autowired @Qualifier("smart01") private SqlSession session;
	@Autowired @Qualifier("bteam") private SqlSession session;
	
	@RequestMapping("/and")
	public String andController_test() {
		return "and";
	}
	
	@RequestMapping(value = "/home.mj", produces ="text/html;charset=UTF-8")
	public String home() {
		List<TESTMemberVO> list = session.selectList("lms.home");
		System.out.println("list.size() : " + list.size());
		System.out.println("sql.selectList(\"lms.login\").size() : "
				+ session.selectList("lms.login").size());
		
		return new Gson().toJson(list);
	}
	
	// http://localhost:80/smart/login.mj?userid=admin
	@RequestMapping(value = "/login.mj", produces ="text/html;charset=UTF-8")
	//public String login(String userid) {
	public String login(String id) {
		//TESTMemberVO test_member = session.selectOne("lms.login",userid);
		MemberVO member = session.selectOne("lms.login",id);
		//System.out.println(test_member.getUserid()); // admin
		System.out.println(member.getId());
		
		//return new Gson().toJson(test_member); // {"userid":"admin"}
		return new Gson().toJson(member); // {"userid":"admin"}
	}
	
	// http://localhost/smart/login1.mj?id=user3&pw=000aA
	// http://192.168.0.3/smart/login1.mj?id=user3&pw=000aA
	@RequestMapping(value = "/login1.mj", produces ="text/html;charset=UTF-8")
	public String login1(String id, String pw) {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		
		MemberVO member = session.selectOne("lms.login1", map);
		System.out.println(member.getId());	//user3
		
		if(member != null) {
			System.out.println("id:" + member.getId());	//id:user3
			System.out.println("email:" + member.getEmail()); //email:user3@gg.com
		}
		
		return new Gson().toJson(member);
		//return member.getId();
	}
}
