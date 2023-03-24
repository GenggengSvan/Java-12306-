package operate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Client {

	public static String Client_register(String ClientName_before, String ClientName_1, String passId, String TrueName_1,
			String Country_1, String CreStyle_1, String CreId_1, String PhoneId_1) throws Exception{

		Connection con=jdbc.Control.getcon();

		int id = 0;
		String msg=null;
		String sql1 = "select * from log where �û���='" + ClientName_before + "'";
		// �������ݿ⣬ʹ��preparedStatement�����ż��뷴б�ܣ����Է�ֹsqlע��
		PreparedStatement pstmt = con.prepareStatement(sql1);
		ResultSet resultSet = pstmt.executeQuery(sql1);
		while (resultSet.next()) {
			id = resultSet.getInt("Id");
		}

		String sql2 = "call ManageUpdataClient(?,?,?,?,?,?,?,?,@msg) ";
		pstmt = con.prepareStatement(sql2);
		pstmt.setInt(1, id);
		pstmt.setString(2, ClientName_1);
		pstmt.setString(3, passId);
		pstmt.setString(4, TrueName_1);
		pstmt.setString(5, Country_1);
		pstmt.setString(6, CreStyle_1);
		pstmt.setString(7, CreId_1);
		pstmt.setString(8, PhoneId_1);	
		pstmt.executeUpdate();
	
		String sql3="select @msg";
		pstmt = con.prepareStatement(sql3);
		resultSet = pstmt.executeQuery(sql3);
		while (resultSet.next()) {
			msg=resultSet.getString("@msg");
		}
		System.out.println(msg);
		resultSet.close();
		pstmt.close();
		con.close();

		return msg;
	}

	public static List<List<String>> managecheck() throws Exception, IOException {

		List<List<String>> re = new ArrayList<List<String>>();
		Connection con=jdbc.Control.getcon();
		String sql1 = "SELECT * FROM �û���Ϣ";
		PreparedStatement pstmt1 = con.prepareStatement(sql1);
		ResultSet resultSet1 = pstmt1.executeQuery(sql1);

		while (resultSet1.next()) {
			List<String> one_line1 = new ArrayList<String>();
			String �û��� = resultSet1.getString("�û���");
			String trueName = resultSet1.getString("trueName");
			String country = resultSet1.getString("country");
			String creStyle = resultSet1.getString("creStyle");
			String creId = resultSet1.getString("creId");
			String phoneId = resultSet1.getString("phoneId");
			String passId = resultSet1.getString("passId");

			one_line1.add(�û���);
			one_line1.add(trueName);
			one_line1.add(country);
			one_line1.add(creStyle);
			one_line1.add(creId);
			one_line1.add(phoneId);
			one_line1.add(passId);
			re.add(one_line1);
		}

		pstmt1.execute();
		resultSet1.close();
		pstmt1.close();
		con.close();

		return re;
	}

	public static List<String> usercheck(String �û���) throws Exception {
		List<String> re = new ArrayList<String>();
		Connection con=jdbc.Control.getcon();

		String sql1 = "SELECT * FROM �û���Ϣ where �û���='"+�û���+"'";
		PreparedStatement pstmt1 = con.prepareStatement(sql1);
		ResultSet resultSet1 = pstmt1.executeQuery(sql1);

		while (resultSet1.next()) {

			String passId = resultSet1.getString("passId");
			String trueName = resultSet1.getString("trueName");
			String country = resultSet1.getString("country");
			String creStyle = resultSet1.getString("creStyle");
			String creId = resultSet1.getString("creId");
			String phoneId = resultSet1.getString("phoneId");
			
			re.add(�û���);
			re.add(passId);
			re.add(trueName);
			re.add(country);
			re.add(creStyle);
			re.add(creId);
			re.add(phoneId);

		}

		pstmt1.execute();
		resultSet1.close();
		pstmt1.close();
		con.close();

		return re;
	}
}
