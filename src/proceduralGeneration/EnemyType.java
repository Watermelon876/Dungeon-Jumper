package proceduralGeneration;

//import java.util.Random;
/**
* Dummy enemy class to be fleshed out later or ignored.
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


public class EnemyType {
	//Right now this is a dummy class. I will add in functionality later.
	public int difficulty;
	
	int maxHP;
	int maxMP;
	int attack;
	int defense;
	int magattack;
	int magdefense;
	boolean alive;
	
	public EnemyType(int difficulty) //An actual constructor will soon be implemented.
	//Or maybe just a random generation of enemy attributes based on desired difficulty.
	{
		this.difficulty=difficulty;
	}
	
	
}
