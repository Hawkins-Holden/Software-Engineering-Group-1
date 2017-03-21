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
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL resource = classLoader.getResource("filename.ext");
		File file = new File("excelReport.xls");
		
		WritableWorkbook myexcel = Workbook.createWorkbook(file);
		WritableSheet mysheet = myexcel.createSheet("mySheet", 0);
		Label label = new Label(0, 0, "data1");
		mysheet.addCell(label);
		myexcel.write();
		myexcel.close();
	}

}