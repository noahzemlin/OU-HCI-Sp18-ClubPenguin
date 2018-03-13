package edu.ou.cs.hci.stages.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Game {
	
	private static Random random = new Random();
	
	private String name;
	private String description;
	private Calendar releaseDate;
	private ArrayList<String> tags;

	public Game() {
		name = "Dummy game #" + random.nextInt(200);
		description = "Dummy description";
		releaseDate = new GregorianCalendar();
		tags = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}

	public Game setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Game setDescription(String description) {
		this.description = description;
		return this;
	}

	public Calendar getReleaseDate() {
		return releaseDate;
	}
	
	public Game setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
		return this;
	}
	
	public void addTag(String tag) {
		if (!tag.isEmpty()) {
			tag = tag.toLowerCase();
			tags.add(tag);
		}
	}
	
	public void removeTag(String tag) {
		tag = tag.toLowerCase();
		if (tags.contains(tag)) {
			tags.remove(tag);
		}
	}
	
	public boolean hasTag(String tag) {
		tag = tag.toLowerCase();
		return tag.contains(tag);
	}

	public String getTagsAsString() {
		if (tags.size() == 0) return "";
		
		String out = tags.get(0);
		
		for (int i=1; i<tags.size(); i++) {
			out += ", " + tags.get(i);
		}
		
		return out;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
