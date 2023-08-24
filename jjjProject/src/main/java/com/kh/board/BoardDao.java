package com.kh.board;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class BoardDao {

	private Properties prop = new Properties();
	public BoardDao() {
		try {
			// sql모음집 연결
			prop.loadFromXML(new FileInputStream(BoardDao.class.getResource("/db/sql/board-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Board> selectList(Connection conn) {
		// select문
		
		ArrayList<Board> list = new ArrayList<Board>(); /**/
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		
	}
	
}
