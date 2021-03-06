package ru.netology.repository;

import ru.netology.domain.Issue;
import ru.netology.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class IssueRepository {

    public Collection<Issue> issues = new ArrayList<>();

    public void save(Issue item) {
        issues.add(item);
    }

    public Issue findById(int id) {
        for (Issue item : issues) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Collection<Issue> findAll() {
        return issues;
    }

    public void closedById(int id) {

        if (findById(id) != null)
            for (Issue item : issues) {
                if (item.getId() == id) {
                    item.setClosed(true);
                }

            }
        else throw new NotFoundException("Element with id: " + id + " not found");
    }

    public void openById(int id) {

        if (findById(id) != null)
            for (Issue item : issues) {
                if (item.getId() == id) {
                    item.setClosed(false);
                }
            }

        else throw new NotFoundException("Element with id: " + id + " not found");

    }
}
