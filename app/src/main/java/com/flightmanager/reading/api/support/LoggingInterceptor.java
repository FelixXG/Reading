package com.flightmanager.reading.api.support;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpEngine;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Retrofit2 Logger拦截器
 */

public class LoggingInterceptor implements Interceptor {
    private static final Charset UTF8=Charset.forName("UTF-8");

    public enum Level{
        NONE,

        BASIC,

        HEADERS,

        BODY
    }

    public interface Logger{
        void log(String message);

        Logger DEFAULT=new Logger() {
            @Override
            public void log(String message) {

            }
        };
    }


    public LoggingInterceptor(){
        this(Logger.DEFAULT);
    }

    public LoggingInterceptor(Logger logger){
        this.logger = logger;
    }

    private final Logger logger;

    private volatile Level level=Level.NONE;

    public LoggingInterceptor setLevel(Level level){
        if(level==null) throw new NullPointerException("level==null Use Level.NONE instead");
        this.level=level;
        return this;
    }

    public Level getLevel(){
        return level;
    }



    @Override
    public Response intercept(Chain chain) throws IOException {
        Level level=this.level;

        Request request=chain.request();
        if(level==Level.NONE){
            return chain.proceed(request);
        }

        boolean logBody=level==level.BODY;
        boolean logHeaders=logBody||level==level.HEADERS;

        RequestBody requestBody=request.body();
        boolean hasRequestBody=requestBody!=null;

        Connection connection=chain.connection();
        Protocol protocol=connection!=null?connection.protocol():Protocol.HTTP_1_1;
        String requestStartMessage=
                "-->"+request.method()+' '+request.url()+' '+protocol(protocol);
        if(!logHeaders&&hasRequestBody){
            requestStartMessage+="("+requestBody.contentLength()+"-byte body)";
        }
        logger.log(requestStartMessage);
        if(logHeaders){
            if(hasRequestBody){
                if(requestBody.contentType()!=null){
                    logger.log("Content-Type: "+requestBody.contentType());
                }
                if(requestBody.contentLength()!=-1){
                    logger.log("Content-Length:"+requestBody.contentLength());
                }
            }

            Headers headers=request.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name=headers.name(i);
                if(!"Content-Type".equalsIgnoreCase(name)&&!"Content-Length".equalsIgnoreCase(name)){
                    logger.log(name+": "+headers.value(i));
                }
            }

            if(!logBody||!hasRequestBody){
                logger.log("--->END"+request.method());
            }else if(bodyEncoded(request.headers())){
                logger.log("--->END"+request.method()+"("+requestBody.contentLength()+"-byte body");
            }

        }

        long startNs=System.nanoTime();
        Response response=chain.proceed(request);
        long tookMs= TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-startNs);

        ResponseBody responseBody=response.body();
        long contentLength=responseBody.contentLength();
        String bodySize=contentLength!=-1?contentLength+"-byte":"unknow-length";
        logger.log("<--"
                +response.code()
                +' '
                +response.message()
                +' '
                +response.request().url()
                +"("
                +tookMs
                +"ms"
                +(!logHeaders?","+bodySize+"body":"")
                +')');
        if(logHeaders){
            Headers headers=response.headers();
            for(int i=0,count=headers.size();i<count;i++){
                logger.log(headers.name(i)+":"+headers.value(i));
            }

            if(!logBody||!HttpEngine.hasBody(response)){
                logger.log("<-- END HTTP");
            }else if(bodyEncoded(response.headers())){
                  logger.log("<-- END HTTP (encode body omitted)");
            }else{
                BufferedSource source=responseBody.source();
                source.request(Long.MAX_VALUE);//Buffer the entire body
                Buffer buffer=new Buffer();
                requestBody.writeTo(buffer);

                Charset charset=UTF8;
                MediaType contentType=requestBody.contentType();
                if(contentType!=null){
                    charset=contentType.charset(UTF8);
                }

                logger.log("");
                logger.log(buffer.readString(charset));
                logger.log("--> END"+request.method()+"("+requestBody.contentLength()+"-byte body");
            }
        }


        return response;
    }

    private boolean bodyEncoded(Headers headers){
        String contentEncoding= headers.get("Content-Encoding");
        return contentEncoding!=null&&!contentEncoding.equalsIgnoreCase("identity");
    }

    private static String protocol(Protocol protocol){
        return protocol==Protocol.HTTP_1_0?"HTTP/1.0":"HTTP/1.1";
    }

}
