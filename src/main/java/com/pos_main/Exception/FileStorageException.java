package com.pos_main.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Title: FileStorageException.java. Company: www.codearson.com Copyright:
 * Copyright (c) 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Jan 6, 2022.
 * @version 1.0
 **/

@Getter
@Setter
@AllArgsConstructor
public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

}
