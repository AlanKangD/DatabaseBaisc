package day20_DB;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num, age, result;
		String id, name;
		ArrayList<StudentDTO> list = null;
 		DBClass db = new DBClass();
		
		while(true) {
			System.out.println("1.모든데이터보기 2.검색 3.저장 4.삭제 5. 수정");
			num = sc.nextInt();
			switch(num) {
			case 1: 
				// 데이터 베이스의 모든 데이터를 가져와서 보여준다.
				list = db.getList();
				for(int i=0; i<list.size(); i++) {
					System.out.println("id : " + list.get(i).getId()); //상단에 ResultSet으로 값을 set을 해주었으니 get으로 값들을 이클립스에서 출력해주게 되는 형식입니다.
					System.out.println("name : " + list.get(i).getName());
					System.out.println("age : " + list.get(i).getAge());
					System.out.println("-------------------");
				}
				break;
			case 2: 
				// 검색 데이터를 데이터베이스에서 가져오기
				System.out.println("검색 id 입력");
				id = sc.next();
				StudentDTO dto  = db.serchST(id);
				if(dto != null) {
				System.out.println("id : " + dto.getId());
				System.out.println("name : " + dto.getName());
				System.out.println("age : " + dto.getAge());
				System.out.println();
				}else {
					System.out.println("해당 아이디는 존재 하지 않습니다.");
				}
				break;
			case 3: 
				System.out.println("아이디 입력 : ");
				id = sc.next();
				System.out.println("이름 입력 : ");
				name = sc.next();
				System.out.println("나이 입력 :"); 
				age = sc.nextInt();
				
				result = db.saveData(id, name, age);
				
				if(result == 1) {
					System.out.println("저장성공!");
				} else {
					System.out.println("동일한 아이디가 존재합니다.");
				}
				break;
			case 4:
				System.out.println("삭제할 아이디 입력 : ");
				id = sc.next();
				result = db.deleteData(id);
				if(result == 1) {
					System.out.println("정상적으로 삭제 되었습니다.");
				}else {
					System.out.println("해당하는 아이디는 존재하지 않습니다.(삭제 실패)");
				}
				break;
			case 5: 
				System.out.println("수정할 아이디 : ");
				id = sc.next();
				System.out.println("수정할 이름 : ");
				name = sc.next();
				System.out.println("수정할 나이 : ");
				age = sc.nextInt();
				result = db.updateData(id,name,age);
				if(result == 1) {
					System.out.println("정상적으로 수정되셨습니다.");
				}else {
					System.out.println("수정에 실패했습니다.");
				}
				break;
			}
			
			
		}
		
		
		
	}
	
}
