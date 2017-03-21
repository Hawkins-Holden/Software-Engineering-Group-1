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

	public void write() throws IOException, WriteException {
		File file = new File("excelReport.xls");
		
		WritableWorkbook myexcel = Workbook.createWorkbook(file);
		WritableSheet mysheet = myexcel.createSheet("mySheet", 0);

		List<List<String>> emailData = AdminJDBC.getEmails();
		
		mysheet.addCell(new Label(1, 0, "VisitorID"));
		mysheet.addCell(new Label(1, 1, "First"));
		mysheet.addCell(new Label(1, 2, "MI"));
		mysheet.addCell(new Label(1, 3, "Last"));
		mysheet.addCell(new Label(1, 4, "Email"));
		
		for(int i = 0; i < emailData.size(); i++)
		{
			for (int j = 0; j < emailData.size(); j++)
			{
				mysheet.addCell(formatData(i, j, emailData.get(i).get(j)));
			}
		}
		myexcel.write();
		myexcel.close();
	}
	
	private Label formatData(int i, int j, String data)
	{
		return new Label(i, j, data);
	}

	private String getTime() {
		Calendar timestamp = Calendar.getInstance();
		return timestamp.get(Calendar.HOUR) + ":" + timestamp.get(Calendar.MINUTE) + ":" + timestamp.get(Calendar.SECOND) + timestamp.get(Calendar.AM_PM);
	}

	private String getDate() {
		Calendar timestamp = Calendar.getInstance();
		return timestamp.get(Calendar.MONTH) + " " + timestamp.get(Calendar.DATE) + ", " + timestamp.get(Calendar.YEAR);
	}

}