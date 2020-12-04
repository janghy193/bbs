package com.tjoeun.prj;

import java.io.*;

public class StreamTest {
	public static void main(String[] args) {
		// 파일에서 다른 파일로 복사하기
		// 파일에서 읽어서 메모리에 로드한다
		File f = new File("C:/Users/tjoeun/Downloads/1.jpg");
		
		//imageCopy2(f);
		//textCopy("c:/test/user.csv");
		//copyImgByFilter(f);
		//readTextByFilter("C:/test/user.csv");
		kbdToFile(new File("C:/test/kbdsave.txt"));
		
		System.out.println("저장된 파일 내용");
		readTextByFilter("C:/test/kbdsave.txt");
		
		System.out.println("프로그램 종료");
	}
	
	private static void kbdToFile(File file) {
		// file.exists(); // 파일 실존 여부 불리언으로 리턴하는 함수
		
		// 키보드에서 회원정보 한행을 입력받아서 kdbsave.txt 파일에 한 행으로 저장
		// 모니터:out(표준 출력 스트림, PrintStream), System.out
		// 키보드:in(표준 입력 스트림, InputStream), System.in
		// BufferedReader + System.in
		// 입력시의 변환스트림 : InputStreamReader 가 요구됨
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw = new PrintWriter(file);
			String line = null;
			boolean go = true;
			while(go) {
				System.out.print("종료(ctrl+z)| 번호,이름,전화,메일:");
				if((line=br.readLine())!=null){
					pw.println(line);	// 한 행 프린트(PrintWriter객체이므로 파일에 프린트)
				}
			}
			br.close();
			pw.close();
			System.out.println("키보드->파일 작업 완료");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void readTextByFilter(String f) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = null;
			while((line=br.readLine())!=null) {
				System.out.println(line);
			}
			br.close();  // 닫을때는 최상위 스트림만
			System.out.println("필터스트림에 의한 복사 완료");
		} catch (Exception e) {
			System.err.println("그런 파일 없음");
			e.printStackTrace();
		} // filter stream이므로 직접 파일 받을 수 없음
	}

	private static void copyImgByFilter(File f) {
		try {
			// 메모리를 사용(메모리 버퍼)하여 성능을 향상시키는 filter stream
			// node stream이 아니기때문에 직접 파일을 인자로 받을 수는 없다
			// 인자는 node stream인 fileinputstream
			BufferedInputStream bin = new BufferedInputStream(new FileInputStream(f));
			FileOutputStream fout = new FileOutputStream("C:/Users/tjoeun/Downloads/copy1.jpg");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			byte[] buf = new byte[1024];
			int red = 0;
			while((red=bin.read(buf))!=-1) {
				bout.write(buf,0,red);
			}
			bin.close();
			bout.close();	// 닫을때는 최 상위 스트림만
			System.out.println("필터스트림에 의한 복사 완료");
		} catch (Exception e) {
			System.err.println("그런 파일 없음");
			e.printStackTrace();
		} // filter stream이므로 직접 파일 받을 수 없음
	}

	private static void textCopy(String f) {
		try {	
			FileInputStream fin = new FileInputStream(f);
			
			FileOutputStream fout = new FileOutputStream("C:/test/copy.csv");
			
			byte[] buf = new  byte[1024];
			int red = 0;
			
			while((red=fin.read(buf,0,buf.length))!=-1) {
				//System.out.printf("읽어온 바이트 수 : %d\n",red);
				fout.write(buf,0,red);
			}
			fin.close();
			fout.close();
			System.out.println("텍스트 파일 복사 완료!");
		} catch(Exception e) {
			System.err.println("그런 파일 없음");
		}
	}

	// 이미지파일을 복사한다 (디스크->디스크)
	private static void imageCopy(File f) {
		try {
			FileInputStream fin = new FileInputStream(f);
			//int data = fin.read(); // 한 바이트 로드
			byte[] fdata = fin.readAllBytes(); // 파일 전체 데이터 로드
			System.out.printf("총 바이트수:%d\n", fdata.length);
			fin.close(); // stream은 사용이 끝나면 close해줌. 메모리 소모 큼
			FileOutputStream fout = new FileOutputStream("C:/test/images/copy.jpg");
			fout.write(fdata);
			fout.close();
			System.out.println("이미지파일 복사 완료!");
		} catch(Exception e) {
			System.err.println("그런 파일 없음");
		}
	}
	
	private static void imageCopy2(File f) {
		try {
			
			FileInputStream fin = new FileInputStream(f);
			
			FileOutputStream fout = new FileOutputStream("C:/Users/tjoeun/Downloads/2.jpg");
			
			byte[] buf = new  byte[1024];
			int red = 0;
			
			while((red=fin.read(buf,0,buf.length))!=-1) {
				//System.out.printf("읽어온 바이트 수 : %d\n",red);
				fout.write(buf,0,red);
			}
			fin.close();
			fout.close();
			System.out.println("이미지 복사 완료!");
		} catch(Exception e) {
			System.err.println("그런 파일 없음");
		}
	}
}
