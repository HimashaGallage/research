package com.test.process_one;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
 
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
 
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentencedetectionExample {
	 public static void main(String[] args) {
	        try {
	            new SentencedetectionExample().sentenceDetect();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public String[] sentenceDetect() throws InvalidFormatException,  IOException {
	        String paragraph = "A country's debt crisis affects the world through a loss of investor confidence and systemic financial instability. ";
	 
	        
	        InputStream is = new FileInputStream("en-sent.bin");
	        SentenceModel model = new SentenceModel(is);
	        SentenceDetectorME sdetector = new SentenceDetectorME(model);
	        String sentences[] = sdetector.sentDetect(paragraph);
	 
	        // print the sentences detected, to console
//	        for(int i=0;i<sentences.length;i++){
//	            System.out.println(sentences[i]);
//	        }
	   
	        return sentences;
	    }
}
