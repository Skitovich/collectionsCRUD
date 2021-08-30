package ru.netology.issueRepositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.manager.IssueManager;
import ru.netology.repository.IssueRepository;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IssueRepositoryTest {

    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);

    Issue first = new Issue(1, "Ruslan", new HashSet<>(Collections.singletonList("Bug")),
            new HashSet<>(Collections.singletonList("Rus")), true);
    Issue second = new Issue(2, "Dmitriy", new HashSet<>(Collections.singletonList("Update")),
            new HashSet<>(Collections.singletonList("Dmitriy")), false);
    Issue third = new Issue(3, "Maksim", new HashSet<>(Collections.singletonList("NewFiche")),
            new HashSet<>(Collections.singletonList("Maksim")), true);

    @BeforeEach
    void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        manager.findAll();
    }

    @Test
    public void shouldFindByID() {
        Issue expected = third;
        Issue actual = repository.findById(3);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindByID() {
        assertNull(repository.findById(4));
    }

}