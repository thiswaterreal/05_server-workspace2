package com.kh.board.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.common.model.vo.PageInfo;

import static com.kh.common.JDBCTemplate.*;

public class BoardDao {
	
	// BoardDao 호출할때마다 sql모음집 불러들임
	private Properties prop = new Properties();
	public BoardDao() {
		try {
			prop.loadFromXML(new FileInputStream(BoardDao.class.getResource("/db/sql/board-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public int selectListCount(Connection conn) { // 총 게시글 갯수 구하기
		// select문 => ResultSet (한개) => int
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql); // 쿼리 완벽
			rset = pstmt.executeQuery();
			
			if(rset.next()) { // 성공
				listCount = rset.getInt("count"); // db에서 설정한 'COUNT(*)'의 별칭 : count
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
		
	}
	
	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
		// select문 => ResultSet (여러행) => ArrayList<Board>
		
		ArrayList<Board> list = new ArrayList<Board>(); // 애초에 만들고 반복문에서는 add작업만
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList"); // 미완성
		
		/*
		 * currentPage : 1 => 시작값 :  1 | 끝값 : 10
		 * currentPage : 2 => 시작값 : 11 | 끝값 : 20
		 * currentPage : 3 => 시작값 : 21 | 끝값 : 30
		 * 
		 * 시작값 : (currentPage - 1) * boardLimit + 1
		 * 끝값	: 시작값 + boardLimit - 1
		 */
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();	// rset에 10개씩 담겨있음
			
			while(rset.next()) {	// vo가서 6개짜리 매개변수생성자 만들어주기
				list.add(new Board(rset.getInt("board_no"),
								   rset.getString("category_name"),
								   rset.getString("board_title"),
								   rset.getString("user_id"),
								   rset.getInt("count"),
								   rset.getString("create_date")
								   ));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
	public ArrayList<Category> selectCategoryList(Connection conn) {
		// select문 => ResultSet (여러행) => ArrayList<Category>
		
		ArrayList<Category> list = new ArrayList<Category>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectCategoryList");
		
		try {
			pstmt = conn.prepareStatement(sql); // 완벽
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {	// 이미 매개변수생성자(2개) 존재 개꿀
				list.add(new Category(rset.getInt("category_no"),
									  rset.getString("category_name")
									  ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
	public int insertBoard(Connection conn, Board b, Attachment at) {
		// insert문 => 처리된 행수 => 트랜젝션 처리
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4, Integer.parseInt(b.getBoardWriter()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int insertAttachment(Connection conn, Attachment at) {
		// insert문 => 처리된 행수 => 트랜젝션 처리
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;

	}
	
	
	
}
