package com.ysfmedia;
import java.awt.*;

/**
* Class for noninteractive and nonmoving game objects, simplifying a great deal of the GameObject class.
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


public class BackgroundSprite {

	Image sprite;
	public boolean isPassable;
	int x;
	int y;
	public Rectangle bounds;
	public BackgroundSprite(Image backgroundSprite, int x, int y, boolean isObstacle)
	{
		this.x=x;
		this.y=y;
		this.sprite= backgroundSprite;
		this.isPassable=!isObstacle;
		bounds = new Rectangle(x,y,sprite.getHeight(null),sprite.getHeight(null));
	}
	public void Draw(Graphics g)
	{
		if(sprite!=null)
		{
		g.drawImage(sprite, x, y, null);
		}
	}
}
