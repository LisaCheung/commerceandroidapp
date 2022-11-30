package com.example.ecommerceapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemCount extends ViewModel {
    private int itemCount = 0;
    private MutableLiveData<Integer> itemCountLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> getInitialCount(){
        itemCountLiveData.setValue(itemCount);
        return itemCountLiveData;
    }

    public void increaseCount(){
        itemCount++;
        itemCountLiveData.setValue(itemCount);
    }

    public void decreaseCount(){
        if(itemCount > 0){
            itemCount--;
            itemCountLiveData.setValue(itemCount);
        }
    }
}
