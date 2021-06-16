package application.web.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // �����, ����������� �� ����� ������������
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }


    // ���������� ������������, � ������� �������������� ViewResolver, ��� ����������� ����������� jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
        };
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        registerHiddenFieldFilter(servletContext);
    }

    private void registerHiddenFieldFilter(ServletContext servletContext) {
        servletContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true,"/*");
    }

    /* ������ ����� ��������� url, �� ������� ����� ������������ ���������� */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}