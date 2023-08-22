package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ThumbnailInsertController
 */
@WebServlet("/insert.th")
public class ThumbnailInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 1_1. 전송용량제한
			int maxSize = 10 * 1024 * 1024;
			
			// 2_2. 저장시킬 폴더의 물리적인 경로
			// application == request.getSession().getServletContext()
			String savePath = request.getSession().getServletContext().getRealPath("/resources/thumbnail_upfiles/"); // '-안에' == '/' 
			
			// 2. 전달된 파일 업로드
			// MultipartRequest multiRequest = new MultipartRequest(request, 저장경로, 용량제한, 인코딩, 이름변경객체);
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "utf-8", new MyFileRenamePolicy());
			
			// 3. db에 기록할 값 뽑기
			// Board Insert
			Board b = new Board();
			b.setBoardWriter(multiRequest.getParameter("userNo"));
			b.setBoardTitle(multiRequest.getParameter("title"));
			b.setBoardContent(multiRequest.getParameter("content"));
			
			// Attachment에 여러번 insert할 데이터 뽑기
			ArrayList<Attachment> list = new ArrayList<Attachment>();
			
			for(int i=1; i<=4; i++) { // file1,2,3,4
				
				String key = "file" + i;
				
				if(multiRequest.getOriginalFileName(key) != null) {
					// 첨부한 파일이 존재. 원본명 뱉음
					// Attachment 객체 생성 => 원본명, 수정명, 폴더경로, 파일레벨 담아서 => list에 추가!
					Attachment at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(key));
					at.setChangeName(multiRequest.getFilesystemName(key));
					at.setFilePath("resources/thumbnail_upfiles");
					
					if(i == 1) { // 대표이미지(1) 일 경우 => FileLever : 1
						at.setFileLevel(1);
					}else { // 상세이미지(2,3,4) 일 경우 => FileLever : 2
						at.setFileLevel(2);
					}
					
					list.add(at);
					
				}
				
			}
			
			int result = new BoardService().insertThumbnailBoard(b, list);
			
			if(result > 0) {
				// 성공 => /jsp/list.th	(url 재요청)
				request.getSession().setAttribute("alertMsg", "성공적으로 게시글 등록이 되었습니다.");
				response.sendRedirect(request.getContextPath() + "/list.th");
			}else {
				// 실패
				request.setAttribute("errorMsg", "사진게시판 등록에 실패했습니다.");
				RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
				view.forward(request, response);
			}
			
		}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
