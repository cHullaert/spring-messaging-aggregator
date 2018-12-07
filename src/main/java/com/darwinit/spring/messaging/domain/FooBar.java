package com.darwinit.spring.messaging.domain;

import java.util.List;

public class FooBar {
    private List<Foo> foos;
    private List<Bar> bars;

    public FooBar(List<Foo> foos, List<Bar> bars) {
        this.foos=foos;
        this.bars=bars;
    }

    public List<Foo> getFoos() {
        return foos;
    }

    public void setFoos(List<Foo> foos) {
        this.foos = foos;
    }

    public List<Bar> getBars() {
        return bars;
    }

    public void setBars(List<Bar> bars) {
        this.bars = bars;
    }
}
