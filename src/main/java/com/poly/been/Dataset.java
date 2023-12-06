package com.poly.been;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Dataset {
	private String label;
    private List<Object> data;
    private String backgroundColor;
    private String borderColor;
}	
