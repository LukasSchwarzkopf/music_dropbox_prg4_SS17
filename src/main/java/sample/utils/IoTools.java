package sample.utils;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rnimmagadda
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scott Taylor
 */
public final class IoTools
{
    private static final Logger LOG = Logger.getLogger(IoTools.class.getName());
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    protected IoTools()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Closes the <code>InputStream</code>, catching and ignoring any
     * <code>IOException<code> that is thrown.
     *
     * @param c the <code>InputStream</code> to close.
     */
    public static void closeQuietly(InputStream inputStream)
    {
        try
        {
            inputStream.close();
        }
        catch (IOException e)
        {
            // Is logging the exception breaking the quiet contract.
            LOG.log(Level.FINE, "Exception while closing InputStream.", e);
        }
    }

    /**
     * Closes the <code>OutputStream</code>, catching and ignoring any
     * <code>IOException<code> that is thrown.
     *
     * @param c the <code>OutputStream</code> to close.
     */
    public static void closeQuietly(OutputStream outputStream)
    {
        try
        {
            outputStream.close();
        }
        catch (IOException e)
        {
            // Is logging the exception breaking the quiet contract.
            LOG.log(Level.FINE, "Exception while closing OutputStream.", e);
        }
    }

    /**
     * Closes the <code>InputStream</code>, catching and ignoring any
     * <code>IOException<code> that is thrown.
     *
     * @param c the <code>InputStream</code> to close.
     */
    public static void closeQuietly(Reader reader)
    {
        try
        {
            reader.close();
        }
        catch (IOException e)
        {
            // Is logging the exception breaking the quiet contract.
            LOG.log(Level.FINE, "Exception while closing Reader.", e);
        }
    }

    /**
     * Closes the <code>OutputStream</code>, catching and ignoring any
     * <code>IOException<code> that is thrown.
     *
     * @param c the <code>OutputStream</code> to close.
     */
    public static void closeQuietly(Writer writer)
    {
        try
        {
            writer.close();
        }
        catch (IOException e)
        {
            // Is logging the exception breaking the quiet contract.
            LOG.log(Level.FINE, "Exception while closing Writer.", e);
        }
    }

    public static URL getResourceUrl(String path)
    {
        return new ClassPathLoader().getResourceUrl(path);
    }

    public static InputStream getResourceAsStream(String path)
    {
        return new ClassPathLoader().getResourceAsStream(path);
    }

    public static String getResourceAsString(String path, Charset charset)
    {
        final InputStream inputStream = getResourceAsStream(path);
        try
        {
            return toString(inputStream, charset);
        }
        finally
        {
            closeQuietly(inputStream);
        }
    }

    public static String toString(InputStream inputStream, Charset charset)
    {
        final StringWriter sw = new StringWriter(DEFAULT_BUFFER_SIZE);
        copy(new InputStreamReader(inputStream, charset), sw);
        return sw.toString();
    }

    private static void copy(Reader reader, Writer writer)
    {
        try
        {
            final char[] buffer = new char[DEFAULT_BUFFER_SIZE];
            while (true)
            {
                final int n = reader.read(buffer);
                if (n == -1)
                {
                    break;
                }
                writer.write(buffer, 0, n);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


}
