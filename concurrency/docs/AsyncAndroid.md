Android的异步处理技术有很多种：



Thread

-> HandlerThread

​    -> AsyncQueryHeadler

​        IntentService

-> Executor Framework

​    -> ThreadPoolExecutor

​    -> AsyncTask

​        Loader



* HandlerThread是一个集成了Looper和MessageQueue的线程，当启动HandlerThread时，会同时生成Looper和MessageQueue，然后等待消息进行处理。好处在于开发者不需要自己去创建和维护Looper。

* AsyncQueryHandler是用于在ContentProvider上执行异步的CRUD操作的工具类，CRUD操作会被放到一个单独的子线程中执行，当操作结束获取到结果后，将通过消息的方式传递给调用AsyncQueryHandler的线程，通常是主线程。
* Android中Service的各个生命周期函数是运行在主线程的，因此它本身并不是一个异步处理技术，为了能够在Service中实现在子线程中处理耗时任务，引入了一个Service的子类：IntentService。IntentService具有Service一样的生命周期，同时也提供了在后台线程中处理异步任务的机制。
* Executor Framework的主要目的是分离任务的创建和它的执行，最终为开发者提供如下能力：
  * 创建工作线程池，同时通过队列来控制能够在这些线程执行的任务的个数。
  * 检测导致线程意外终止的错误。
  * 等待线程执行完成并获取执行结果。
  * 批量执行线程，并通过固定的顺序获取执行结果。
  * 在合适的时机启动后台线程，从而保证线程执行结果可以很快反馈给用户。

* ThreadPoolExecutor，开发者可以自定义线程池的一些行为，构造函数入参的详细定义如下：
  * corePoolSize：核心线程数，核心线程会一直存在于线程池中，即使当前没有任务需要处理；当线程数小于核心线程数时，即使当前有空闲的线程，线程池也会优先创建新的线程来处理任务。
  * maximumPoolSize：最大线程数，当最大线程数大于核心线程数，且任务队列已经满了，这时线程池就会创建新的线程，直到线程数达到最大线程数为止。
  * keepAliveTime：线程的空闲存活时间，当线程的空闲时间超过这个值之时，线程会被销毁，直到线程数等于核心线程数。
  * unit：keepAliveTime的单位，可选的有TimeUnit类中的NANOSECONDS、MIRCOSECONDS、MILLISECONDS和SECONDS。
  * workQueue：线程池所使用的任务缓冲队列。

* AsyncTask是在Executor框架基础上进行的封装，它实现将耗时任务移动到工作线程中执行，同时提供方便的接口实现工作线程和主线程的通信。
* Loader是Android引入的一个异步数据加载框架，使得在Activity或者Fragment中异步加载数据变得简单，同时它在数据源发生变化时，能够及时发出消息通知。