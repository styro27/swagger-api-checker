package org.example.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:env", "classpath:${profile}.properties", "file:~/${profile}.properties"})
public interface IPetBaseConfig extends Config {
    @Key("baseUrl")
    String baseUrl();
}
