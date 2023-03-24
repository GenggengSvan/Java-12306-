package operate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class Train {

	public static List<List<String>> train_check(String start, String end) throws Exception, IOException {
		List<List<String>> re = new ArrayList<List<String>>();
		Connection con=jdbc.Control.getcon();

		// JDBC��֧�ֱ���
		String sql1 = "SELECT * FROM stop WHERE վ��='" + start + "'";
		String sql2 = "SELECT * FROM stop WHERE վ��='" + end + "'";
		PreparedStatement pstmt1 = con.prepareStatement(sql1);
		ResultSet resultSet1 = pstmt1.executeQuery(sql1);

		while (resultSet1.next()) {
			List<String> one_line1 = new ArrayList<String>();
			String ���κ� = resultSet1.getString("���κ�");
			Time ����ʱ�� = resultSet1.getTime("����ʱ��");
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			ResultSet resultSet2 = pstmt2.executeQuery(sql2);
			while (resultSet2.next()) {
				String ���κ�_end = resultSet2.getString("���κ�");
				Time ��վʱ�� = resultSet2.getTime("��վʱ��");
				if (���κ�.equals(���κ�_end) && ����ʱ��.before(��վʱ��)) {
					one_line1.add(���κ�);
					one_line1.add(����ʱ��.toString());
					one_line1.add(��վʱ��.toString());
					re.add(one_line1);
				}
			}
			resultSet2.close();
			pstmt2.close();
		}

		resultSet1.close();
		pstmt1.close();

		con.close();

		return re;
	}

	public static String buyticket(String start, String end, String ����, String �û���) throws Exception {
		Connection con=jdbc.Control.getcon();

		String result = null;
		Time ����ʱ�� = null;
		Time ��վʱ�� = null;
		String �˳��� = null;
		int �����û�ID = -1;

		String sql1 = "SELECT * FROM stop WHERE վ��='" + start + "' AND ���κ�='" + ���� + "'";
		PreparedStatement pstmt1 = con.prepareStatement(sql1);
		ResultSet resultSet1 = pstmt1.executeQuery(sql1);
		while (resultSet1.next()) {
			����ʱ�� = resultSet1.getTime("����ʱ��");
		}
		if (����ʱ�� == null) {
			result = "ʼ��վ������";
			return result;
		}

		String sql2 = "SELECT * FROM stop WHERE վ��='" + end + "' AND ���κ�='" + ���� + "'";
		pstmt1 = con.prepareStatement(sql2);
		resultSet1 = pstmt1.executeQuery(sql2);
		while (resultSet1.next()) {
			��վʱ�� = resultSet1.getTime("��վʱ��");
		}
		if (��վʱ�� == null) {
			result = "�յ�վ������";
			return result;
		}

		String sql4 = "SELECT * FROM client WHERE id=(select ID from log where �û���='" + �û��� + "')";
		pstmt1 = con.prepareStatement(sql4);
		resultSet1 = pstmt1.executeQuery(sql4);
		while (resultSet1.next()) {
			�˳��� = resultSet1.getString("trueName");
			�����û�ID = resultSet1.getInt("ID");
		}
		if (�˳��� == null) {
			result = "�������û���Ϣ";
			return result;
		}

		String sql3 = "insert into useorder(����վ,Ŀ��վ,����ʱ��,��վʱ��,����,�˳���,�����û�ID) VALUES (?,?,?,?,?,?,?)";
		pstmt1 = con.prepareStatement(sql3);
		pstmt1.setString(1, start);
		pstmt1.setString(2, end);
		pstmt1.setTime(3, ����ʱ��);
		pstmt1.setTime(4, ��վʱ��);
		pstmt1.setString(5, ����);
		pstmt1.setString(6, �˳���);
		pstmt1.setInt(7, �����û�ID);
		pstmt1.executeUpdate();
		result = "����ɹ�";

		resultSet1.close();
		pstmt1.close();
		con.close();

		return result;
	}

	public static List<List<String>> train_new_check(String start, String end) throws Exception {
		List<List<String>> re = new ArrayList<List<String>>();
		Connection con=jdbc.Control.getcon();
		// JDBC��֧�ֱ���
		String sql1 = "SELECT * FROM stop WHERE վ��='" + start + "'";
		String sql2 = "SELECT * FROM stop WHERE վ��='" + end + "'";
		int start_index = 0;
		int end_index = 0;
		PreparedStatement pstmt1 = con.prepareStatement(sql1);
		ResultSet resultSet1 = pstmt1.executeQuery(sql1);

		while (resultSet1.next()) {
			List<String> one_line1 = new ArrayList<String>();
			String ���κ� = resultSet1.getString("���κ�");
			Time ����ʱ�� = resultSet1.getTime("����ʱ��");
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			ResultSet resultSet2 = pstmt2.executeQuery(sql2);
			while (resultSet2.next()) {
				String ���κ�_end = resultSet2.getString("���κ�");
				Time ��վʱ�� = resultSet2.getTime("��վʱ��");
				if (���κ�.equals(���κ�_end) && ����ʱ��.before(��վʱ��)) {
					one_line1.add(���κ�);
					one_line1.add(����ʱ��.toString());
					one_line1.add(��վʱ��.toString());
					start_index = resultSet1.getInt("��������");
					end_index = resultSet2.getInt("��������");

					String shopname = null;
					for (int i = start_index; i <= end_index; i++) {
						String sql3 = "SELECT * FROM stop WHERE ���κ�='" + ���κ� + "' and ��������=" + i + "";
						PreparedStatement pstmt3 = con.prepareStatement(sql3);
						ResultSet resultSet3 = pstmt3.executeQuery(sql3);
						while (resultSet3.next()) {
							if (shopname == null) {
								shopname = resultSet3.getString("վ��");
							} else {
								shopname = shopname + "-" + resultSet3.getString("վ��");
							}
						}
						resultSet3.close();
						pstmt3.close();
					}
					one_line1.add(shopname);
					re.add(one_line1);
				}
			}
			resultSet2.close();
			pstmt2.close();
		}

		resultSet1.close();
		pstmt1.close();

		con.close();

		return re;
	}

	public static List<List<String>> train_new_checkleft(String start, String end, String �г���) throws Exception {
		List<List<String>> re = new ArrayList<List<String>>();
		Connection con=jdbc.Control.getcon();

		String sql1 = "SELECT * FROM stop WHERE վ��='" + start + "' and ���κ�='" + �г��� + "'";
		String sql2 = "SELECT * FROM stop WHERE վ��='" + end + "' and ���κ�='" + �г��� + "'";
		int start_index = 0;
		int end_index = 0;
		PreparedStatement pstmt1 = con.prepareStatement(sql1);
		ResultSet resultSet1 = pstmt1.executeQuery(sql1);

		while (resultSet1.next()) {
			start_index = resultSet1.getInt("��������");
		}
		pstmt1.close();
		resultSet1.close();

		pstmt1 = con.prepareStatement(sql2);
		resultSet1 = pstmt1.executeQuery(sql2);
		while (resultSet1.next()) {
			end_index = resultSet1.getInt("��������");
		}
		pstmt1.close();
		resultSet1.close();

		String sql3 = "SELECT * FROM leftover" + �г��� + " GROUP BY ����";
		int data_num = 0;
		pstmt1 = con.prepareStatement(sql3);
		resultSet1 = pstmt1.executeQuery(sql3);
		while (resultSet1.next()) {
			List<String> one_line1 = new ArrayList<String>();
			one_line1.add(resultSet1.getString("����"));
			re.add(one_line1);
			data_num++;
		}
		pstmt1.close();
		resultSet1.close();

		String sql_select = "select count(*) from leftover" + �г��� + " where ";
		int i = start_index;
		while (true) {
			sql_select += (change(i) + "=0 ");
			if (i == end_index - 1) {
				break;
			}
			i++;
			sql_select += " and ";
		}

		for (int x = 0; x < data_num; x++) {
			String sql_select_shangwu = sql_select + " and ����='" + re.get(x).get(0)
					+ "' and ����� in (select ����� from carriage where ��λ����='������' and ���κ�='" + �г��� + "');";
			pstmt1 = con.prepareStatement(sql_select_shangwu);
			resultSet1 = pstmt1.executeQuery(sql_select_shangwu);
			while (resultSet1.next()) {
				re.get(x).add(resultSet1.getString("count(*)"));
			}
			pstmt1.close();
			resultSet1.close();

			String sql_select_1 = sql_select + " and ����='" + re.get(x).get(0)
					+ "' and ����� in (select ����� from carriage where ��λ����='һ����' and ���κ�='" + �г��� + "');";
			pstmt1 = con.prepareStatement(sql_select_1);
			resultSet1 = pstmt1.executeQuery(sql_select_1);
			while (resultSet1.next()) {
				re.get(x).add(resultSet1.getString("count(*)"));
			}
			pstmt1.close();
			resultSet1.close();

			String sql_select_2 = sql_select + " and ����='" + re.get(x).get(0)
					+ "' and ����� in (select ����� from carriage where ��λ����='������' and ���κ�='" + �г��� + "');";
			pstmt1 = con.prepareStatement(sql_select_2);
			resultSet1 = pstmt1.executeQuery(sql_select_2);
			while (resultSet1.next()) {
				re.get(x).add(resultSet1.getString("count(*)"));
			}
			pstmt1.close();
			resultSet1.close();

		}
		con.close();
		return re;
	}

	private static String change(int i) {
		if (i == 1)
			return "һ";
		else if (i == 2)
			return "��";
		else if (i == 3)
			return "��";
		else if (i == 4)
			return "��";
		else if (i == 5)
			return "��";
		else if(i==6)
			return "��";
		else if (i == 7)
			return "��";
		else if (i == 8)
			return "��";
		else if (i == 9)
			return "��";
		else if (i == 10)
			return "ʮ";
		else if(i==11)
			return "ʮһ";

		return null;
	}

	public static String newbuyticket(String data, String type_seat, String start, String end, String ����, String �û���)
			throws Exception {
		Connection con=jdbc.Control.getcon();

		String result = null;
		String ����ʱ�� = null;
		String ��վʱ�� = null;
		String �˳��� = null;
		int �����û�ID = -1;

		String sql1 = "SELECT * FROM stop WHERE վ��='" + start + "' and ���κ�='" + ���� + "'";
		String sql2 = "SELECT * FROM stop WHERE վ��='" + end + "' and ���κ�='" + ���� + "'";
		int start_index = 0;
		int end_index = 0;
		PreparedStatement pstmt1 = con.prepareStatement(sql1);
		ResultSet resultSet1 = pstmt1.executeQuery(sql1);

		while (resultSet1.next()) {
			start_index = resultSet1.getInt("��������");
		}
		pstmt1.close();
		resultSet1.close();

		pstmt1 = con.prepareStatement(sql2);
		resultSet1 = pstmt1.executeQuery(sql2);
		while (resultSet1.next()) {
			end_index = resultSet1.getInt("��������");
		}
		pstmt1.close();
		resultSet1.close();

		String sql1_1 = "SELECT * FROM stop WHERE վ��='" + start + "' AND ���κ�='" + ���� + "'";
		pstmt1 = con.prepareStatement(sql1_1);
		resultSet1 = pstmt1.executeQuery(sql1_1);
		while (resultSet1.next()) {
			����ʱ�� = resultSet1.getString("����ʱ��");
		}
		if (����ʱ�� == null) {
			result = "ʼ��վ������";
			return result;
		}
		pstmt1.close();
		resultSet1.close();

		String sql2_2 = "SELECT * FROM stop WHERE վ��='" + end + "' AND ���κ�='" + ���� + "'";
		pstmt1 = con.prepareStatement(sql2_2);
		resultSet1 = pstmt1.executeQuery(sql2_2);
		while (resultSet1.next()) {
			��վʱ�� = resultSet1.getString("��վʱ��");
		}
		if (��վʱ�� == null) {
			result = "�յ�վ������";
			return result;
		}
		pstmt1.close();
		resultSet1.close();

		String sql4 = "SELECT * FROM client WHERE id=(select ID from log where �û���='" + �û��� + "')";
		pstmt1 = con.prepareStatement(sql4);
		resultSet1 = pstmt1.executeQuery(sql4);
		while (resultSet1.next()) {
			�˳��� = resultSet1.getString("trueName");
			�����û�ID = resultSet1.getInt("ID");
		}
		if (�˳��� == null) {
			result = "�������û���Ϣ";
			return result;
		}
		pstmt1.close();
		resultSet1.close();

		String sql_select = "select * from leftover" + ���� + " where ";
		int i = start_index;
		while (true) {
			sql_select += (change(i) + "=0 ");
			if (i == end_index - 1) {
				break;
			}
			i++;
			sql_select += " and ";
		}

		String ���� = null;
		String ��λ = null;
		String sql_select_shangwu = sql_select + " and ����='" + data
				+ "' and ����� in (select ����� from carriage where ��λ����='" + type_seat + "' and ���κ�='" + ���� + "');";
		pstmt1 = con.prepareStatement(sql_select_shangwu);
		resultSet1 = pstmt1.executeQuery(sql_select_shangwu);
		while (resultSet1.next()) {
			���� = resultSet1.getString("�����");
			��λ = resultSet1.getString("��λ��");
			break;
		}
		pstmt1.close();
		resultSet1.close();

		boolean continue_judge=true;
		try {
			String ins = "insert into useorder(����վ,Ŀ��վ,����ʱ��,��վʱ��,����,�˳���,�����û�ID,����,��λ,����) VALUES (?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement pstmt2 = con.prepareStatement(ins);
			pstmt2.setString(1, start);
			pstmt2.setString(2, end);
			pstmt2.setString(3, ����ʱ��);
			pstmt2.setString(4, ��վʱ��);
			pstmt2.setString(5, ����);
			pstmt2.setString(6, �˳���);
			pstmt2.setInt(7, �����û�ID);
			pstmt2.setString(8, ����);
			pstmt2.setString(9, ��λ);
			pstmt2.setString(10, data);

			pstmt2.executeUpdate();
			result = "����ɹ�";

			pstmt2.close();
		} catch (Exception e) {
			result = "����ʧ�ܣ���ǰ�������ڸ��¼������г�";
			continue_judge=false;
		}
		
		if(continue_judge==true) {
			String sql_left="Update leftover"+ ���� + " Set ";
			int t = start_index;
			while (true) {
				sql_left += (change(t) + "=1");
				if (t == end_index - 1) {
					break;
				}
				t++;
				sql_left += ",";
			}
			
			sql_left+=" where ����='"+data+"' and �����='"+����+"' and ��λ��='"+��λ+"'";
			System.out.println(sql_left);
			
			PreparedStatement pstmt3 = con.prepareStatement(sql_left);
			pstmt3.executeUpdate();
			pstmt3.close();
		}

		con.close();
		return result;

	}

	public static List<List<String>> showorder(String �û���) throws Exception {
		List<List<String>> re = new ArrayList<List<String>>();
		Connection con=jdbc.Control.getcon();

		String sql1 = "SELECT * FROM useorder WHERE �����û�ID=(select ID from log where �û���='"+�û���+"') ORDER BY ���� ASC";
		PreparedStatement pstmt1 = con.prepareStatement(sql1);
		ResultSet resultSet1 = pstmt1.executeQuery(sql1);

		while (resultSet1.next()) {
			List<String> one_line1 = new ArrayList<String>();
			one_line1.add(resultSet1.getString("���"));
			one_line1.add(resultSet1.getString("����"));
			one_line1.add(resultSet1.getString("����վ"));
			one_line1.add(resultSet1.getString("Ŀ��վ"));
			one_line1.add(resultSet1.getString("����ʱ��"));
			one_line1.add(resultSet1.getString("��վʱ��"));
			one_line1.add(resultSet1.getString("�˳���"));
			one_line1.add(resultSet1.getString("����"));
			one_line1.add(resultSet1.getString("����"));
			one_line1.add(resultSet1.getString("��λ"));
			
			re.add(one_line1);
		}
		pstmt1.close();
		resultSet1.close();
		con.close();
		return re;
	}

	public static String releaseticket(String ���, String ����, String ����, String ����, String ��λ,
			String start, String end) throws Exception {
		String result=null;
		Connection con=jdbc.Control.getcon();
		
		String sql1_1 = "SELECT * FROM stop WHERE վ��='" + start + "' and ���κ�='" + ���� + "'";
		String sql2_2 = "SELECT * FROM stop WHERE վ��='" + end + "' and ���κ�='" + ���� + "'";
		int start_index = 0;
		int end_index = 0;
		PreparedStatement pstmt1 = con.prepareStatement(sql1_1);
		ResultSet resultSet1 = pstmt1.executeQuery(sql1_1);

		while (resultSet1.next()) {
			start_index = resultSet1.getInt("��������");
		}

		pstmt1 = con.prepareStatement(sql2_2);
		resultSet1 = pstmt1.executeQuery(sql2_2);
		while (resultSet1.next()) {
			end_index = resultSet1.getInt("��������");
		}
	
		String sql1 = "Delete FROM useorder WHERE ���='"+���+"'";
		String sql_left="Update leftover"+ ���� + " Set ";
		int t = start_index;
		while (true) {
			sql_left += (change(t) + "=0");
			if (t == end_index - 1) {
				break;
			}
			t++;
			sql_left += ",";
		}
		
		sql_left+=" where ����='"+����+"' and �����='"+����+"' and ��λ��='"+��λ+"'";

		try {
			con.setAutoCommit(false);
			
			pstmt1=con.prepareStatement(sql_left);
			pstmt1.executeUpdate();
			
			pstmt1=con.prepareStatement(sql1);
			pstmt1.executeUpdate();
			
			con.commit();
			resultSet1.close();
			pstmt1.close();
			con.close();
			result="�ɹ�";
		}catch(Exception e) {
			con.rollback();
			e.printStackTrace();
			result="����";
		}
		return result;
	}

	public static String getdata(String text) {
		Connection con=jdbc.Control.getcon();
		String result=" ";
		String sql1 = "SELECT ���� FROM leftover"+text+" Group by ����";
		PreparedStatement pstmt1;
		try {
			pstmt1 = con.prepareStatement(sql1);
			ResultSet resultSet1 = pstmt1.executeQuery(sql1);

			while (resultSet1.next()) {
				result+=resultSet1.getString("����");
				result+="  ";
			}
			resultSet1.close();
			pstmt1.close();
			con.close();
			
		} catch (SQLException e) {
			result="�г��Ų�����";
			e.printStackTrace();
		}
		
		return result;
		
	}

	public static String change(String before, String after,String text) {
		String result=null;
		Connection con=jdbc.Control.getcon();
		String sql_pre="Select * from stop where ���κ�='"+text+"'";
		PreparedStatement pstmt1;
		String pre=" ";
		try {
			pstmt1 = con.prepareStatement(sql_pre);
			ResultSet resultSet1 = pstmt1.executeQuery(sql_pre);

			while (resultSet1.next()) {
				int index=resultSet1.getInt("��������");
				pre+=change(index)+"=0,";
			}
			resultSet1.close();
			
		} catch (SQLException e) {
			result="����";
			e.printStackTrace();
		}
		String sql1 = "Update leftover"+text+" Set "+pre+"����='"+after+"' where ����='"+before+"'";
		System.out.print(sql1);
		try {
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.executeUpdate();
		
			pstmt1.close();
			con.close();
			result="���³ɹ�";
			
		} catch (SQLException e) {
			result="����";
			e.printStackTrace();
		}
		return result;
	}

}
