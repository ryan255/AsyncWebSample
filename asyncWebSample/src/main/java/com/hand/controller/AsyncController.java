package com.hand.controller;

import com.hand.service.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Subscriber;

import java.net.URLEncoder;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RestController
public class AsyncController {
    private TaskService taskService = new TaskService();
    @RequestMapping("/deferred")
    public DeferredResult<String> excuteDeferredTask(){
        System.out.println("Request received at " + System.currentTimeMillis());
        DeferredResult<String> deferredResult = new DeferredResult<String>();
        CompletableFuture.supplyAsync(()->taskService.execute())
                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
        System.out.println("Servlet thread released at "+ System.currentTimeMillis());
        return deferredResult;
    }

    @RequestMapping("/callable")
    public Callable<String> excuteCallableTask(){
        System.out.println("Request received at " + System.currentTimeMillis());
        Callable<String> callable = taskService::execute;
        System.out.println("Servlet thread released at "+ System.currentTimeMillis());
        return callable;
    }


//    public static Observable<String> crateObservable() {
//        return Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                TaskService taskService = new TaskService();
//                String result =  taskService.execute();
//                subscriber.onNext(result);
//                subscriber.onCompleted();
//            }
//        });
////                .subscribeOn(Schedulers.io())
////                .observeOn();
//
//    }
//    public static Subscriber<String> subscriber(){
//        return new Subscriber<String>() {
//            @Override
//            public void onCompleted() {}
//
//            @Override
//            public void onError(Throwable throwable) {}
//
//            @Override
//            public void onNext(String s) {
//                System.out.println(s);
//            }
//        };
//    }
}
