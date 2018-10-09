package source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.DBConn;

public class GenreDAO {
	private Connection conn = DBConn.getConnection();
	
	public int insertGenre(GenreDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO genre(genreCode, gerneName)";
			sql += " VALUES (?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getGenreCode());
			pstmt.setString(2, dto.getGenreName());
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
	}//insert_genre 종료
	
	public int updateGenre(GenreDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE genre SET genreName=? WHERE genreCode=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getGenreName());
			pstmt.setString(2, dto.getGenreCode());
			
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
	}// updateGenre 종료
	
	public GenreDTO readGenre(String genreCode) {
		GenreDTO dto = null;
		ResultSet rs = null;
		String sql;
		PreparedStatement pstmt = null;

		try {
			sql = "SELECT genreCode, genreName FROM genre";
			sql += "  WHERE genreCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, genreCode);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new GenreDTO();
				dto.setGenreCode(rs.getString("genreCode"));
				dto.setGenreName(rs.getString("genreName"));
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
	}// readGenre 종료
	
	public int deleteGenre(String genreCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM genre WHERE genreCode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, genreCode);
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
	
	public List<GenreDTO> listGenre() {
		List<GenreDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {
			sb.append("SELECT genreCode, genreName ");
			sb.append("  FROM genre");
			
			pstmt = conn.prepareStatement(sb.toString());
			// ? 가 없으므로 setter가 없음
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GenreDTO dto = new GenreDTO();
				dto.setGenreCode(rs.getString("genreCode"));
				dto.setGenreName(rs.getString("genreName"));
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
