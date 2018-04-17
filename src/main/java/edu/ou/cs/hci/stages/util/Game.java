package edu.ou.cs.hci.stages.util;

import java.util.Random;

public class Game {
	
	private static Random random = new Random();
	
	private String name;
	private String description;
	private String developers;
	private String publishers;
	private String genres;
	private String tags;
	private String picture;
	private String location;

	public Game() {
		setName("Dummy game #" + random.nextInt(200));
		setDescription("Dummy description");
		setDevelopers("");
		setPublishers("");
		setGenres("");
		setTags("");
		setPicture("");
		setLocation("");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDevelopers() {
		return developers;
	}

	public void setDevelopers(String developers) {
		this.developers = developers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublishers() {
		return publishers;
	}

	public void setPublishers(String publishers) {
		this.publishers = publishers;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public String[] getCSVData() {
		String[] data = {name, description, developers, publishers, genres, tags, picture, location};
		return data;
	}

}
