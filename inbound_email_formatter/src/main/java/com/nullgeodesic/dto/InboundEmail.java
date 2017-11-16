package com.nullgeodesic.dto;

import java.util.List;
import java.util.Map;

public class InboundEmail {

	public final String source;
	public final String subject;

	public InboundEmail(Map<Object,Object> map) {
		final Map<Object, Object> record = getItemInList(map, "Records",0);
		final Map<Object, Object> ses = get(record, "ses");
		final Map<Object, Object> mail = get(ses, "mail");
		this.source = mail.get("source").toString();
		final Map<Object, Object> commonHeaders = get(mail, "commonHeaders");
		this.subject = commonHeaders.get("subject").toString();
	}

	private Map<Object, Object> get(Map<Object, Object> map, String target) {
		return safeMap(getSafely(map, target));
	}

	private List<Object> getList(Map<Object, Object> map, String target) {
		return safeList(getSafely(map, target));
	}

	private Map<Object, Object> getItemInList(Map<Object, Object> map, String target, int item) {
		return safeMap((getList(map,target)).get(0));
	}
	

	@SuppressWarnings("unchecked")
	private Map<Object, Object> safeMap(final Object newMap) {
		if(!(newMap instanceof Map)) {
			throw new RuntimeException("Object is not of type InboundEmail");
		}
		return (Map<Object, Object>) newMap;
	}
	
	@SuppressWarnings("unchecked")
	private List<Object> safeList(final Object list) {
		if(!(list instanceof List)) {
			throw new RuntimeException("Object is not of type InboundEmail");
		}
		return (List<Object>) list;
	}
	
	private Object getSafely(Map<Object, Object> map, String target) {
		final Object object = map.get(target);
		if(object == null) {
			throw new RuntimeException("Object is not of type InboundEmail");
		}
		return object;
	}	
	
}
