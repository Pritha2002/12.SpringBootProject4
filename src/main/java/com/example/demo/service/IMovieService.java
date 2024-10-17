package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Movies;

public interface IMovieService {
	public String add(Movies movies) throws Exception;
	public String delete(String movieName) throws Exception;
	public String update(String movieName,double budget) throws Exception;
	public List<Movies> select(String actorName) throws Exception;
}
