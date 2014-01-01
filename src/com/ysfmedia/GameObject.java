package com.ysfmedia;
import java.awt.*;

/**
* Basic class for game objects including drawing and basic movement functionality.
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


public class GameObject {
	public Rectangle bounds;
	double x_pos;
	double y_pos;
	double x_veloc;
	double y_veloc;
	Image sprite;
	
	public GameObject(Image gamesprite)
	{
		sprite=gamesprite;
		bounds = new Rectangle(0,0,sprite.getWidth(null),sprite.getHeight(null));
		x_pos=0.0;
		y_pos=0.0;
		x_veloc=0.0;
		y_veloc=0.0;
	}
	
	public GameObject(Image gamesprite, double xpos, double ypos)
	{
		if(gamesprite!=null)
		sprite=gamesprite;
		bounds = new Rectangle(0,0,sprite.getWidth(null),sprite.getHeight(null));
		x_pos=xpos;
		y_pos=ypos;
		x_veloc=0.0;
		y_veloc=0.0;
	}
	
	public void Update()
	{
		x_pos+=x_veloc;
		y_pos+=y_veloc;
	}
	
	public void TestForCollision(GameObject gameObject2){
		bounds.setLocation((int) (x_pos+x_veloc), (int) (y_pos+y_veloc));
		if (bounds.intersects(gameObject2.bounds))
		{
			x_pos-=x_veloc;
			y_pos-=y_veloc;
		}
	}
	public void TestForCollision(BackgroundSprite backsprite){
		bounds.setLocation((int) x_pos, (int) y_pos);
		if (bounds.intersects(backsprite.bounds)&&!backsprite.isPassable)
		{
			x_pos-=x_veloc;
			y_pos-=y_veloc;
		}
	}
	
	public void Draw(Graphics g, int x_right, int y_top, int width, int height)
	{
		if(x_pos>=x_right&&y_pos>=y_top&&x_pos<(x_right+width)&&y_pos<(y_top+height))
		if(sprite!=null)
		{
		g.drawImage(sprite, (int) (x_pos-x_right), (int) (y_pos-y_top), null);
		}
	}
	
}
