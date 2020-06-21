package com.example.oppoUser.config.util;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2633161817290176204L;
	
	private final String jwttoken;
	public JwtResponse(String jwttoken) {
	this.jwttoken = jwttoken;
	}
	public String getToken() {
	return this.jwttoken;
	}

}
