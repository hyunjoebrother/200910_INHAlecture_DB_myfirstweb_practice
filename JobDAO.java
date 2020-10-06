package examples.dao;

// DAO (Data Access Object)

// DB 관련 작업을 전담하는 클래스
// DB에 연결하여 CRUD 작업을 하는 클래스

// getJob, addJob, deleteJob, updateJob, getJobs 클래스를 만들어주자 (5개 필수)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.Job;

public class JobDAO {
	private static String dburl = "jdbc:mysql://localhost/dbdesign?serverTimezone=Asia/Seoul";
	private static String dbUser = "dbuser";
	private static String dbpasswd = "dbuser123!";


	public Job getJob(Integer jobId) // 1개를 select -> job_id = ? -> ?에 들어간다
	{
		Job job = null;
	
		// (1) JDBC 드라이버 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// (2) 질의문 설정
		String sql = "SELECT job_id, description FROM job WHERE job_id = ?";
	
		// (3) MySQL 연결 및 질의 수행
		// try문에 conn, ps를 여는 코드를 넣으면 finally 블럭에서 close를 안해줘도 된다 (Closable 객체)
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql))
		{
			// (4) 질의문 설정 및 ResultSet 처리 (결과가 여러 개가 있을 수 있음)
			ps.setInt(1, jobId);
		
			try (ResultSet rs = ps.executeQuery())
			{
				if (rs.next())
				{
					int id = rs.getInt(1);
					String description = rs.getString(2);
					job = new Job(id, description);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		//밑에 finally 블럭을 쓰지 않아도 된다
	
		return job;
	}



	public int addJob(Job job) // 2개를 insert -> values (?, ?) -> ? 2개에 insert
	{
		int insertCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("연결 성공!!~");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		String sql = "INSERT INTO job (job_id, description) VALUES ( ?, ? )";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setInt(1,  job.getJobId());
			ps.setNString(2,  job.getDescription());
		
			insertCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return insertCount;
	}
			
	public int deleteJob(Integer jobId) // jobId 1개를 delete
	{
		int deleteCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
		String sql = "DELETE FROM job WHERE job_id = ?";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setInt(1, jobId);
			deleteCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return deleteCount;
	}

	public int updateJob(Job job) // 2개를 update 
	{
		int updateCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		String sql = "update job set description = ? where job_id = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setNString(1,  job.getDescription());
			ps.setInt(2,  job.getJobId());
		
			updateCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return updateCount;
	}

	public List<Job> getJobs() //select한 모든 Job들을 list로 만든다 -> list.add(job)
	{
		//ArrayList 생성
		List<Job> list = new ArrayList<>();
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
		String sql = "SELECT description, job_id FROM job order by job_id desc";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) 
		{
			try (ResultSet rs = ps.executeQuery())
			{
				while (rs.next())
				{
					String description = rs.getString(1);
					int id = rs.getInt("job_id");
					
					Job job = new Job(id, description);
					list.add(job); //list에 반복할 때마다 Job 인스턴스를 생성하여 list에 추가한다
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return list;
	}
}