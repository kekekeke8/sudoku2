package com.liren.net;

import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.liren.net.HttpDownloader;
import com.liren.sudoku.model.SudokuUpdateModel;
import com.liren.xml.SudokuHandler;

public class UpdateSudoku {
	private final String url = "http://1.shuoshuo.sinaapp.com/sudoku.xml";	
	private SudokuUpdateModel parse(String xml){
		SAXParserFactory saxParserFactory  = SAXParserFactory.newInstance();
		try{
			XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
			SudokuUpdateModel sudokuUpdateModel = new SudokuUpdateModel();
			SudokuHandler handler = new SudokuHandler(sudokuUpdateModel);
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new StringReader(xml)));
			return sudokuUpdateModel;
		}catch(Exception e){
			System.out.println(e.toString());
			return null;
		}		
	}
	
	public SudokuUpdateModel getSudokuUpdate(){
		String xml  = new HttpDownloader().download(url);
		SudokuUpdateModel result = this.parse(xml);
		return result;
	}
}
