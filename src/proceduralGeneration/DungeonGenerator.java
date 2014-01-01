package proceduralGeneration;

/**
* Procedural maze generator which also keeps track of the maps.
*
*
*  @author Yash Suhail Farooqui (http://www.ysfmedia.com)
*  @author See my blog at http://blog.ysfmedia.com
* 
*  @version 1.0
*
*   This program is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


import java.util.Random;
import java.awt.*;

public class DungeonGenerator {

	int[][][] dungeon;
	int width;
	int height;

	int world_x;
	int world_y;
	
	private int tilesize=com.ysfmedia.MainGame.tilesize;
	
	Color newColor;
	
	public DungeonGenerator()
	{
		
	}
	public DungeonGenerator(int desiredDifficulty, int levels,	int width,	int height)
	{
		this.width=width;
		this.height=height;
		dungeon= new int[levels][][];
		int tempdifficulty=(((desiredDifficulty/levels)+levels)/2)+5;
		for(int i=0;i<levels;i++)
		{
			dungeon[i]=GenerateFloor(width,height,tempdifficulty);
			tempdifficulty+=10;
			tempdifficulty+=(dungeon[i][0][0]/1024);
			System.out.print("\n---Floor Number "+(i+1)+" ---\n\n");
			//DrawDungeon(dungeon[i]);
		}
	}
	
	public void Generate(int desiredDifficulty, int levels, int width,	int height)
	{
		this.width=width;
		this.height=height;
		dungeon= new int[levels][][];
		int tempdifficulty=(((desiredDifficulty/levels)+levels)/2)+5;
		for(int i=0;i<levels;i++)
		{
			dungeon[i]=GenerateFloor(width,height,tempdifficulty);
			tempdifficulty+=10;
			tempdifficulty+=(dungeon[i][0][0]/1024);
			//DrawDungeon(dungeon[i]);
		}
		Random r=new Random();
		newColor=Color.getHSBColor(r.nextFloat(), 240.0f, 0);
	}
	
	public int[][] GenerateFloor(int width, int height, int desiredDifficulty)
	{
		//Just so you know, the way the int is used is like this:
		//The first two bits break down as being floor tiles
		//The next 5 bits are enemies.
		//The next 5 bits are treasure.
		//Anything else is fair game.
		Random r= new Random(); //A random number generator is really important.
		int max=(r.nextInt(3)+1)*(width*height)/16;//Now the bigness of the room should be about 2/5 of the room probably... I'm just pulling a guess out of nowhere.
		int[] position={(width-1)/2,(height-1)/2};
		int[][] tiles= new int[width][height];
		int carved=0;
		int lastdirection=0;
		
		
		for(int i=0;i<width;i++) //Mark every tile impassable //Look at every x
		{
			for(int j=0;j<height;j++) //Look at every y
			{
				tiles[i][j]=0; //Marking tile impassable
			}
		}
		
		while(carved<max) //Repeat until we have enough empty tiles I mean.
		{
			if(tiles[position[0]][position[1]]==0) //(Is the current tile empty? Because otherwise, we don't want to override, do we?)
			{
				tiles[position[0]][position[1]]=1; //(Set the tile under consideration to be empty)
				carved++; //Mark up the number of tiles carved. We've got to keep track.
			}
				lastdirection+=(int) (r.nextFloat()*4); //This should pick a direction randomly.
				lastdirection%=4;
			
			
			switch(lastdirection)
			{
			case 0: position[0]+=width+1; break; //Hold on... Now why are we ADDING width? That doesn't make sense does it?
			//Well, it actually does because what we're doing is we're taking mods after, and I'm not sure if Java does mods right when negative.
			//So I'm making sure it's positive, so that I can be POSITIVE it's right!  
			case 1: position[1]+=height+1; break; //Same thing.
			case 2: position[0]+=width-1; break; //Same thing.
			case 3: position[1]+=height-1; break; //Same thing.
			}
			position[0]%=width; //Now take mods to be sure it's in range.
			position[1]%=height; //Same thing.
			
			if(carved==max) //All right, we've reached the end of the line. It's been a good trip, but all good things must come to an end sometime.
			{
				tiles[position[0]][position[1]]=3; //Before we leave, I'd like to say something. The spot we're at has been good. Very good. So I've decided to mark it as an exit.
				tiles[(width-1)/2][(height-1)/2]=2; //It's been a while, but don't forget where it all began. I'm marking that tile as an entrance. Thank you for all you've done for us!
			}
		}
		
		//EnemyType[] enemies= {new EnemyType(4),new EnemyType(32), new EnemyType(18), new EnemyType(6)};
		//In reality, we'd be passed an array of enemy types, not creating one. Of course it's a dummy class right now. This is just a waste of time thing, so delete it later.
		//tiles=PlaceEnemies(tiles,enemies, desiredDifficulty);
		
		//And we're done!
		return tiles;
	}
	
	public int[][] PlaceEnemies(int[][] tiles, EnemyType[] enemies, int allowedDifficulty)
	{
		//Note: enemies were removed from the game in order to change the focus.
		
		int height=tiles.length;
		int width=tiles[0].length;
		
		Random r= new Random();		
		
		int minDifficulty=allowedDifficulty+1; //We don't want the minimum difficulty to be greater than the allowed difficulty
		//If there is a difficulty of an enemy less, then this will be overridden, but otherwise, we don't want to waste time.
		
		for(int i=0;i<enemies.length;i++)//Go through each enemy, looking for the minimum.
		{
			//If nothing interesting happens, the minimum difficulty is constant

			if(enemies[i].difficulty<minDifficulty) //If there is a difficutly level less than the min, then it's obviously not the minimum.
			{
				//Since this is the lowest difficulty we encountered so far, then based on available information it's the minimum.
				//This can change based on new info.
				minDifficulty=enemies[i].difficulty;
				//Yeah so just say this is the lowest we can get.
			}
		}
		
		while(allowedDifficulty>=minDifficulty)
		{			
			int tempindex=r.nextInt(enemies.length); //Pick a random enemy type by selecting the index. 
			EnemyType tempEnemyType=enemies[tempindex]; //Pick a random enemy type and see if it works
			if(tempEnemyType.difficulty<=allowedDifficulty) //If the enemy can be used. (Allowed difficulty should never be negative)
			{
				int tempx=r.nextInt(width);
				int tempy=r.nextInt(height);
				while(true) //Placing algorithm. It'll break.
				{
					if(tiles[tempx][tempy]==1) //Enemies live only on floor tiles
					{
						tempindex*=4; //Shift enemy index two bits
						tiles[tempx][tempy]+=tempindex; //Store along with floor tile info.
						break;
					}
					tempx=r.nextInt(width);
					tempy=r.nextInt(height);
				}
				allowedDifficulty-=tempEnemyType.difficulty; //Decrement the max difficulty of the remaining enemies.
			}
		}
		tiles[0][0]+=allowedDifficulty*1024;//We'll store allowedDifficulty in the array.
		//It has to be in the last 22 bits as only 10 are used.
		return tiles; //Give back the tile array
	}
	
	public void DrawFloor(int floor,Graphics g, Image tileset, int x_right, int y_top, int ScreenWidth, int ScreenHeight)
	{
		int[][]  tiles= dungeon[floor];
		for(int i=0;i<(ScreenHeight/tilesize)+1;i++) //Every y in range (Since it's a 64-x480 window, the range will be 15 tiles vertically)
		{
			for(int j=0;j<(ScreenWidth/tilesize)+1;j++) //Every x in range (And 20 tiles horizontally)
			{
				//I don't need a switch statement, because I am using mods and stuff.
				//The image is 512x512 square pixels. Each tile 16x16 and 32x32 tiles on each side.
				//IN order to get the x and y coordinates of the tile in the tileset, we take stuff mod 32 for x. and we use truncated division for 32.
				int x=tiles[(x_right+j+width)%width][(y_top+i+height)%height] %32;
				int y=(tiles[(x_right+j+width)%width][(y_top+i+height)%height] %1024)/32+floor;
				g.drawImage(tileset, j*tilesize, i*tilesize, (j+1)*tilesize, (i+1)*tilesize, x*tilesize, y*tilesize, (x+1)*tilesize, (y+1)*tilesize,null);
				
			}
		}
	}
	public boolean IsObstacle(int x,int y, int floor)
	{
		return dungeon[floor][(x)%width][(y)%height]==0;
	}
	
	public boolean IsExit(int x, int y, int floor)
	{
		return dungeon[floor][(x)%width][(y)%height]==3;
	}
}
