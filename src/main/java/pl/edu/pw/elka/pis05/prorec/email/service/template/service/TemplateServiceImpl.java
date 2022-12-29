package pl.edu.pw.elka.pis05.prorec.email.service.template.service;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Component;

@Component
public class TemplateServiceImpl implements TemplateService {
    public String createContent(final String templateName, final Map<String, Object> model) {
        final VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();
        final Template template = velocityEngine.getTemplate(templateName);
        final VelocityContext context = new VelocityContext(model);
        final StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }
}
