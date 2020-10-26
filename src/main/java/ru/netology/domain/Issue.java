package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Issue {
    private int id;
    private String author;
    private HashSet<String> assignees;
    private HashSet<String> tags;
    private boolean isClosed;

}
