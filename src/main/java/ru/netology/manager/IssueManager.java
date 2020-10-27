package ru.netology.manager;

import ru.netology.repository.IssueRepository;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;

public class IssueManager {

    private IssueRepository repository;
    private Predicate<Set> predicate;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public Collection<Issue> findAll() {
        return repository.findAll();
    }

    public void add(Issue item) {
        repository.save(item);
    }


    public void closedById(int id) {
        repository.closedById(id);
    }

    public void openById(int id) {
        repository.openById(id);
    }


    public Collection<Issue> showOpenIssues() {
        Collection<Issue> openedIssues = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (!item.isClosed()) {
                openedIssues.add(item);
            }
        }
        return openedIssues;
    }

    public Collection<Issue> showClosedIssues() {
        Collection<Issue> closedIssues = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (item.isClosed()) {
                closedIssues.add(item);
            }
        }
        return closedIssues;
    }

    public Collection<Issue> filterByAuthors(Predicate<String> predicate) {
        Collection<Issue> authors = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (predicate.test(item.getAuthor())) {
                authors.add(item);
            }
        }
        return authors;
    }


    public Collection<Issue> filterByTags(Predicate<Set> predicate) {
        Collection<Issue> tags = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (predicate.test(item.getTags())) {
                tags.add(item);
            }
        }
        return tags;
    }

    public Collection<Issue> filterByAssignees(Predicate<Set> predicate) {
        Collection<Issue> assignees = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (predicate.test(item.getAssignees())) {
                assignees.add(item);
            }
        }
        return assignees;
    }


    public Collection<Issue> sortById(Comparator<Issue> comparator) {
        ArrayList<Issue> result = new ArrayList<>(repository.findAll());
        result.sort(comparator);
        return result;
    }


}
