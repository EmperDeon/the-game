package main.db.table;

import main.db.util.Slice;

import java.util.Comparator;

// todo this interface needs more thought
public interface UserComparator extends Comparator<Slice>
{
    String name();

    Slice findShortestSeparator(Slice start, Slice limit);

    Slice findShortSuccessor(Slice key);
}
