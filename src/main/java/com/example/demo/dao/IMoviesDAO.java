package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Movies;

public interface IMoviesDAO {
	public String addMovies(Movies movies) throws Exception;
	public String deleteMovies(String movieName) throws Exception;
	public String updateMovies(String movieName,double budget) throws Exception;
	public List<Movies> selectMovies(String actorName) throws Exception;
}
