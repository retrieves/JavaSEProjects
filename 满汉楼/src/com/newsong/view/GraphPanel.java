package com.newsong.view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

import com.newsong.JavaBean.SalesItem;
import com.newsong.model.SalesItemDAOImpl;

@SuppressWarnings("all")
public class GraphPanel extends JPanel implements ActionListener {
	int w = Toolkit.getDefaultToolkit().getScreenSize().width;
	int h = Toolkit.getDefaultToolkit().getScreenSize().height;
	static SalesItemDAOImpl sidi = new SalesItemDAOImpl();
	ChartPanel chartPanel;
	JPanel jpChart;
	JPanel jpSouth;

	JTabbedPane jtp;
	JPanel jpMonthSelection;
	JComboBox jcbYear;
	JLabel jlYear;
	JComboBox jcbMonth;
	JLabel jlMonth;
	JButton jbConfirmMonth;

	JPanel jpYearSelection;
	JComboBox jcbYear1;
	JLabel jlYear1;
	JButton jbConfirmYear;

	JPanel jpYearsSelection;
	JComboBox jcbYear2;
	JLabel jlYearLeft;
	JLabel jlYearRight;
	JButton jbConfirmYears;
	JPanel jpTemp;
	JPanel jpStatistic;
	JLabel jlMax;
	JLabel jlMin;
	JPanel jpSouthEast;
	JLabel jlTemp1;
	JLabel jlTemp2;
	JButton jbSwitch;
	int currentYear = 2016;
	int currentMonth = 5;
	boolean isHistogram = true;
	
