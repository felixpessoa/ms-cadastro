package br.com.attornatus.mscadastro.domain.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class JasperService {
	
	private static final String JASPER_DIRETORIO = "classpath:jasper/";
	private static final String JASPER_PREFIXO = "cadastros";
	private static final String JASPER_SUFIXO = ".jasper";
	
	@Autowired
	private Connection connection;
	
	private Map<String, Object> params = new HashMap<>();
	

	public JasperService() {
		this.params.put("IMAGEM_DIRETORIO", JASPER_DIRETORIO);
	}

	public void addParams(String key, Object value) {
		this.params.put(key, value);
	}
	
	public byte[] exportarPDF() {
		byte[] bytes = null;
		try {
			File file = ResourceUtils
					.getFile(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(JASPER_SUFIXO));
			JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
			bytes = JasperExportManager.exportReportToPdf(print);
		} catch (FileNotFoundException | JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bytes;
		
	}

}
