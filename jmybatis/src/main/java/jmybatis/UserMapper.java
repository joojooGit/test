package jmybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	
	//리턴타입, 매서드 명, 매개변수 적어줘야함
	
	public ArrayList<UserDTO> getUser();//select
	
	public void insertUser(UserDTO userDTO);//insert
//							   (매게변수)
	public void updateUser(@Param("name")String name, @Param("user_id") String user_id);//updaete
//	mapper는 하나 값 밖에 처리하지 못하기 때문에 "@Param을 사용
	public void deleteUser(String user_id);//delete
	
}
