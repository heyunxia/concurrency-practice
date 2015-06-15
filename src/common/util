package common.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author heyunxia.
 * @Description
 * @time 2015/5/15 14:44
 */
public class ConcurrencyUtils {

    ////////////////////////////////////////字典缓存////////////////////////////////////////////////////////////////

    public interface IClientDictionary<A, V> {
        V getDic(A groupName) throws InterruptedException;

        /**
         * 刷新缓存
         * @param key
         * @throws InterruptedException
         */
        void refresh(A key) throws InterruptedException;

        void refreshAll() throws InterruptedException;
    }

    public static class ClientDictionary<A, V> implements IClientDictionary<A, V> {

        private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
        private final IClientDictionary<A, V> d;

        public ClientDictionary(IClientDictionary<A, V> d) {
            this.d = d;
        }

        @Override
        public V getDic(final A groupName) throws InterruptedException {
            while (true) {

                Future<V> f = cache.get(groupName);
                try {
                    if (f == null) {
                        Callable<V> eval = new Callable<V>() {
                            @Override
                            public V call() throws Exception {
                                return d.getDic(groupName);
                            }
                        };

                        FutureTask<V> futureTask = new FutureTask<>(eval);
                        f = cache.putIfAbsent(groupName, futureTask);
                        if (f == null) {
                            f = futureTask;
                            futureTask.run();
                        }
                    }
                    return f.get();
                } catch (CancellationException e) {
                    cache.remove(groupName, f);
                } catch (ExecutionException e) {
                    f.cancel(true);
                    cache.remove(groupName);
                    //e.printStackTrace();
                }
            }
        }

        @Override
        public void refresh(A key) throws InterruptedException {
            cache.remove(key);
        }

        @Override
        public void refreshAll() throws InterruptedException {
            Set<A> set  = cache.keySet();
            for(A key: set) {
                cache.remove(key);
            }
        }
    }


    private static final IClientDictionary<String, List<Dictionary>> dic = new IClientDictionary<String, List<Dictionary>>() {
        @Override
        public List<Dictionary> getDic(String groupName) throws InterruptedException {
            try {
                List<Dictionary> list = null;//load data from db, filter by groupName..;
                return list;
            } catch (Exception e) {
                return new ArrayList<Dictionary>();
            }
        }

        @Override
        public void refresh(String key) throws InterruptedException {

        }

        @Override
        public void refreshAll() throws InterruptedException {

        }
    };


    private static final IClientDictionary<String, List<Dictionary>> cacheDictionary = new ClientDictionary<>(dic);

    public static List<Dictionary> getDictionary(String groupName) {

        try {
            List<Dictionary> list = cacheDictionary.getDic(groupName);
            if (list.size() == 0){
                cacheDictionary.refresh(groupName);
            }
            return list;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new ArrayList<Dictionary>();
        }
    }

    /**
     * 根据指定的groupName查询 对应key的value
     * @param groupName     字典名称
     * @param key            字典key
     * @return
     */
    public static Dictionary getDictionaryByKey(String groupName, String key) {
        Dictionary dict = null;

        List<Dictionary> list = getDictionary(groupName);
        for (Dictionary dictonary : list) {
            if (dictonary.getDictKey().equals(key)) {
                dict = dictonary;
                break;
            }
        }
        if (dict == null) {
            dict = new Dictionary();
        }
        return dict;
    }
}
