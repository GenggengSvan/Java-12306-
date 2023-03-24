package jdbc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import operate.Train;

public class ticket_ope {

	public static JFrame initialize_frmvusercheck(String �û���) {
		JFrame f = new JFrame();
		f.setTitle("��ѯ");
		f.setBounds(500, 150, 535, 500);
		f.getContentPane().setLayout(null);

		JLabel l_title = new JLabel("�г��г���Ϣ��ѯ");
		l_title.setBounds(20, 5, 100, 20);
		f.getContentPane().add(l_title);

		JLabel l1 = new JLabel("ʼ����");
		l1.setBounds(20, 30, 50, 20);
		f.getContentPane().add(l1);

		JTextField f1 = new JTextField();
		f1.setBounds(70, 30, 100, 20);
		f.getContentPane().add(f1);

		JLabel l2 = new JLabel("Ŀ�ĵ�");
		l2.setBounds(200, 30, 50, 20);
		f.getContentPane().add(l2);

		JTextField f2 = new JTextField();
		f2.setBounds(250, 30, 100, 20);
		f.getContentPane().add(f2);

		JButton b1 = new JButton("��ѯ");
		b1.setBounds(400, 30, 100, 20);
		f.getContentPane().add(b1);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (f1.getText().length() < 1 || f2.getText().length() < 1) {
					JOptionPane.showMessageDialog(f, "�������ѯ��Ϣ");

				} else {
					String[] Columns = new String[] { "�г���", "����ʱ��", "��վʱ��", "��ͣվ" };
					List<List<String>> re = new ArrayList<List<String>>();
					try {
						re = Train.train_new_check(f1.getText(), f2.getText());
						String[][] list = Control.getArray(re);
						JTable t = new JTable(list, Columns);
						t.setEnabled(false);
						JScrollPane sp = new JScrollPane(t);
						sp.setBounds(10, 70, 500, 150);
						f.getContentPane().add(sp);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JLabel l_title2 = new JLabel("��Ʊ��Ϣ��ѯ    ע��ʼ���غ�Ŀ�ĵ�����������ı�����");
		l_title2.setBounds(20, 240, 400, 20);
		f.getContentPane().add(l_title2);

		JLabel l3 = new JLabel("�г���");
		l3.setBounds(20, 270, 50, 20);
		f.getContentPane().add(l3);

		JTextField f3 = new JTextField();
		f3.setBounds(70, 270, 100, 20);
		f.getContentPane().add(f3);

		JButton b3 = new JButton("��ѯ");
		b3.setBounds(200, 270, 100, 20);
		f.getContentPane().add(b3);
		
		
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (f1.getText().length() < 1 || f2.getText().length() < 1 || f3.getText().length() < 1) {
					JOptionPane.showMessageDialog(f, "�������ѯ��Ϣ");

				} else {
					String[] Columns = new String[] { "����", "������", "һ����", "������" };
					List<List<String>> re;
					try {
						re = Train.train_new_checkleft(f1.getText(), f2.getText(), f3.getText());
						String[][] list = Control.getArray(re);
						JTable t = new JTable(list, Columns);
						JScrollPane sp = new JScrollPane(t);
						sp.setBounds(10, 300, 500, 150);
						f.getContentPane().add(sp);
						t.addMouseListener(new MouseListener() {

							@Override
							public void mouseClicked(MouseEvent e) {
								if (e.getClickCount() == 1) {

									int col = t.getSelectedColumn();
									int row = t.getSelectedRow();
									if (re.get(row).get(col).equals("0")) {
										JOptionPane.showMessageDialog(f, "��Ʊ����");
									} else {
										String data = re.get(row).get(0);
										String type_seat = null;
										if (col == 1)
											type_seat = "������";
										else if (col == 2)
											type_seat = "һ����";
										else if (col == 3)
											type_seat = "������";
										int result = JOptionPane.showConfirmDialog(f, "�Ƿ���" + data + "�յ�" + type_seat,
												"��ʾ", JOptionPane.YES_NO_OPTION);
										if (result != 1) {
											String tip;
											try {
												tip = Train.newbuyticket(data, type_seat, f1.getText(), f2.getText(),
														f3.getText(), �û���);
												JOptionPane.showMessageDialog(f, tip);
											} catch (Exception e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}

										}
									}
								}
							}

							@Override
							public void mousePressed(MouseEvent e) {
							}

							@Override
							public void mouseReleased(MouseEvent e) {
							}

							@Override
							public void mouseEntered(MouseEvent e) {
							}

							@Override
							public void mouseExited(MouseEvent e) {
							}
						});
					} catch (

					Exception e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		return f;
	}

	public static JFrame showorder(String �û���) {
		JFrame f = new JFrame();
		f.setTitle("������Ϣ");
		f.setBounds(500, 150, 810, 500);
		f.getContentPane().setLayout(null);

		String[] Columns = new String[] {"��Ʊ���", "����", "����վ", "Ŀ��վ", "����ʱ��","��վʱ��", "�˳���", "����", "����", "��λ" };
		List<List<String>> re;
		try {
			re = Train.showorder(�û���);
			String[][] list = Control.getArray(re);
			JTable t = new JTable(list, Columns);
			JScrollPane sp = new JScrollPane(t);
			sp.setBounds(10, 10, 800, 450);
			f.getContentPane().add(sp);
			t.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1) {

						int row = t.getSelectedRow();

						int result = JOptionPane.showConfirmDialog(f, "ȷ�϶Ե�ǰ�ж���������Ʊ", "��ʾ", JOptionPane.YES_NO_OPTION);
						if (result != 1) {
							String tip;
							try {
								tip = Train.releaseticket(re.get(row).get(0),re.get(row).get(1),re.get(row).get(7),re.get(row).get(8),re.get(row).get(9),re.get(row).get(2),re.get(row).get(3));
								JOptionPane.showMessageDialog(f, tip);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
				}
			});
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		return f;

	}

}
