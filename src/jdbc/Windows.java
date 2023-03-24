package jdbc;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.*;

import operate.Client;
import operate.Log;
import operate.Train;

public class Windows {

	private JFrame frmv;
	private JFrame frmv_register;
	private JFrame frmv_log;
	private JFrame frmv_useroperate;
	private JFrame frmv_usercheck;
	private JFrame frmv_managelog;
	private JFrame frmv_manageoperate;
	private JFrame frmv_managecheakuse;
	private JFrame frmv_trainchange;

	private String �û��� = null;

	// ������
	public Windows() {
		initialize_frmvlog();
		frmv.setVisible(true);
	}

	private void initialize_frmvlog() {
		frmv = new JFrame();

		frmv.setTitle("Ʊ��ϵͳ");
		frmv.setBounds(500, 150, 535, 420);
		frmv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmv.getContentPane().setLayout(null);

		JLabel label1 = new JLabel("��ӭʹ��Ʊ��ϵͳ");
		label1.setFont(new Font("", Font.BOLD, 22));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setBounds(110, 100, 300, 25);
		frmv.getContentPane().add(label1);

		JButton Button2 = new JButton("�û���½");
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				douserlog();
			}
		});
		Button2.setBounds(120, 200, 100, 25);
		frmv.getContentPane().add(Button2);

		JButton Button3 = new JButton("����Ա��½");
		Button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				domanagelog();
			}
		});
		Button3.setBounds(300, 200, 100, 25);
		frmv.getContentPane().add(Button3);

	}

	protected void domanagelog() {
		frmv.setVisible(false);
		initialize_frmvmanagelog();
		frmv_managelog.setVisible(true);

	}

	// ����Ա��½����
	private void initialize_frmvmanagelog() {
		frmv_managelog = new JFrame();
		frmv_managelog.setTitle("����Ա��½");
		frmv_managelog.setBounds(500, 150, 535, 420);
		frmv_managelog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmv_managelog.getContentPane().setLayout(null);

		JLabel label2 = new JLabel("�˺�");
		label2.setBounds(50, 100, 100, 25);
		frmv_managelog.getContentPane().add(label2);

		JTextField test2 = new JTextField();
		test2.setBounds(150, 100, 200, 25);
		frmv_managelog.getContentPane().add(test2);

		JLabel label3 = new JLabel("����");
		label3.setBounds(50, 150, 100, 25);
		frmv_managelog.getContentPane().add(label3);

		JPasswordField test3 = new JPasswordField();
		test3.setBounds(150, 150, 200, 25);
		frmv_managelog.getContentPane().add(test3);

		JCheckBox checkBox = new JCheckBox("��ʾ����");
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {// ��ѡ��
					test3.setEchoChar((char) 0);
				} else {
					test3.setEchoChar('*');
				}
			}
		});
		checkBox.setBounds(400, 150, 135, 27);
		frmv_managelog.getContentPane().add(checkBox);

		JLabel label4 = new JLabel();
		label4.setBounds(50, 350, 300, 25);
		frmv_managelog.getContentPane().add(label4);

		JButton b1 = new JButton("��½");
		b1.setBounds(100, 280, 100, 20);
		frmv_managelog.getContentPane().add(b1);

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				�û��� = test2.getText();
				boolean result = false;
				try {
					String test_temp = String.valueOf(test3.getPassword());
					result = Log.Logmanage_log(test2.getText(), test_temp);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (result == true) {
					�û��� = test2.getText();
					domanageoperate();
				} else {
					label4.setText("�������");
				}
			}
		});
	}

	protected void domanageoperate() {
		frmv_managelog.setVisible(false);
		initialize_frmvmanageroperate();
		frmv_manageoperate.setVisible(true);

	}

	private void initialize_frmvmanageroperate() {
		frmv_manageoperate = new JFrame();
		frmv_manageoperate.setTitle("����Ա����");
		frmv_manageoperate.setBounds(400, 100, 800, 600);
		frmv_manageoperate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmv_manageoperate.getContentPane().setLayout(null);

		Button b1 = new Button("�û���Ϣ�鿴�͸���");
		b1.setBounds(50, 50, 200, 100);
		frmv_manageoperate.getContentPane().add(b1);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doviewuser();

			}
		});

		Button b2 = new Button("�г���Ϣ����");
		b2.setBounds(350, 50, 200, 100);
		frmv_manageoperate.getContentPane().add(b2);

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dotrainchange();

			}
		});

	}

	protected void dotrainchange() {
		initialize_frmvtrainchange();
		frmv_trainchange.setVisible(true);

	}

	private void initialize_frmvtrainchange() {
		frmv_trainchange = new JFrame();
		frmv_trainchange.setTitle("����");
		frmv_trainchange.setBounds(500, 150, 635, 420);
		frmv_trainchange.getContentPane().setLayout(null);

		JLabel l1 = new JLabel("��������Ҫ��ѯ���г���");
		l1.setBounds(30, 20, 200, 20);
		frmv_trainchange.getContentPane().add(l1);

		JTextField t1 = new JTextField();
		t1.setBounds(250, 20, 100, 20);
		frmv_trainchange.getContentPane().add(t1);

		JLabel l1_1 = new JLabel();
		l1_1.setBounds(30, 50, 400, 20);
		frmv_trainchange.getContentPane().add(l1_1);

		JButton b1 = new JButton("�ύ");
		b1.setBounds(390, 20, 60, 20);
		frmv_trainchange.getContentPane().add(b1);
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String result = Train.getdata(t1.getText());
				l1_1.setText(result);

			}
		});

		JLabel l2 = new JLabel("��������и�����Ʊ������");
		l2.setBounds(30, 250, 200, 20);
		frmv_trainchange.getContentPane().add(l2);

		JLabel l3 = new JLabel("����ǰ����");
		l3.setBounds(30, 270, 100, 20);
		frmv_trainchange.getContentPane().add(l3);

		JTextField t2 = new JTextField();
		t2.setBounds(150, 270, 100, 20);
		frmv_trainchange.getContentPane().add(t2);

		JLabel l4 = new JLabel("���º�����");
		l4.setBounds(270, 270, 100, 20);
		frmv_trainchange.getContentPane().add(l4);

		JTextField t3 = new JTextField();
		t3.setBounds(400, 270, 100, 20);
		frmv_trainchange.getContentPane().add(t3);

		JButton b2 = new JButton("�ύ");
		b2.setBounds(510, 270, 60, 20);
		frmv_trainchange.getContentPane().add(b2);
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String result=Train.change(t2.getText(),t3.getText(),t1.getText());
				JOptionPane.showMessageDialog(frmv_trainchange, result);
				
			}
		});

	}

	// �û���Ϣ�鿴
	protected void doviewuser() {
		try {
			initialize_frmvmanagecheakuse();
			frmv_managecheakuse.setVisible(true);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initialize_frmvmanagecheakuse() throws Exception {
		frmv_managecheakuse = new JFrame();
		frmv_managecheakuse.setTitle("��ѯ");
		frmv_managecheakuse.setBounds(500, 150, 635, 420);
		frmv_managecheakuse.getContentPane().setLayout(null);

		JLabel l1 = new JLabel("��������Ҫ�޸��û����û���");
		l1.setBounds(30, 20, 200, 20);
		frmv_managecheakuse.getContentPane().add(l1);

		JTextField t1 = new JTextField();
		t1.setBounds(250, 20, 100, 20);
		frmv_managecheakuse.getContentPane().add(t1);

		JButton b1 = new JButton("�ύ");
		b1.setBounds(390, 20, 60, 20);
		frmv_managecheakuse.getContentPane().add(b1);

		JLabel l2 = new JLabel();
		l2.setBounds(30, 50, 200, 20);
		frmv_managecheakuse.getContentPane().add(l2);

		String[] Columns = new String[] { "�û���", "��ʵ����", "����", "֤������", "֤����", "�ֻ���", "ϵͳ����" };
		List<List<String>> re = Client.managecheck();
		String[][] list = Control.getArray(re);
		JTable t = new JTable(list, Columns);
		JScrollPane sp = new JScrollPane(t);
		sp.setBounds(20, 100, 580, 400);
		frmv_managecheakuse.getContentPane().add(sp);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int len = re.size();
				String name = t1.getText();
				int change_index = -1;
				for (int i = 0; i < len; i++) {
					if (re.get(i).get(0).equals(name)) {
						change_index = i;
						break;
					}
				}
				if (change_index == -1)
					JOptionPane.showMessageDialog(frmv_managecheakuse, "δ�ҵ����û�");
				else {
					try {
						String result = Client.Client_register(name, (String) t.getModel().getValueAt(change_index, 0),
								(String) t.getModel().getValueAt(change_index, 6),
								(String) t.getModel().getValueAt(change_index, 1),
								(String) t.getModel().getValueAt(change_index, 2),
								(String) t.getModel().getValueAt(change_index, 3),
								(String) t.getModel().getValueAt(change_index, 4),
								(String) t.getModel().getValueAt(change_index, 5));
						
						l2.setText(result);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

	}

	protected void douserlog() {
		frmv.setVisible(false);
		initialize_frmvuserlog();
		frmv_log.setVisible(true);

	}

	// �û�ע���¼����
	private void initialize_frmvuserlog() {

		frmv_log = new JFrame();
		frmv_log.setTitle("�û���½");
		frmv_log.setBounds(500, 150, 535, 420);
		frmv_log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmv_log.getContentPane().setLayout(null);

		JLabel label2 = new JLabel("�û���");
		label2.setBounds(50, 100, 100, 25);
		frmv_log.getContentPane().add(label2);

		JTextField test2 = new JTextField();
		test2.setBounds(150, 100, 200, 25);
		frmv_log.getContentPane().add(test2);

		JLabel label3 = new JLabel("����");
		label3.setBounds(50, 150, 100, 25);
		frmv_log.getContentPane().add(label3);

		JPasswordField test3 = new JPasswordField();
		test3.setBounds(150, 150, 200, 25);
		frmv_log.getContentPane().add(test3);

		JCheckBox checkBox = new JCheckBox("��ʾ����");
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {// ��ѡ��
					test3.setEchoChar((char) 0);
				} else {
					test3.setEchoChar('*');
				}
			}
		});
		checkBox.setBounds(400, 150, 135, 27);
		frmv_log.getContentPane().add(checkBox);

		JLabel label4 = new JLabel();
		label4.setBounds(50, 350, 300, 25);
		frmv_log.getContentPane().add(label4);

		JButton Button1 = new JButton("ע��");
		Button1.setBounds(250, 280, 100, 20);
		frmv_log.getContentPane().add(Button1);
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String test_2 = test2.getText();
				String test_temp = String.valueOf(test3.getPassword());
				if (test_2.length() < 1) {
					label4.setText("�û�������Ϊ��");
				} else if (test_temp.length() < 1) {
					label4.setText("���벻��Ϊ��");
				} else {
					try {
						boolean result = Log.register(test2.getText(), test_temp);
						if (result == false) {
							label4.setText("�û����Ѵ���");
						} else {
							label4.setText("ע��ɹ�");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});

		JButton b2 = new JButton("��½");
		b2.setBounds(100, 280, 100, 20);
		frmv_log.getContentPane().add(b2);

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				�û��� = test2.getText();
				boolean result = false;
				try {
					String test_temp = String.valueOf(test3.getPassword());
					result = Log.Log_log(test2.getText(), test_temp);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (result == true) {
					�û��� = test2.getText();
					douseroperate();
				} else {
					label4.setText("�������");
				}
			}
		});

	}

	// �û���������
	protected void douseroperate() {
		frmv_log.setVisible(false);
		initialize_frmvuseroperate();
		frmv_useroperate.setVisible(true);

	}

	private void initialize_frmvuseroperate() {
		frmv_useroperate = new JFrame();

		frmv_useroperate.setTitle("�û�����");
		frmv_useroperate.setBounds(400, 100, 800, 600);
		frmv_useroperate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmv_useroperate.getContentPane().setLayout(null);

		Button b1 = new Button("�û���Ϣ���Ƹ���");
		b1.setBounds(50, 50, 200, 100);
		frmv_useroperate.getContentPane().add(b1);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doregister();

			}
		});

		Button b2 = new Button("�û�ע��");
		b2.setBounds(50, 275, 200, 100);
		frmv_useroperate.getContentPane().add(b2);

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frmv_useroperate, "ȷ��ע���û���", "��ʾ",
						JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						operate.Log.Log_Delete(�û���);
						frmv_useroperate.setVisible(false);
						frmv.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		/*
		 * Button b4 = new Button("Ʊ���ѯ�͹���"); b4.setBounds(50, 300, 200, 100);
		 * frmv_useroperate.getContentPane().add(b4);
		 * 
		 * b4.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { dousercheck(); } });
		 */

		Button b5 = new Button("Ʊ���ѯ�͹���");
		b5.setBounds(300, 50, 200, 100);
		frmv_useroperate.getContentPane().add(b5);
		b5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frmv_newusercheck = ticket_ope.initialize_frmvusercheck(�û���);
				frmv_newusercheck.setVisible(true);

			}
		});

		Button b6 = new Button("�û�������ѯ");
		b6.setBounds(300, 275, 200, 100);
		frmv_useroperate.getContentPane().add(b6);

		b6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frmv_showorder = ticket_ope.showorder(�û���);
				frmv_showorder.setVisible(true);
			}
		});

	}

	// Ʊ���ѯ����
	protected void dousercheck() {
		initialize_frmvusercheck();
		frmv_usercheck.setVisible(true);

	}

	private void initialize_frmvusercheck() {

		frmv_usercheck = new JFrame();
		frmv_usercheck.setTitle("��ѯ");
		frmv_usercheck.setBounds(500, 150, 535, 420);
		frmv_usercheck.getContentPane().setLayout(null);

		JLabel l1 = new JLabel("ʼ����");
		l1.setBounds(20, 10, 50, 20);
		frmv_usercheck.getContentPane().add(l1);

		JTextField f1 = new JTextField();
		f1.setBounds(70, 10, 100, 20);
		frmv_usercheck.getContentPane().add(f1);

		JLabel l2 = new JLabel("Ŀ�ĵ�");
		l2.setBounds(200, 10, 50, 20);
		frmv_usercheck.getContentPane().add(l2);

		JTextField f2 = new JTextField();
		f2.setBounds(250, 10, 100, 20);
		frmv_usercheck.getContentPane().add(f2);

		JButton b1 = new JButton("��ѯ");
		b1.setBounds(400, 10, 100, 20);
		frmv_usercheck.getContentPane().add(b1);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] Columns = new String[] { "�г���", "����ʱ��", "��վʱ��" };
				List<List<String>> re = new ArrayList<List<String>>();
				try {
					re = Train.train_check(f1.getText(), f2.getText());
					String[][] list = Control.getArray(re);
					JTable t = new JTable(list, Columns);
					JScrollPane sp = new JScrollPane(t);
					sp.setBounds(20, 100, 500, 400);
					frmv_usercheck.getContentPane().add(sp);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JLabel l3 = new JLabel("�����빺����г���");
		l3.setBounds(20, 40, 150, 20);
		frmv_usercheck.getContentPane().add(l3);

		JTextField f3 = new JTextField();
		f3.setBounds(200, 40, 100, 20);
		frmv_usercheck.getContentPane().add(f3);

		JButton b2 = new JButton("����");
		b2.setBounds(400, 40, 100, 20);
		frmv_usercheck.getContentPane().add(b2);

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String result = null;
				try {
					result = Train.buyticket(f1.getText(), f2.getText(), f3.getText(), �û���);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					result = "��Ʊ����";
				}
				JOptionPane.showMessageDialog(frmv_usercheck, result, "��ʾ", 1);

			}
		});

	}

	// �û���Ϣ���ƽ���
	protected void doregister() {
		frmv.setVisible(false);
		initialize_frmvregister();
		frmv_register.setVisible(true);

	}

	private void initialize_frmvregister() {

		frmv_register = new JFrame();

		frmv_register.setTitle("�û���Ϣ����");
		frmv_register.setBounds(500, 150, 535, 420);
		frmv_register.getContentPane().setLayout(null);

		List<String> re = new ArrayList<String>();
		try {
			re = Client.usercheck(�û���);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel label1 = new JLabel("���������Ϣ���и��ĺ����ƣ����Ǻ�Ϊ������");
		label1.setBounds(20, 10, 300, 20);
		frmv_register.getContentPane().add(label1);

		JLabel label2 = new JLabel("�û���*");
		label2.setBounds(20, 30, 100, 25);
		frmv_register.getContentPane().add(label2);

		JTextField test2 = new JTextField();
		test2.setBounds(150, 30, 200, 25);
		test2.setText(re.get(0));
		frmv_register.getContentPane().add(test2);

		JLabel label3 = new JLabel("����*");
		label3.setBounds(20, 60, 100, 25);
		frmv_register.getContentPane().add(label3);

		JPasswordField test3 = new JPasswordField();
		test3.setBounds(150, 60, 200, 25);
		test3.setText(re.get(1));
		frmv_register.getContentPane().add(test3);

		JCheckBox checkBox = new JCheckBox("��ʾ����");
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {// ��ѡ��
					test3.setEchoChar((char) 0);
				} else {
					test3.setEchoChar('*');
				}
			}
		});
		checkBox.setBounds(400, 60, 135, 27);
		frmv_register.getContentPane().add(checkBox);

		JLabel label4 = new JLabel("��ʵ����*");
		label4.setBounds(20, 95, 100, 25);
		frmv_register.getContentPane().add(label4);

		JTextField test4 = new JTextField();
		test4.setBounds(150, 95, 200, 25);
		test4.setText(re.get(2));
		frmv_register.getContentPane().add(test4);

		JLabel label5 = new JLabel("����");
		label5.setBounds(20, 125, 100, 25);
		frmv_register.getContentPane().add(label5);

		JTextField test5 = new JTextField();
		test5.setBounds(150, 125, 200, 25);
		test5.setText(re.get(3));
		frmv_register.getContentPane().add(test5);

		JLabel label6 = new JLabel("֤������(���֤/����)*");
		label6.setBounds(20, 155, 200, 25);
		frmv_register.getContentPane().add(label6);

		JTextField test6 = new JTextField();
		test6.setBounds(150, 155, 200, 25);
		test6.setText(re.get(4));
		frmv_register.getContentPane().add(test6);

		JLabel label7 = new JLabel("֤������*");
		label7.setBounds(20, 185, 100, 25);
		frmv_register.getContentPane().add(label7);

		JTextField test7 = new JTextField();
		test7.setBounds(150, 185, 200, 25);
		test7.setText(re.get(5));
		frmv_register.getContentPane().add(test7);

		JLabel label8 = new JLabel("�ֻ�����*");
		label8.setBounds(20, 215, 100, 25);
		frmv_register.getContentPane().add(label8);

		JTextField test8 = new JTextField();
		test8.setBounds(150, 215, 200, 25);
		test8.setText(re.get(6));
		frmv_register.getContentPane().add(test8);

		JButton b1 = new JButton("����");
		b1.setBounds(150, 280, 100, 20);
		frmv_register.getContentPane().add(b1);

		JLabel label9 = new JLabel();
		label9.setBounds(20, 350, 300, 30);
		frmv_register.getContentPane().add(label9);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String re = null;
				String temp_name=test2.getText();
				String test_temp = String.valueOf(test3.getPassword());

				if (test2.getText().length() < 1 || test3.getPassword().length < 1 || test4.getText().length() < 1
						|| test5.getText().length() < 1 || test6.getText().length() < 1 || test7.getText().length() < 1
						|| test8.getText().length() < 1) {
					re = "����ʧ�ܣ�����������Ϣ�Ƿ�����";
				} else {
					try {
						re = Client.Client_register(�û���, test2.getText(), test_temp, test4.getText(), test5.getText(),
								test6.getText(), test7.getText(), test8.getText());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if(re.equals("���³ɹ�")) {
					�û��� = temp_name;
				}
				label9.setText(re);
			}
		});
	}

}