package pl.krzysiek.api.simpay.connection;

public class SimpayResponse<R, P> extends SimpayRequest<P>
{
    private R respond;

    public SimpayResponse(R respond, P parameters)
    {
        super(parameters);
        this.respond = respond;
    }

    public R getRespond()
    {
        return respond;
    }

    public void setRespond(R respond)
    {
        this.respond = respond;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("SimpayResponse{");
        sb.append("respond=").append(respond);
        sb.append(", parameters=").append(getParameters());
        sb.append(", error=").append(getError());
        sb.append('}');
        return sb.toString();
    }
}
