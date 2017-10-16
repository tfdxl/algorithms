package com.tfdxl.nio;

import java.nio.ByteBuffer;

public class BufferTest {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(88);
        String value = "Netty 权威指南";
        byteBuffer.put(value.getBytes());
        byteBuffer.flip();

        int remaining = byteBuffer.remaining();
        System.out.println("remaining ---> " + remaining);

        byte[] vArray = new byte[byteBuffer.remaining()];
        byteBuffer.get(vArray);

        String decodeValue = new String(vArray);
        System.out.println("Decode value: " + decodeValue);


    }
}
