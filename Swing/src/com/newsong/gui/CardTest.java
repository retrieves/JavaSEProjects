package com.newsong.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;//�����¼���

//������ʱʵ�ּ����ӿ�
public class CardTest extends JFrame implements ActionListener {
	
	JButton nextbutton;
	JButton preButton;
	Panel cardPanel = new Panel();
	Panel controlpaPanel = new Panel();
	// ���忨Ƭ���ֶ���
	CardLayout card = new CardLayout();
	// ���幹�캯��

	public CardTest() {
		
		super("��Ƭ���ֹ�����");
		this.setBounds(400, 400, 640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// ����cardPanel������Ϊ��Ƭ����
		cardPanel.setLayout(card);
		
		// ѭ������cardPanel����������������ť
		// ��ΪcardPanel������Ϊ��Ƭ���֣����ֻ��ʾ������ӵ����
		for (int i = 0; i < 5; i++) {
			JPanel jp = new JPanel();
			jp.add(new JButton("��ť" + i));
			cardPanel.add(jp,i);
		}
		
		// ʵ������ť����
		nextbutton = new JButton("��һ�ſ�Ƭ");
		preButton = new JButton("��һ�ſ�Ƭ");

		// Ϊ��ť����ע�������
		nextbutton.addActionListener(this);
		preButton.addActionListener(this);

		controlpaPanel.add(preButton);
		controlpaPanel.add(nextbutton);
		// ������������Ϊ��ǰ������������

		// �� cardPanel�������ڴ��ڱ߽粼�ֵ��м䣬����Ĭ��Ϊ�߽粼��
		this.add(cardPanel, BorderLayout.CENTER);
		// ��controlpaPanel�������ڴ��ڱ߽粼�ֵ��ϱߣ�
		this.add(controlpaPanel, BorderLayout.SOUTH);
	}

	// ʵ�ְ�ť�ļ�������ʱ�Ĵ���
	public void actionPerformed(ActionEvent e) {
		// ����û�����nextbutton��ִ�е����
		if (e.getSource() == nextbutton) {
			// �л�cardPanel����е�ǰ���֮���һ�����
			// ����ǰ���Ϊ�����ӵ����������ʾ��һ�����������Ƭ�����ʾ��ѭ���ġ�
			card.next(cardPanel);
		}
		if (e.getSource() == preButton) {
			// �л�cardPanel����е�ǰ���֮ǰ��һ�����
			// ����ǰ���Ϊ��һ����ӵ����������ʾ���һ�����������Ƭ�����ʾ��ѭ���ġ�
			card.previous(cardPanel);
		}
	}

	public static void main(String[] args) {
		new CardTest();
	}
}
