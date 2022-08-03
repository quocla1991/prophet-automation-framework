package utils;

import org.aeonbits.owner.ConfigFactory;

public class Config {

    public static Environments ENV = ConfigFactory.create(Environments.class, System.getenv());

}
