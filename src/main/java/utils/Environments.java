package utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.FIRST)
@Sources({"classpath:env.${env}.properties"
})
public interface Environments extends Config {

    @Key("WEB_CHALLENGE_1.URL")
    String WEB_CHALLENGE_1();

    @Key("WEB_CHALLENGE_2.URL")
    String WEB_CHALLENGE_2();

    @Key("location")
    @DefaultValue("lc")
    String RUN_LOCATION();

    @Key("page.time_out")
    @DefaultValue("30")
    long TIME_OUTS();

}
