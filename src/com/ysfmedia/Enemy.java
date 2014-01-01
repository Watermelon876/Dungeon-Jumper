package com.ysfmedia;

/**
* Basic class for enemies: effectively GameObject class with added animation behaviors.
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


//import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

public class Enemy extends GameObject {

	int dir;
	Random r;
	
	public Enemy(Image gamesprite) {
		super(gamesprite);
		r=new Random();
		dir=r.nextInt(4);
	}
	
	public Enemy(Image gamesprite, double x_pos, double y_pos)
	{
		super(gamesprite,x_pos,y_pos);
		r=new Random();
		dir=r.nextInt(4);
	}
	
	public void Update(int player_x,int player_y)
	{
		super.Update();
		switch(dir)
		{
		case 0: this.x_veloc=-3.0;
			break;
		case 1: this.y_veloc=3.0;
			break;
		case 2: this.y_veloc=-3.0;
			break;
		case 3: this.x_veloc=3.0;
			break;
		}
		
	}
	
	public void TestForCollision(BackgroundSprite backsprite)
	{
		bounds.setLocation((int) x_pos, (int) y_pos);
		if (bounds.intersects(backsprite.bounds)&&!backsprite.isPassable)
		{
			x_pos-=x_veloc;
			y_pos-=y_veloc;
			dir=4-dir;
		}
	}
	


}
