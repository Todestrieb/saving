package gui;

/**
 * @author john
 * version 1
 */


import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import com.sun.xml.internal.messaging.saaj.util.CharReader;
import com.toedter.calendar.JDateChooser;
import Charts.BarChartDemo1;
import Charts.LineGraph;
import net.miginfocom.swing.MigLayout;

public class View extends JFrame{
	//Instance of the GUI
	public static View currentInstance;
	
	//Panels
	private JPanel mainPanel;
	
	//Labels
	private JLabel beginDate;
	private JLabel endDate;
	private JLabel startAmount;
	private JLabel goal;
	private JLabel payPeriod;
	private JLabel amountToSave;
	
	//TextField
	private JTextField tfStartAmount;
	private JTextField tfGoal;
	private JTextField tfAmountToSave;
	
	//Calendar stuff
	private JDateChooser calBegin;
	private JDateChooser calEnd;
	
	//Menu bar
	JMenuBar mainMenu;
	JMenu file;
	JMenuItem save;
	JMenuItem load;
	
	//Radio buttons
	private JRadioButton weekly;
	private JRadioButton fortnightly;
	private JRadioButton monthly;
	private JRadioButton yearly;
	
	//Group buttons
	private ButtonGroup timeFrame;
	
	//JTable 
	private JTable amountUpdate;
	
	//JButton
	private JButton apply;
	
	//ChartPanel
	private ChartPanel chartPanel;
	
	
	
	public View()
	{		
		//Create the the components
		mainPanel 	= new JPanel(new MigLayout());
		beginDate	= new JLabel("Begin date:");
		endDate		= new JLabel("End date:");
		calBegin	= new JDateChooser();
		calEnd		= new JDateChooser();
		mainMenu    = new JMenuBar();
		file		= new JMenu("File");
		save		= new JMenuItem("Save");
		load		= new JMenuItem("Load");
		weekly		= new JRadioButton("Weekly");
		fortnightly = new JRadioButton("Fortnightly");
		monthly		= new JRadioButton("Monthly");
		yearly		= new JRadioButton("Yearly");
		timeFrame	= new ButtonGroup();
		startAmount = new JLabel("Starting amount:");
		goal		= new JLabel("Goal amount:");
		payPeriod	= new JLabel("Payment period:");
		tfStartAmount 	= new JTextField();
		tfGoal			= new JTextField();
		amountUpdate 	= new JTable();
		amountToSave	= new JLabel("Amount to save each pay day in order to reach your target is: ");
		tfAmountToSave	= new JTextField();
		apply			= new JButton("Apply");
		
		//Add the radio buttons to the group
		timeFrame.add(weekly);
		timeFrame.add(fortnightly);
		timeFrame.add(monthly);
		timeFrame.add(yearly);
		
		
		//Chart demo
		CategoryDataset dataset = LineGraph.createSampleDataset();
		JFreeChart chart = LineGraph.createChart(dataset);
        chartPanel = new ChartPanel(chart);
                
        //Set the main layout
      	setLayout(new MigLayout());
      	
      	//Create a menu bar
      	setJMenuBar(mainMenu);
      	mainMenu.add(file);
      	file.add(save);
      	file.add(load);
      	
      	//Set up table with scrollPane
      	JScrollPane amountTable = new JScrollPane(amountUpdate);
        		
        //Add all the stuff to the main panel
        mainPanel.add(beginDate, 		"split 2");
        mainPanel.add(calBegin, 		"align left, wrap");
        mainPanel.add(endDate, 			"split 2");
        mainPanel.add(calEnd, 			"align left, Wrap");
        mainPanel.add(startAmount, 		"split 2");
        mainPanel.add(tfStartAmount, 	"grow, wrap");
        mainPanel.add(goal, 			"align right, split");
        mainPanel.add(tfGoal, 			"grow, wrap");
        mainPanel.add(payPeriod);
        mainPanel.add(weekly,			"cell 0 4");
        mainPanel.add(fortnightly,		"cell 0 4");
        mainPanel.add(monthly, 			"cell 0 4");
        mainPanel.add(yearly, 			"cell 0 4, wrap");
        mainPanel.add(amountToSave,		"split 2");
        mainPanel.add(tfAmountToSave, 	"grow");      
        mainPanel.add(apply,			"south");        
        mainPanel.add(amountTable, 		"south");
        
        //Set the group buttons to a default
        weekly.setSelected(true);
                        		
		//Add the panels to the main panel
		add(mainPanel);
		add(chartPanel);
		
		//Load the icons
		setIconImages(IconLoader.getIcons());
				
		//Frame stuff
		setTitle("Budget");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);//Loads the window in the center		
	}
			
	
	//Getters//
	//////////
	//Get the dates chosen	
	public Date getBeginDate()
	{
		return calBegin.getDate();
	}
	
	public Date getEndDate()
	{
		return calEnd.getDate();
	}
	
	
	//Get the Amounts chosen
	public String getStartingAmount()
	{
		return tfStartAmount.getText();
	}
	
	public String getGoalAmount()
	{
		return tfGoal.getText();
	}
	
	public int getpaymentTimeChoice()
	{
		if(weekly.isSelected())
		{
			return 1;
		}
		else if(fortnightly.isSelected())
		{
			return 2;
		}
		else if(monthly.isSelected())
		{
			return 3;
		}
		else
		{
			return 4;
		}
	}
	
	public DefaultTableModel getTableModel()
	{
		return (DefaultTableModel) amountUpdate.getModel();
	}
	
			
	
	
	//Setters//
	//////////
	public void setAverageAmount(BigDecimal average)
	{
		tfAmountToSave.setText(String.valueOf(average));
	}
	
	public void setData(Date beginDate, Date endDate, BigDecimal startAmount, BigDecimal endAmount)
	{
		calBegin.setDate(beginDate);
		calEnd.setDate(endDate);
		tfStartAmount.setText(String.valueOf(startAmount));
		tfGoal.setText(String.valueOf(endAmount));
	}
	
	public void setChart(JFreeChart chart)
	{
		chartPanel.setChart(chart);
	}
	
	

	//Table manipulation
	public void setSaveTableData(String[] columnNames, Object[][] data)
	{
		DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
		amountUpdate.setModel(dtm);
	}
	
	
	//ActionListners//
	/////////////////
	public void applyActionListener(ActionListener e)
	{
		apply.addActionListener(e);
	}
	
	public void saveActionListener(ActionListener e)
	{
		save.addActionListener(e);
	}
	
	public void loadActionListener(ActionListener e)
	{
		load.addActionListener(e);
	}
	
	public void saveTableActionListener(FocusListener e)
	{
		amountUpdate.addFocusListener(e);
	}
	
	
	


}
