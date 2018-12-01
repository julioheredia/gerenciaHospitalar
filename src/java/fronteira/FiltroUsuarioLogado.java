/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import entidade.UsuarioEntidade;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author desenvolvimento
 */
@WebFilter(filterName = "FiltroUsuarioLogado", urlPatterns = {"/logado/*"})
public class FiltroUsuarioLogado implements Filter {

    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    public FiltroUsuarioLogado() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        UsuarioEntidade u = (UsuarioEntidade) req.getSession().getAttribute("usuarioLogado");

        if (u != null) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/GerenciaHospitalar/index.jsf");

        }
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FiltroUsuarioLogado:Initializing filter");
            }
        }
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltroUsuarioLogado()");
        }
        StringBuilder sb = new StringBuilder("FiltroUsuarioLogado(");
        sb.append(filterConfig);
        return (sb.toString());
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
