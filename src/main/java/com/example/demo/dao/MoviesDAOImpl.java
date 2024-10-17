package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Movies;

@Repository
public class MoviesDAOImpl implements IMoviesDAO {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public String addMovies(Movies movies) throws Exception {
		String query="INSERT INTO Movies VALUES(:movieName,:actorName,:actressName,:budget)";
		
		SqlParameterSource parameterSource=new MapSqlParameterSource()
				.addValue("movieName", movies.getMovieName())
				.addValue("actorName", movies.getActorName())
				.addValue("actressName",movies.getActressName())
				.addValue("budget", movies.getBudget());
		
		int update = namedParameterJdbcTemplate.update(query,parameterSource);
		if(update==0) {
			return "Failed to Add";
		}
		return "Movies Added Successfully";
	}

	@Override
	public String deleteMovies(String movieName) throws Exception {
		String query="DELETE FROM Movies WHERE movieName=:movieName";
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("movieName",movieName);
		if(namedParameterJdbcTemplate.update(query, parameterSource)==0) {
			return "Failed to Delete";
		}
		return "Deleted Successfully";
	}

	@Override
	public String updateMovies(String movieName, double budget) throws Exception {
		String query="UPDATE Movies SET budget=:budget WHERE movieName=:movieName";
		SqlParameterSource parameterSource=new MapSqlParameterSource() 
				.addValue("budget",budget)
				.addValue("movieName",movieName);
		
		if(namedParameterJdbcTemplate.update(query, parameterSource)==0) {
			return "Failed to Update";
		}
		return "Updated Successfully";
	}

	@Override
	public List<Movies> selectMovies(String actorName) throws Exception {
		String query="SELECT * FROM Movies WHERE actorName=:actorName";
		
		SqlParameterSource parameterSource=new MapSqlParameterSource()
				.addValue("actorName",actorName);
		
		
		List<Map<String, Object>> queryList = namedParameterJdbcTemplate.queryForList(query, parameterSource);
		
		List<Movies> movie=new ArrayList<>();
		
		for(Map<String, Object> m: queryList) {
			
			Movies m1=new Movies();
			m1.setMovieName((String) m.get("movieName"));
			
			m1.setActorName((String) m.get("actorName"));
			
			m1.setActressName((String) m.get("actressName"));
			
			Object movieObj= m.get("budget");
			
			if (movieObj instanceof BigDecimal) {
	            m1.setBudget(((BigDecimal) movieObj).doubleValue());
	        } else {
	            m1.setBudget(Double.valueOf((float) movieObj)); 
	        }
			
			movie.add(m1);
		}
		return movie;
	}

}
