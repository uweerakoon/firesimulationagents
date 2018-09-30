package edu.usu.cs.mas.firesimulationagents;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;

import sim.engine.SimState;
import sim.field.geo.GeomVectorField;
import sim.io.geo.ShapeFileImporter;

public class FireSimulationAgents extends SimState {
  
  private static final long serialVersionUID = -4554882816749973618L;
  private static final Logger LOGGER = Logger.getLogger(FireSimulationAgents.class);
  
  public static final int WIDTH = 600; 
  public static final int HEIGHT = 600;
  
  public GeomVectorField gvfUtahWater = new GeomVectorField(WIDTH, HEIGHT);
  
  public FireSimulationAgents(long seed) {
    super(seed);
    try {
      File utahWaterShapeFile = new File("/Users/udaraweerakoon/managedisaster/managedisasterproject/Disaster_Management/GeoMason/Utah_ArcGIS_shapefiles/utah_water/", "utah_water.shp");
      LOGGER.info("Absolute path: "+utahWaterShapeFile.getAbsolutePath());
      if(utahWaterShapeFile.createNewFile()) {
        LOGGER.info("file is created");
      }
      else {
        LOGGER.info("file is already exists");
      }
      
      URL utahWaterShapeUrl = utahWaterShapeFile.toURI().toURL();
      ShapeFileImporter.read(utahWaterShapeUrl, gvfUtahWater);
      LOGGER.info("Read the water shape file");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void finish() {
    super.finish();
  }
  
  @Override
  public void start() {
      super.start();
  }

	public static void main(String[] args) {
	}
}
