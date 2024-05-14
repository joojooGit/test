package jmybatis;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtil {
	SqlSessionFactory sqlSessionFactory;

	public void init() {

		try {
			String resource = "jmybatis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("MyBatis 설정 파일 가져오기 실패");
			e.printStackTrace();
		}
	}

	// select
	public ArrayList<UserDTO> getUser() {
		// SQL문 처리
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		ArrayList<UserDTO> userlist = mapper.getUser();

		return userlist;
	}// select 끝

	// insert
	public void insertUser(String user_id, String user_pw, String name, String phone, String grade, int age) {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		UserDTO userDTO = new UserDTO(user_id, user_pw, name, phone, grade, age);
		mapper.insertUser(userDTO);
		session.commit();// insert,update,delete에만 해당 (필수)

	}// insert 끝

	// update
	public void updateUser(String name, String user_id) {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.updateUser(name, user_id);
		session.commit();
	}// update 끝

	// delete
	public void deleteUser(String user_id) {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.deleteUser(user_id);
		session.commit();
	}

	public void login(String user_id, String user_pw) {
		UserDTO dto = new UserDTO(user_id, user_pw);
		SqlSession session = sqlSessionFactory.openSession();
		int count = session.selectOne("loginCh", user_id);

		if (count == 0) {
			System.out.println("아이디가 존재하지않음");
		} else if() {
			dto = session.selectOne("login", user_id);
			if (user_pw.equals(dto.getUser_id()))
				;
			System.out.println("환영합니다" + dto.getName());
		}else {
			System.out.println("비밀번호가 다릅다니");
		}
	}

}// class
