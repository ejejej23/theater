package source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.DBConn;

public class MovieDAO {
	private Connection conn = DBConn.getConnection();

	public int insertMovie(MovieDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "INSERT INTO movie(movieCode, title, eng_title, director, actor, releseDate, story, movieCompany, playtime, genreCode, gradeCode, reviewCode)";
			sql += " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getMovieCode());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getEng_title());
			pstmt.setString(4, dto.getDirector());
			pstmt.setString(5, dto.getActor());
			pstmt.setString(6, dto.getReleseDate());
			pstmt.setString(7, dto.getStory());
			pstmt.setString(8, dto.getMovieCompany());
			pstmt.setInt(9, dto.getPlaytime());
			pstmt.setString(10, dto.getGenreCode());
			pstmt.setString(11, dto.getGradeCode());
			pstmt.setString(12,  dto.getReviewCode());
			
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
	}// insert_movie 종료

	public int updateMovie(MovieDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE movie SET title=?, eng_title=?, director=?, actor=?, releseDate=?, story=?, movieCompany=?, playtime=?, genreCode=?, gradeCode=?, reviewCode=? WHERE movieCode=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getEng_title());
			pstmt.setString(3, dto.getDirector());
			pstmt.setString(4, dto.getActor());
			pstmt.setString(5, dto.getReleseDate());
			pstmt.setString(6, dto.getStory());
			pstmt.setString(7, dto.getMovieCompany());
			pstmt.setInt(8, dto.getPlaytime());
			pstmt.setString(9, dto.getMovieCode());
			pstmt.setString(10, dto.getGenreCode());
			pstmt.setString(11, dto.getGradeCode());
			pstmt.setString(12,  dto.getReviewCode());
			
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
	}// updateMovie 종료

	public MovieDTO readMovie(String movieCode) {
		MovieDTO dto = null;
		ResultSet rs = null;
		String sql;
		PreparedStatement pstmt = null;

		try {
			sql = "SELECT movieCode, title, eng_title, director, actor, releseDate, story, movieCompany, playtime, genreCode, gradeCode, reviewCode FROM movie";
			sql += "  WHERE movieCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieCode);
			rs = pstmt.executeQuery();
			

			if (rs.next()) {
				dto = new MovieDTO();
				dto.setMovieCode(rs.getString("movieCode"));
				dto.setTitle(rs.getString("title"));
				dto.setEng_title(rs.getString("Eng_title"));
				dto.setDirector(rs.getString("director"));
				dto.setActor(rs.getString("actor"));
				dto.setReleseDate(rs.getString("releseDate"));
				dto.setStory(rs.getString("story"));
				dto.setMovieCompany(rs.getString("movieCompany"));
				dto.setPlaytime(rs.getInt("playtime"));
				dto.setGenreCode(rs.getString("genreCode"));
				dto.setGradeCode(rs.getString("gradeCode"));
				dto.setReviewCode(rs.getString("reviewCode"));

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
	}// readMovie 종료

	public List<MovieDTO> listMovie() {
		List<MovieDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {
			sb.append("SELECT movieCode, title, eng_title, ");
			sb.append("  director, ");
			sb.append("  actor, ");
			sb.append("  releseDate, ");
			sb.append("  story, ");
			sb.append("  movieCompany, ");
			sb.append("  playtime, ");
			sb.append("  genreCode, ");
			sb.append("  gradeCode, ");
			sb.append("  reviewCode  ");
			sb.append("  FROM movie");
			
			pstmt = conn.prepareStatement(sb.toString());
			// ? 가 없으므로 setter가 없음
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MovieDTO dto = new MovieDTO();
				dto.setMovieCode(rs.getString("movieCode"));
				dto.setTitle(rs.getString("title"));
				dto.setEng_title(rs.getString("eng_title"));
				dto.setDirector(rs.getString("director"));
				dto.setActor(rs.getString("actor"));
				dto.setReleseDate(rs.getString("releseDate"));
				dto.setStory(rs.getString("story"));
				dto.setMovieCompany(rs.getString("movieCompany"));
				dto.setPlaytime(rs.getInt("playtime"));
				dto.setGenreCode(rs.getString("genreCode"));
				dto.setGradeCode(rs.getString("gradeCode"));
				dto.setReviewCode(rs.getString("reviewCode"));
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

	public List<MovieDTO> listMovie(String val) {
		List<MovieDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {
			sb.append("  director, ");
			sb.append("  actor, ");
			sb.append("  releseDate, ");
			sb.append("  story, ");
			sb.append("  movieCompany, ");
			sb.append("  playtime, ");
			sb.append("  genreCode ");
			sb.append("  gradeCode ");
			sb.append("  reviewCode ");
			sb.append("  FROM movie");
			sb.append("  WHERE INSTR(title, ?) >= 1");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, val);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MovieDTO dto = new MovieDTO();
				dto.setMovieCode(rs.getString("movieCode"));
				dto.setTitle(rs.getString("title"));
				dto.setEng_title(rs.getString("eng_title"));
				dto.setDirector(rs.getString("director"));
				dto.setActor(rs.getString("actor"));
				dto.setReleseDate(rs.getString("releseDate"));
				dto.setStory(rs.getString("story"));
				dto.setMovieCompany(rs.getString("movieCompany"));
				dto.setPlaytime(rs.getInt("playtime"));
				dto.setGenreCode(rs.getString("genreCode"));
				dto.setGradeCode(rs.getString("gradeCode"));
				dto.setReviewCode(rs.getString("reviewCode"));
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
	}// listMovie(String val) 종료

	public int deleteMovie(String movieCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM movie WHERE movieCode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieCode);
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
}
