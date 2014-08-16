package pl.mjasion.nettool.conf

import org.apache.catalina.connector.Connector
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory
import org.springframework.context.annotation.Bean
import org.springframework.util.ResourceUtils

//@Configuration
//@Profile(Profiles.DEVELOP)
class TomcatConfiguration {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new MyCustomizer();
    }

    private static class MyCustomizer implements EmbeddedServletContainerCustomizer {

        @Override
        public void customize(ConfigurableEmbeddedServletContainer factory) {
            if (factory instanceof TomcatEmbeddedServletContainerFactory) {
                customizeTomcat((TomcatEmbeddedServletContainerFactory) factory);
            }
        }

        public void customizeTomcat(TomcatEmbeddedServletContainerFactory factory) {
            factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
                @Override
                public void customize(Connector connector) {
                    connector.setPort(8443);
                    connector.setSecure(true);
                    connector.setScheme("https");
                    connector.setAttribute("keyAlias", "tomcat");
                    connector.setAttribute("keystorePass", "password");
                    try {
                        connector.setAttribute("keystoreFile",
                                ResourceUtils.getFile("src/ssl/tomcat.keystore").getAbsolutePath());
                    } catch (FileNotFoundException e) {
                        throw new IllegalStateException("Cannot load keystore", e);
                    }
                    connector.setAttribute("clientAuth", "false");
                    connector.setAttribute("sslProtocol", "TLS");
                    connector.setAttribute("SSLEnabled", true);
                }
            });
        }

    }

}
