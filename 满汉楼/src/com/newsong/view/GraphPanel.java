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

		// 月份销售额
		jpMonthSelection = new JPanel();
		jpMonthSelection.setLayout(new FlowLayout());
		jcbYear = new JComboBox<>(year);
		jlYear = new JLabel("年");
		jcbMonth = new JComboBox<>(month);
		jlMonth = new JLabel("月");

		jbConfirmMonth = new JButton("确定");
		jbConfirmMonth.setActionCommand("confirmMonth");
		jbConfirmMonth.addActionListener(this);

		jpMonthSelection.add(jcbYear);
		jpMonthSelection.add(jlYear);
		jpMonthSelection.add(jcbMonth);
		jpMonthSelection.add(jlMonth);
		jpMonthSelection.add(jbConfirmMonth);
		jtp.add("月份销售额", jpMonthSelection);

		// 年份销售额
		jpYearSelection = new JPanel();
		jpYearSelection.setLayout(new FlowLayout());
		jcbYear1 = new JComboBox<>(year);
		jlYear1 = new JLabel("年");
		jbConfirmYear = new JButton("确定");
		jbConfirmYear.setActionCommand("confirmYear");
		jbConfirmYear.addActionListener(this);
		jpYearSelection.add(jcbYear1);
		jpYearSelection.add(jlYear1);
		jpYearSelection.add(jbConfirmYear);
		jtp.add("年份销售额", jpYearSelection);

		// 历年销售额
		jpYearsSelection = new JPanel();
		jpYearSelection.setLayout(new FlowLayout());
		jcbYear2 = new JComboBox<>(year);
		jlYearLeft = new JLabel("自");
		jlYearRight = new JLabel("年起12年内");
		jbConfirmYears = new JButton("确定");
		jbConfirmYears.setActionCommand("confirmYears");
		jbConfirmYears.addActionListener(this);
		jpYearsSelection.add(jlYearLeft);
		jpYearsSelection.add(jcbYear2);
		jpYearsSelection.add(jlYearRight);
		jpYearsSelection.add(jbConfirmYears);
		jtp.add("历年销售额", jpYearsSelection);

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
		jbSwitch = new JButton("切换为折线图");
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
					jbSwitch.setText("切换为柱状图");
					isHistogram = false;
				}
				else {
					switchPanel(createHistogram(currentYear, currentMonth));
					jbSwitch.setText("切换为折线图");
					isHistogram = true;
				}
				break;
			case 1:
				if(isHistogram) {
					switchPanel(createLineChart(currentYear));
					jbSwitch.setText("切换为柱状图");
					isHistogram = false;
				}
				else {
					switchPanel(createHistogram(currentYear));
					jbSwitch.setText("切换为折线图");
					isHistogram = true;
				}
				break;
			case 2:
				if(isHistogram) {
					switchPanel(createLineChart(currentYear,true));
					jbSwitch.setText("切换为柱状图");
					isHistogram = false;
				}
				else {
					switchPanel(createHistogram(currentYear,true));
					jbSwitch.setText("切换为折线图");
					isHistogram = true;
				}
				break;
			}

		}
	}

	// 更换图表
	private void switchPanel(ChartPanel newChart) {
		jpChart.remove(chartPanel);
		chartPanel = newChart;
		jpChart.add(chartPanel);
		this.repaint();
	}

	// 创建缺省图表
	private ChartPanel createDefaultChart() {
		return createHistogram(2016, 5);
	}

	// 生成月份的柱状图
	private ChartPanel createHistogram(int year, int month) {
		CategoryDataset dataset = createDataSet(year, month);

		JFreeChart chart = ChartFactory.createBarChart3D(year + "年" + month + "月销售额柱状图 (单位:元)", // 图表标题
				"天数", // 目录轴的显示标签
				"销售额", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				false, // 是否显示图例(对于简单的柱状图必须是 false)
				false, // 是否生成工具
				false // 是否生成 URL 链接
		);
		return getAdjustedHistogramPanel(chart, dataset);
	}

	// 生成年份的柱状图
	private ChartPanel createHistogram(int year) {
		CategoryDataset dataset = createDataSet(year);
		JFreeChart chart = ChartFactory.createBarChart3D(year + "年销售额柱状图 (单位:千元)", // 图表标题
				"月份", // 目录轴的显示标签
				"销售额", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				false, // 是否显示图例(对于简单的柱状图必须是 false)
				false, // 是否生成工具
				false // 是否生成 URL 链接
		);
		return getAdjustedHistogramPanel(chart, dataset);
	}

	private ChartPanel createHistogram(int year, boolean isAll) {
		CategoryDataset dataset = createDataSet(year, true);

		JFreeChart chart = ChartFactory.createBarChart3D(
				year + "年至" + Calendar.getInstance().get(Calendar.YEAR) + "年销售额柱状图 (单位:万元)", // 图表标题
				"年份", // 目录轴的显示标签
				"销售额", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				false, // 是否显示图例(对于简单的柱状图必须是 false)
				false, // 是否生成工具
				false // 是否生成 URL 链接
		);
		return getAdjustedHistogramPanel(chart, dataset);
	}

	private ChartPanel createLineChart(int year, int month) {
		CategoryDataset dataset = createDataSet(year, month);
		JFreeChart chart = ChartFactory.createLineChart(year + "年" + month + "月销售额折线图(单位:元)", // 报表题目，字符串类型
				"天数", // 横轴
				"销售额", // 纵轴
				dataset, // 获得数据集
				PlotOrientation.VERTICAL, // 图标方向垂直
				false, // 显示图例
				false, // 不用生成工具
				false // 不用生成URL地址
		);
		chart.setAntiAlias(true);
		return getAdjustedLinePanel(chart, dataset);
	}

	private ChartPanel createLineChart(int year) {
		CategoryDataset dataset = createDataSet(year);
		JFreeChart chart = ChartFactory.createLineChart(year + "年销售额折线图 (单位:千元)", // 报表题目，字符串类型
				"月份", // 横轴
				"销售额", // 纵轴
				dataset, // 获得数据集
				PlotOrientation.VERTICAL, // 图标方向垂直
				false, // 显示图例
				false, // 不用生成工具
				false // 不用生成URL地址
		);
		chart.setAntiAlias(true);
		return getAdjustedLinePanel(chart, dataset);
	}

	private ChartPanel createLineChart(int year, boolean isAll) {
		CategoryDataset dataset = createDataSet(year,true);
		JFreeChart chart = ChartFactory.createLineChart(year + "年至" + Calendar.getInstance().get(Calendar.YEAR) + "年销售额柱状图 (单位:万元)", // 报表题目，字符串类型
				"年份", // 横轴
				"销售额", // 纵轴
				dataset, // 获得数据集
				PlotOrientation.VERTICAL, // 图标方向垂直
				false, // 显示图例
				false, // 不用生成工具
				false // 不用生成URL地址
		);
		chart.setAntiAlias(true);
		return getAdjustedLinePanel(chart, dataset);
	}
	//-----------------------------------------------------------------------------------------------------------------------------
	// 创建数据源 并设置JLabel的最值
	private CategoryDataset createDataSet(int year, int month) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<SalesItem> list = sidi.findMonth(year, month);
		for (SalesItem salesItem : list) {
			dataset.addValue(salesItem.getAmount(), "", "" + salesItem.getId());
		}
		SalesItem maxItem = sidi.findValue(list, true);
		jlMax.setText(year + "年" + month + "月最大销售额(" + maxItem.getId() + "日):   ￥" + maxItem.getAmount());
		SalesItem minItem = sidi.findValue(list, false);
		jlMin.setText(year + "年" + month + "月最小销售额(" + minItem.getId() + "日):   ￥" + minItem.getAmount());
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
		jlMax.setText(year + "年最大销售额(" + maxItem.getId() + "月):   ￥" + maxItem.getAmount());
		SalesItem minItem = sidi.findValue(list, false);
		jlMin.setText(year + "年最大销售额(" + minItem.getId() + "月):   ￥" + minItem.getAmount());
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
		jlMax.setText("自" + year + "年起最大销售额(" + maxItem.getId() + "年):   ￥" + maxItem.getAmount());
		SalesItem minItem = sidi.findValue(list, false);
		jlMin.setText("自" + year + "年起最小销售额(" + minItem.getId() + "年):   ￥" + minItem.getAmount());
		return dataset;
	}

	// 解决中文乱码问题
	private void adjustCharset(JFreeChart chart) {
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		CategoryAxis domainAxis = categoryplot.getDomainAxis();
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));

	}

	// 调整柱体显示问题
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

	// 调整了乱码，柱体显示和大小
	public ChartPanel getAdjustedHistogramPanel(JFreeChart chart, CategoryDataset dataset) {
		CategoryPlot plot = chart.getCategoryPlot();
		adjustCharset(chart);
		adjustDisplay(dataset, plot);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(w - 400, h - 350));
		return chartPanel;
	}
	
	//设置了折线图的显示属性
	public ChartPanel getAdjustedLinePanel(JFreeChart chart, CategoryDataset dataset) {
		adjustCharset(chart);
	    chart.setBackgroundPaint(Color.white);// 设置背景色   
        //获取绘图区对象  
        CategoryPlot plot = chart.getCategoryPlot();  
        plot.setBackgroundPaint(Color.LIGHT_GRAY); // 设置绘图区背景色  
        plot.setRangeGridlinePaint(Color.WHITE); // 设置水平方向背景线颜色  
        plot.setRangeGridlinesVisible(true);// 设置是否显示水平方向背景线,默认值为true  
        plot.setDomainGridlinePaint(Color.WHITE); // 设置垂直方向背景线颜色  
        plot.setDomainGridlinesVisible(true); // 设置是否显示垂直方向背景线,默认值为false  
          
          
        CategoryAxis domainAxis = plot.getDomainAxis();     
        domainAxis.setLabelFont(new Font("黑体", Font.PLAIN, 20)); // 设置横轴字体  
        domainAxis.setTickLabelFont(new Font("黑体", Font.PLAIN, 20));// 设置坐标轴标尺值字体  
        domainAxis.setLowerMargin(0.01);// 左边距 边框距离  
        domainAxis.setUpperMargin(0.06);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。  
        domainAxis.setMaximumCategoryLabelLines(2);  
          
        ValueAxis rangeAxis = plot.getRangeAxis();  
        rangeAxis.setLabelFont(new Font("黑体", Font.PLAIN, 20));   
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//Y轴显示整数  
        rangeAxis.setAutoRangeMinimumSize(1);   //最小跨度  
        rangeAxis.setUpperMargin(0.18);//上边距,防止最大的一个数据靠近了坐标轴。     
        rangeAxis.setLowerBound(0);   //最小值显示0  
        rangeAxis.setAutoRange(false);   //不自动分配Y轴数据  
        rangeAxis.setTickMarkStroke(new BasicStroke(1.6f));     // 设置坐标标记大小  
        rangeAxis.setTickMarkPaint(Color.BLACK);     // 设置坐标标记颜色  
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(w - 400, h - 350));
		return chartPanel;
	}
}
