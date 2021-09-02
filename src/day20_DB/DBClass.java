package day20_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBClass {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "java";
	private String pw = "1234";
	
	public DBClass() {
		//1. 자바에서 오라클에 관련된 기능을 사용할 수 있게 기능을 등록하는 것
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public ArrayList<StudentDTO> getList() {
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		//2. 데이터베이스 연결
		try {
			Connection con = DriverManager.getConnection(url,id,pw);
			System.out.println("연결이 잘 이뤄졌습니다!!!");
			
			//3. 데이터베이스에 연결된 객체를 이용해서 명령어를 수행할 수 있는 객체를 얻어온다.
			String sql = "select * from newst";
			PreparedStatement ps = con.prepareStatement(sql);
			
			//4. 명령어를 수행할 수 있는 객체를 이용해서 명령어 수행
			//5. 수행 결과를 저장한다.
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				/*
				System.out.println("id : " + rs.getString("id")); //상단에 ResultSet으로 값을 set을 해주었으니 get으로 값들을 이클립스에서 출력해주게 되는 형식입니다.
				System.out.println("name : " + rs.getString("name"));
				System.out.println("age : " + rs.getInt("age"));
				System.out.println("-------------------");
				*/
				StudentDTO dto = new StudentDTO();
				dto.setId(rs.getString("id")); //여기서는 값을 받아온 데이터베이스를 dto에 set으로 값을 넘겨주는 작업입니다/
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				list.add(dto); //dto로 받은 정보를 arraylist로 저장시켜주는 작업입니다.
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public StudentDTO serchST(String id) {
		String sql = "select * from newst where id='"+id +"'";
		StudentDTO dto = null;
		try {
			Connection con = DriverManager.getConnection(url,this.id,pw);
			System.out.println("---연결 확인---");
			//2. 명령어 (쿼리문) 전송 객체 생성
			PreparedStatement ps = con.prepareStatement(sql);
			//3.전송 후 결과값 저장
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				dto = new StudentDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dto;
	}
	
	public int saveData(String userId, String userName, int userAge) {
		String sql = "insert into newst values(?,?,?)";
		int result =0;
		try {
			Connection con = DriverManager.getConnection(url,id,pw);
			PreparedStatement ps = con.prepareStatement(sql);
			//? 자리 채우기
			ps.setString(1, userId);
			ps.setString(2, userName);
			ps.setInt(3, userAge);
			// 쿼리문 전송(실행)
			//ResultSet rs = ps.executeQuery(); 는 select 쿼리문일때 executeQuery를 사용합니다. 
			// select를 제외한 다른 쿼리문은 executeUpadate()를 사용합니다. 
			//executeUpdate는 int 형태의 값을 돌려준다. 성공 1 실패 0 또는 에러
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return result;		
	}
	
	public int deleteData(String userId) {
		String sql = "delete from newst where id=?";
		int result = 0;
		try {
			Connection con = DriverManager.getConnection(url,id,pw);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return result;
	}
	
	public int updateData(String userId, String userName, int userAge) {
		int result = 0;
		String sql = "update newst set name=?, age=? where id=?";
		try {
			Connection con = DriverManager.getConnection(url, id, pw);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setInt(2, userAge);
			ps.setString(3, userId);
			result = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return result;
		
	}
	
	
	
}





















