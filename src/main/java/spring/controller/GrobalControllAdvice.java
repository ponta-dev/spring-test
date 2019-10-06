package spring.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class GrobalControllAdvice {
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		model.addAttribute("error", "DBアクセス時に内部サーバーエラーが発生しました");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		model.addAttribute("message", "DataAccessExceptionが発生しました");
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		model.addAttribute("error", "内部サーバーエラーが発生しました");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		model.addAttribute("message", "Exceptionが発生しました");
		return "error";
	}
}
