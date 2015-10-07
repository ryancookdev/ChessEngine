package software.ryancook.generics;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.Map.Entry;

public class MultiQueue<T>
{
    private int size;
    private TreeMap<String, Queue<T>> multiQueue;

    public MultiQueue()
    {
        multiQueue = new TreeMap<>();
    }

    public void addLevel(String levelName)
    {
        if (levelName == null) {
            throw new InvalidParameterException();
        }
        checkIfLevelNameIsUnique(levelName);
        multiQueue.put(levelName, new LinkedList<>());
    }

    private void checkIfLevelNameIsUnique(String levelName)
    {
        if (multiQueue.containsKey(levelName)) {
            throw new DuplicateLevelException();
        }
    }

    public void removeLevel()
    {
        clearLevelAndDecreaseCount();
    }

    private void clearLevelAndDecreaseCount()
    {
        Queue<T> level = getHeadLevel();
        size -= level.size();
        level.clear();
    }

    private Queue<T> getHeadLevel()
    {
        Entry<String, Queue<T>> firstEntry = multiQueue.firstEntry();
        if (firstEntry == null) {
            throw new LevelNotFoundException();
        }
        return firstEntry.getValue();
    }

    public void add(T obj)
    {
        Queue<T> level = getTailLevel();
        level.add(obj);
        size++;
    }

    private Queue<T> getTailLevel()
    {
        Entry<String, Queue<T>> lastEntry = multiQueue.lastEntry();
        if (lastEntry == null) {
            throw new LevelNotFoundException();
        }
        return lastEntry.getValue();
    }

    public T next()
    {
        removeLevelIfEmpty();
        if (noLevelsExist()) {
            return null;
        }
        return popNextObject();
    }

    private T popNextObject()
    {
        Queue<T> level = getHeadLevel();
        if (level.size() == 0) {
            return null;
        }
        size--;
        return level.remove();
    }

    private void removeLevelIfEmpty()
    {
        while (multiQueue.size() > 0) {
            Entry<String, Queue<T>> levelEntry = multiQueue.firstEntry();
            if (levelEntry.getValue().size() == 0) {
                multiQueue.remove(levelEntry.getKey());
                continue;
            }
            break;
        }
    }

    private boolean noLevelsExist()
    {
        return multiQueue.size() == 0;
    }

    public int size()
    {
        return size;
    }
}
