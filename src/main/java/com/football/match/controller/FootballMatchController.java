package com.football.match.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.football.match.model.Country;
import com.football.match.model.League;


@RestController
public class FootballMatchController {
	
;
	
	@RequestMapping(value="/getCountries", method=RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Country> getCounties() {
		
		List<Country> countries = getAllCounties();
		
		return countries;
	}
	
	@RequestMapping(value="/getLeagues", method=RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<League> getLeagues(@RequestParam(name = "country_id") String country_id) {
		
		List<League> leagues = getAllLeagues(country_id);
		
		return leagues;
	}
	
	private static List<Country>  getAllCounties()
	{
	    final String uri = "https://apiv2.apifootball.com/?action=get_countries&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<List<Country>> response = restTemplate.exchange(
	    		uri,
	      HttpMethod.GET,
	      null,
	      new ParameterizedTypeReference<List<Country>>(){});
	    List<Country> countries = response.getBody();
	    return countries;
	} 
	
	
	private static List<League>  getAllLeagues(String cId)
	{
	    final String uri = "https://apiv2.apifootball.com/?action=get_leagues&country_id=" + cId +"&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<List<League>> response = restTemplate.exchange(
	    		uri,
	      HttpMethod.GET,
	      null,
	      new ParameterizedTypeReference<List<League>>(){});
	    List<League> leagues = response.getBody();
	    return leagues;
	}

}
