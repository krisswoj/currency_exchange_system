package pl.krzysiek.api.simpay.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class SkipArrayAdapterFactory implements TypeAdapterFactory
{
    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken)
    {
        TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, typeToken);
        return (TypeAdapter<T>) new SkipArrayFactory((TypeAdapter<Object>) delegateAdapter);
    }

    public class SkipArrayFactory extends TypeAdapter<Object>
    {
        private TypeAdapter<Object> defaultAdapter;

        public SkipArrayFactory(TypeAdapter<Object> defaultAdapter)
        {
            this.defaultAdapter = defaultAdapter;
        }

        @Override
        public void write(JsonWriter out, Object value) throws IOException
        {
            defaultAdapter.write(out, value);
        }

        @Override
        public Object read(JsonReader in) throws IOException
        {
            if (in.peek() == JsonToken.BEGIN_ARRAY)
            {
                try
                {
                    return defaultAdapter.read(in);
                }
                catch (JsonSyntaxException ignored)
                {
                    in.skipValue();
                    return null;
                }
            }
            return defaultAdapter.read(in);
        }
    }
}
