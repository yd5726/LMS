package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lms_member.MemberVO;

@RestController
public class LMSController {
	@Autowired @Qualifier("smart01") private SqlSession session;
	@RequestMapping("/and")
	public String andController_test() {
		return "and";
	}
	@RequestMapping(value = "/home.mj", produces ="text/html;charset=UTF-8")
	public String login() {
		List<MemberVO> list = session.selectList("lms.login");
		System.out.println("list.size() : " + list.size());
		System.out.println("sql.selectList(\"lms.login\").size() : "
				+ session.selectList("lms.login").size());
		
		return new Gson().toJson(list);
	}
}
