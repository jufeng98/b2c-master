package org.javamaster.b2c.b2cwebflux;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.reactivestreams.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import reactor.core.CorePublisher;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.*;

import java.util.List;
import java.util.function.*;

/**
 * @author yudong
 * @date 2021/8/24
 */
@Slf4j
public class RxJavaTest {

    @Test
    public void test() {
        CoreSubscriber<String> coreSubscriber = new CoreSubscriber<String>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                log.info("onSubscribe!");
                subscription.request(100L);
                subscription.cancel();
            }

            @Override
            public void onNext(String s) {
                log.info("Item: " + s);
            }

            @Override
            public void onError(Throwable e) {
                log.info("Error!");
            }

            @Override
            public void onComplete() {
                log.info("onComplete!");
            }
        };

        CorePublisher<String> corePublisher = new CorePublisher<String>() {
            @Override
            public void subscribe(CoreSubscriber<? super String> coreSubscriber) {
                log.info("CoreSubscriber");
                coreSubscriber.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) {
                        log.info("request:" + n);
                    }

                    @Override
                    public void cancel() {
                        log.info("cancel");
                    }
                });
                coreSubscriber.onNext("hello");
                coreSubscriber.onNext("world");
                coreSubscriber.onComplete();
            }

            @Override
            public void subscribe(Subscriber<? super String> subscriber) {
                log.info("Subscriber");
                this.subscribe((CoreSubscriber) subscriber);
            }
        };
        Flux.from(corePublisher).subscribe(coreSubscriber);
    }

    @Test
    public void test1() {
        Flux.create(new Consumer<FluxSink<String>>() {
            @Override
            public void accept(FluxSink<String> fluxSink) {
                fluxSink.next("hello");
                fluxSink.next("world");
                fluxSink.complete();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                log.info("item:{}", s);
            }
        });
    }

    @Test
    public void test2() {
        CoreSubscriber<String> coreSubscriber = new CoreSubscriber<String>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                log.info("onSubscribe!");
                subscription.request(100L);
                subscription.cancel();
            }

            @Override
            public void onNext(String s) {
                log.info("Item: " + s);
            }

            @Override
            public void onError(Throwable e) {
                log.info("Error!");
            }

            @Override
            public void onComplete() {
                log.info("onComplete!");
            }
        };
        Flux.just("hello", "world")
                .subscribe(coreSubscriber);
    }

    @Test
    public void test3() {
        Flux.defer(new Supplier<Publisher<String>>() {
            @Override
            public Publisher<String> get() {
                return new Publisher<String>() {
                    @Override
                    public void subscribe(Subscriber<? super String> s) {
                        s.onNext("hello world");
                        s.onComplete();
                    }
                };
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                log.info("res:{}", s);
            }
        });
    }


    @Test
    public void test4() {
        Flux.defer(new Supplier<Publisher<String>>() {
            @Override
            public Publisher<String> get() {
                return Mono.just("hello world");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                log.info("res:{}", s);
            }
        });
    }

    @Test
    public void test5() {
        SecurityProperties.User user = new SecurityProperties.User();
        user.setName("Jack");
        user.setRoles(Lists.newArrayList("ADMIN", "ROOT"));

        SecurityProperties.User user1 = new SecurityProperties.User();
        user1.setName("Rose");
        user1.setRoles(Lists.newArrayList("SYSADMIN", "TROUSER"));

        List<SecurityProperties.User> list = Lists.newArrayList(user, user1);

        CoreSubscriber<String> coreSubscriber = new CoreSubscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(10);
            }

            @Override
            public void onNext(String s) {
                log.info("name:{}", s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

        SecurityProperties.User[] array = list.toArray(new SecurityProperties.User[0]);
        Flux.just(array)
                .map(SecurityProperties.User::getName)
                .subscribe(coreSubscriber);

        Flux.just(array)
                .flatMap(new Function<SecurityProperties.User, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(SecurityProperties.User user) {
                        return Flux.just(user.getRoles().toArray(new String[0]));
                    }
                })
                .subscribe(coreSubscriber);
    }
}
