package spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import spring.vo.Member;

public class MemberDao {// 데이터베이스 연결과 쿼리전송

	private JdbcTemplate jdbcTemplate;

	// 생성자를 통한 주입
	public MemberDao(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	// 공통의 RowMapper 를 꺼내 봅시다.
	private RowMapper<Member> rowMapper =
			new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member m = new Member(
								rs.getString("email"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getTimestamp("regdate"));
						m.setId(rs.getLong("id"));
			
						return m;
					}
			};
	
	

	// 회원 정보를 조회하기 위한 email 정보를 통해서 특정 회원 정보를 조회하는 내용
	public Member selectByEmail(String email) {
		String sql = "SELECT * FROM members WHERE email=?"; 

		List<Member> result = jdbcTemplate.query(sql,rowMapper,email);
		return result.isEmpty()?null:result.get(0);
	}

	// 가입된 전체 회원을 알기 위한 selectAll
	public List<Member> selectAll(){
		String sql = "SELECT * FROM members ORDER BY id ASC"; 				  
		List<Member> result = jdbcTemplate.query(sql,rowMapper);
		return result;
	}

	// 레코드의 개수를 알고 싶다.결과가 숫자로만 나오는 값
	public int count() {
		Integer cnt = jdbcTemplate.queryForObject(
				"SELECT count(*) FROM members",
				Integer.class);
		return cnt;
	}

	// 회원 정보 변경을 위한 update
	public void update(Member member) {
		String sql = "UPDATE members SET name=?, password=? WHERE email=?";
		jdbcTemplate.update(sql,member.getName(),member.getPassword(),member.getEmail());
	}

	// 회원가입을 위한 insert  => 현재 저장되는 ID(시퀀스)를 알아오고 싶을 때 :  KeyHolder
	public void insert(Member member) {
		KeyHolder key = new GeneratedKeyHolder();

		jdbcTemplate.update(
				new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement psmt = con.prepareStatement(
								"INSERT INTO members VALUES(members_seq.nextval,?,?,?,?)",
								new String[] {"id"});

						psmt.setString(1, member.getEmail());
						psmt.setString(2, member.getPassword());
						psmt.setString(3, member.getName());
						psmt.setTimestamp(4, new Timestamp(member.getRegisterDate().getTime()));
						return psmt;
					}
				},key);
		Number keyValue = key.getKey();
		member.setId(keyValue.longValue());  //1024

	}

	//날짜 기준으로 회원 데이터 검색 하는 기능  :  시작 날짜~끝 날짜

	public List<Member> selectByRegDate(Date from, Date to){
		List<Member> result = jdbcTemplate.query(
				"SELECT * FROM members WHERE regdate BETWEEN ? AND ? ORDER BY regdate ASC",
				rowMapper,from,to);
		return result;
	}
	
	
	// ID 값으로 회원 정보 읽어오기
	public Member selectById(long id) {
		String sql = "SELECT * FROM members WHERE id=?";
		List<Member> result = jdbcTemplate.query(sql,rowMapper,id);

		return result.isEmpty()?null:result.get(0);
	}
	
	// 날짜로 데이터 검색하기 
	public List<Member> selectByRegdate(Date from, Date to){
		List<Member> list = jdbcTemplate.query(
								"SELECT * FROM MEMBERS WHERE REGDATE BETWEEN ? AND ? ORDER BY REGDATE ASC", 
								new RowMapper<Member>() {

					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getString("email"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getTimestamp("regdate"));
						member.setId(rs.getLong("id"));
						
						return member;
					}
					
				},from,to);
		
		return list;
	}
	
	
	
	
}

