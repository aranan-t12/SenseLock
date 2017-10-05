package com.group25.design3a.senselock3;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;

import java.util.Arrays;

/**
 *
 * Created by Aranan on 5/9/2017.
 *
 */



public class HceService extends HostApduService{


    private static final byte[] SELECT_AID = {0x00, (byte) 0xA4, 0x00,0x00,0x07,(byte) 0xF0, 0x46, 0x52,0x47,0x41,0x4C};
    private static final byte[] MY_UID = {0x05,0x04,0x08,0x01, (byte) 0x90,0x00};
    private static final byte[] MY_ERROR = {0x6F, 0x00};

    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {

        if (Arrays.equals(SELECT_AID, apdu))
        {
            return MY_UID;
        }
        else return MY_ERROR;
    }
    @Override
    public void onDeactivated(int reason) {

    }
}