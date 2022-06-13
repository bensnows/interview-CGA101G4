package com.taiwan.utils.config;

import java.util.Optional;

public class RedisConfig {

	public static final  String REDIS_HOST = "localhost";
	public static final  String PARAM_REDIS_HOST = "REDIS_HOST";

	public static String getRedisHost() { 
		return  Optional.ofNullable(System.getenv(PARAM_REDIS_HOST))
				.orElse( Optional.ofNullable(System.getProperty(PARAM_REDIS_HOST))
				.orElse(REDIS_HOST));
	}

}
