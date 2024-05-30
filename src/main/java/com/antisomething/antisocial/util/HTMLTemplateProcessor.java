package com.antisomething.antisocial.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.TimeZone;

public final class HTMLTemplateProcessor {

    private static final String TEMPLATES_DIR = FileSystems.getDefault()
            .getPath("webapps", "antisocial", "html")
            .toAbsolutePath()
            .toString();

    private static final Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

    static {
        try {
            cfg.setDirectoryForTemplateLoading(new File(TEMPLATES_DIR));
            cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
            cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
        } catch (IOException ex) {
            System.out.println("Failed to set templates directory for Freemarker: ".concat(ex.getMessage()));
        }
    }

    private HTMLTemplateProcessor() {
    }

    public static Template getTemplate(String name) throws IOException {
        return cfg.getTemplate(name);
    }
}
