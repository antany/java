import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

/**
 * 
 * Provide the pdf from the presto transit report as input
 * 
 * used the library pdfbox-app-1.8.11 from http://pdfbox.apache.org/download.cgi
 * tested with version 1.8.11
 * 
 * Currently it works with Durham TTC & Go Transit, Others should also work fine. Incase any issues feel free to reachme
 * 
 * @author antany
 *
 *
 */
public class PrestoUsageReportParser {
	public static void main(String[] args) throws Exception {
		PDDocument doc = PDDocument.load("C:\\Antany\\2015-06156895_024.pdf");
		int numberOfPages = doc.getNumberOfPages();
		//System.out.println(numberOfPages);
		
		PDFTextStripper stripper  = new PDFTextStripper();
		stripper.setStartPage(1);
		stripper.setEndPage(numberOfPages);
		
		String pdfTextContent = stripper.getText(doc);
		
		//System.out.println(pdfTextContent);
		//15/01/2015 - 21/01/2015 Total PRESTO Trips
		String finalPattern = PATTERN_PRE_QUAL+"(("+PATTERN_FROM_TO_DATE+") .*?)(?=(("+PATTERN_FROM_TO_DATE+")|("+PATTERN_TOT_NUMB_TRIPS+")))";
		System.out.println(finalPattern);
		Pattern p = Pattern.compile(finalPattern);
		Matcher m = p.matcher(pdfTextContent);
		
		
		
		System.out.println("Start Date\tEnd Date\tTransit\tNumber of Trips\tCost");
		
		ArrayList<TravelInfo> travelList = new ArrayList<TravelInfo>();
		
		while(m.find()){
			Pattern p2 = Pattern.compile(PATTERN_TRANSIT_AND_USAGE_AND_FAIR);
			Matcher m2 = p2.matcher(m.group());
			while(m2.find()){
				
				BigDecimal bg = new BigDecimal(m2.group(3).replace("$", "").replace("(", "-").replace(")", ""));
				int numberOfTrips = Integer.parseInt(m2.group(2));
				
				TravelInfo ti = new TravelInfo(m.group(3), m.group(4), m2.group(1),numberOfTrips,bg);
				travelList.add(ti);
				
				System.out.println(ti);
			}
		}
		
		
		
	}
	
	
	
	private static final String  PATTERN_PRE_QUAL  = "(?i)(?s)";
	private static final String  PATTERN_FROM_TO_DATE = "(\\d{2}/\\d{2}/\\d{4}) - (\\d{2}/\\d{2}/\\d{4})";
	private static final String  PATTERN_TRANSIT_AND_USAGE_AND_FAIR = "(.*?) (\\d{1,2}) (\\(?\\$.*)";
	private static final String  PATTERN_TOT_NUMB_TRIPS = "\\d{4} Total.*";
	
	
}

class TravelInfo{
	
	private String transit;
	private int numberOfTrips;
	private BigDecimal costOfTrips;
	private String startDate;
	private String endDate;
	private String startMonth;
	private String endMonth;
	
	private SimpleDateFormat sdf  = new SimpleDateFormat("dd/MM/yyyy");
	private String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	
	TravelInfo(String startDate, String endDate, String transit, int numberOfTrips, BigDecimal costOfTrips){
		this.transit = transit;
		this.numberOfTrips = numberOfTrips;
		this.endDate = endDate;
		this.startDate = startDate;
		this.costOfTrips = costOfTrips;
		this.startMonth = getMonth(startDate);
		this.endMonth = getMonth(endDate);
	}
	
	private String getMonth(String date){
		try{
			Date dt = sdf.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			int month = cal.get(Calendar.MONDAY);
			return months[month];
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTransit() {
		return transit;
	}
	public void setTransit(String transit) {
		this.transit = transit;
	}
	public int getNumberOfTrips() {
		return numberOfTrips;
	}
	public void setNumberOfTrips(int numberOfTrips) {
		this.numberOfTrips = numberOfTrips;
	}
	public BigDecimal getCostOfTrips() {
		return costOfTrips;
	}
	public void setCostOfTrips(BigDecimal costOfTrips) {
		this.costOfTrips = costOfTrips;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	@Override
	public String toString() {
		return startMonth+"\t"+endMonth+"\t"+transit+"\t"+numberOfTrips+"\t"+costOfTrips;
	}
	
	
	
}
