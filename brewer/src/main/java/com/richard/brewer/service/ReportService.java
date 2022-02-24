package com.richard.brewer.service;

import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richard.brewer.dto.ReportingPeriod;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class ReportService {
	
	@Autowired
	private DataSource dataSource;
	
	public byte[] gerarRelatorioVendasEmitidas(ReportingPeriod reportingPeriod) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		
		Date startDate = Date.from(LocalDateTime.of(reportingPeriod.getStartDate(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		Date endDate = Date.from(LocalDateTime.of(reportingPeriod.getEndDate(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		parameters.put("format", "pdf");
		parameters.put("start_date", startDate);
		parameters.put("end_date", endDate);
		
		InputStream inputStream = this.getClass().getResourceAsStream("reports/report_sales_issued.jasper");
		Connection connection = this.dataSource.getConnection();
		
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, connection);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} finally {
			connection.close();
		}
	}

}
