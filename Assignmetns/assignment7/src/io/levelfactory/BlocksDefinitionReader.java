package io.levelfactory;

import java.io.IOException;
import java.io.BufferedReader;

/**
 * Provides a factory that reads from the block definition file and provides a
 * generator of blocks.
 */
public class BlocksDefinitionReader {

  /**
   *
   * @param reader block definitions file.
   * @return Factory to generate blocks.
   * @throws IOException if the file is not found.
   */
  public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) throws IOException {
    BlocksFromSymbolsFactory factory = new BlocksFromSymbolsFactory();

    BufferedReader br = new BufferedReader(reader);
    String line = "";
    while ((line = br.readLine()) != null) {
      if (!line.startsWith("#") && !line.trim().equals("")) {
        factory.addSymbol(line);
      }
    }
    return factory;
  }
}