package earthquake_module;

import java.util.*;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthquakeCityMap extends PApplet{
	
	private UnfoldingMap map;
	
	public void setup() {
		size(950, 600);
		map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		Location valLocChile = new Location(-38.14f, -73.03f);
		PointFeature valEqChile = new PointFeature(valLocChile);
		valEqChile.addProperty("title", "Valdivia, Chile");
		valEqChile.addProperty("magnitude", "9.5");
		valEqChile.addProperty("date", "May 22, 1960");
		valEqChile.addProperty("year", "1960");
		
		Location valLocAlaska = new Location(61.02f, -147.65f);
		PointFeature valEqAlaska = new PointFeature(valLocAlaska);
		valEqAlaska.addProperty("title", "Great Alaska Earthquake");
		valEqAlaska.addProperty("magnitude", "9.2");
		valEqAlaska.addProperty("date", "March 28, 1964");
		valEqAlaska.addProperty("year", "1964");
		
		Location valLocSumatra = new Location(3.30f, 95.78f);
		PointFeature valEqSumatra = new PointFeature(valLocSumatra);
		valEqSumatra.addProperty("title", "Off the West Coast of Northern Sumatra");
		valEqSumatra.addProperty("magnitude", "9.1");
		valEqSumatra.addProperty("date", "December 26, 2004");
		valEqSumatra.addProperty("year", "2004");
		
		Location valLocJapan = new Location(38.322f, 142.369f);
		PointFeature valEqJapan = new PointFeature(valLocJapan);
		valEqJapan.addProperty("title", "Near the East Coast of Honshu, Japan");
		valEqJapan.addProperty("magnitude", "9.0");
		valEqJapan.addProperty("date", "March 11, 2011");
		valEqJapan.addProperty("year", "2011");
		
		Location valLocKamchatka = new Location(52.76f, 160.06f);
		PointFeature valEqKamchatka = new PointFeature(valLocKamchatka);
		valEqKamchatka.addProperty("title", "Kamchatka");
		valEqKamchatka.addProperty("magnitude", "9.0");
		valEqKamchatka.addProperty("date", "November 04, 1952");
		valEqKamchatka.addProperty("year", "1952");
		
		SimplePointMarker valMkChile = new SimplePointMarker(valLocChile, valEqChile.getProperties());
		SimplePointMarker valMkAlaska = new SimplePointMarker(valLocAlaska, valEqAlaska.getProperties());
		SimplePointMarker valMkSumatra = new SimplePointMarker(valLocSumatra, valEqSumatra.getProperties());
		SimplePointMarker valMkJapan = new SimplePointMarker(valLocJapan, valEqJapan.getProperties());
		SimplePointMarker valMkKamchatka = new SimplePointMarker(valLocJapan, valEqKamchatka.getProperties());
		map.addMarker(valMkChile);
		map.addMarker(valMkAlaska);
		map.addMarker(valMkSumatra);
		map.addMarker(valMkJapan);
		map.addMarker(valMkKamchatka);
	
		List<PointFeature> bigEqs = new ArrayList<PointFeature>();
		bigEqs.add(valEqChile);
		bigEqs.add(valEqAlaska);
		bigEqs.add(valEqSumatra);
		bigEqs.add(valEqJapan);
		bigEqs.add(valEqKamchatka);
		
		List<Marker> markers = new ArrayList<Marker>();
		for (PointFeature eq: bigEqs) {
			markers.add(new SimplePointMarker(eq.getLocation(), eq.getProperties()));
		}
		map.addMarkers(markers);
		
		int yellow = color(255, 255, 0);
		int gray = color(150, 150, 150);
		
		for (Marker mk: markers) {
			mk.setColor(yellow);	
		}
	}
	
	
	public void draw() {
		background(10);
		map.draw();
		addKey();
	}
	
	private void addKey() {
		
	}
}
