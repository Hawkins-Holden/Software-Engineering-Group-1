package adminApplication;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import jxl.Workbook;
import jxl.WorkbookSettings;
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

	public void write(String filename) throws IOException, WriteException {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(new Locale("en", "EN"));
		WritableWorkbook workbook = Workbook.createWorkbook(new File(filename), ws);
		
		WritableSheet sheet = workbook.createSheet("Emails", 2);
		sheet.setColumnView(0, 60);

	    Label lr = new Label(0,0, "Arial Fonts");
	    sheet.addCell(lr);
	}

}