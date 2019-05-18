package hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ser.springweb.SerializedDataMessageConverter;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    // Override configuration methods...
    @Nullable
    private ApplicationContext applicationContext;

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
        if (this.applicationContext != null) {
            builder.applicationContext(this.applicationContext);
        }
        converters.add(0, new SerializedDataMessageConverter(builder.build()));
    }

}