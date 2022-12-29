package pl.edu.pw.elka.pis05.prorec.email.service.template.service;

import java.util.Map;

public interface TemplateService {
    String createContent(String templateName, Map<String, Object> model);
}
