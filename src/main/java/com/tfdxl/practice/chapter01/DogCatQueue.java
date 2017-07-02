package com.tfdxl.practice.chapter01;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tianfeng on 2017/7/2.
 */
public class DogCatQueue {

    private Queue<PetEnterQueue> dogQ;
    private Queue<PetEnterQueue> catQ;
    //the shared counter
    private long count;

    public DogCatQueue() {
        this.catQ = new LinkedList<>();
        this.catQ = new LinkedList<>();
        this.count = 0;
    }

    public void add(Pet pet) {
        if ("dog".equals(pet.getType())) {
            this.dogQ.add(new PetEnterQueue(pet, this.count++));
        } else if ("cat".equals(pet.getType())) {
            this.catQ.add(new PetEnterQueue(pet, this.count++));
        } else {
            throw new RuntimeException("not dog or cat");
        }
    }

    public Pet pollAll() {
        if (!this.catQ.isEmpty() && !this.dogQ.isEmpty()) {
            if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
                return this.dogQ.poll().getPet();
            } else {
                return this.catQ.poll().getPet();
            }
        } else if (!this.catQ.isEmpty()) {
            return this.catQ.poll().getPet();
        } else if (!this.dogQ.isEmpty()) {
            return this.catQ.poll().getPet();
        } else {
            throw new RuntimeException("err,queue is empty");
        }
    }

    public Dog pollDog() {
        if (this.dogQ.isEmpty())
            throw new RuntimeException("err,the dog queue id empty");
        else
            return (Dog) this.dogQ.poll().getPet();
    }

    public Cat pollCat() {
        if (this.catQ.isEmpty())
            throw new RuntimeException("err,the cat queue id empty");
        else
            return (Cat) this.catQ.poll().getPet();
    }

    public boolean isEmpty() {
        return this.dogQ.isEmpty() && this.catQ.isEmpty();
    }

    public boolean isDogQueueEmpty() {
        return this.dogQ.isEmpty();
    }

    public boolean isCatQueueEmpty() {
        return this.catQ.isEmpty();
    }

}
