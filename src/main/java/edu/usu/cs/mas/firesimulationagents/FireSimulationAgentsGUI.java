package edu.usu.cs.mas.firesimulationagents;

import java.awt.Color;

import javax.swing.JFrame;

import com.vividsolutions.jts.io.ParseException;

import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.geo.GeomPortrayal;
import sim.portrayal.geo.GeomVectorFieldPortrayal;

public class FireSimulationAgentsGUI extends GUIState {
  
  private Display2D display;
  private JFrame displayFrame;
  
  private GeomVectorFieldPortrayal gvfpUtahWater = new GeomVectorFieldPortrayal();
  
  public FireSimulationAgentsGUI(SimState state) {
    super(state);
  }

  public FireSimulationAgentsGUI() throws ParseException {
    super(new FireSimulationAgents(System.currentTimeMillis()));
  }
  
  @Override
  public void init(Controller controller) {
    super.init(controller);
    display = new Display2D(FireSimulationAgents.WIDTH, FireSimulationAgents.HEIGHT, this);
    
    display.attach(gvfpUtahWater, "Roads", true);
    
    displayFrame = display.createFrame();
    controller.registerFrame(displayFrame);
    displayFrame.setVisible(true);
  }
  
  @Override
  public void start() {
    super.start();
    setupPortrayals();
  }
  
  private void setupPortrayals() {
    FireSimulationAgents fireSimulationAgents = (FireSimulationAgents) state;
    
    gvfpUtahWater.setField(fireSimulationAgents.gvfUtahWater);
    gvfpUtahWater.setPortrayalForAll(new GeomPortrayal(Color.BLUE,true));
    
    display.reset();
    display.setBackdrop(Color.WHITE);

    display.repaint();
  }
  
  public static void main(String[] args) {
    FireSimulationAgentsGUI fireSimulationAgentsGUI = null;
    try {
      fireSimulationAgentsGUI = new FireSimulationAgentsGUI();
      fireSimulationAgentsGUI.createController();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

}
