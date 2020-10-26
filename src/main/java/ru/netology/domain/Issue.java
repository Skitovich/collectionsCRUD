package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Issue {
    private int id;
    private String author;
    private Set<String> assignees;
    private Set<String> tags;
    private boolean isClosed;



}
