package uoc.edu.raulsuarez.devsecop.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Calcul {

    private List<Integer> items;

    private String operation;

    public Calcul() {
        items = new ArrayList<>();
        items.add(Integer.valueOf(0));
        items.add(Integer.valueOf(0));
    }
}
