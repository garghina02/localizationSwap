package com.adp.localization.test;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;

public class testPDF {
	
	/* String pdfFilePath = "C:\\TEMP\\WOTC_1.pdf";
	 Document pdf = PDF.open(pdfFilePath);
	    StringBuilder text = new StringBuilder(1024);
	    PDF.Feature
	    pdf.close();
	    System.out.println(text);*/
	    
	    public static void main (String[] args) throws java.io.IOException {
	       /* String pdfFilePath = ("C:\\TEMP\\WOTC_1.pdf");
	        System.out.println("✔");
	     
	        Document pdf = PDF.open(pdfFilePath);
	        StringBuilder text = new StringBuilder(2048);
	        pdf.pipe(new OutputTarget(text));
	        pdf.close();
	        System.out.println(text);*/
	    	System.out.println("✔");
	        PdfReader reader = new PdfReader("C:\\TEMP\\WOTC_1.pdf");
	      

	        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
	        

	        TextExtractionStrategy strategy = null;

	        for(int i = 1; i <= reader.getNumberOfPages(); i++) {

	               strategy = parser.processContent(i,new SimpleTextExtractionStrategy());
	           

	               System.out.println(strategy.getResultantText());
	               String page = PdfTextExtractor.getTextFromPage(reader, i, new SimpleTextExtractionStrategy());
	               System.out.println("Page Content:\n\n"+page+"\n\n");
	               System.out.println("Is this document tampered: "+reader.isTampered());
	               System.out.println("Is this document encrypted: "+reader.isEncrypted());
	               break;

	        }
	      }

}
