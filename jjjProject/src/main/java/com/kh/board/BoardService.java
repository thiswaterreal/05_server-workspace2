package com.kh.board;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

public class BoardService {

	public ArrayList<Board> selectList() {
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectList(conn);
		close(conn);
		return list;
	}
	
	
}
