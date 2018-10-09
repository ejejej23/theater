package source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.DBConn;

public class GradeDAO {
private Connection conn = DBConn.getConnection();
	
	public int insertGrade(GradeDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO grade(gradeCode, gradeName)";
			sql += " VALUES (?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getGradeCode());
			pstmt.setString(2, dto.getGradeName());
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
	}//insert_grade 종료
	
	public int updateGrade(GradeDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE grade SET gradeName=? WHERE gradeCode=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getGradeName());
			pstmt.setString(2, dto.getGradeCode());
			
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
	}// updateGrade 종료
	
	public GradeDTO readGrade(String gradeCode) {
		GradeDTO dto = null;
		ResultSet rs = null;
		String sql;
		PreparedStatement pstmt = null;

		try {
			sql = "SELECT gradeCode, gradeName FROM grade";
			sql += "  WHERE gradeCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gradeCode);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new GradeDTO();
				dto.setGradeCode(rs.getString("gradeCode"));
				dto.setGradeName(rs.getString("gradeName"));
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
	}// readGrade 종료
	
	public int deleteGrade(String gradeCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM grade WHERE gradeCode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gradeCode);
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
	}
	
	public List<GradeDTO> listGrade() {
		List<GradeDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {
			sb.append("SELECT gradeCode, gradeName ");
			sb.append("  FROM grade");
			
			pstmt = conn.prepareStatement(sb.toString());
			// ? 가 없으므로 setter가 없음
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GradeDTO dto = new GradeDTO();
				dto.setGradeCode(rs.getString("gradeCode"));
				dto.setGradeName(rs.getString("gradeName"));
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
	}
}
