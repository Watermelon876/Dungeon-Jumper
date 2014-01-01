package proceduralGeneration;
/**
* Random name generation algorithm.
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

public class RandomNameGenerator {
	private Random r;
	private String name;
	//This didn't end up being used, but it's pretty cool imo.
	
	public RandomNameGenerator()
	{
		r=new Random();
	}
	public RandomNameGenerator(Random r)
	{
		this.r=r;
	}
	
	public String GenerateName()
	{
		char lastchar='A';
		name=" ";
		while(name.length()<10)
		{
			//Some characters just don't make sense after other characters and some always make sense. This is what the whole allowed characters deal is about.
			char[] allowed={'A','E','I','O','U','Y','H','L','R'};
			char[] vowels={'A','E','I','O','U','Y'};
			char[] allowedg={'A','E','I','O','U','Y','H','L','R','G','N'};
			switch(lastchar)
			{
			case 'A':
				lastchar=(char)(r.nextInt(26)+65);
				break;
			case 'B':
				lastchar=allowed[r.nextInt(allowed.length)];
				break;
			case 'C':
				lastchar=allowed[r.nextInt(allowed.length)];
				break;
			case 'D':
				lastchar=allowed[r.nextInt(allowed.length)];
				if(lastchar=='L')
				{
					lastchar='I';
				}
				break;
			case 'E':
				lastchar=(char)(r.nextInt(26)+65);
				break;
			case 'F':
				lastchar=allowed[r.nextInt(allowed.length)];
				break;
			case 'G':
				lastchar=allowedg[r.nextInt(allowedg.length)];
				break;
			case 'H':
				lastchar=vowels[r.nextInt(vowels.length)];
				break;
			case 'I':
				lastchar=(char)(r.nextInt(26)+65);
				break;
			case 'J':
				lastchar=allowed[r.nextInt(allowed.length)];
				if(lastchar=='L')
				{
					lastchar='E';
				}
				break;
			case 'K':
				lastchar=allowed[r.nextInt(allowed.length)];
				break;
			case 'L':
				lastchar=vowels[r.nextInt(vowels.length)];
				break;
			case 'M':
				lastchar=allowedg[r.nextInt(allowedg.length)];
				if(lastchar=='G')
				{
					lastchar='E';
				}
				break;
			case 'N':
				lastchar=allowed[r.nextInt(allowed.length)];
				if(lastchar=='L')
				{
					lastchar='Y';
				}
				break;
			case 'O':
				lastchar=(char)(r.nextInt(26)+65);
				break;
			case 'P':
				//Inspect after this
				lastchar=allowed[r.nextInt(allowed.length)];
				break;
			case 'Q':
				lastchar=vowels[r.nextInt(vowels.length)];
				break;
			case 'R':
				lastchar=allowed[r.nextInt(allowed.length)];
				break;
			case 'S':
				lastchar=allowed[r.nextInt(allowed.length)];
				break;
			case 'T':
				lastchar=allowed[r.nextInt(allowed.length)];
				break;
			case 'U':
				lastchar=(char)(r.nextInt(26)+65);
				break;
			case 'V':
				lastchar=allowed[r.nextInt(allowed.length)];
				break;
			case 'W':
				lastchar=allowed[r.nextInt(allowed.length)];
				break;
			case 'X':
				lastchar=vowels[r.nextInt(vowels.length)];
				break;
			case 'Y':
				lastchar=(char)(r.nextInt(26)+65);
				if(lastchar=='Y')
				{
					lastchar='E';
				}
				break;
			case 'Z':
				lastchar=allowed[r.nextInt(allowed.length)];
				break;
			}
			name+=lastchar;
		}
		System.out.print(name);
		return name;
	}
}
