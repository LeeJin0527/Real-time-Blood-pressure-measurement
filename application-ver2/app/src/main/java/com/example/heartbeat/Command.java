package com.example.heartbeat;

public class Command {
    public byte[] str_readtemp0 = {0x72, 0x65, 0x61, 0x64, 0x20, 0x74, 0x65, 0x6d, 0x70, 0x20, 0x30, 0x0a, 0x00, 0x00, 0x00, 0x00};
    public byte[] str_readppg0 = {0x72, 0x65, 0x61, 0x64, 0x20, 0x70, 0x70, 0x67, 0x20, 0x30, 0x0a, 0x00, 0x00, 0x00, 0x00, 0x00};
    public byte[] str_readecg2 = {0x72, 0x65, 0x61, 0x64, 0x20, 0x65, 0x63, 0x67, 0x20, 0x32, 0x0a, 0x00, 0x00, 0x00, 0x00, 0x00};
    public byte[] str_stop = {0x73, 0x74, 0x6f, 0x70, 0x0a, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

}
