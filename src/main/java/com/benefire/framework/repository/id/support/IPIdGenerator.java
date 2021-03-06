package com.benefire.framework.repository.id.support;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.benefire.framework.repository.id.IdGenerator;


/**
 * Example IP 192.168.1.108 :11000000 10101000 00000001 01101100，
 * Intercepting the last 10 bits 01 01101100,Decimal system：364, workerId > 364.
 */
public class IPIdGenerator extends CommonIdGenerator implements IdGenerator{

    { initWorkerId(); }

    void initWorkerId() {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!");
        }
        byte[] ipAddressByteArray = address.getAddress();
        this.setWorkerId((long) (((ipAddressByteArray[ipAddressByteArray.length - 2] & 0B11) << Byte.SIZE) + (ipAddressByteArray[ipAddressByteArray.length - 1] & 0xFF)));
    }

    @Override
    public Serializable generate() {
    	
        return this.createId();
    }
    
}
