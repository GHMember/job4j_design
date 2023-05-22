package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String[] arr = in.readLine().split("=");
                    String clientAsk = arr[1].substring(0, arr[1].indexOf(" "));
                    if (clientAsk.contains("Exit")) {
                        server.close();
                    }
                    out.write(clientAsk.getBytes());
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Server isn't responding", e);
        }
    }
}
