package pl.krzysiek.api.simpay.models;

import com.google.gson.annotations.SerializedName;

public class SimpayStatusRespond
{
    private final String code;
    private final SimpayStatusType status;
    private final int test;

    @SerializedName("time_used")
    private final long timeUsed;

    public SimpayStatusRespond(String code, SimpayStatusType status, int test, long timeUsed)
    {
        this.code = code;
        this.status = status;
        this.test = test;
        this.timeUsed = timeUsed;
    }

    public String getCode()
    {
        return code;
    }

    public SimpayStatusType getStatus()
    {
        return status;
    }

    public boolean isTest()
    {
        return test == 1;
    }

    public long getTimeUsed()
    {
        return timeUsed;
    }

    @Override
    public String toString()
    {
        return "SimpayStatusRespond{" + "code='" + code + '\'' + ", status='" + status + '\'' + ", test=" + test + ", timeUsed=" + timeUsed + '}';
    }
}
