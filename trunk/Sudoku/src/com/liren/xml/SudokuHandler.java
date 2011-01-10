package com.liren.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.liren.sudoku.model.SudokuModel;
import com.liren.sudoku.model.SudokuUpdateModel;

public class SudokuHandler extends DefaultHandler {
	private SudokuUpdateModel result = null;
	private SudokuModel model = null;
	private List<SudokuModel> models = new ArrayList<SudokuModel>();
	
	private String tagName = null;

	public SudokuHandler(SudokuUpdateModel value){
		result = value;
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String temp = new String(ch,start,length);
		if(tagName.equals("id")){
			model.setId(Integer.parseInt(temp));
		}else if(tagName.equals("type")){
			model.setType(Integer.parseInt(temp));
		}else if(tagName.equals("level")){
			model.setLevel(Integer.parseInt(temp));
		}else if(tagName.equals("tipcount")){
			model.setTipcount(Integer.parseInt(temp));
		}else if(tagName.equals("data")){
			model.setData(temp);
		}else if(tagName.equals("version")){
			result.setVersion(Integer.parseInt(temp));
		}
	}

	@Override
	public void endDocument() throws SAXException {
		result.setSudokus(models);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(localName.equals("sudoku")){
			models.add(model);
		}
		tagName = "";		
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		this.tagName =  localName;
		if(tagName.equals("sudoku")){
			this.model = new SudokuModel();
		}	
	}
}
