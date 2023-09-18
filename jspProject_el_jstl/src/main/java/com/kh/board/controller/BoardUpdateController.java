package com.kh.board.controller;

import java.io.IOException;

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
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 1_1. 전달되는 파일 용량 제한 (int maxSize)
			int maxSize = 10 * 1024 * 1024; // 10메가바이트
			
			// 1-2. 전달되는 파일을 저장시킬 서버('application'<session<request)의 폴더 물리적인 경로 (String savePath)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/"); // 그안에!니까 마지막에 '/'붙임
			
			// 2. 전달된 파일명 수정작업 후 서버에 업로드
			// HttpServletRequest => MultipartRequest 로 변경해야함 (lib에 cos.jar 있어야함)
			// MultipartRequest multiRequest = new MultipartRequest(request, 저장경로, 파일용량, 인코딩방식, 파일명변경객체);
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "utf-8", new MyFileRenamePolicy());
			
			// 3. 본격적으로 sql문 실행할 때 필요한 값 (요청시 전달값) 뽑아서 vo에 기록
			// >> 공통적으로 수행 : update board (sql문)
			// .getParameter 는 반환형이 String 이기 때문에 형변환!!
			int boardNo = Integer.parseInt(multiRequest.getParameter("bno"));
			String category = multiRequest.getParameter("category");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			
			Board b = new Board(); // 기본생성자에 set해서 담아가보자
			b.setBoardNo(boardNo);
			b.setCategory(category);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			
			Attachment at = null;  // 처음에는 null로 초기화, 넘어온 새로운 첨부파일이 있을 경우 그때 생성
			
			if(multiRequest.getOriginalFileName("upfile") != null) {
				// 새로 넘어온 첨부파일 있을 경우 => Attachment 객체 생성할것임
				at = new Attachment(); // 일단 기본생성자로 생성하고
				at.setOriginName(multiRequest.getOriginalFileName("upfile")); // 원본명
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				at.setFilePath("resources/board_upfiles");
					// 여기까지는 공통..
				
				if(multiRequest.getParameter("originFileNo") != null) {
					// 기존에 첨부파일이 있었을 경우 => Update Attachment (기존의 첨부파일 번호 필요(FILE_NO))
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
				}else {
					// 기존에 첨부파일이 없었을 경우 => Insert New Attachment (현재 게시글 번호 필요(REF_BNO))
					at.setRefBoardNo(boardNo);
				}
			}
			// 새로 넘어온 첨부파일 없다면 at는 여전히 null 임
			
			int result = new BoardService().updateBoard(b, at);
			// 새로운 첨부파일 x							 => b, null					=> Board Update 만 하면 됨
			// 새로운 첨부파일 o, 기존의 첨부파일 o			 => b, fileNo이 담긴 at		=> Board Update, Attachment Update
			// 새로운 첨부파일 o, 기존의 첨부파일 x			 => b, refBoardNo이 단긴 at	=> Board Update, Attachment Insert
			
			if(result > 0) {
				// 성공 => /jsp/detail.bo?bno=해당게시글번호	(url재요청 => 기존에 봤던 상세조회 페이지)
				request.getSession().setAttribute("alertMsg", "성공적으로 수정되었습니다.");
				response.sendRedirect(request.getContextPath()+"/detail.bo?bno="+boardNo);
			}else {
				// 실패
				request.setAttribute("errorMsg", "일반게시판 수정에 실패했습니다.");
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
