package pl.krzysiek.api.simpay.models;

import com.google.gson.annotations.SerializedName;

public class SimpayError
{
    @SerializedName("error_code")
    private int code;

    @SerializedName("error_name")
    private String name;

    @SerializedName("error_value")
    private String value;

    public SimpayError(int code, String name, String value)
    {
        this.code = code;
        this.name = name;
        this.value = value;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "SimpayError{" + "code=" + code + ", name='" + name + '\'' + ", value='" + value + '\'' + '}';
    }
}
