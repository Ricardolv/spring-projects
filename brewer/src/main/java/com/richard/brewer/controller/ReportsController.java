package com.richard.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.richard.brewer.dto.ReportingPeriod;
import com.richard.brewer.service.ReportService;

@Controller
@RequestMapping("/reports")
public class ReportsController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/salesIssued")
	public ModelAndView relatorioVendasEmitidas() {
		ModelAndView mv = new ModelAndView("report/report_sales_issued");
		mv.addObject(new ReportingPeriod());
		return mv;
	}
	
	@PostMapping("/salesIssued")
	public ResponseEntity<byte[]> gerarRelatorioVendasEmitidas(ReportingPeriod reportingPeriod) throws Exception {
		byte[] report = reportService.gerarRelatorioVendasEmitidas(reportingPeriod);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(report);
	}

}
