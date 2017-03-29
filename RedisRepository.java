package com.verint.im.messaging.conversation.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisRepository {

	@Autowired 
	private RedisTemplate< String, Object > template;

	public Object getValue( final String key ) {
	    return template.opsForValue().get( key );
	}

	public void setValue( final String key, final String value ) {
		if (!this.template.hasKey(key)) //No Duplicates. 
		{
			template.opsForValue().set( key, value );
			template.expire( key, 120, TimeUnit.SECONDS );
		}
	}
}