	static Object[] year = { "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016" };
	static Object[] month = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };

	public GraphPanel() {
		this.setLayout(new BorderLayout());
		jlMax = new JLabel();
		jlMin = new JLabel();
		jpSouth = new JPanel();
		jpSouth.setLayout(new GridLayout(1, 4));
		jtp = new JTabbedPane();
		jpChart = new JPanel();
		chartPanel = createDefaultChart();
		jpChart.add(chartPanel);

		// �·����۶�
		jpMonthSelection = new JPanel();
		jpMonthSelection.setLayout(new FlowLayout());
		jcbYear = new JComboBox<>(year);
		jlYear = new JLabel("��");
		jcbMonth = new JComboBox<>(month);
		jlMonth = new JLabel("��");

		jbConfirmMonth = new JButton("ȷ��");
		jbConfirmMonth.setActionCommand("confirmMonth");
		jbConfirmMonth.addActionListener(this);

		jpMonthSelection.add(jcbYear);
		jpMonthSelection.add(jlYear);
		jpMonthSelection.add(jcbMonth);
		jpMonthSelection.add(jlMonth);
		jpMonthSelection.add(jbConfirmMonth);
		jtp.add("�·����۶�", jpMonthSelection);

		// ������۶�
		jpYearSelection = new JPanel();
		jpYearSelection.setLayout(new FlowLayout());
		jcbYear1 = new JComboBox<>(year);
		jlYear1 = new JLabel("��");
		jbConfirmYear = new JButton("ȷ��");
		jbConfirmYear.setActionCommand("confirmYear");
		jbConfirmYear.addActionListener(this);
		jpYearSelection.add(jcbYear1);
		jpYearSelection.add(jlYear1);
		jpYearSelection.add(jbConfirmYear);
		jtp.add("������۶�", jpYearSelection);

		// �������۶�
		jpYearsSelection = new JPanel();
		jpYearSelection.setLayout(new FlowLayout());
		jcbYear2 = new JComboBox<>(year);
		jlYearLeft = new JLabel("��");
		jlYearRight = new JLabel("����12����");
		jbConfirmYears = new JButton("ȷ��");
		jbConfirmYears.setActionCommand("confirmYears");
		jbConfirmYears.addActionListener(this);
		jpYearsSelection.add(jlYearLeft);
		jpYearsSelection.add(jcbYear2);
		jpYearsSelection.add(jlYearRight);
		jpYearsSelection.add(jbConfirmYears);
		jtp.add("�������۶�", jpYearsSelection);

		jpSouth.add(jtp);
		jpTemp = new JPanel();
		jpSouth.add(jpTemp);

		jpStatistic = new JPanel();
		jpStatistic.setLayout(new FlowLayout());

		jpStatistic.add(jlMax);
		jpStatistic.add(jlMin);

		jpSouth.add(jpStatistic);

		jpSouthEast = new JPanel();
		jpSouthEast.setLayout(new GridLayout(1, 3));
		jlTemp1 = new JLabel();
		jlTemp2 = new JLabel();
		jbSwitch = new JButton("�л�Ϊ����ͼ");
		jbSwitch.setActionCommand("switch");
		jbSwitch.addActionListener(this);

		jpSouthEast.add(jlTemp1);
		jpSouthEast.add(jlTemp2);
		jpSouthEast.add(jbSwitch);

		jpSouth.add(jpSouthEast);
		this.add(jpChart, BorderLayout.CENTER);
		this.add(jpSouth, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "confirmMonth":
			this.currentYear = Integer.parseInt((String) jcbYear.getSelectedItem());
			this.currentMonth = Integer.parseInt((String) jcbMonth.getSelectedItem());
			switchPanel(createHistogram(currentYear, currentMonth));
			isHistogram = true;
			break;
		case "confirmYear":
			this.currentYear = Integer.parseInt((String) jcbYear1.getSelectedItem());
			switchPanel(createHistogram(currentYear));
			isHistogram = true;
			break;
		case "confirmYears":
			this.currentYear = Integer.parseInt((String) jcbYear2.getSelectedItem());
			switchPanel(createHistogram(currentYear, true));
			isHistogram = true;
			break;
		case "switch":
			switch (jtp.getSelectedIndex()) {
			case 0:
				if(isHistogram) {
					switchPanel(createLineChart(currentYear, currentMonth));
					jbSwitch.setText("�л�Ϊ��״ͼ");
					isHistogram = false;
				}
				else {
					switchPanel(createHistogram(currentYear, currentMonth));
					jbSwitch.setText("�л�Ϊ����ͼ");
					isHistogram = true;
				}
				break;
			case 1:
				if(isHistogram) {
					switchPanel(createLineChart(currentYear));
					jbSwitch.setText("�л�Ϊ��״ͼ");
					isHistogram = false;
				}
				else {
					switchPanel(createHistogram(currentYear));
					jbSwitch.setText("�л�Ϊ����ͼ");
					isHistogram = true;
				}
				break;
			case 2:
				if(isHistogram) {
					switchPanel(createLineChart(currentYear,true));
					jbSwitch.setText("�л�Ϊ��״ͼ");
					isHistogram = false;
				}
				else {
					switchPanel(createHistogram(currentYear,true));
					jbSwitch.setText("�л�Ϊ����ͼ");
					isHistogram = true;
				}
				break;
			}

		}
	}

	// ����ͼ��
	private void switchPanel(ChartPanel newChart) {
		jpChart.remove(chartPanel);
		chartPanel = newChart;
		jpChart.add(chartPanel);
		this.repaint();
	}

	// ����ȱʡͼ��
	private ChartPanel createDefaultChart() {
		return createHistogram(2016, 5);
	}

	// �����·ݵ���״ͼ
	private ChartPanel createHistogram(int year, int month) {
		CategoryDataset dataset = createDataSet(year, month);

		JFreeChart chart = ChartFactory.createBarChart3D(year + "��" + month + "�����۶���״ͼ (��λ:Ԫ)", // ͼ�����
				"����", // Ŀ¼�����ʾ��ǩ
				"���۶�", // ��ֵ�����ʾ��ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				false, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������ false)
				false, // �Ƿ����ɹ���
				false // �Ƿ����� URL ����
		);
		return getAdjustedHistogramPanel(chart, dataset);
	}

	// ������ݵ���״ͼ
	private ChartPanel createHistogram(int year) {
		CategoryDataset dataset = createDataSet(year);
		JFreeChart chart = ChartFactory.createBarChart3D(year + "�����۶���״ͼ (��λ:ǧԪ)", // ͼ�����
				"�·�", // Ŀ¼�����ʾ��ǩ
				"���۶�", // ��ֵ�����ʾ��ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				false, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������ false)
				false, // �Ƿ����ɹ���
				false // �Ƿ����� URL ����
		);
		return getAdjustedHistogramPanel(chart, dataset);
	}

	private ChartPanel createHistogram(int year, boolean isAll) {
		CategoryDataset dataset = createDataSet(year, true);

		JFreeChart chart = ChartFactory.createBarChart3D(
				year + "����" + Calendar.getInstance().get(Calendar.YEAR) + "�����۶���״ͼ (��λ:��Ԫ)", // ͼ�����
				"���", // Ŀ¼�����ʾ��ǩ
				"���۶�", // ��ֵ�����ʾ��ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				false, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������ false)
				false, // �Ƿ����ɹ���
				false // �Ƿ����� URL ����
		);
		return getAdjustedHistogramPanel(chart, dataset);
	}

	private ChartPanel createLineChart(int year, int month) {
		CategoryDataset dataset = createDataSet(year, month);
		JFreeChart chart = ChartFactory.createLineChart(year + "��" + month + "�����۶�����ͼ(��λ:Ԫ)", // ������Ŀ���ַ�������
				"����", // ����
				"���۶�", // ����
				dataset, // ������ݼ�
				PlotOrientation.VERTICAL, // ͼ�귽��ֱ
				false, // ��ʾͼ��
				false, // �������ɹ���
				false // ��������URL��ַ
		);
		chart.setAntiAlias(true);
		return getAdjustedLinePanel(chart, dataset);
	}

	private ChartPanel createLineChart(int year) {
		CategoryDataset dataset = createDataSet(year);
		JFreeChart chart = ChartFactory.createLineChart(year + "�����۶�����ͼ (��λ:ǧԪ)", // ������Ŀ���ַ�������
				"�·�", // ����
				"���۶�", // ����
				dataset, // ������ݼ�
				PlotOrientation.VERTICAL, // ͼ�귽��ֱ
				false, // ��ʾͼ��
				false, // �������ɹ���
				false // ��������URL��ַ
		);
		chart.setAntiAlias(true);
		return getAdjustedLinePanel(chart, dataset);
	}

	private ChartPanel createLineChart(int year, boolean isAll) {
		CategoryDataset dataset = createDataSet(year,true);
		JFreeChart chart = ChartFactory.createLineChart(year + "����" + Calendar.getInstance().get(Calendar.YEAR) + "�����۶���״ͼ (��λ:��Ԫ)", // ������Ŀ���ַ�������
				"���", // ����
				"���۶�", // ����
				dataset, // ������ݼ�
				PlotOrientation.VERTICAL, // ͼ�귽��ֱ
				false, // ��ʾͼ��
				false, // �������ɹ���
				false // ��������URL��ַ
		);
		chart.setAntiAlias(true);
		return getAdjustedLinePanel(chart, dataset);
	}
	//-----------------------------------------------------------------------------------------------------------------------------
	// ��������Դ ������JLabel����ֵ
	private CategoryDataset createDataSet(int year, int month) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<SalesItem> list = sidi.findMonth(year, month);
		for (SalesItem salesItem : list) {
			dataset.addValue(salesItem.getAmount(), "", "" + salesItem.getId());
		}
		SalesItem maxItem = sidi.findValue(list, true);
		jlMax.setText(year + "��" + month + "��������۶�(" + maxItem.getId() + "��):   ��" + maxItem.getAmount());
		SalesItem minItem = sidi.findValue(list, false);
		jlMin.setText(year + "��" + month + "����С���۶�(" + minItem.getId() + "��):   ��" + minItem.getAmount());
		return dataset;
	}

	private CategoryDataset createDataSet(int year) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<SalesItem> list = sidi.findYear(year);
		for (SalesItem salesItem : list) {
			dataset.addValue(salesItem.getAmount() / 1000, "", "" + salesItem.getId());
			System.out.println(salesItem.getId() + "," + salesItem.getAmount());
		}
		SalesItem maxItem = sidi.findValue(list, true);
		jlMax.setText(year + "��������۶�(" + maxItem.getId() + "��):   ��" + maxItem.getAmount());
		SalesItem minItem = sidi.findValue(list, false);
		jlMin.setText(year + "��������۶�(" + minItem.getId() + "��):   ��" + minItem.getAmount());
		return dataset;
	}

	private CategoryDataset createDataSet(int year, boolean isAll) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<SalesItem> list = sidi.findYears(year);
		for (SalesItem salesItem : list) {
			dataset.addValue(salesItem.getAmount() / 10000, "", "" + salesItem.getId());
			System.out.println(salesItem.getId() + "," + salesItem.getAmount());
		}
		SalesItem maxItem = sidi.findValue(list, true);
		jlMax.setText("��" + year + "����������۶�(" + maxItem.getId() + "��):   ��" + maxItem.getAmount());
		SalesItem minItem = sidi.findValue(list, false);
		jlMin.setText("��" + year + "������С���۶�(" + minItem.getId() + "��):   ��" + minItem.getAmount());
		return dataset;
	}

	// ���������������
	private void adjustCharset(JFreeChart chart) {
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		CategoryAxis domainAxis = categoryplot.getDomainAxis();
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("����", Font.PLAIN, 20));
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
		domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));

	}

	// ����������ʾ����
	private void adjustDisplay(CategoryDataset dataset, CategoryPlot plot) {
		int col = dataset.getColumnCount();
		if (col == 1) {
			plot.getDomainAxis().setLowerMargin(0.26);
			plot.getDomainAxis().setUpperMargin(0.66);
		} else if (col < 6) {
			double margin = (1.0 - col * 0.08) / 3;
			plot.getDomainAxis().setLowerMargin(margin);
			plot.getDomainAxis().setUpperMargin(margin);
			((BarRenderer3D) plot.getRenderer()).setItemMargin(margin);
		} else {
			((BarRenderer3D) plot.getRenderer()).setItemMargin(0.1);
		}
	}

	// ���������룬������ʾ�ʹ�С
	public ChartPanel getAdjustedHistogramPanel(JFreeChart chart, CategoryDataset dataset) {
		CategoryPlot plot = chart.getCategoryPlot();
		adjustCharset(chart);
		adjustDisplay(dataset, plot);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(w - 400, h - 350));
		return chartPanel;
	}
	
	//����������ͼ����ʾ����
	public ChartPanel getAdjustedLinePanel(JFreeChart chart, CategoryDataset dataset) {
		adjustCharset(chart);
	    chart.setBackgroundPaint(Color.white);// ���ñ���ɫ   
        //��ȡ��ͼ������  
        CategoryPlot plot = chart.getCategoryPlot();  
        plot.setBackgroundPaint(Color.LIGHT_GRAY); // ���û�ͼ������ɫ  
        plot.setRangeGridlinePaint(Color.WHITE); // ����ˮƽ���򱳾�����ɫ  
        plot.setRangeGridlinesVisible(true);// �����Ƿ���ʾˮƽ���򱳾���,Ĭ��ֵΪtrue  
        plot.setDomainGridlinePaint(Color.WHITE); // ���ô�ֱ���򱳾�����ɫ  
        plot.setDomainGridlinesVisible(true); // �����Ƿ���ʾ��ֱ���򱳾���,Ĭ��ֵΪfalse  
          
          
        CategoryAxis domainAxis = plot.getDomainAxis();     
        domainAxis.setLabelFont(new Font("����", Font.PLAIN, 20)); // ���ú�������  
        domainAxis.setTickLabelFont(new Font("����", Font.PLAIN, 20));// ������������ֵ����  
        domainAxis.setLowerMargin(0.01);// ��߾� �߿����  
        domainAxis.setUpperMargin(0.06);// �ұ߾� �߿����,��ֹ���ߵ�һ�����ݿ����������ᡣ  
        domainAxis.setMaximumCategoryLabelLines(2);  
          
        ValueAxis rangeAxis = plot.getRangeAxis();  
        rangeAxis.setLabelFont(new Font("����", Font.PLAIN, 20));   
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//Y����ʾ����  
        rangeAxis.setAutoRangeMinimumSize(1);   //��С���  
        rangeAxis.setUpperMargin(0.18);//�ϱ߾�,��ֹ����һ�����ݿ����������ᡣ     
        rangeAxis.setLowerBound(0);   //��Сֵ��ʾ0  
        rangeAxis.setAutoRange(false);   //���Զ�����Y������  
        rangeAxis.setTickMarkStroke(new BasicStroke(1.6f));     // ���������Ǵ�С  
        rangeAxis.setTickMarkPaint(Color.BLACK);     // ������������ɫ  
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(w - 400, h - 350));
		return chartPanel;
	}
}
