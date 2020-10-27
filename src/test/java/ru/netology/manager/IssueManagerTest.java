package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.repository.IssueRepository;
import ru.netology.domain.Issue;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueManagerTest {
    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);

    Issue first = new Issue(1, "Ruslan", new HashSet<String>(Arrays.asList("Bug")), new HashSet<String>(Arrays.asList("Rus")), true);
    Issue second = new Issue(2, "Dmitriy", new HashSet<String>(Arrays.asList("Update")), new HashSet<String>(Arrays.asList("Dmitriy")), false);
    Issue third = new Issue(3, "Maksim", new HashSet<String>(Arrays.asList("NewFicha")), new HashSet<String>(Arrays.asList("Maksim")), true);

    @BeforeEach
    void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.findAll();
    }

    @Test
    public void shouldAddOne() {
        Issue fourth = new Issue(4, "Sergey", new HashSet<String>(Collections.singletonList("Bug")), new HashSet<String>(Collections.singletonList("Sergey")), true);
        manager.add(fourth);
        Collection<Issue> expected = List.of(first, second, third, fourth);
        Collection<Issue> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldShowOpenIssues() {
        Collection<Issue> expected = List.of(second);
        Collection<Issue> actual = manager.showOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldShowClosedIssues() {
        Collection<Issue> expected = List.of(first, third);
        Collection<Issue> actual = manager.showClosedIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByAuthor() {
        String author = "Ruslan";
        Predicate<String> equalAuthor = t -> t.equals(author);
        Collection<Issue> expected = List.of(first);
        Collection<Issue> actual = manager.filterByAuthors(equalAuthor);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByLabel() {
        Set<String> tag = new HashSet<>(Collections.singletonList("Rus"));
        Predicate<Set> Tag = t -> t.equals(tag);
        Collection<Issue> expected = List.of(first);
        Collection<Issue> actual = manager.filterByTags(Tag);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByAssignee() {
        Set<String> assignee = new HashSet<>(Collections.singletonList("Update"));
        Predicate<Set> Assignee = t -> t.equals(assignee);
        Collection<Issue> expected = List.of(second);
        Collection<Issue> actual = manager.filterByAssignees(Assignee);
        assertEquals(expected, actual);
    }


    @Test
    public void shouldSortByCommentsQuantityAsc() {
        Collection<Issue> expected = List.of(first, second, third);
        Collection<Issue> actual = manager.sortById(Comparator.comparing(Issue::getId));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldViewOpenIssue() {

        Collection<Issue> expected = List.of(second);
        Collection<Issue> actual = manager.showOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldViewCloseIssue() {
        manager.closedById(1);
        Collection<Issue> expected = List.of(first, third);
        Collection<Issue> actual = manager.showClosedIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotOpenIssue() {
        manager.openById(4);
        Collection<Issue> expected = List.of(second);
        Collection<Issue> actual = manager.showOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotCloseIssue() {
        manager.closedById(4);
        Collection<Issue> expected = List.of(first, third);
        Collection<Issue> actual = manager.showClosedIssues();
        assertEquals(expected, actual);
    }
}