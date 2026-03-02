    package com.exemple.binary.follow.me;
     
    import fr.liglab.adele.icasa.device.DeviceListener;
	import fr.liglab.adele.icasa.device.GenericDevice;
	import fr.liglab.adele.icasa.device.light.BinaryLight;
	import fr.liglab.adele.icasa.device.presence.PresenceSensor;
    import java.util.*;
     
    public class BinaryFollowMeImpl implements DeviceListener{
     
      /** Field for binaryLights dependency */
      private BinaryLight[] binaryLights;
     
      /** Field for presenceSensors dependency */
      private PresenceSensor[] presenceSensors;
     
      /**
       * Bind Method for binaryLights dependency.
       * This method is not mandatory and implemented for debug purpose only.
       */
      public void bindBinaryLight(BinaryLight binaryLight, Map<Object, Object> properties) {
        System.out.println("\n\n\nbind binary light " + binaryLight.getSerialNumber());
      }
      
      public void bindBinaryLights() {
    	  System.out.println("OUISS");
      }
       
      /**
       * Unbind Method for binaryLights dependency. 
       * This method is not mandatory and implemented for debug purpose only.
       */
      public void unbindBinaryLight(BinaryLight binaryLight, Map<Object, Object> properties) {
        System.out.println("unbind binary light " + binaryLight.getSerialNumber());
      }
       
      /** 
       * Bind Method for PresenceSensors dependency.
       * This method will be used to manage device listener.
       */
      public synchronized void bindPresenceSensor(PresenceSensor presenceSensor, Map properties) {
    	  // Add the listener to the presence sensor
    	  presenceSensor.addListener(this); //..
    	}
       
      /** 
       * Unbind Method for PresenceSensors dependency.
       * This method will be used to manage device listener.
       */
      public synchronized void unbindPresenceSensor(PresenceSensor presenceSensor, Map properties) {
    	  // Remove the listener from the presence sensor
    	  presenceSensor.removeListener(this); //..
    	}
     
      public synchronized void stop() {
    	   for(PresenceSensor sensor : presenceSensors){
    	      sensor.removeListener(this);
    	   }
    	}
       
      /** Component Lifecycle Method */
      public void start() {
         System.out.println("Component is starting...");
      }
      
      /**
       * The name of the LOCATION property
       */
      public static final String LOCATION_PROPERTY_NAME = "Location";
       
      /**
       * The name of the location for unknown value
       */
      public static final String LOCATION_UNKNOWN = "unknown";
      
      /**
       * This method is part of the DeviceListener interface and is called when a
       * subscribed device property is modified.
       * 
       * @param device
       *            is the device whose property has been modified.
       * @param propertyName
       *            is the name of the modified property.
       * @param oldValue
       *            is the old value of the property
       * @param newValue
       *            is the new value of the property
       */
      
      /**
       * Return all BinaryLight from the given location
       * 
       * @param location
       *            : the given location
       * @return the list of matching BinaryLights
       */
      private synchronized List<BinaryLight> getBinaryLightFromLocation(
          String location) {
        List<BinaryLight> binaryLightsLocation = new ArrayList<BinaryLight>();
        for (BinaryLight binLight : binaryLights) {
          if (binLight.getPropertyValue(LOCATION_PROPERTY_NAME).equals(
              location)) {
            binaryLightsLocation.add(binLight);
          }
        }
        return binaryLightsLocation;
      }
      
      
      
      /**
       * This method is part of the DeviceListener interface and is called when a
       * subscribed device property is modified.
       * 
       * @param device
       *            is the device whose property has been modified.
       * @param propertyName
       *            is the name of the modified property.
       * @param oldValue
       *            is the old value of the property
       * @param newValue
       *            is the new value of the property
       */
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
	  public void devicePropertyRemoved(GenericDevice arg0, String arg1) {
		// TODO Auto-generated method stub
		
	  }

	  @Override
	  public void deviceRemoved(GenericDevice arg0) {
		// TODO Auto-generated method stub
		
	  }
    }