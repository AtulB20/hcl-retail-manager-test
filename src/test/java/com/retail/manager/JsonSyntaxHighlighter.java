package com.retail.manager;

import java.nio.ByteBuffer;

import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.ContentModifier;

public class JsonSyntaxHighlighter implements ContentModifier {

	@Override
	public byte[] modifyContent(byte[] content, MediaType arg1) {
		byte[] end = "\n----\n\n".getBytes();
		byte[] start = ("[source,json,options=\"nowrap\"]\n" + "----\n").getBytes();
		ByteBuffer byteBuffer = ByteBuffer.allocate(start.length + content.length + end.length);
		return byteBuffer.put(end).put(start).put(content).array();
	}

}
