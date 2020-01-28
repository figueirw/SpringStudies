package br.com.devdojo.awesome.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

//WebMvcConfigurerAdapter classe spring pra controle de paginacao
@Configuration
public class SPEAdapter extends WebMvcConfigurerAdapter {
    /*
    sort: http://localhost:8181/students?sort=name,asc
    page: http://localhost:8181/students?page=0&size=5
     */

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();
        //comeca na pagina 1 (segunda) e vai trazer 5 elementos por vez
        phmar.setFallbackPageable(new PageRequest(1, 5));
        //adiciona os parametros no metodo pai
        argumentResolvers.add(phmar);
    }
}
