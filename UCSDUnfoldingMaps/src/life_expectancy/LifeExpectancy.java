package life_expectancy;

import java.util.*;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class LifeExpectancy extends PApplet {
	UnfoldingMap map;
	Map<String, Float> lifeExpByCountry;
	
	List<Feature> countries;
	List<Marker> countryMarkers;
	
	public void setup() {
		size(800, 600);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		lifeExpByCountry = loadLifeExpectancyFromCSV("/Users/kasiabadiomac/Desktop/java-course-2/UCSDUnfoldingMaps/data/LifeExpectancyWorldBank.csv");
		//lifeExpByCountry = loadLifeExpectancyFromCSV("data/LifeExpectancyWorldBank.csv");
				
		countries = GeoJSONReader.loadData(this,  "/Users/kasiabadiomac/Desktop/java-course-2/UCSDUnfoldingMaps/data/countries.geo.json");
		//countries = GeoJSONReader.loadData(this,  "data/countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		shadeCountries();
	}
	
	public void draw() {
		map.draw();
	}
	
	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName){
		Map<String, Float> lifeExpMap = new HashMap<String, Float>();
		String[] rows = loadStrings(fileName);
		for (String row: rows) {
			String[] columns = row.split(",");
			//System.out.println(Arrays.toString(columns));
			if (columns.length > 6) {
				if (!columns[5].contains("..")) {
					float value = Float.parseFloat(columns[5]);
					lifeExpMap.put(columns[4], value);
					//System.out.println(columns[4] + " " + columns[5]);
				}
				
			}
		}
		return lifeExpMap;
	}
	
	private void shadeCountries() {
		for (Marker marker: countryMarkers) {
			String countryId = marker.getId();
			if (lifeExpByCountry.containsKey(countryId)) {
				float lifeExp = lifeExpByCountry.get(countryId);
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 25);
				marker.setColor(color(255-colorLevel, 100, colorLevel));
			} else {
				marker.setColor(color(150, 150, 150));
			}
		}
	}
}
