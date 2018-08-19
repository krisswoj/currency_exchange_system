package pl.krzysiek.api.simpay.connection;

import pl.krzysiek.api.simpay.models.SimpayStatusParameters;

public class SimpayStatusRequest extends SimpayRequest<SimpayStatusParameters>
{
    public SimpayStatusRequest(SimpayStatusParameters parameters)
    {
        super(parameters);
    }
}
