package source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.DBConn;

public class ReviewDAO {
	private Connection conn = DBConn.getConnection();
	
	public int insertReview(ReviewDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO reviews(reviewCode, rivew)";
			sql += " VALUES (?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getReviewCode());
			pstmt.setInt(2, dto.getReview());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return result;
	}//insertReview 종료
	
	
	public int updateReview(ReviewDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE review SET Review=? WHERE ReviewCode=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getReview());
			pstmt.setString(2, dto.getReviewCode());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return result;
	}// updateReview 종료
	
	
	public ReviewDTO readReview(String reviewCode) {
		ReviewDTO dto = null;
		ResultSet rs = null;
		String sql;
		PreparedStatement pstmt = null;

		try {
			sql = "SELECT reviewCode, review FROM review";
			sql += "  WHERE reviewCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reviewCode);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new ReviewDTO();
				dto.setReviewCode(rs.getString("reviewCode"));
				dto.setReview(rs.getInt("review"));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {

				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {

				}
			}
		}
		return dto;
	}// readReview 종료
	
	
	
	public int deleteReview(String reviewCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM review WHERE reviewCode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reviewCode);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return result;
	}//deleteReview 종료
	
	
	
	public List<ReviewDTO> listReview() {
		List<ReviewDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {
			sb.append("SELECT reviewCode, review ");
			sb.append("  FROM review");
			
			pstmt = conn.prepareStatement(sb.toString());
			// ? 가 없으므로 setter가 없음
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReviewCode(rs.getString("reviewCode"));
				dto.setReview(rs.getInt("review"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return list;
	}//listReview 종료
	
	
}
