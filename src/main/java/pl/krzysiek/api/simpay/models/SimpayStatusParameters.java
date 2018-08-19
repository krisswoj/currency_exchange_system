package pl.krzysiek.api.simpay.models;

import com.google.gson.annotations.SerializedName;

public class SimpayStatusParameters
{
    private SimpayAuth auth;

    @SerializedName("service_id")
    private int serviceId;
    private int number;
    private String code;

    public SimpayStatusParameters(SimpayAuth auth, int serviceId, int number, String code)
    {
        this.auth = auth;
        this.serviceId = serviceId;
        this.number = number;
        this.code = code;
    }

    public SimpayAuth getAuth()
    {
        return auth;
    }

    public void setAuth(SimpayAuth auth)
    {
        this.auth = auth;
    }

    public int getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(int serviceId)
    {
        this.serviceId = serviceId;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "SimpayStatusParameters{" + "auth=" + auth + ", serviceId=" + serviceId + ", number=" + number + ", code='" + code + '\'' + '}';
    }
}
