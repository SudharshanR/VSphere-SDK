package Lab1;

public interface VMState
{
    public void on();

    public void off();

    public void suspend();
    
    public void reset();
    
    public void forceShutDown();
}

