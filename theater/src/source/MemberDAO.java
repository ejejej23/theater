package source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.util.DBConn;

public class MemberDAO {
	private Connection conn = DBConn.getConnection();

	public int insertMember(MemberDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "INSERT INTO member(memcode, name, id, pwd, email, tel, birth) VALUES ('회원'||SEQMOVIE.NEXTVAL, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);// 객체생성

			// Setter부름
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPwd());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getTel());
			pstmt.setString(6, dto.getBirth());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
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
	}

	public int outMember(MemberDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM MEMBER WHERE ID=?";

			pstmt = conn.prepareStatement(sql);// 객체생성

			// Setter부름
			pstmt.setString(1, dto.getId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
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
	}

	public int updateMember(MemberDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "Update member Set name=?, pwd=?, email=?, tel=?,  birth=? WHERE memcode=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, dto.getBirth());
			pstmt.setString(6, dto.getMemcode());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
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
	}

	public MemberDTO readMember(String id) {
		MemberDTO dto = null;
		StringBuffer sb = new StringBuffer();
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			// TEST : 생년월일 가져오는 형식 바꿧음
			sb.append("SELECT memcode, name, id, pwd, email, tel,to_char(birth,'YYYYMMDD') birth ");
			sb.append("   FROM member WHERE id = ?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {// 조건에 만족하는 건 1개 밖에 없기 때문에 if를 사용함
				dto = new MemberDTO();
				dto.setMemcode(rs.getString("memcode"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				dto.setBirth(rs.getString("birth"));
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
	}

	public MemberDTO readMemberMemcode(String memcode) {
		MemberDTO dto = null;
		StringBuffer sb = new StringBuffer();
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			// TEST : 생년월일 가져오는 형식 바꿧음
			sb.append("SELECT memcode, name, id, pwd, email, tel,to_char(birth,'YYYYMMDD') birth ");
			sb.append("   FROM member WHERE memcode = ?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, memcode);

			rs = pstmt.executeQuery();

			if (rs.next()) {// 조건에 만족하는 건 1개 밖에 없기 때문에 if를 사용함
				dto = new MemberDTO();
				dto.setMemcode(rs.getString("memcode"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				dto.setBirth(rs.getString("birth"));
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
	}

	public MemberDTO findMember(String id, String name, String email) {
		MemberDTO dto = null;
		StringBuffer sb = new StringBuffer();
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			sb.append("SELECT memcode, name, id, pwd, email, tel, birth ");
			sb.append("   FROM member WHERE id = ? and name = ? and email = ?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {// 조건에 만족하는 건 1개 밖에 없기 때문에 if를 사용함
				dto = new MemberDTO();
				dto.setMemcode(rs.getString("memcode"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				dto.setBirth(rs.getString("birth"));
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
	}
}
