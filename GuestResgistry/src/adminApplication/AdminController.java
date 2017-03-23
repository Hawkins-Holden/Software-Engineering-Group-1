package adminApplication;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Label;

@SuppressWarnings("restriction")
public class AdminController implements Initializable {

	@FXML
	private Button generate_report;
	@FXML
	private Button transfer_email;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void generateMailingList() throws IOException, WriteException {
		File file = new File("mailingList.xls");
		
		WritableWorkbook myexcel = Workbook.createWorkbook(file);
		WritableSheet mysheet = myexcel.createSheet("mySheet", 0);

		List<List<String>> emailData = AdminJDBC.getEmails();
		
		mysheet.addCell(new Label(0, 0, "This report was generated on " + getDate() + " at " + getTime() + "."));
		
		mysheet.addCell(new Label(0, 1, "VisitorID"));
		mysheet.addCell(new Label(1, 1, "First"));
		mysheet.addCell(new Label(2, 1, "MI"));
		mysheet.addCell(new Label(3, 1, "Last"));
		mysheet.addCell(new Label(4, 1, "Email"));
		
		for(int i = 0; i < emailData.size(); i++)
		{
			for (int j = 0; j < emailData.get(0).size(); j++)
			{
				mysheet.addCell(formatData(i, j, emailData.get(i).get(j)));
			}
		}
		myexcel.write();
		myexcel.close();
	}
	
	public void generateReport() throws IOException, WriteException {
		File file = new File("comprehensiveReport.xls");
		
		WritableWorkbook myexcel = Workbook.createWorkbook(file);
		WritableSheet mysheet = myexcel.createSheet("mySheet", 0);

		List<List<String>> dataTable = AdminJDBC.generateReport();
		
		mysheet.addCell(new Label(0, 0, "This report was generated on " + getDate() + " at " + getTime() + "."));
		
		mysheet.addCell(new Label(0, 1, "VisitorID"));
		mysheet.addCell(new Label(1, 1, "First"));
		mysheet.addCell(new Label(2, 1, "MI"));
		mysheet.addCell(new Label(3, 1, "Last"));
		mysheet.addCell(new Label(4, 1, "Email"));
		mysheet.addCell(new Label(5, 1, "From (Latitude)"));
		mysheet.addCell(new Label(6, 1, "From (Longitude)"));
		mysheet.addCell(new Label(7, 1, "Number in Party"));
		mysheet.addCell(new Label(8, 1, "How Referred"));
		mysheet.addCell(new Label(9, 1, "Stayed in M/WM Hotel"));
		mysheet.addCell(new Label(10, 1, "Destination"));
		mysheet.addCell(new Label(11, 1, "Repeat Visitor?"));
		mysheet.addCell(new Label(12, 1, "Reason For Travelling"));
		mysheet.addCell(new Label(13, 1, "Date of Visit"));
		
		for(int i = 0; i < dataTable.size(); i++)
		{
			for (int j = 0; j < dataTable.get(0).size(); j++)
			{
				mysheet.addCell(formatData(i, j, dataTable.get(i).get(j)));
			}
		}
		myexcel.write();
		myexcel.close();
	}
	
	private Label formatData(int i, int j, String data)
	{
		return new Label(j, i+2, data);
	}

	private String getTime() {
		Calendar timestamp = Calendar.getInstance();
		return timestamp.get(Calendar.HOUR) + ":" + timestamp.get(Calendar.MINUTE) + ":" + timestamp.get(Calendar.SECOND) + timestamp.get(Calendar.AM_PM);
	}

	private String getDate() {
		Calendar timestamp = Calendar.getInstance();
		return (timestamp.get(Calendar.MONTH) + 1) + "/" + timestamp.get(Calendar.DATE) + "/" + timestamp.get(Calendar.YEAR);
	}

}