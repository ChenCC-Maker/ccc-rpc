package com.ccc.protocol;

import com.ccc.common.Invocation;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    /**
     * 发送传统的http网络请求
     * @param hostName
     * @param port
     * @param invocation
     * @return
     * @param <T>
     */
    public <T> String send(String hostName, int port, Invocation invocation) throws IOException {
        URL url = new URL("http", hostName, port, "/");
        // 通过java.net.HttpURLConnection发送HTTP请求，打开连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        // 启用输出流
        connection.setDoOutput(true);

        // 将invocation对象序列化成字节数组，并写入到输出流中
        OutputStream outputStream = connection.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(invocation);
        objectOutputStream.flush();
        objectOutputStream.close();

        // 获取返回结果
        InputStream inputStream = connection.getInputStream();
        // 使用BufferedReader将InputStream转换为字符串
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();
        return result.toString();
    }
}