package com.ysfmedia;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

import proceduralGeneration.*;

/**
* Contains all game logic and the game loop.
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

public class MainGame extends Applet implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	
	int width=32;
	int height=32;
	
	int screenWidth=1440;
	int screenHeight=960;
	
	DungeonGenerator[] dungeons;
	
	boolean isUpDown=false;
	boolean isDownDown=false;
	boolean isLeftDown=false;
	boolean isRightDown=false;
	//boolean isAttackDown=false;
	//Keeping track of whether keys are currently pressed is the easiest way to take input.
	
	Image offscreen; //An offscreen buffer is used to draw on. Then, the offscreen buffer will be directly drawn onto the screen in one step. Otherwise, flicker ensues.
	Image tileset = null;
	int floor=0;
	PlayerClass player;
	//Enemy badguy;
	int counter=0;
	int timer=1000;
	
	public static int tilesize=32;
	
	DungeonGenerator DunGen=new DungeonGenerator();
	int x=(width-1)/2-(screenWidth/((tilesize*2)));
	int y=(height-1)/2-(screenHeight/((tilesize*2)));
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP)
			isUpDown=true;
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
			isDownDown=true;
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
			isLeftDown=true;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			isRightDown=true;
		/*if(e.getKeyCode()==KeyEvent.VK_CONTROL)
			isAttackDown=true;*/
	    }
	public void keyTyped(KeyEvent e)
	{ 
		
	}
	public void keyReleased(KeyEvent e) {
		if(isUpDown&&e.getKeyCode()==KeyEvent.VK_UP)
			isUpDown=false;
		if(isDownDown&&e.getKeyCode()==KeyEvent.VK_DOWN)
			isDownDown=false;
		if(isLeftDown&&e.getKeyCode()==KeyEvent.VK_LEFT)
			isLeftDown=false;
		if(isRightDown&&e.getKeyCode()==KeyEvent.VK_RIGHT)
			isRightDown=false;
		/*if(isAttackDown&&e.getKeyCode()==KeyEvent.VK_CONTROL)
			isAttackDown=false;*/
		e.consume();
    }
	public void init()
	{
		try {
			URL doc_url = getDocumentBase();
			tileset= getImage(doc_url, "Resources/tileset.png");
			
			//Tilesets are used so that I don't have to store 16 to 24 images in memory. Otherwise, it would be cumbersome to work with.
			
			//if (tileset.getHeight(null) == -1) {
				//throw new Exception("Image could not be loaded.") ;
				//Note: At this point, the image is still loading which is the height is -1. This try/catch statement is just to make sure nothing is done with the image until it loads.
				//In future iterations, there may be some kind of a loading screen implemented
			//}
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.resize(screenWidth, screenHeight);
		try
		{
			Thread.sleep(66);
		}
		catch(InterruptedException e){
			
		}
		
		try {
			URL doc_url = getDocumentBase();
			Image temp= getImage(doc_url, "Resources/PlayerSprite.png");
			player= new PlayerClass(temp,x,y);
			
			//if (temp.getHeight(null) == -1) {
				//throw new Exception("Image could not be loaded.") ;
			//}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try
		{
			Thread.sleep(66);
		}
		catch(InterruptedException e){
			
		}
		
		/*try {
			URL doc_url = getDocumentBase();
			Image temp= getImage(doc_url, "Resources/EnemySprite.png");
			badguy= new Enemy(temp,x,y);
			
			if (temp.getHeight(null) == -1) {
				//throw new Exception("Image could not be loaded.") ;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}*/
		
		try
		{
			Thread.sleep(66);
		}
		catch(InterruptedException e){
			
		}
		
		offscreen = createImage(screenWidth,screenHeight-20);
		try
		{
			Thread.sleep(66);
		}
		
		catch(InterruptedException e){
			
		}
		
		this.addKeyListener(this);
		DunGen.Generate(2500, 8, width,height);
		Thread mainthread=new Thread(this);
		mainthread.start();
	}
	
	
	public void run()
	{
		while(true)
			{
			try {
			Thread.sleep(66);} catch (InterruptedException e) {
			e.printStackTrace();}
			timer--;
			if(timer<0)
			timer=0;
		if(isUpDown)
		{
			if(!DunGen.IsObstacle(x+(screenWidth/(tilesize*2)), y-1+(screenHeight/(tilesize*2)), floor))
			y--;
			player.SetAnim(1,2);
		}
		if(isDownDown)
		{
			if(!DunGen.IsObstacle(x+(screenWidth/(tilesize*2)), y+1+(screenHeight/(tilesize*2)), floor))
			y++;
			player.SetAnim(1,0);
		}
		if(isRightDown)
		{
			if(!DunGen.IsObstacle(x+1+(screenWidth/(tilesize*2)), y+(screenHeight/(tilesize*2)), floor))
			x++;
			player.SetAnim(1,1);
		}
		if(isLeftDown)
		{
			if(!DunGen.IsObstacle(x-1+(screenWidth/(tilesize*2)), y+(screenHeight/(tilesize*2)), floor))
			x--;
			player.SetAnim(1,3);
		}
		x= (x+width)%width;
		y= (y+height)%height;
		if(DunGen.IsExit(x+(screenWidth/(tilesize*2)), y+(screenHeight/(tilesize*2)), floor)&&timer>0)
		{
			floor++;
			counter++;
			timer+=125;
			if(floor>3)
			{
				floor=0;
				width*=1.5;
				height*=1.5;
				DunGen.Generate(2500, 4, width, height);
			}
			x=(width-1)/2-(screenWidth/(tilesize*2));
			y=(height-1)/2-(screenHeight/(tilesize*2));
		}
		if(DunGen.IsExit(x+(screenWidth/(tilesize*2)), y+(screenHeight/(tilesize*2)), floor)&&timer>0)
		{
			floor=0;
			counter=0;
			timer=1000;
			width=32;
			height=32;
			x=(width-1)/2-(screenWidth/(tilesize*2));
			y=(height-1)/2-(screenHeight/(tilesize*2));
		}
		if(!(isLeftDown||isRightDown||isUpDown||isDownDown))
			player.SetAnim(0);
		repaint();
			}
	}
	public void update(Graphics g)
	{
		paint(g);
	}
	public void paint(Graphics g)
	{
		if (offscreen.getHeight(null)>0) {
			offscreen.getGraphics().clearRect(0, 0, screenWidth, screenHeight);
			//offscreen.getGraphics().drawImage(tileset,0,0,null);
			DunGen.DrawFloor(floor, offscreen.getGraphics(), tileset,x,y,screenWidth,screenHeight-30);
			player.Draw(offscreen.getGraphics());
			//badguy.Draw(offscreen.getGraphics(), x, y, screenWidth, screenHeight);
			//offscreen.getGraphics().setFont(getFont())
			g.setColor(Color.red);
			Font myFont= new Font("Helvetica",Font.BOLD,20);
			g.setFont(myFont) ;
			g.clearRect(0, 0, screenWidth, 20);
			g.drawString("Level: "+counter,20,20);
			g.drawString("Time Left: "+timer,1240,20);
			g.drawImage(offscreen, 0, 30, null);
		} else {
			g.drawString("offscreen image has negative height",20,20) ;
		}
	}
	public void drawMessage(Graphics g,String s)
	{
		
	}
}
