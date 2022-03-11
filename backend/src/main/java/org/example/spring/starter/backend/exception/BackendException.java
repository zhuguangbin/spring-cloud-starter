package org.example.spring.starter.backend.exception;

public class BackendException extends RuntimeException
{

    private Status status;
    private String message;

    public BackendException(String message, Status status)
    {
        super(message);
        this.status = status;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
