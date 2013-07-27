package logic;

/**
 * @author john
 * version 1
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;

import org.joda.time.DateTime;

import state.MainState;
import utill.ConvertDateToDateTime;
import utill.GeneralUtill;
import utill.InputChecker;
import gui.GuiUtill;
import gui.View;

public class Controller {
	
	Model mod;
	View view;
	MainState state;
	
	public Controller(Model mod, View view, MainState state)
	{
		this.mod 	= mod;
		this.view 	= view;
		this.state	= state;
		
		//Load all the details from the state
		loadFromState();
		
		
		//Connect action listeners
		view.applyActionListener(new applyAction() );
	}
	
	/**
	 * Loads the table headings
	 */
	private void loadFromState()
	{
		view.setSaveTableData(DataForTable.getSaveTableHeadings(), state.getTableContents());
	}
	
	/**
	 * saves changes to the state of the program
	 */
	private void saveToState()
	{
		
	}
	
	
	
	
	
	////Action listeners//////
	/////////////////////////
	
	/**
	 * Action event for the apply button. Get all the data entered
	 * @author john
	 *
	 */
	class applyAction implements ActionListener{

		public void actionPerformed(ActionEvent e) 
		{
			BigDecimal beginAmount;
			BigDecimal goal;
			
			//Gets the dates from the GUI
			DateTime beginDate 	= ConvertDateToDateTime.convertDateToDateTime(view.getBeginDate());
			DateTime endDate	= ConvertDateToDateTime.convertDateToDateTime(view.getEndDate());	
			
			//Check the inputs are big decimal
			if(InputChecker.checkInputs(view.getStartingAmount(), view.getGoalAmount(), beginDate, endDate))
			{			
				//Get the data to store			
				beginAmount = GeneralUtill.convertStringToBigDecimal(view.getStartingAmount()); 
				goal 		= GeneralUtill.convertStringToBigDecimal(view.getGoalAmount());
				
				//Get the weeks between the two dates
				int weeks = mod.getWeeksStarting(beginDate, endDate);
				
				//Display the average amount
				view.setAverageAmount(mod.averageAmountWeekly(weeks, beginAmount, goal));
				
				//Save the state
				saveToState();
				
			}			
		}
		
	}
	

}
