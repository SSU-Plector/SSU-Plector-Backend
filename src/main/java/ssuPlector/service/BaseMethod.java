package ssuPlector.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BaseMethod {
    public <T> ArrayList<T> fillList(List<T> sourceList) {
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (i < sourceList.size()) {
                list.add(sourceList.get(i));
            } else {
                list.add(null);
            }
        }
        return list;
    }
}
