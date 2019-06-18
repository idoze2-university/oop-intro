package io.levelfactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import game.component.Block;
import game.component.Sprite;
import game.levels.LevelInformation;
import geometry.Velocity;

/**
 * The LeveelSpecificationReader reads LevelInfo from formatted file.
 */
public class LevelSpecificationReader {

   /**
    * @param reader the file to read specifications from.
    * @return List of Levels parsed.
    * @throws IOException if reader cannot read.
    */
   public static List<LevelInformation> fromReader(java.io.Reader reader) throws IOException {
      List<LevelInformation> levels = new ArrayList<LevelInformation>();

      BufferedReader br = new BufferedReader(reader);
      do {
         String top = "";
         while (!top.equals("START_LEVEL")) {
            top = br.readLine();
            if (top == null) {
               return levels;
            }
         }
         String levelName = SpecificationParser.getDataValue(br.readLine());
         List<Velocity> initialBallVelocities = SpecificationParser.parseVelocities(br.readLine());
         Sprite background = SpecificationParser.parseBackGround(br.readLine());
         int paddleSpeedRaw = Integer.parseInt(SpecificationParser.getDataValue(br.readLine()));
         while(paddleSpeedRaw>100){
            paddleSpeedRaw/=2;
         }
         int paddleSpeed = paddleSpeedRaw;
         int paddleWidth = Integer.parseInt(SpecificationParser.getDataValue(br.readLine()));
         String defPath = SpecificationParser.getDataValue(br.readLine());
         InputStreamReader fr = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(defPath));
         BlocksFromSymbolsFactory factory = BlocksDefinitionReader.fromReader(fr);
         int startx = Integer.parseInt(SpecificationParser.getDataValue(br.readLine()));
         int y = Integer.parseInt(SpecificationParser.getDataValue(br.readLine()));
         int rowHeight = Integer.parseInt(SpecificationParser.getDataValue(br.readLine()));
         int numOfBlocks = Integer.parseInt(SpecificationParser.getDataValue(br.readLine()));
         br.readLine();
         List<Block> blocks = new ArrayList<Block>();
         String line = br.readLine();
         while (!(line).equals("END_BLOCKS")){
            if (line.trim().equals("")) {
               line = br.readLine();
               continue;
            }
            int x = startx;
            for (String s : line.split("")) {
               if (factory.isSpaceSymbol(s)) {
                  x += factory.getSpaceWidth(s);
               } else {
                  blocks.add(factory.getBlock(s, x, y));
                  x+=factory.getBlockWidth(s);
               }
            }
            y += rowHeight;
            line = br.readLine();
         }

         LevelInformation info = new LevelInformation() {
            @Override
            public int paddleWidth() {
               return paddleWidth;
            }

            @Override
            public int paddleSpeed() {
               return paddleSpeed;
            }

            @Override
            public int numberOfBlocksToRemove() {
               return numOfBlocks;
            }

            @Override
            public int numberOfBalls() {
               return initialBallVelocities.size();
            }

            @Override
            public String levelName() {
               return levelName;
            }

            @Override
            public List<Velocity> initialBallVelocities() {
               return initialBallVelocities;
            }

            @Override
            public Sprite getBackground() {
               return background;
            }

            @Override
            public List<Block> blocks() {
               return blocks;
            }
         };
         levels.add(info);
      } while ((br.readLine()) != null);
      return levels;
   }

}