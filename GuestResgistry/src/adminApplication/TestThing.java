package adminApplication;

public class TestThing {
	public static void main (String[] args){
		String testString = "\"long_name\": \"San Francisco Metropolitan Area\",";
		int metroIndex = testString.indexOf("Metropolitan");
		String begin = testString.substring(0, metroIndex);
		int begindex = begin.lastIndexOf('"')+1;
		String end = testString.substring(metroIndex);
		int endex = testString.indexOf('"')+metroIndex-1;
		System.out.println(testString.substring(begindex, endex));
	}
}
