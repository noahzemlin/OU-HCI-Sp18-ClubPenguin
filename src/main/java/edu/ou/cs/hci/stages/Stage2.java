package edu.ou.cs.hci.stages;

import edu.ou.cs.hci.stages.frames.*;
import edu.ou.cs.hci.stages.panels.*;

public final class Stage2
{
	public static void main(String[] args)
	{
		//Create the Main Window at 50, 50
		new ClubPenguin(50, 50);
		
		//Create the Scenario Window at 300, 150
		new ScenarioFrame(300, 150);
	}
	
}

//******************************************************************************
