package com.util;

import javafx.animation.KeyFrame;
import javafx.application.Platform;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class TimelineBuilder {
    private KeyFrame keyFrame;

    public static TimelineBuilder create() {
        return new TimelineBuilder();
    }

    public TimelineBuilder cycleCount(int mode) {
        return this;
    }

    public TimelineBuilder keyFrames(KeyFrame keyFrame) {
        this.keyFrame = keyFrame;
        return this;
    }

    public TimelineBuilder build() {
        return this;
    }

    public void play() {
        Flux.interval(java.time.Duration.ofMillis((long) keyFrame.getTime().toMillis()))
                .doOnNext(this::each)
                .subscribe();
    }

    private void each(Long l) {
        if (keyFrame.getOnFinished() == null) return;
        // https://stackoverflow.com/a/32489845
        Platform.runLater(this::runOnFinished);
    }

    private void runOnFinished() {
        try {
            keyFrame.getOnFinished().handle(null);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
    }
}
