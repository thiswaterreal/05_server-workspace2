package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy { // FileRenamePolicy 를 상속받아서 'MyFileRenamePolicy' 클래스 맹글기

	// 원본파일 전달 받아서 파일명 수정작업 후 수정된 파일을 반환시켜주는 메소드
	@Override
	public File rename(File originFile) {
		
		// 원본파일명 ("aaa.jpg")
		String originName = originFile.getName();
		
		// => 수정파일명 ("2023081712253012345.jpg")
		//				파일업로드시간(년월일시분초) + 5자리랜덤값(10000~99999) + 원본파일확장자
		
		// 1. 파일 업로드 시간 (년월일시분초 형태) (String currentTime)
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); // "20230817122955"
		
		// 2. 5자리 랜덤값 (int ranNum)
		int ranNum = (int)(Math.random() * 90000 + 10000); // 23123
		
		// 3. 원본파일 확장자 (String ext)
		String ext = originName.substring(originName.lastIndexOf("."));	// png

		// 이제 모두 합쳐요~
		String changeName = currentTime + ranNum + ext;
		
		return new File(originFile.getParent(), changeName); // 원본의 디렉토리를 알아낸 후 변경된 이름으로 저장하는 메소드 : new File(원본디렉토리, 변경이름)
	}

}
