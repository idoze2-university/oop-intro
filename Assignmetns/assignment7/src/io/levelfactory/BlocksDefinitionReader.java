package io.levelfactory;

import java.io.IOException;
import java.io.BufferedReader;

public class BlocksDefinitionReader {

  public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) throws IOException {
    BlocksFromSymbolsFactory factory = new BlocksFromSymbolsFactory();

    BufferedReader br = new BufferedReader(reader);
    String  line = "";
    while ((line = br.readLine())!=null) {
      if(!line.startsWith("#") && !line.trim().equals("")){
        factory.addSymbol(line);
      }
    }
    return factory;
  }
}