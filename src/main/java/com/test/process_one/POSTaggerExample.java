package com.test.process_one;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import java.util.*;

	public class POSTaggerExample {
		 
		public void main(String[] args) throws IOException {
			postagging();
		}
		
		public HashMap<Integer,HashMap<String,String>> postagging(){
			InputStream tokenModelIn = null;
			InputStream posModelIn = null;
			
			HashMap<Integer,HashMap<String,String>> sentenceList = new HashMap<Integer,HashMap<String,String>>();
		HashMap<String, String> wordList = new HashMap<String,String>();
			
			try {
				
				SentencedetectionExample instanceOfClassA = new SentencedetectionExample();
				
				String sentences[] = instanceOfClassA.sentenceDetect();
				 for(int i=0;i<sentences.length;i++){
					 
			            String sentence = sentences[i];
			         
				
						tokenModelIn = new FileInputStream("en-token.bin");
						TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
						Tokenizer tokenizer = new TokenizerME(tokenModel);
						String tokens[] = tokenizer.tokenize(sentence);

				
						posModelIn = new FileInputStream("en-pos-maxent.bin");
				
						POSModel posModel = new POSModel(posModelIn);
				
						POSTaggerME posTagger = new POSTaggerME(posModel);
					
						String tags[] = posTagger.tag(tokens);
						int sentenceId = 1+i;
					
						System.out.println("Sentence id: " + sentenceId + "\n---------------");
						System.out.println("Sentence is: " + sentences[i]+ "\n---------------");
						System.out.println("Token\t:\tTag\t\n---------------------------------------------");
						
						for(int j=0;j<tokens.length;j++){
 							System.out.println(tokens[j]+"\t:\t"+tags[j]);
//							String wordWithTag = tokens[j]+" "+tags[j];
//							System.out.println(wordWithTag);
 							
 							
 							wordList.put(tokens[j], tags[j]);
						}
						
						sentenceList.put(sentenceId, wordList);

			        }
				
			}
			catch (IOException e) {
				// Model loading failed, handle the error
				e.printStackTrace();
			}
			finally {
				if (tokenModelIn != null) {
					try {
						tokenModelIn.close();
					}
					catch (IOException e) {
					}
				}
				if (posModelIn != null) {
					try {
						posModelIn.close();
					}
					catch (IOException e) {
					}
				}
			}
			return sentenceList;
			
		}
	}

