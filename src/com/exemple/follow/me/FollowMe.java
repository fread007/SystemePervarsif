package com.exemple.follow.me;

import fr.liglab.adele.icasa.device.presence.PresenceSensor;
import fr.liglab.adele.icasa.device.light.BinaryLight;
import fr.liglab.adele.icasa.device.temperature.Heater;
import fr.liglab.adele.icasa.device.temperature.Thermometer;
import fr.liglab.adele.icasa.device.doorWindow.DoorWindowSensor;
import fr.liglab.adele.icasa.device.light.Photometer;
import fr.liglab.adele.icasa.device.DeviceListener;
import fr.liglab.adele.icasa.device.GenericDevice;

import java.util.*;

public class FollowMe implements DeviceListener{
	
	//Definition des listes pour chaque type de sensor

	/** Field for presenceSensors dependency */
	private PresenceSensor[] presenceSensors;
	/** Field for binaryLights dependency */
	private BinaryLight[] binaryLights;
	/** Field for heaters dependency */
	private Heater[] heaters;
	/** Field for thermometers dependency */
	private Thermometer[] thermometers;
	/** Field for doorWindows dependency */
	private DoorWindowSensor[] doorWindows;
	/** Field for photometers dependency */
	private Photometer[] photometers;
	
	//definition des valeur static
	
	public static final String LOCATION_PROPERTY_NAME = "Location";
	public static final String LOCATION_UNKNOWN = "unknown";
	
	
	//Methodes bind et unbind pour chaque sensor

	/** Bind Method for doorWindows dependency */
	public void bindDoorWindowSensors(DoorWindowSensor doorWindowSensor, Map properties) {
		System.out.println("Bind DoorWindows Sensor");
	}

	/** Unbind Method for doorWindows dependency */
	public void unbindDoorWindowSensors(DoorWindowSensor doorWindowSensor, Map properties) {
		System.out.println("unBind DoorWindows Sensor");
	}

	/** Bind Method for thermometers dependency */
	public void bindThermometers(Thermometer thermometer, Map properties) {
		System.out.println("Bind Thermometers Sensor");
	}

	/** Unbind Method for thermometers dependency */
	public void unbindThermometers(Thermometer thermometer, Map properties) {
		System.out.println("unBind Thermometer Sensor");
	}

	/** Bind Method for heaters dependency */
	public void bindHeaters(Heater heater, Map properties) {
		System.out.println("Bind Heaters Sensor");
	}

	/** Unbind Method for heaters dependency */
	public void unbindHeaters(Heater heater, Map properties) {
		System.out.println("unBind Heaters Sensor");
	}

	/** Bind Method for binaryLights dependency */
	public void bindBinaryLights(BinaryLight binaryLight, Map properties) {
		System.out.println("Bind BinaryLight Sensor");
	}

	/** Unbind Method for binaryLights dependency */
	public void unbindBinaryLights(BinaryLight binaryLight, Map properties) {
		System.out.println("unBind BinaryLight Sensor");
	}

	/** Bind Method for presenceSensors dependency */
	public synchronized void bindPresenceSensor(PresenceSensor presenceSensor, Map properties) {
		presenceSensor.addListener(this);
	}

	/** Unbind Method for presenceSensors dependency */
	public synchronized void unbindPresenceSensor(PresenceSensor presenceSensor, Map properties) {
		presenceSensor.removeListener(this);
	}

	/** Bind Method for photometers dependency */
	public void bindPhotometers(Photometer photometer, Map properties) {
		System.out.println("Bind Photometers Sensor");
	}

	/** Unbind Method for photometers dependency */
	public void unbindPhotometers(Photometer photometer, Map properties) {
		System.out.println("unBind Photometers Sensor");
	}
	
	//Methodes pour le cycle de vie de l'application

	/** Component Lifecycle Method */
	public void stop() {
		for(PresenceSensor sensor : presenceSensors){
  	      sensor.removeListener(this);
  	   }
	}

	/** Component Lifecycle Method */
	public void start() {
		System.out.println("System starting ...");
	}
	
	//Methode du Device Listner
	
	private synchronized List<BinaryLight> getBinaryLightFromLocation(String location) {
		List<BinaryLight> binaryLightsLocation = new ArrayList<BinaryLight>();
		for (BinaryLight binLight : binaryLights) {
			if (binLight.getPropertyValue(LOCATION_PROPERTY_NAME).equals(location)) {
				binaryLightsLocation.add(binLight);
	        }
		}
		return binaryLightsLocation;
	}

	@Override
	public void deviceAdded(GenericDevice arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deviceEvent(GenericDevice arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void devicePropertyAdded(GenericDevice arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void devicePropertyModified(GenericDevice device, String propertyName, Object oldValue, Object newValue) {
		System.out.println("debut modify");
		PresenceSensor changingSensor = (PresenceSensor) device;
		// check the change is related to presence sensing
		if (propertyName.equals(PresenceSensor.PRESENCE_SENSOR_SENSED_PRESENCE)) {
			// get the location where the sensor is:
			String detectorLocation = (String) changingSensor.getPropertyValue(LOCATION_PROPERTY_NAME);
			// if the location is known :
			if (!detectorLocation.equals(LOCATION_UNKNOWN)) {
					// get the related binary lights
					List<BinaryLight> sameLocationLigths = getBinaryLightFromLocation(detectorLocation);
					for (BinaryLight binaryLight : sameLocationLigths) {
						// and switch them on/off depending on the sensed presence
						if(changingSensor.getSensedPresence()){
							binaryLight.turnOn();
						}else{
							binaryLight.turnOff();
						}
					}
			}
        }
	}

	@Override
	public void devicePropertyRemoved(GenericDevice arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deviceRemoved(GenericDevice arg0) {
		// TODO Auto-generated method stub
		
	}

}

