package ir.piana.business.store.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties("image-loader")
@Getter
@Setter
@NoArgsConstructor
public class ImageLoaderProperties {
    Map<String, String> groups;
}
