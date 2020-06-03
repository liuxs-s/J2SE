线程资源必须通过线程池提供，不允许在应用中自行显式创建线程。 
说明：使用线程池的好处是减少在创建和销毁线程上所花的时间以及系统资源的开销，解决资源不足的问题。
如果不使用线程池，有可能造成系统创建大量同类线程而导致消耗完内存或者“过度切换”的问题。
            
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
        .setNameFormat("demo-pool-%d").build();
    ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
    singleThreadPool.shutdown();
    
   public class FutureTask<V> implements RunnableFuture<V> {}
   public interface RunnableFuture<V> extends Runnable, Future<V> {
       /**
        * Sets this Future to the result of its computation
        * unless it has been cancelled.
        */
       void run();
   }
   @FunctionalInterface
   public interface Callable<V> {
       /**
        * Computes a result, or throws an exception if unable to do so.
        *
        * @return computed result
        * @throws Exception if unable to compute a result
        */
       V call() throws Exception;
   }
首先Callable<V>同Runable一样，用来书写线程任务的。 
  
Future接口是Java标准API的一部分，在java.util.concurrent包中。Future接口是Java线程Future模式的实现，可以来进行异步计算。
Future模式可以这样来描述：我有一个任务，提交给了Future，Future替我完成这个任务。期间我自己可以去做任何想做的事情。一段时间之后，
我就便可以从Future那儿取出结果。就相当于下了一张订货单，一段时间后可以拿着提订单来提货，这期间可以干别的任何事情。
其中Future 接口就是订货单，真正处理订单的是Executor类，它根据Future接口的要求来生产产品。

Future接口提供方法来检测任务是否被执行完，等待任务执行完获得结果，也可以设置任务执行的超时时间。
这个设置超时的方法就是实现Java程序执行超时的关键。
Future接口是一个泛型接口，严格的格式应该是Future<V>，其中V代表了Future执行的任务返回值的类型。 Future接口的方法介绍如下：

boolean cancel (boolean mayInterruptIfRunning) 取消任务的执行。参数指定是否立即中断任务执行，或者等等任务结束
boolean isCancelled () 任务是否已经取消，任务正常完成前将其取消，则返回 true
boolean isDone () 任务是否已经完成。需要注意的是如果任务正常终止、异常或取消，都将返回true
V get () throws InterruptedException, ExecutionException  等待任务执行结束，然后获得V类型的结果。
InterruptedException 线程被中断异常， ExecutionException任务执行异常，如果任务被取消，还会抛出CancellationException
V get (long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException 
同上面的get功能一样，多了设置超时时间。参数timeout指定超时时间，uint指定时间的单位，在枚举类TimeUnit中有相关的定义。
如果计算超时，将抛出TimeoutException
Future的实现类有java.util.concurrent.FutureTask<V>即 javax.swing.SwingWorker<T,V>。
通常使用FutureTask来处理我们的任务。FutureTask类同时又实现了Runnable接口，所以可以直接提交给Executor执行。
使用FutureTask实现超时执行的代码如下：
