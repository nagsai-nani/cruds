//package com.practice.crud.roughwork;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class RoughWork{
//public static void main(String[] args) {
//    // define the directory that contains the text files
//    String dir = "C:\\Users\\harry\\Downloads\\crud\\crud\\src\\main\\java\\com\\practice\\crud";
//    Path dirPath = Paths.get(dir);
//    // predefine some lines to be appended to every file
//    List<String> linesToBeAppended = new ArrayList();
//    linesToBeAppended.add("Hello new line in the file!");
//
//    try {
//        // go through all files in the directory (tested with .txt files only)
//        Files.list(dirPath)
//            // filter only files 
//            .filter(Files::isRegularFile
//            		)
//            .forEach(filePath -> {
//                try {
//                    // append the predefined text to the file
//                    Files.write(filePath, linesToBeAppended, StandardOpenOption.APPEND
//                     		);
//                   
//                } catch (IOException e) {
//                    System.err.println("Could not append text to file " 
//                            + filePath.toAbsolutePath().toString());
//                    e.printStackTrace();
//                }
//            });
//    } catch (IOException e) {
//        System.err.println("Could not list files in " 
//                + dirPath.toAbsolutePath().toString());
//        e.printStackTrace();
//    }
//}
////	
////	 public static void main(String[] args) {
////		    ArrayList<Integer> numbers = new ArrayList<Integer>();
////		    numbers.add(5);
////		    numbers.add(9);
////		    numbers.add(8);
////		    numbers.add(1);
////		    numbers.forEach( (n) -> { System.out.println(n); 
////		    for (int i = 0; i < 9; i++) {
////				System.out.println(i);
////				
////			}
////		    
////		    
////		    } );
////		  }
////	
//	
//	
//}