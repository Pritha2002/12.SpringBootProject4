package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.entity.Movies;
import com.example.demo.service.IMovieService;

@Controller
public class MovieController {
	
	@Autowired
	IMovieService service;
	
	public String addMovieDetails(Movies movies) throws Exception{
		return service.add(movies);
	}
	
	public String deleteMovieDetails(String movieName) throws Exception{
		return service.delete(movieName);
	}
	
	public String updateMovieDetails(String movieName, double budget) throws Exception {
		return service.update(movieName, budget);
	}
	
	public List<Movies> selectMovieDetails(String actorName) throws Exception {
		return service.select(actorName);
	}
}
