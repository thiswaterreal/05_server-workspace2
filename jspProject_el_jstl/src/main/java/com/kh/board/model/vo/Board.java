package com.kh.board.model.vo;

public class Board {

	private int boardNo;
	private int boardType;
	private String category; // 작성기능시 카테고리번호 | 조회기능시 카테고리명 
	private String boardTitle;
	private String boardContent;
	private String boardWriter; // 작성기능시 회원번호 | 조회기능시 회원아이디명
	private int count;
	private String createDate;	// String형
	private String status;
	
	private String titleImg; // 대표이미지경로를 보관할 필드 (8/23 9:43)
	
	public Board() {}
	
	// 매개변수생성자(전체)
	public Board(int boardNo, int boardType, String category, String boardTitle, String boardContent,
			String boardWriter, int count, String createDate, String status) {
		super();
		this.boardNo = boardNo;
		this.boardType = boardType;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
	}
	
	
	// 매개변수생성자(6개_게시판리스트 각 행마다 담길 값 조회시 필요)
	public Board(int boardNo, String category, String boardTitle, String boardWriter, int count, String createDate) {
		super();
		this.boardNo = boardNo;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
	}

	
	// 매개변수생성자(6개_게시글리스트 중 원하는 게시글 눌루했을때 나오는 상세페이지에 필요)
	public Board(int boardNo, String category, String boardTitle, String boardContent, String boardWriter,
			String createDate) {
		super();
		this.boardNo = boardNo;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
	}
	
	// 내가추가
	public Board(int boardNo, String boardTitle, int count, String titleImg) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.count = count;
		this.titleImg = titleImg;
	}
	
	
	
	// titleImg get set 추가
	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
	

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getBoardType() {
		return boardType;
	}

	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardType=" + boardType + ", category=" + category + ", boardTitle="
				+ boardTitle + ", boardContent=" + boardContent + ", boardWriter=" + boardWriter + ", count=" + count
				+ ", createDate=" + createDate + ", status=" + status + "]";
	};
	
	
	
	
	
}
