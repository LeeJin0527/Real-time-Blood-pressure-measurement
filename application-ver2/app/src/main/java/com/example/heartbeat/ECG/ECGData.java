package com.example.heartbeat.ECG;

import android.util.Log;

public class ECGData {
    private byte[] packet;
    private int count;
    private int RtoR;
    private int RtoRBpm;
    private int ecg1;
    private int ecg2;
    private int ecg3;
    private int ecg4;
    private int eTag;
    private int pTag;
    private int ecg1eTag;
    private int ecg1pTag;
    private int ecg2eTag;
    private int ecg2pTag;
    private int ecg3eTag;
    private int ecg3pTag;
    private int ecg4eTag;
    private int ecg4pTag;
    private int test_value_graph;

    public ECGData(){
        this.RtoR = 0;
        this.RtoRBpm = 0;
    }

    public void setPacket(byte[] packet) {
        this.packet = packet;

        processData();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setRtor(int RtoR) {
        this.RtoR = RtoR;
    }

    public void setARtoR(int RtoRBpm){
        this.RtoRBpm = RtoRBpm;
    }

    public int getCount() {
        return this.count;
    }

    public int getRtoR(){
        return this.RtoR;
    }

    public int getRtoRBpm() {
        return this.RtoRBpm;
    }

    public int getEcg1() {
        return this.ecg1;
    }
    public int getEcg2(){return this.ecg2;}
    public int getEcg3(){return this.ecg3;}
    public int getEcg4(){return this.ecg4;}
    public int geteTag(){return this.eTag;}
    public int getpTag(){return this.pTag;}
    public int getEcg1eTag(){return this.ecg1eTag;}
    public int getEcg2eTag(){return this.ecg2eTag;}
    public int getEcg3eTag(){return this.ecg3eTag;}
    public int getEcg4eTag(){return this.ecg4eTag;}

    public int getTest_value_graph(){return this.test_value_graph;}

    public void processData(){
        int tmp = 0;
        int total_ecg = 0;
        byte[] dataPacket = this.packet;
        this.count = dataPacket[1] & 0xff;

        this.ecg1 = ((dataPacket[4] & 0xc0) >> 6) + ((dataPacket[5]& 0xff) << 2) + ((dataPacket[6] & 0xff) << 10) + ((dataPacket[7] &0x3f) << 18);
        this.ecg2 = ((dataPacket[7] & 0xc0) >> 6) + ((dataPacket[8] & 0xff) << 2) + ((dataPacket[9] & 0xff) << 10) + ((dataPacket[10] & 0x3f) << 18);
        this.ecg3 = ((dataPacket[10] & 0xc0) >> 6) + ((dataPacket[11] & 0xff) << 2) + ((dataPacket[12] & 0xff) << 10) + ((dataPacket[13] & 0x3f) << 18);
        this.ecg4 = ((dataPacket[13] & 0xc0) >> 6) + ((dataPacket[14] & 0xff) << 2) + ((dataPacket[15] & 0xff) << 10) + ((dataPacket[16] & 0x3f) << 18);

        this.ecg1eTag = (this.ecg1 & 0x38) >> 3;
        this.ecg1pTag = (this.ecg1 & 0x07);
        this.ecg1 = this.ecg1 >> 6;

        this.ecg2eTag = (this.ecg2 & 0x38) >> 3;
        this.ecg2pTag = (this.ecg2 & 0x07);
        this.ecg2 = this.ecg2 >> 6;

        this.ecg3eTag = (this.ecg3 & 0x38) >> 3;
        this.ecg3pTag = (this.ecg3 & 0x07);
        this.ecg3 = this.ecg3 >> 6;

        this.ecg4eTag = (this.ecg4 & 0x38) >> 3;
        this.ecg4pTag = (this.ecg4 & 0x07);
        this.ecg4 = this.ecg4 >> 6;

        //Log.i("eTag : ", Integer.toString(this.eTag));
        //Log.i("pTag : ", Integer.toString(this.pTag));


        tmp = (dataPacket[2] & 0xff) + ((dataPacket[3] & 0x3f) << 8);
        if(tmp != 0)this.RtoR = tmp;

        tmp = ((dataPacket[3] & 0xc0) >> 6) + ((dataPacket[4] & 0x3f) << 2);
        if(tmp != 0)this.RtoRBpm = tmp;


        //tmp_data = ((dataPacket[4] & 0xc0) >> 6) + ((dataPacket[5]& 0xff) << 2) + ((dataPacket[6] & 0xff) << 10) + ((dataPacket[7] &0x3f) << 18);
        //tmp_data = this.ecg1;
        //tmp_data = total_ecg;


        //tmp_data = tmp_data >> 6;
        //Log.i("전체 데이터 : ", Integer.toBinaryString(tmp_data));


        if((this.ecg1 & 0x20000) != 0x0){
            this.ecg1 = this.ecg1 ^ 0x3ffff;
            this.ecg1 = this.ecg1 + 0x01;
            this.ecg1 = - this.ecg1;
        }

        if((this.ecg2 & 0x20000) != 0x0){
            this.ecg2 = this.ecg2 ^ 0x3ffff;
            this.ecg2 = this.ecg2 + 0x01;
            this.ecg2 = - this.ecg2;
        }
        if((this.ecg3 & 0x20000) != 0x0){
            this.ecg3 = this.ecg3 ^ 0x3ffff;
            this.ecg3 = this.ecg3 + 0x01;
            this.ecg3 = - this.ecg3;
        }
        if((this.ecg4 & 0x20000) != 0x0){
            this.ecg4 = this.ecg4 ^ 0x3ffff;
            this.ecg4 = this.ecg4 + 0x01;
            this.ecg4 = - this.ecg4;
        }

        if(this.ecg1eTag == 0 || this.ecg1eTag == 2)test_value_graph = this.ecg1;
        
        if(this.ecg2eTag == 0 || this.ecg2eTag == 2)test_value_graph = this.ecg2;

        if(this.ecg3eTag == 0 || this.ecg3eTag == 2)test_value_graph = this.ecg3;

        if(this.ecg4eTag == 0 || this.ecg4eTag == 2)test_value_graph = this.ecg4;
        //모든 eTag값을 검사해봐야함, 뭔가 두번이상 실행되는 분기문도 있을듯

    }

}
