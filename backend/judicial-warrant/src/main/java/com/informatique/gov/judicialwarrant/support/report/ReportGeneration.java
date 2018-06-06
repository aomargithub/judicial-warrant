package com.informatique.gov.judicialwarrant.support.report;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportGeneration implements Serializable {

	private static final long serialVersionUID = -2157284910174686034L;

	public static void generateReportToResponse(String reportName, Map<String, Object> parameters, List<? extends Object> reportList,
			HttpServletResponse response) throws JRException, IOException, Exception {
		JasperPrint jasperPrint = generateReport(reportName, parameters, reportList);

		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=" + reportName + ".pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}

	public static void generateReportToFile(String reportName, Map<String, Object> parameters, List<? extends Object> reportList, String filePath)
			throws JRException, Exception {
		JasperPrint jasperPrint = generateReport(reportName, parameters, reportList);
		JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
	}
	
	public static JasperPrint generateReport(String reportName, Map<String, Object> parameters, List<? extends Object> reportList)
			throws JRException, Exception {
		String reportLocation = getReportFullPath(reportName);
		JasperReport jasperReport = JasperCompileManager.compileReport(reportLocation);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
				new JRBeanCollectionDataSource(reportList));
		return jasperPrint;
	}
	
	public static String getReportFullPath(String reportName) throws Exception {
		String fullPath = null;
		String path = ClassLoader.getSystemClassLoader().getResource("report/" + reportName + ".jrxml").getPath();
		fullPath = URLDecoder.decode(path, "UTF-8");
		return fullPath;
	}

}
