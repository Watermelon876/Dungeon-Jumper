package com.ysfmedia;

import java.awt.Graphics;
import java.awt.Image;

/**
* Important particular instance of GameObject class with added animation.
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

public class PlayerClass extends GameObject {

	/**
	 * @param gamesprite
	 */
	public boolean isAttacking;
	private int counter;
	private int frame;
	private int anim_number=0;
	private int dir; //0 is front view, 1 is side view right, 2 is back view, 3 is side view left
	
	private int tilesize=com.ysfmedia.MainGame.tilesize;
	
	public PlayerClass(Image gamesprite, int xpos, int ypos) {
		super(gamesprite, xpos, ypos);
		isAttacking=false;
	}
	
	public void SetAnim(int anim_number,int direction)
	{
		this.anim_number=anim_number;
		dir=direction;
	}
	public void SetAnim(int anim_number)
	{
		this.anim_number=anim_number;
	}
	
	public void Update(boolean isUpDown, boolean isLeftDown, boolean isRightDown, boolean isDownDown, boolean isAttackDown)
	{
		if(isUpDown)
		{
			y_veloc=-3;
			dir=2;
		}
		if(isDownDown)
		{
			y_veloc=+3;
			dir=0;
		}
		if(isLeftDown)
		{
			x_veloc=-3;
			dir=1;
		}
		if(isRightDown)
		{
			x_veloc=+3;
			dir=3;
		}
		if(isAttacking)
		{
			if(counter>=10)
			{
				isAttacking=false;
				counter=0;
			}
			else
				counter++;
		}
		else if(isAttackDown)
		{
			isAttacking=true;
			counter=0;
		}
	}
	
	public void Draw(Graphics g)
	{
		frame++;
		frame%=4;
		g.drawImage(sprite, 720-(tilesize/2), 480, 720+(tilesize/2), 480+(tilesize), dir*tilesize, (anim_number*4+frame)*tilesize, (dir+1)*tilesize, (anim_number*4+frame+1)*tilesize, null);
		
	}

}
