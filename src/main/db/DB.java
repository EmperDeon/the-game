package main.db;

import java.io.Closeable;
import java.util.Map;

public interface DB extends Iterable<Map.Entry<byte[], byte[]>>, Closeable {

    public byte[] get(byte[] key) throws DBException;
    public byte[] get(byte[] key, ReadOptions options) throws DBException;

    public DBIterator iterator();
    
    public DBIterator iterator(ReadOptions options);

    public void put(byte[] key, byte[] value) throws DBException;
    public void delete(byte[] key) throws DBException;
    public void write(WriteBatch updates) throws DBException;

    public WriteBatch createWriteBatch();

    public Snapshot put(byte[] key, byte[] value, WriteOptions options) throws DBException;

    public Snapshot delete(byte[] key, WriteOptions options) throws DBException;

    public Snapshot write(WriteBatch updates, WriteOptions options) throws DBException;

    public Snapshot getSnapshot();

    public long[] getApproximateSizes(Range ... ranges);
    
    public String getProperty(String name);

    public void suspendCompactions() throws InterruptedException;

    public void resumeCompactions();

    public void compactRange(byte[] begin, byte[] end) throws DBException;
}
