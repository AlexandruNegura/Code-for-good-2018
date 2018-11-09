package jp.morgan.jp;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ModelMapperConfig implements WebMvcConfigurer {

    @Bean
    public ModelMapper getBeanModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                   .setFieldMatchingEnabled(true)
                   .setMatchingStrategy(MatchingStrategies.STRICT)
                   .setAmbiguityIgnored(true)
                   .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PACKAGE_PRIVATE)
                   .setImplicitMappingEnabled(true)
                   .setFullTypeMatchingRequired(true);

        return modelMapper;
    }

}