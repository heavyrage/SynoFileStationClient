package com.heavyrage.syno.client.interceptors;

import com.google.common.primitives.Bytes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class SynoUploadHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if(request.getMethod().name().equalsIgnoreCase("POST") ) {
            body = removeByteSequence(body, "Content-Type: application/octet-stream\r\n".getBytes());
            body = removeVariableByteSequence(body, "Content-Length");
        }

        return execution.execute(request, body);
    }

    public static String guessEncoding(byte[] bytes) {
        String DEFAULT_ENCODING = "UTF-8";
        org.mozilla.universalchardet.UniversalDetector detector =
                new org.mozilla.universalchardet.UniversalDetector(null);
        detector.handleData(bytes, 0, bytes.length);
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();
        detector.reset();
        if (encoding == null) {
            encoding = DEFAULT_ENCODING;
        }
        return encoding;
    }

    public static byte[] removeByteSequence(byte[] body, byte[] toBeRemoved) {
        while(Bytes.indexOf(body, toBeRemoved) != -1) {
            int index = Bytes.indexOf(body, toBeRemoved);
            byte[] tempBody1 = Arrays.copyOfRange(body, 0, index);
            byte[] tempBody2 = Arrays.copyOfRange(body, index+toBeRemoved.length, body.length);
            ByteBuffer buffer = ByteBuffer.allocate(tempBody1.length+ tempBody2.length);
            buffer.put(tempBody1);
            buffer.put(tempBody2);
            body = buffer.array();
        }
        return body;
    }

    public static byte[] removeVariableByteSequence(byte[] body, String removableStr) {
        byte[] toBeRemoved = removableStr.getBytes();
        byte[] extra = "\r\n".getBytes();
        if (removableStr.equalsIgnoreCase("Content-Length")) {
            while(Bytes.indexOf(body, toBeRemoved) != -1) {
                int index = Bytes.indexOf(body, toBeRemoved);
                byte[] tempBody1 = Arrays.copyOfRange(body, 0, index);
                byte[] tempBody2 = Arrays.copyOfRange(body, index+toBeRemoved.length, body.length);
                int otherIndex = Bytes.indexOf(tempBody2, extra);
                tempBody2 = Arrays.copyOfRange(tempBody2, otherIndex+extra.length, tempBody2.length);
                ByteBuffer buffer = ByteBuffer.allocate(tempBody1.length+ tempBody2.length);
                buffer.put(tempBody1);
                buffer.put(tempBody2);
                body = buffer.array();
            }
        }

        return body;
    }
}
