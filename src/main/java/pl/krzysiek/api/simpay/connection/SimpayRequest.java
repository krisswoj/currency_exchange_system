package pl.krzysiek.api.simpay.connection;

import com.google.gson.annotations.SerializedName;
import pl.krzysiek.api.simpay.models.SimpayError;

import java.util.ArrayList;
import java.util.List;

public class SimpayRequest<P>
{
    @SerializedName("error")
    private final List<SimpayError> errors = new ArrayList<>();

    @SerializedName("params")
    private P parameters;

    public SimpayRequest(P parameters)
    {
        this.parameters = parameters;
    }

    public P getParameters()
    {
        return parameters;
    }

    public void setParameters(P parameters)
    {
        this.parameters = parameters;
    }

    public SimpayError getError()
    {
        return this.errors.size() < 1 ? null : this.errors.get(0);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("SimpayRequest{");
        sb.append("parameters=").append(parameters);
        sb.append(", error=").append(getError());
        sb.append('}');
        return sb.toString();
    }
}
