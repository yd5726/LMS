package com.and.middle;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lms_board.BoardVO;
import lms_lecture.LectureVO;
import lms_member.MemberVO;

@RestController
public class LMSController {
	@Autowired @Qualifier("bteam") private SqlSession session;
	
	// Spring을 연 곳이 서버 _ 202호 컴퓨터 _ 192.168.0.122로 안드로이드 ip 설정
	// http://192.168.0.122/smart/login.mj?id=user3&pw=000aA
	// 로그인
	@RequestMapping(value = "/login.mj", produces ="text/html;charset=UTF-8")
	public String login1(String id, String pw) {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		
		MemberVO member = session.selectOne("member.login", map);
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
	
	// 회원가입
	@RequestMapping(value = "/join.mj", produces ="text/html;charset=utf-8")
	public String join(String member, HttpServletRequest request) {
		MemberVO vo = new Gson().fromJson(member, MemberVO.class);
		int result = session.insert("member.join",vo);
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
	
	// 특정 회원의 일주일 시간표
	@RequestMapping(value = "/ttlist.mj", produces ="text/html;charset=utf-8")
	public String ttlist(int teacher_code) {
		List<LectureVO> ttlist = session.selectList("lecture.list",teacher_code);
		
		return new Gson().toJson(ttlist);
	}
	
	// board 날짜별 정보 조회
	@RequestMapping(value = "/aclist.mj", produces ="text/html;charset=utf-8")
	public String aclist(String writedate) {
		List<BoardVO> aclist = session.selectList("board.list",writedate);
		
		return new Gson().toJson(aclist);
	}
}
