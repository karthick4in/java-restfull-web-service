package com.ick.acclist.restfulwebserviceacc.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException { 
	private static final long serialVersionUID = 1L;

		public UserNotFoundException(String msg) {
			super(msg);
			// TODO Auto-generated constructor stub
		}
}
