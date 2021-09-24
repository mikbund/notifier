package dk.notfound.notifier;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application.notifier")
@Setter
@Getter
public class ApplicationConfiguration {

 String eventsMaxAge = "90";

}
