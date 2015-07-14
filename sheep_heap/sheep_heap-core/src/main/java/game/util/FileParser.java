package game.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import org.apache.commons.io.IOUtils;

import static game.Global.*;

import org.json.simple.*;
import org.json.simple.JSONArray;


public class FileParser {
	/**
	 * Return names of sheeps as stack.
	 * @return
	 */
	public static Stack<String[]> names(){
		Stack<String[]> stack = new Stack<String[]>();
		String[] sa;
        InputStream is;
		try {
			is = new FileInputStream(FileParser.class.getResource("/names.json").getPath());
            String jsonTxt = IOUtils.toString(is);

            Object obj=JSONValue.parse(jsonTxt);
            JSONArray array=(JSONArray)obj;
            for(int i =0; i < array.size();i++){
            	sa = new String[2];
            	JSONArray ent = (JSONArray)array.get(i);
            	sa[0] = ent.get(1).toString();
            	sa[1] = ent.get(2).toString();
            	stack.push(sa);
            }
            return stack;
		} catch (IOException e) {
			System.err.println("File with names not found.");
			e.printStackTrace();
			return null;
		}

	}	
	/**
	 * Parse config file
	 */
	public static void config() {

		try {
			System.out.print(FileParser.class.getResource("/config.ini").getPath());
			BufferedReader br = new BufferedReader(new FileReader(FileParser.class.getResource("/config.ini").getPath()));
			String line = br.readLine();
			while (line != null) {
				//System.out.println("LINE="+line);
				int tmp;
				switch (line.charAt(0)) {
					case ';': // commentary
						break;	
					case '[': // section
						break;
					case 'b': //boolean values
						if ( (line.indexOf("bTestMode=")) != -1 ){
							TEST_MODE = (getValueOfOption(line, "bTestMode=") != 0);
						}
						else if( (line.indexOf("bDebug=")) != -1 ){
							DEBUG = (getValueOfOption(line, "bDebug=") != 0);
						}
						else if( (line.indexOf("bDebugMap=")) != -1 ){
							DEBUG_MAP = (getValueOfOption(line, "bDebugMap=") != 0);
						}
						else if( (line.indexOf("bDebugAnimation=")) != -1 ){
							DEBUG_ANIMATION = (getValueOfOption(line, "bDebugAnimation=") != 0);
						}
						break;
					case 'i': //integer values
						if ( ((tmp=getValueOfOption(line, "iMaxMessages=")) != -1) ){
							MAX_MESSAGES = tmp;
						}
						else if ( ((tmp=getValueOfOption(line, "iDefaultZoom=")) != -1) ){
							ZOOM = tmp;
						}
						else if ( ((tmp=getValueOfOption(line, "iMinimalZoom=")) != -1) ){
							MIN_ZOOM = tmp;
						}
						else if ( ((tmp=getValueOfOption(line, "iMaximalZoom=")) != -1) ){
							MAX_ZOOM = tmp;
						}
						else if ( ((tmp=getValueOfOption(line, "iMapSize=")) != -1) ){
							MAP_SIZE = tmp;
						}
						else if ( ((tmp=getValueOfOption(line, "iSheeps=")) != -1) ){
							SHEEP_COUNT = tmp;
						}
						else if ( ((tmp=getValueOfOption(line, "iWindowWidth=")) != -1) ){
							DEFAULT_WINDOW_WIDTH = tmp;
						}
						else if ( ((tmp=getValueOfOption(line, "iWindowHeight=")) != -1) ){
							DEFAULT_WINDOW_HEIGHT = tmp;
						}
						break;
				}
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.err.println("Config file not found.");
			e.printStackTrace();
		}
	}
	/**
	 * Returns value of match in line, Returns -1 if match not found or is not int value.
	 * @param line
	 * @param match
	 * @return
	 */
	private static int getValueOfOption(String line, String match){
	    try { 
	    	if((line.indexOf(match) != -1 ))
	    		return Integer.parseInt(line.substring(match.length()));
	    	else 
	    		return -1; 
	    } catch(NumberFormatException e) { 
	        return -1; 
	    }
	}
}